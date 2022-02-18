package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.InventoryTransaction;
import fi.vamk.database.northwind.service.InventoryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/inventoryTransactions")
public class InventoryTransactionController {
    @Autowired
    private InventoryTransactionService inventoryTransactionService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody InventoryTransaction inventoryTransaction){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryTransactionService.save(inventoryTransaction));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer inventoryTransactionId){
        Optional<InventoryTransaction> oInventoryTransaction = inventoryTransactionService.findById(inventoryTransactionId);
        if(!oInventoryTransaction.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oInventoryTransaction);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody InventoryTransaction inventoryTransactionDetails, @PathVariable(value = "id") Integer inventoryTransactionId){
        Optional<InventoryTransaction> oInventoryTransaction = inventoryTransactionService.findById(inventoryTransactionId);
        if(!oInventoryTransaction.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oInventoryTransaction.get().setId(inventoryTransactionDetails.getId());
        oInventoryTransaction.get().setTransactionType(inventoryTransactionDetails.getTransactionType());
        oInventoryTransaction.get().setTransactionCreatedDate(inventoryTransactionDetails.getTransactionCreatedDate());
        oInventoryTransaction.get().setTransactionModifiedDate(inventoryTransactionDetails.getTransactionModifiedDate());
        oInventoryTransaction.get().setProducts(inventoryTransactionDetails.getProducts());
        oInventoryTransaction.get().setQuantity(inventoryTransactionDetails.getQuantity());
        oInventoryTransaction.get().setPurchaseOrders(inventoryTransactionDetails.getPurchaseOrders());
        oInventoryTransaction.get().setOrders(inventoryTransactionDetails.getOrders());
        oInventoryTransaction.get().setComments(inventoryTransactionDetails.getComments());
        oInventoryTransaction.get().setPurchaseOrderDetails(inventoryTransactionDetails.getPurchaseOrderDetails());

        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryTransactionService.save(oInventoryTransaction.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer inventoryTransactionId) {
        Optional<InventoryTransaction> oInventoryTransaction = inventoryTransactionService.findById(inventoryTransactionId);
        if(!oInventoryTransaction.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        inventoryTransactionService.deleteById(inventoryTransactionId);
        return ResponseEntity.ok().build();
    }
    //read all
    @GetMapping
    public List<InventoryTransaction> readAll(){
        List<InventoryTransaction> inventoryTransaction = StreamSupport
                .stream(inventoryTransactionService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return inventoryTransaction;
    }
}
