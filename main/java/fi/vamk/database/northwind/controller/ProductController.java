package fi.vamk.database.northwind.controller;

import fi.vamk.database.northwind.entity.Product;
import fi.vamk.database.northwind.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //*********CRUD********

    //create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    //read a user
    @GetMapping("/{id}")									// id esta entre corchetes por que es variable
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer productId){
        Optional<Product> oProduct = productService.findById(productId);
        if(!oProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oProduct);
    }

    //update user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product productDetails, @PathVariable(value = "id") Integer productId){
        Optional<Product> oProduct = productService.findById(productId);
        if(!oProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oProduct.get().setSupplierIds(productDetails.getSupplierIds());
        oProduct.get().setId(productDetails.getId());
        oProduct.get().setProductCode(productDetails.getProductCode());
        oProduct.get().setProductName(productDetails.getProductName());
        oProduct.get().setDescription(productDetails.getDescription());
        oProduct.get().setStandardCost(productDetails.getStandardCost());
        oProduct.get().setListPrice(productDetails.getListPrice());
        oProduct.get().setReorderLevel(productDetails.getReorderLevel());
        oProduct.get().setTargetLevel(productDetails.getTargetLevel());
        oProduct.get().setQuantityPerUnit(productDetails.getQuantityPerUnit());
        oProduct.get().setDiscontinued(productDetails.getDiscontinued());
        oProduct.get().setMinimumReorderQuantity(productDetails.getMinimumReorderQuantity());
        oProduct.get().setCategory(productDetails.getCategory());
        oProduct.get().setAttachments(productDetails.getAttachments());

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(oProduct.get()));
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer productId) {
        Optional<Product> oProduct = productService.findById(productId);
        if(!oProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        productService.deleteById(productId);
        return ResponseEntity.ok().build();
    }

    //read all
    @GetMapping
    public List<Product> readAll(){
        List<Product> productList = StreamSupport
                .stream(productService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return productList;
    }
}
