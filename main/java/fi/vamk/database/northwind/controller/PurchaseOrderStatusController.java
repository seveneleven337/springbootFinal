package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.PurchaseOrderStatus;
import fi.vamk.database.northwind.service.PurchaseOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/PurchaseOrderStatus")
public class PurchaseOrderStatusController {

    @Autowired
    private PurchaseOrderStatusService purchaseOrderStatusService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PurchaseOrderStatus purchaseOrderStatus){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderStatusService.save(purchaseOrderStatus));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer purchaseOrderStatusId){
        Optional<PurchaseOrderStatus> oPurchaseOrderStatus = purchaseOrderStatusService.findById(purchaseOrderStatusId);
        if(!oPurchaseOrderStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oPurchaseOrderStatus);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PurchaseOrderStatus purchaseOrderStatusDetails, @PathVariable(value = "id") Integer purchaseOrderStatusId){
        Optional<PurchaseOrderStatus> oPurchaseOrderStatus = purchaseOrderStatusService.findById(purchaseOrderStatusId);
        if(!oPurchaseOrderStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oPurchaseOrderStatus.get().setId(purchaseOrderStatusDetails.getId());
        oPurchaseOrderStatus.get().setStatus(purchaseOrderStatusDetails.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderStatusService.save(oPurchaseOrderStatus.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer purchaseOrderStatusId) {
        Optional<PurchaseOrderStatus> oPurchaseOrderStatus = purchaseOrderStatusService.findById(purchaseOrderStatusId);
        if(!oPurchaseOrderStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        purchaseOrderStatusService.deleteById(purchaseOrderStatusId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<PurchaseOrderStatus> readAll(){
        List<PurchaseOrderStatus> purchaseOrderStatusList = StreamSupport
                .stream(purchaseOrderStatusService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return purchaseOrderStatusList;
    }
}
