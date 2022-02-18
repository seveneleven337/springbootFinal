package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Strings;
import fi.vamk.database.northwind.service.StringsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/Strings")
public class StringsController {

    @Autowired
    private StringsService stringsService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Strings strings){
        return ResponseEntity.status(HttpStatus.CREATED).body(stringsService.save(strings));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer stringsId){
        Optional<Strings> oStrings = stringsService.findById(stringsId);
        if(!oStrings.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oStrings);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Strings stringsDetails, @PathVariable(value = "id") Integer stringsId){
        Optional<Strings> oStrings = stringsService.findById(stringsId);
        if(!oStrings.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oStrings.get().setId(stringsDetails.getId());
        oStrings.get().setStringData(stringsDetails.getStringData());

        return ResponseEntity.status(HttpStatus.CREATED).body(stringsService.save(oStrings.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer stringsId) {
        Optional<Strings> oStrings = stringsService.findById(stringsId);
        if(!oStrings.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        stringsService.deleteById(stringsId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<Strings> readAll(){
        List<Strings> stringsList = StreamSupport
                .stream(stringsService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return stringsList;
    }
}
