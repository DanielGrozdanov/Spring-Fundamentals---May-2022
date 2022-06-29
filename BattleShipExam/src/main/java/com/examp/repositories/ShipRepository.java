package com.examp.repositories;

import com.examp.models.Category;
import com.examp.models.Ship;
import com.examp.models.ShipType;
import com.examp.models.dtos.ShipStatusView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship,Long> {

    Optional<Ship> findByName(String name);

    Ship getByName(String name);

    List<Ship> getByOrderByNameAscHealthAscPowerAsc();

    List<Ship> getByUserId(long user_id);

    List<Ship> getByUserIdNot(long id);
}

