package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.OrderDetailsStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsStatusRepository extends JpaRepository<OrderDetailsStatus, Integer> {
}
