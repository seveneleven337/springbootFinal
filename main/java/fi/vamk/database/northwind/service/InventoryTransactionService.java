package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.InventoryTransaction;
import fi.vamk.database.northwind.repository.InventoryTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryTransactionService {
    @Autowired
    private InventoryTransactionRepository inventoryTransactionRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<InventoryTransaction> findAll() {
        return inventoryTransactionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<InventoryTransaction> findAll(Pageable pageable) {
        return inventoryTransactionRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<InventoryTransaction> findById(Integer id) {
        return inventoryTransactionRepository.findById(id);
    }

    @Transactional
    public InventoryTransaction save(InventoryTransaction inventoryTransaction) {
        return inventoryTransactionRepository.save(inventoryTransaction);
    }

    @Transactional
    public void deleteById(Integer Id) {
        inventoryTransactionRepository.deleteById(Id);
    }
}
