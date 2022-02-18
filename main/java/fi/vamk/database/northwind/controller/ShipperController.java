package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Shipper;
import fi.vamk.database.northwind.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/shippers")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Shipper shipper){
        return ResponseEntity.status(HttpStatus.CREATED).body(shipperService.save(shipper));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer shipperId){
        Optional<Shipper> oShipper = shipperService.findById(shipperId);
        if(!oShipper.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oShipper);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Shipper shipperDetails, @PathVariable(value = "id") Integer shipperId){
        Optional<Shipper> oShipper = shipperService.findById(shipperId);
        if(!oShipper.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oShipper.get().setId(shipperDetails.getId());
        oShipper.get().setCompany(shipperDetails.getCompany());
        oShipper.get().setLastName(shipperDetails.getLastName());
        oShipper.get().setFirstName(shipperDetails.getFirstName());
        oShipper.get().setEmailAddress(shipperDetails.getEmailAddress());
        oShipper.get().setJobTitle(shipperDetails.getJobTitle());
        oShipper.get().setBusinessPhone(shipperDetails.getBusinessPhone());
        oShipper.get().setHomePhone(shipperDetails.getHomePhone());
        oShipper.get().setMobilePhone(shipperDetails.getMobilePhone());
        oShipper.get().setFaxNumber(shipperDetails.getFaxNumber());
        oShipper.get().setAddress(shipperDetails.getAddress());
        oShipper.get().setCity(shipperDetails.getCity());
        oShipper.get().setStateProvince(shipperDetails.getStateProvince());
        oShipper.get().setZipPostalCode(shipperDetails.getZipPostalCode());
        oShipper.get().setCountryRegion(shipperDetails.getCountryRegion());
        oShipper.get().setWebPage(shipperDetails.getWebPage());
        oShipper.get().setNotes(shipperDetails.getNotes());
        oShipper.get().setAttachments(shipperDetails.getAttachments());


        return ResponseEntity.status(HttpStatus.CREATED).body(shipperService.save(oShipper.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer shipperId) {
        Optional<Shipper> oShipper = shipperService.findById(shipperId);
        if(!oShipper.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        shipperService.deleteById(shipperId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<Shipper> readAll(){
        List<Shipper> shipperList = StreamSupport
                .stream(shipperService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return shipperList;
    }
}
