package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Supplier;
import fi.vamk.database.northwind.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Supplier supplier){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.save(supplier));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer supplierId){
        Optional<Supplier> oSupplier = supplierService.findById(supplierId);
        if(!oSupplier.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oSupplier);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Supplier supplierDetails, @PathVariable(value = "id") Integer supplierId){
        Optional<Supplier> oSupplier = supplierService.findById(supplierId);
        if(!oSupplier.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oSupplier.get().setId(supplierDetails.getId());
        oSupplier.get().setCompany(supplierDetails.getCompany());
        oSupplier.get().setLastName(supplierDetails.getLastName());
        oSupplier.get().setFirstName(supplierDetails.getFirstName());
        oSupplier.get().setEmailAddress(supplierDetails.getEmailAddress());
        oSupplier.get().setJobTitle(supplierDetails.getJobTitle());
        oSupplier.get().setBusinessPhone(supplierDetails.getBusinessPhone());
        oSupplier.get().setHomePhone(supplierDetails.getHomePhone());
        oSupplier.get().setMobilePhone(supplierDetails.getMobilePhone());
        oSupplier.get().setFaxNumber(supplierDetails.getFaxNumber());
        oSupplier.get().setAddress(supplierDetails.getAddress());
        oSupplier.get().setCity(supplierDetails.getCity());
        oSupplier.get().setStateProvince(supplierDetails.getStateProvince());
        oSupplier.get().setZipPostalCode(supplierDetails.getZipPostalCode());
        oSupplier.get().setCountryRegion(supplierDetails.getCountryRegion());
        oSupplier.get().setWebPage(supplierDetails.getWebPage());
        oSupplier.get().setNotes(supplierDetails.getNotes());
        oSupplier.get().setAttachments(supplierDetails.getAttachments());


        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.save(oSupplier.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer supplierId) {
        Optional<Supplier> oSupplier = supplierService.findById(supplierId);
        if(!oSupplier.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        supplierService.deleteById(supplierId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<Supplier> readAll(){
        List<Supplier> supplierList = StreamSupport
                .stream(supplierService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return supplierList;
    }

}
