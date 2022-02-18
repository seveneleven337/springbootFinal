package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Invoice;
import fi.vamk.database.northwind.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Invoice invoice){
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.save(invoice));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer invoiceId){
        Optional<Invoice> oInvoice = invoiceService.findById(invoiceId);
        if(!oInvoice.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oInvoice);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Invoice invoiceDetails, @PathVariable(value = "id") Integer invoiceId){
        Optional<Invoice> oInvoice = invoiceService.findById(invoiceId);
        if(!oInvoice.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oInvoice.get().setId(invoiceDetails.getId());
        oInvoice.get().setOrders(invoiceDetails.getOrders());
        oInvoice.get().setInvoiceDate(invoiceDetails.getInvoiceDate());
        oInvoice.get().setDueDate(invoiceDetails.getDueDate());
        oInvoice.get().setTax(invoiceDetails.getTax());
        oInvoice.get().setShipping(invoiceDetails.getShipping());
        oInvoice.get().setAmountDue(invoiceDetails.getAmountDue());

        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.save(oInvoice.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer invoiceId) {
        Optional<Invoice> oInvoice = invoiceService.findById(invoiceId);
        if(!oInvoice.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        invoiceService.deleteById(invoiceId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<Invoice> readAll(){
        List<Invoice> invoiceList = StreamSupport
                .stream(invoiceService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return invoiceList;
    }

}
