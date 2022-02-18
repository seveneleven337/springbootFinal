package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Invoice;
import fi.vamk.database.northwind.entity.OrdersStatus;
import fi.vamk.database.northwind.repository.InvoiceRepository;
import fi.vamk.database.northwind.repository.OrdersStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class OrdersStatusService {

        @Autowired
        private OrdersStatusRepository ordersStatusRepository;

        @Transactional(readOnly = true) 									//para no modifical base de datos
        public Iterable<OrdersStatus> findAll() {
            return ordersStatusRepository.findAll();
        }

        @Transactional(readOnly = true)
        public Page<OrdersStatus> findAll(Pageable pageable) {
            return ordersStatusRepository.findAll(pageable);
        }

        @Transactional(readOnly = true)
        public Optional<OrdersStatus> findById(Integer id) {
            return ordersStatusRepository.findById(id);
        }

        @Transactional
        public OrdersStatus save(OrdersStatus ordersStatus) {
            return ordersStatusRepository.save(ordersStatus);
        }

        @Transactional
        public void deleteById(Integer Id) {
            ordersStatusRepository.deleteById(Id);
        }

}
