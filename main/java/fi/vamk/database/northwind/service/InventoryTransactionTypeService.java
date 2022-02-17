package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.InventoryTransactionType;
import fi.vamk.database.northwind.repository.InventoryTransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryTransactionTypeService {

    @Autowired
    private InventoryTransactionTypeRepository inventoryTransactionTypeRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<InventoryTransactionType> findAll() {
        return inventoryTransactionTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<InventoryTransactionType> findAll(Pageable pageable) {
        return inventoryTransactionTypeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<InventoryTransactionType> findById(Integer id) {
        return inventoryTransactionTypeRepository.findById(id);
    }

    @Transactional
    public InventoryTransactionType save(InventoryTransactionType inventoryTransactionType) {
        return inventoryTransactionTypeRepository.save(inventoryTransactionType);
    }

    @Transactional
    public void deleteById(Integer Id) {
        inventoryTransactionTypeRepository.deleteById(Id);
    }
}
