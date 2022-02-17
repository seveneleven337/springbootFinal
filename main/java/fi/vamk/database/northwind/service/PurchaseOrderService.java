package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.PurchaseOrder;
import fi.vamk.database.northwind.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<PurchaseOrder> findAll(Pageable pageable) {
        return purchaseOrderRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<PurchaseOrder> findById(Integer id) {
        return purchaseOrderRepository.findById(id);
    }

    @Transactional
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Transactional
    public void deleteById(Integer Id) {
        purchaseOrderRepository.deleteById(Id);
    }
}
