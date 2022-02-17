package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.PurchaseOrderDetail;
import fi.vamk.database.northwind.repository.PurchaseOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PurchaseOrderDetailService {
    @Autowired
    private PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<PurchaseOrderDetail> findAll() {
        return purchaseOrderDetailRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<PurchaseOrderDetail> findAll(Pageable pageable) {
        return purchaseOrderDetailRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<PurchaseOrderDetail> findById(Integer id) {
        return purchaseOrderDetailRepository.findById(id);
    }

    @Transactional
    public PurchaseOrderDetail save(PurchaseOrderDetail purchaseOrderDetail) {
        return purchaseOrderDetailRepository.save(purchaseOrderDetail);
    }

    @Transactional
    public void deleteById(Integer Id) {
        purchaseOrderDetailRepository.deleteById(Id);
    }
}
