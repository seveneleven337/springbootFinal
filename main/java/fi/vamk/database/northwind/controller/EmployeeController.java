package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Employee;
import fi.vamk.database.northwind.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer employeeId){
        Optional<Employee> oEmployee = employeeService.findById(employeeId);
        if(!oEmployee.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oEmployee);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Employee employeeDetails, @PathVariable(value = "id") Integer employeeId){
        Optional<Employee> oEmployee = employeeService.findById(employeeId);
        if(!oEmployee.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oEmployee.get().setId(employeeDetails.getId());
        oEmployee.get().setCompany(employeeDetails.getCompany());
        oEmployee.get().setLastName(employeeDetails.getLastName());
        oEmployee.get().setFirstName(employeeDetails.getFirstName());
        oEmployee.get().setEmailAddress(employeeDetails.getEmailAddress());
        oEmployee.get().setJobTitle(employeeDetails.getJobTitle());
        oEmployee.get().setBusinessPhone(employeeDetails.getBusinessPhone());
        oEmployee.get().setHomePhone(employeeDetails.getHomePhone());
        oEmployee.get().setMobilePhone(employeeDetails.getMobilePhone());
        oEmployee.get().setFaxNumber(employeeDetails.getFaxNumber());
        oEmployee.get().setAddress(employeeDetails.getAddress());
        oEmployee.get().setCity(employeeDetails.getCity());
        oEmployee.get().setStateProvince(employeeDetails.getStateProvince());
        oEmployee.get().setZipPostalCode(employeeDetails.getZipPostalCode());
        oEmployee.get().setCountryRegion(employeeDetails.getCountryRegion());
        oEmployee.get().setWebPage(employeeDetails.getWebPage());
        oEmployee.get().setNotes(employeeDetails.getNotes());
        oEmployee.get().setAttachments(employeeDetails.getAttachments());

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(oEmployee.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer employeeId) {
        Optional<Employee> oEmployee = employeeService.findById(employeeId);
        if(!oEmployee.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        employeeService.deleteById(employeeId);
        return ResponseEntity.ok().build();
    }
    //read all
    @GetMapping
    public List<Employee> readAll(){
        List<Employee> employee = StreamSupport
                .stream(employeeService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return employee;
    }
}
