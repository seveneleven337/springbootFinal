package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Invoice;
import fi.vamk.database.northwind.entity.OrderDetail;
import fi.vamk.database.northwind.repository.InvoiceRepository;
import fi.vamk.database.northwind.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<OrderDetail> findAll(Pageable pageable) {
        return orderDetailRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<OrderDetail> findById(Integer id) {
        return orderDetailRepository.findById(id);
    }

    @Transactional
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Transactional
    public void deleteById(Integer Id) {
        orderDetailRepository.deleteById(Id);
    }

}
