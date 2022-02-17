package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.InventoryTransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryTransactionTypeRepository extends JpaRepository<InventoryTransactionType, Integer> {
}
