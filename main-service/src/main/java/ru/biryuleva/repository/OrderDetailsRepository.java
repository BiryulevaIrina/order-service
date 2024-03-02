package ru.biryuleva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.biryuleva.model.OrderDetails;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {


}
