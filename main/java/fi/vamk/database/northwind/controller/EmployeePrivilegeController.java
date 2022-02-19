package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Employee;
import fi.vamk.database.northwind.entity.EmployeePrivilege;
import fi.vamk.database.northwind.service.EmployeePrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/employeePrivilege")
public class EmployeePrivilegeController {

    @Autowired
    private EmployeePrivilegeService employeePrivilegeService;

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeePrivilege employeePrivilege){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeePrivilegeService.save(employeePrivilege));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Employee employeeId){
        Optional<EmployeePrivilege> oEmployeePrivilege = employeePrivilegeService.findById(employeeId);
        if(!oEmployeePrivilege.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oEmployeePrivilege);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody EmployeePrivilege employeePrivilegeDetails, @PathVariable(value = "id") Employee employeeId){
        Optional<EmployeePrivilege> oEmployeePrivilege = employeePrivilegeService.findById(employeeId);
        if(!oEmployeePrivilege.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oEmployeePrivilege.get().setEmployee(employeePrivilegeDetails.getEmployee());
        oEmployeePrivilege.get().setPrivilege(employeePrivilegeDetails.getPrivilege());

        return ResponseEntity.status(HttpStatus.CREATED).body(employeePrivilegeService.save(oEmployeePrivilege.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Employee employeeId) {
        Optional<EmployeePrivilege> oEmployeePrivilege = employeePrivilegeService.findById(employeeId);
        if(!oEmployeePrivilege.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        employeePrivilegeService.deleteById(employeeId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<EmployeePrivilege> readAll(){
        List<EmployeePrivilege> employeePrivilegeList = StreamSupport
                .stream(employeePrivilegeService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return employeePrivilegeList;
    }

}
