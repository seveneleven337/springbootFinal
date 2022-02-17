package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Shipper;
import fi.vamk.database.northwind.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShipperService {
    @Autowired
    private ShipperRepository shipperRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<Shipper> findAll() {
        return shipperRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Shipper> findAll(Pageable pageable) {
        return shipperRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Shipper> findById(Integer id) {
        return shipperRepository.findById(id);
    }

    @Transactional
    public Shipper save(Shipper shipper) {
        return shipperRepository.save(shipper);
    }

    @Transactional
    public void deleteById(Integer Id) {
        shipperRepository.deleteById(Id);
    }
}
