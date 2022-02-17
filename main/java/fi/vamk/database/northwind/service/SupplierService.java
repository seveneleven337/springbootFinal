package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Supplier;
import fi.vamk.database.northwind.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Supplier> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Supplier> findById(Integer id) {
        return supplierRepository.findById(id);
    }

    @Transactional
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Transactional
    public void deleteById(Integer Id) {
        supplierRepository.deleteById(Id);
    }
}
