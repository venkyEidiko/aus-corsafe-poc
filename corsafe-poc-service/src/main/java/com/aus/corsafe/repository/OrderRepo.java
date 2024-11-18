package com.aus.corsafe.repository;

import com.aus.corsafe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 11904a59ed984377d118f4b1380f9068be5baa7d
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
<<<<<<< HEAD
    Optional<Order> findByRazorPayOrderId(String razorPayOrderId);
=======

    List<Order> findByUserId(Integer userId);

>>>>>>> 11904a59ed984377d118f4b1380f9068be5baa7d
}

