package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Integer> {
}
