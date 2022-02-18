package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.PurchaseOrder;
import fi.vamk.database.northwind.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/purchaseOrder")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PurchaseOrder purchaseOrder){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.save(purchaseOrder));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer purchaseOrderId){
        Optional<PurchaseOrder> oPurchaseOrder = purchaseOrderService.findById(purchaseOrderId);
        if(!oPurchaseOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oPurchaseOrder);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PurchaseOrder purchaseOrderDetails, @PathVariable(value = "id") Integer purchaseOrderId){
        Optional<PurchaseOrder> oPurchaseOrder = purchaseOrderService.findById(purchaseOrderId);
        if(!oPurchaseOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oPurchaseOrder.get().setId(purchaseOrderDetails.getId());
        oPurchaseOrder.get().setSuppliers(purchaseOrderDetails.getSuppliers());
        oPurchaseOrder.get().setCreatedBy(purchaseOrderDetails.getCreatedBy());
        oPurchaseOrder.get().setSubmittedBy(purchaseOrderDetails.getSubmittedBy());
        oPurchaseOrder.get().setCreationDate(purchaseOrderDetails.getCreationDate());
        oPurchaseOrder.get().setStatus(purchaseOrderDetails.getStatus());
        oPurchaseOrder.get().setExpectedDate(purchaseOrderDetails.getExpectedDate());
        oPurchaseOrder.get().setShippingFee(purchaseOrderDetails.getShippingFee());
        oPurchaseOrder.get().setTaxes(purchaseOrderDetails.getTaxes());
        oPurchaseOrder.get().setPaymentDate(purchaseOrderDetails.getPaymentDate());
        oPurchaseOrder.get().setPaymentAmount(purchaseOrderDetails.getPaymentAmount());
        oPurchaseOrder.get().setPaymentMethod(purchaseOrderDetails.getPaymentMethod());
        oPurchaseOrder.get().setNotes(purchaseOrderDetails.getNotes());
        oPurchaseOrder.get().setApprovedBy(purchaseOrderDetails.getApprovedBy());
        oPurchaseOrder.get().setApprovedDate(purchaseOrderDetails.getApprovedDate());
        oPurchaseOrder.get().setSubmittedBy(purchaseOrderDetails.getSubmittedBy());

        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.save(oPurchaseOrder.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer purchaseOrderId) {
        Optional<PurchaseOrder> oPurchaseOrder = purchaseOrderService.findById(purchaseOrderId);
        if(!oPurchaseOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        purchaseOrderService.deleteById(purchaseOrderId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<PurchaseOrder> readAll(){
        List<PurchaseOrder> purchaseOrderList = StreamSupport
                .stream(purchaseOrderService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return purchaseOrderList;
    }
}
