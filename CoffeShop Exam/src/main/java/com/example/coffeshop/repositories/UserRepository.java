package com.example.coffeshop.repositories;

import com.example.coffeshop.model.entity.User;
import com.example.coffeshop.model.views.UserViewOrderDTO;
import com.example.coffeshop.model.views.ViewOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);


    @Query("SELECT u FROM User u order by u.orders.size DESC")
    List<User> findAllByOrderByOrdersDesc();
}

