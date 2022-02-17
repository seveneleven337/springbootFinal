package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.PurchaseOrderStatus;
import fi.vamk.database.northwind.repository.PurchaseOrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PurchaseOrderStatusService {
    @Autowired
    private PurchaseOrderStatusRepository purchaseOrderStatusRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<PurchaseOrderStatus> findAll() {
        return purchaseOrderStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<PurchaseOrderStatus> findAll(Pageable pageable) {
        return purchaseOrderStatusRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<PurchaseOrderStatus> findById(Integer id) {
        return purchaseOrderStatusRepository.findById(id);
    }

    @Transactional
    public PurchaseOrderStatus save(PurchaseOrderStatus purchaseOrderStatus) {
        return purchaseOrderStatusRepository.save(purchaseOrderStatus);
    }

    @Transactional
    public void deleteById(Integer Id) {
        purchaseOrderStatusRepository.deleteById(Id);
    }
}
