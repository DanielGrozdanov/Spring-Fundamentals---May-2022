package com.example.coffeshop.services;


import com.example.coffeshop.model.dto.AddOrderDTO;
import com.example.coffeshop.model.views.ViewOrderDTO;
import com.example.coffeshop.model.entity.Order;
import com.example.coffeshop.model.entity.User;
import com.example.coffeshop.repositories.OrderRepository;
import com.example.coffeshop.repositories.UserRepository;
import com.example.coffeshop.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final CategoryService categoryService;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, LoggedUser loggedUser, CategoryService categoryService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    public boolean addOrder(AddOrderDTO addOrderDTO){
        Optional<Order> orderCheck = this.orderRepository.findByName(addOrderDTO.getName());
        if (orderCheck.isPresent()){
            return false;
        }
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Order order = modelMapper.map(addOrderDTO,Order.class);
        order.setCategory(categoryService.getCategory(addOrderDTO.getCategory()));
        order.setEmployee(user.get());
        this.orderRepository.save(order);
        return true;
    }

    public List<ViewOrderDTO> findAllOrdersOrderedByPriceDesc(){
       return orderRepository.findAllByOrderByPriceDesc()
               .stream()
               .map(order -> modelMapper.map(order,ViewOrderDTO.class))
               .collect(Collectors.toList());
    }

    public void readyOrder(Long id){
        orderRepository.deleteById(id);
    }
}
