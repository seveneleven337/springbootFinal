package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.OrdersTaxStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersTaxStatusRepository extends JpaRepository<OrdersTaxStatus, Integer> {
}
