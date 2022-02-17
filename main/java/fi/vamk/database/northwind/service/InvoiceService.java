package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Invoice;
import fi.vamk.database.northwind.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Invoice> findAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Invoice> findById(Integer id) {
        return invoiceRepository.findById(id);
    }

    @Transactional
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteById(Integer Id) {
        invoiceRepository.deleteById(Id);
    }
}
