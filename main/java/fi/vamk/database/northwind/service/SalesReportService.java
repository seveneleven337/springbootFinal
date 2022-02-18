package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.SalesReport;
import fi.vamk.database.northwind.entity.Shipper;
import fi.vamk.database.northwind.repository.SalesReportRepository;
import fi.vamk.database.northwind.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SalesReportService {

    @Autowired
    private SalesReportRepository salesReportRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<SalesReport> findAll() {
        return salesReportRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<SalesReport> findAll(Pageable pageable) {
        return salesReportRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<SalesReport> findById(String id) {
        return salesReportRepository.findById(id);
    }

    @Transactional
    public SalesReport save(SalesReport salesReport) {
        return salesReportRepository.save(salesReport);
    }

    @Transactional
    public void deleteById(String Id) {
        salesReportRepository.deleteById(Id);
    }
}
