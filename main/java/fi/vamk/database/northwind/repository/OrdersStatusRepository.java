package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.OrdersStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersStatusRepository extends JpaRepository<OrdersStatus, Integer> {
}
