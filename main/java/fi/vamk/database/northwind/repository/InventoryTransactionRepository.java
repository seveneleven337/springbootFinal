package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Integer> {
}
