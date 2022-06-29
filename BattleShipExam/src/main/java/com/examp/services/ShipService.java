package com.examp.services;

import com.examp.models.Ship;
import com.examp.models.User;
import com.examp.models.dtos.CreateShipDTO;
import com.examp.models.dtos.ShipStatusView;
import com.examp.repositories.ShipRepository;
import com.examp.repositories.UserRepository;
import com.examp.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShipService(ShipRepository shipRepository, LoggedUser loggedUser, UserRepository userRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.shipRepository = shipRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    public boolean create(CreateShipDTO createShipDTO) {
        Optional<Ship> byName = this.shipRepository.findByName(createShipDTO.getName());
        if (byName.isPresent()) {
            return false;
        }

        Optional<User> userOwner = this.userRepository.findById(this.loggedUser.getId());
        Ship ship  = modelMapper.map(createShipDTO,Ship.class);
        ship.setCategory(categoryService.getCategory(createShipDTO.getCategory()));
        ship.setUser(userOwner.get());
        this.shipRepository.save(ship);

        return true;
    }

    public List<ShipStatusView> getAll(){
        return shipRepository.getByOrderByNameAscHealthAscPowerAsc().stream().map(ship ->
                modelMapper.map(ship,ShipStatusView.class)).collect(Collectors.toList());
    }

    public List<ShipStatusView> getShipsByLoggedUser(Long id){
       return shipRepository.getByUserId(id).stream()
               .map(ship -> modelMapper.map(ship,ShipStatusView.class))
               .collect(Collectors.toList());
    }
    public List<ShipStatusView> getShipsByOtherUser(Long id){
       return shipRepository.getByUserIdNot(id).stream().map(ship -> modelMapper.map(ship,ShipStatusView.class)).collect(Collectors.toList());
    }

    public void attack(String attacker, String defender){
        Ship attackerUser = shipRepository.getByName(attacker);
        Ship defenderUser = shipRepository.getByName(defender);

        long defenderHealth = defenderUser.getHealth() - attackerUser.getPower();
        if (defenderHealth <= 0){
            shipRepository.delete(defenderUser);
            return;
        }
        defenderUser.setHealth(defenderHealth);
        shipRepository.save(defenderUser);
    }
}
