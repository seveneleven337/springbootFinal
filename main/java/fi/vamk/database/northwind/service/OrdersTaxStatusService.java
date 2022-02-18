package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.OrdersTaxStatus;
import fi.vamk.database.northwind.repository.OrdersTaxStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class OrdersTaxStatusService {

        @Autowired
        private OrdersTaxStatusRepository ordersTaxStatusRepository;

        @Transactional(readOnly = true) 									//para no modifical base de datos
        public Iterable<OrdersTaxStatus> findAll() {
            return ordersTaxStatusRepository.findAll();
        }

        @Transactional(readOnly = true)
        public Page<OrdersTaxStatus> findAll(Pageable pageable) {
            return ordersTaxStatusRepository.findAll(pageable);
        }

        @Transactional(readOnly = true)
        public Optional<OrdersTaxStatus> findById(Integer id) {
            return ordersTaxStatusRepository.findById(id);
        }

        @Transactional
        public OrdersTaxStatus save(OrdersTaxStatus ordersTaxStatus) {
            return ordersTaxStatusRepository.save(ordersTaxStatus);
        }

        @Transactional
        public void deleteById(Integer Id) {
            ordersTaxStatusRepository.deleteById(Id);
        }

}
