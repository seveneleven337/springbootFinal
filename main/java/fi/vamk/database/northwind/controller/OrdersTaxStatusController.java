package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.OrdersTaxStatus;
import fi.vamk.database.northwind.service.OrdersTaxStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/ordersTaxStatus")
public class OrdersTaxStatusController {

    @Autowired
    private OrdersTaxStatusService ordersTaxStatusService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrdersTaxStatus ordersTaxStatus){
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersTaxStatusService.save(ordersTaxStatus));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer ordersTaxStatusId){
        Optional<OrdersTaxStatus> oOrdersTaxStatus = ordersTaxStatusService.findById(ordersTaxStatusId);
        if(!oOrdersTaxStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oOrdersTaxStatus);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrdersTaxStatus ordersTaxStatusDetails, @PathVariable(value = "id") Integer ordersTaxStatusId){
        Optional<OrdersTaxStatus> oOrdersTaxStatus = ordersTaxStatusService.findById(ordersTaxStatusId);
        if(!oOrdersTaxStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oOrdersTaxStatus.get().setId(ordersTaxStatusDetails.getId());
        oOrdersTaxStatus.get().setTaxStatusName(ordersTaxStatusDetails.getTaxStatusName());

        return ResponseEntity.status(HttpStatus.CREATED).body(ordersTaxStatusService.save(oOrdersTaxStatus.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer ordersTaxStatusId) {
        Optional<OrdersTaxStatus> oOrdersTaxStatus = ordersTaxStatusService.findById(ordersTaxStatusId);
        if(!oOrdersTaxStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ordersTaxStatusService.deleteById(ordersTaxStatusId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<OrdersTaxStatus> readAll(){
        List<OrdersTaxStatus> ordersTaxStatusList = StreamSupport
                .stream(ordersTaxStatusService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return ordersTaxStatusList;
    }
}
