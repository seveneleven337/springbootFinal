package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.PurchaseOrderDetail;
import fi.vamk.database.northwind.service.PurchaseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/purchaseOrderDetails")
public class PurchaseOrderDetailController {

    @Autowired
    private PurchaseOrderDetailService purchaseOrderDetailService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PurchaseOrderDetail purchaseOrderDetail){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderDetailService.save(purchaseOrderDetail));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer purchaseOrderDetailId){
        Optional<PurchaseOrderDetail> oPurchaseOrderDetail = purchaseOrderDetailService.findById(purchaseOrderDetailId);
        if(!oPurchaseOrderDetail.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oPurchaseOrderDetail);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PurchaseOrderDetail purchaseOrderDetailDetails, @PathVariable(value = "id") Integer purchaseOrderDetailId){
        Optional<PurchaseOrderDetail> oPurchaseOrderDetail = purchaseOrderDetailService.findById(purchaseOrderDetailId);
        if(!oPurchaseOrderDetail.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oPurchaseOrderDetail.get().setId(purchaseOrderDetailDetails.getId());
        oPurchaseOrderDetail.get().setPurchaseOrders(purchaseOrderDetailDetails.getPurchaseOrders());
        oPurchaseOrderDetail.get().setProduct(purchaseOrderDetailDetails.getProduct());
        oPurchaseOrderDetail.get().setQuantity(purchaseOrderDetailDetails.getQuantity());
        oPurchaseOrderDetail.get().setUnitCost(purchaseOrderDetailDetails.getUnitCost());
        oPurchaseOrderDetail.get().setDateReceived(purchaseOrderDetailDetails.getDateReceived());
        oPurchaseOrderDetail.get().setPostedToInventory(purchaseOrderDetailDetails.getPostedToInventory());
        oPurchaseOrderDetail.get().setInventory(purchaseOrderDetailDetails.getInventory());

        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderDetailService.save(oPurchaseOrderDetail.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer purchaseOrderDetailId) {
        Optional<PurchaseOrderDetail> oPurchaseOrderDetail = purchaseOrderDetailService.findById(purchaseOrderDetailId);
        if(!oPurchaseOrderDetail.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        purchaseOrderDetailService.deleteById(purchaseOrderDetailId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<PurchaseOrderDetail> readAll(){
        List<PurchaseOrderDetail> users = StreamSupport
                .stream(purchaseOrderDetailService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }

}
