package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.SalesReport;
import fi.vamk.database.northwind.service.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/salesReport")
public class SalesReportController {
    @Autowired
    private SalesReportService salesReportService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody SalesReport salesReport){
        return ResponseEntity.status(HttpStatus.CREATED).body(salesReportService.save(salesReport));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") String salesReportId){
        Optional<SalesReport> oSalesReport = salesReportService.findById(salesReportId);
        if(!oSalesReport.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oSalesReport);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody SalesReport salesReportDetails, @PathVariable(value = "id") String salesReportId){
        Optional<SalesReport> oSalesReport = salesReportService.findById(salesReportId);
        if(!oSalesReport.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oSalesReport.get().setId(salesReportDetails.getId());
        oSalesReport.get().setDisplay(salesReportDetails.getDisplay());
        oSalesReport.get().setTitle(salesReportDetails.getTitle());
        oSalesReport.get().setFilterRowSource(salesReportDetails.getFilterRowSource());
        oSalesReport.get().set_default(salesReportDetails.get_default());

        return ResponseEntity.status(HttpStatus.CREATED).body(salesReportService.save(oSalesReport.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") String salesReportId) {
        Optional<SalesReport> oSalesReport = salesReportService.findById(salesReportId);
        if(!oSalesReport.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        salesReportService.deleteById(salesReportId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<SalesReport> readAll(){
        List<SalesReport> salesReportList = StreamSupport
                .stream(salesReportService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return salesReportList;
    }
}
