package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Customer;
import fi.vamk.database.northwind.service.CustomerService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer customerId){
        Optional<Customer> oCustomer = customerService.findById(customerId);
        if(!oCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oCustomer);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Customer customerDetails, @PathVariable(value = "id") Integer customerId){
        Optional<Customer> oCustomer = customerService.findById(customerId);
        if(!oCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oCustomer.get().setId(customerDetails.getId());
        oCustomer.get().setCompany(customerDetails.getCompany());
        oCustomer.get().setLastName(customerDetails.getLastName());
        oCustomer.get().setFirstName(customerDetails.getFirstName());
        oCustomer.get().setEmailAddress(customerDetails.getEmailAddress());
        oCustomer.get().setJobTitle(customerDetails.getJobTitle());
        oCustomer.get().setBusinessPhone(customerDetails.getBusinessPhone());
        oCustomer.get().setHomePhone(customerDetails.getHomePhone());
        oCustomer.get().setMobilePhone(customerDetails.getMobilePhone());
        oCustomer.get().setFaxNumber(customerDetails.getFaxNumber());
        oCustomer.get().setAddress(customerDetails.getAddress());
        oCustomer.get().setCity(customerDetails.getCity());
        oCustomer.get().setStateProvince(customerDetails.getStateProvince());
        oCustomer.get().setZipPostalCode(customerDetails.getZipPostalCode());
        oCustomer.get().setCountryRegion(customerDetails.getCountryRegion());
        oCustomer.get().setWebPage(customerDetails.getWebPage());
        oCustomer.get().setNotes(customerDetails.getNotes());
        oCustomer.get().setAttachments(customerDetails.getAttachments());

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(oCustomer.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer customerId) {
        Optional<Customer> oCustomer = customerService.findById(customerId);
        if(!oCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        customerService.deleteById(customerId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<Customer> readAll(){
        List<Customer> users = StreamSupport
                .stream(customerService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }
}
