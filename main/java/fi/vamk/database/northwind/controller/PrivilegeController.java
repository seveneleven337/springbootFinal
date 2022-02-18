/*package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Privilege;
import fi.vamk.database.northwind.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/privileges")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Privilege privilege){
        return ResponseEntity.status(HttpStatus.CREATED).body(privilegeService.save(privilege));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer privilegeId){
        Optional<Privilege> oPrivilege = privilegeService.findById(privilegeId);
        if(!oPrivilege.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oPrivilege);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Privilege privilegeDetails, @PathVariable(value = "id") Integer privilegeId){
        Optional<Privilege> oPrivilege = privilegeService.findById(privilegeId);
        if(!oPrivilege.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oPrivilege.get().setId(privilegeDetails.getId());
        oPrivilege.get().setPrivilegeName(privilegeDetails.getPrivilegeName());

        return ResponseEntity.status(HttpStatus.CREATED).body(privilegeService.save(oPrivilege.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer privilegeId) {
        Optional<Privilege> oPrivilege = privilegeService.findById(privilegeId);
        if(!oPrivilege.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        privilegeService.deleteById(privilegeId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<Privilege> readAll(){
        List<Privilege> privilegeList = StreamSupport
                .stream(privilegeService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return privilegeList;
    }
}*/
