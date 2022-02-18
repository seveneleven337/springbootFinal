package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Order;
import fi.vamk.database.northwind.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Order order){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer orderId){
        Optional<Order> oOrder = orderService.findById(orderId);
        if(!oOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oOrder);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Order orderDetails, @PathVariable(value = "id") Integer orderId){
        Optional<Order> oOrder = orderService.findById(orderId);
        if(!oOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oOrder.get().setId(orderDetails.getId());
        oOrder.get().setEmployee(orderDetails.getEmployee());
        oOrder.get().setCustomer(orderDetails.getCustomer());
        oOrder.get().setOrderDate(orderDetails.getOrderDate());
        oOrder.get().setShippedDate(orderDetails.getShippedDate());
        oOrder.get().setShippers(orderDetails.getShippers());
        oOrder.get().setShipName(orderDetails.getShipName());
        oOrder.get().setShipAddress(orderDetails.getShipAddress());
        oOrder.get().setShipCity(orderDetails.getShipCity());
        oOrder.get().setShipStateProvince(orderDetails.getShipStateProvince());
        oOrder.get().setShipZipPostalCode(orderDetails.getShipZipPostalCode());
        oOrder.get().setShipCountryRegion(orderDetails.getShipCountryRegion());
        oOrder.get().setShippingFee(orderDetails.getShippingFee());
        oOrder.get().setTaxes(orderDetails.getTaxes());
        oOrder.get().setPaymentType(orderDetails.getPaymentType());
        oOrder.get().setPaidDate(orderDetails.getPaidDate());
        oOrder.get().setNotes(orderDetails.getNotes());
        oOrder.get().setTaxRate(orderDetails.getTaxRate());
        oOrder.get().setOrdersTaxStatus(orderDetails.getOrdersTaxStatus());
        oOrder.get().setOrdersStatus(orderDetails.getOrdersStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(oOrder.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer orderId) {
        Optional<Order> oOrder = orderService.findById(orderId);
        if(!oOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        orderService.deleteById(orderId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<Order> readAll(){
        List<Order> orderList = StreamSupport
                .stream(orderService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return orderList;
    }

}
