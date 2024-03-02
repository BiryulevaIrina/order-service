package ru.biryuleva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.biryuleva.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.date = :date ORDER BY o.amount DESC LIMIT 1")
    Optional<Order> findOrderByAmount(LocalDate date);

    @Query("SELECT o.orderNumber FROM Order o " +
            "LEFT JOIN OrderDetails d " +
            "WHERE d.itemName != :name AND o.date >= :startDate AND o.date <= :endDate")
    List<Long> findOrderNumbersExceptProductAndDateRange(@Param("name") String itemName,
                                                           @Param("startDate") LocalDate startDate,
                                                           @Param("endDate") LocalDate endDate);

}
