package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.InventoryTransaction;
import fi.vamk.database.northwind.entity.InventoryTransactionType;
import fi.vamk.database.northwind.service.InventoryTransactionService;
import fi.vamk.database.northwind.service.InventoryTransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/inventoryTransactionTypes")
public class InventoryTransactionTypeController {

    @Autowired
    private InventoryTransactionTypeService inventoryTransactionTypeService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody InventoryTransactionType inventoryTransactionType){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryTransactionTypeService.save(inventoryTransactionType));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer inventoryTransactionTypeId){
        Optional<InventoryTransactionType> oInventoryTransactionType = inventoryTransactionTypeService.findById(inventoryTransactionTypeId);
        if(!oInventoryTransactionType.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oInventoryTransactionType);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody InventoryTransactionType inventoryTransactionTypeDetails, @PathVariable(value = "id") Integer inventoryTransactionTypeId){
        Optional<InventoryTransactionType> oInventoryTransactionType = inventoryTransactionTypeService.findById(inventoryTransactionTypeId);
        if(!oInventoryTransactionType.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oInventoryTransactionType.get().setId(inventoryTransactionTypeDetails.getId());
        oInventoryTransactionType.get().setTypeName(inventoryTransactionTypeDetails.getTypeName());

        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryTransactionTypeService.save(oInventoryTransactionType.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer inventoryTransactionTypeId) {
        Optional<InventoryTransactionType> oInventoryTransactionType = inventoryTransactionTypeService.findById(inventoryTransactionTypeId);
        if(!oInventoryTransactionType.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        inventoryTransactionTypeService.deleteById(inventoryTransactionTypeId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<InventoryTransactionType> readAll(){
        List<InventoryTransactionType> inventoryTransactionTypes = StreamSupport
                .stream(inventoryTransactionTypeService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return inventoryTransactionTypes;
    }
}
