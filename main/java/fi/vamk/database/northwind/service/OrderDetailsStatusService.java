package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Invoice;
import fi.vamk.database.northwind.entity.OrderDetailsStatus;
import fi.vamk.database.northwind.repository.InvoiceRepository;
import fi.vamk.database.northwind.repository.OrderDetailsStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderDetailsStatusService {

        @Autowired
        private OrderDetailsStatusRepository orderDetailsStatusRepository;

        @Transactional(readOnly = true) 									//para no modifical base de datos
        public Iterable<OrderDetailsStatus> findAll() {
            return orderDetailsStatusRepository.findAll();
        }

        @Transactional(readOnly = true)
        public Page<OrderDetailsStatus> findAll(Pageable pageable) {
            return orderDetailsStatusRepository.findAll(pageable);
        }

        @Transactional(readOnly = true)
        public Optional<OrderDetailsStatus> findById(Integer id) {
            return orderDetailsStatusRepository.findById(id);
        }

        @Transactional
        public OrderDetailsStatus save(OrderDetailsStatus orderDetailsStatus) {
            return orderDetailsStatusRepository.save(orderDetailsStatus);
        }

        @Transactional
        public void deleteById(Integer Id) {
            orderDetailsStatusRepository.deleteById(Id);
        }

}
