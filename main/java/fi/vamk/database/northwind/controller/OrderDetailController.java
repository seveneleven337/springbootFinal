package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.OrderDetail;
import fi.vamk.database.northwind.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderDetail orderDetail){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.save(orderDetail));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer orderDetailId){
        Optional<OrderDetail> oOrderDetail = orderDetailService.findById(orderDetailId);
        if(!oOrderDetail.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oOrderDetail);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrderDetail orderDetailDetails, @PathVariable(value = "id") Integer orderDetailId){
        Optional<OrderDetail> oOrderDetail = orderDetailService.findById(orderDetailId);
        if(!oOrderDetail.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oOrderDetail.get().setId(orderDetailDetails.getId());
        oOrderDetail.get().setOrders(orderDetailDetails.getOrders());
        oOrderDetail.get().setProducts(orderDetailDetails.getProducts());
        oOrderDetail.get().setQuantity(orderDetailDetails.getQuantity());
        oOrderDetail.get().setUnitPrice(orderDetailDetails.getUnitPrice());
        oOrderDetail.get().setDiscount(orderDetailDetails.getDiscount());
        oOrderDetail.get().setOrderDetailsStatus(orderDetailDetails.getOrderDetailsStatus());
        oOrderDetail.get().setDateAllocated(orderDetailDetails.getDateAllocated());
        oOrderDetail.get().setPurchaseOrderId(orderDetailDetails.getPurchaseOrderId());
        oOrderDetail.get().setInventoryId(orderDetailDetails.getInventoryId());

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.save(oOrderDetail.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer orderDetailId) {
        Optional<OrderDetail> oOrderDetail = orderDetailService.findById(orderDetailId);
        if(!oOrderDetail.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        orderDetailService.deleteById(orderDetailId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<OrderDetail> readAll(){
        List<OrderDetail> orderDetailList = StreamSupport
                .stream(orderDetailService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return orderDetailList;
    }

}
