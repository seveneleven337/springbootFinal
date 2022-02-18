package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.OrderDetailsStatus;
import fi.vamk.database.northwind.service.OrderDetailsStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/orderDetailsStatus")
public class OrderDetailsStatusController {

    @Autowired
    private OrderDetailsStatusService orderDetailsStatusService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderDetailsStatus orderDetailsStatus){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsStatusService.save(orderDetailsStatus));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer orderDetailsStatusId){
        Optional<OrderDetailsStatus> oOrderDetailsStatus = orderDetailsStatusService.findById(orderDetailsStatusId);
        if(!oOrderDetailsStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oOrderDetailsStatus);
    }


    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrderDetailsStatus orderDetailsStatusDetails, @PathVariable(value = "id") Integer orderDetailsStatusId){
        Optional<OrderDetailsStatus> oOrderDetailsStatus = orderDetailsStatusService.findById(orderDetailsStatusId);
        if(!oOrderDetailsStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        oOrderDetailsStatus.get().setId(orderDetailsStatusDetails.getId());
        oOrderDetailsStatus.get().setStatusName(orderDetailsStatusDetails.getStatusName());

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsStatusService.save(oOrderDetailsStatus.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer orderDetailsStatusId) {
        Optional<OrderDetailsStatus> oOrderDetailsStatus = orderDetailsStatusService.findById(orderDetailsStatusId);
        if(!oOrderDetailsStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        orderDetailsStatusService.deleteById(orderDetailsStatusId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<OrderDetailsStatus> readAll(){
        List<OrderDetailsStatus> orderDetailsStatusList = StreamSupport
                .stream(orderDetailsStatusService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return orderDetailsStatusList;
    }
}
