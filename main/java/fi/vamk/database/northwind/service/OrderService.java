package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Customer;
import fi.vamk.database.northwind.entity.Order;
import fi.vamk.database.northwind.repository.CustomerRepository;
import fi.vamk.database.northwind.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<Order> findAll() {
        return ordersRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Order> findAll(Pageable pageable) {
        return ordersRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(Integer id) {
        return ordersRepository.findById(id);
    }

    @Transactional
    public Order save(Order order) {
        return ordersRepository.save(order);
    }

    @Transactional
    public void deleteById(Integer Id) {
        ordersRepository.deleteById(Id);
    }
}
