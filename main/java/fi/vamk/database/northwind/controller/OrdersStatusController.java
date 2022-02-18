package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Order;
import fi.vamk.database.northwind.entity.OrdersStatus;
import fi.vamk.database.northwind.service.OrdersStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/ordersStatus")
public class OrdersStatusController {

    @Autowired
    private OrdersStatusService ordersStatusService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrdersStatus ordersStatus){
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersStatusService.save(ordersStatus));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer ordersStatusId){
        Optional<OrdersStatus> oOrdersStatus = ordersStatusService.findById(ordersStatusId);
        if(!oOrdersStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oOrdersStatus);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrdersStatus ordersStatusDetails, @PathVariable(value = "id") Integer ordersStatusId){
        Optional<OrdersStatus> oOrdersStatus = ordersStatusService.findById(ordersStatusId);
        if(!oOrdersStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oOrdersStatus.get().setId(ordersStatusDetails.getId());
        oOrdersStatus.get().setStatusName(ordersStatusDetails.getStatusName());

        return ResponseEntity.status(HttpStatus.CREATED).body(ordersStatusService.save(oOrdersStatus.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer ordersStatusId) {
        Optional<OrdersStatus> oOrdersStatus = ordersStatusService.findById(ordersStatusId);
        if(!oOrdersStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ordersStatusService.deleteById(ordersStatusId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<OrdersStatus> readAll(){
        List<OrdersStatus> ordersStatusList = StreamSupport
                .stream(ordersStatusService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return ordersStatusList;
    }
}
