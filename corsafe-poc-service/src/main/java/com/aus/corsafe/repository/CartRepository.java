package com.aus.corsafe.repository;

import com.aus.corsafe.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    List<Cart> findByUserId(Integer userId); // Find all cart items for a specific user
}
