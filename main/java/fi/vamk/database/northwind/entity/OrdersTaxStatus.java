package fi.vamk.database.northwind.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders_tax_status")
public class OrdersTaxStatus {
    @Id
    @Column(name = "id", nullable = false, columnDefinition="TINYINT")
    private Integer id;

    @Column(name = "tax_status_name", nullable = false, length = 50)
    private String taxStatusName;

    @JsonIgnore
    @OneToMany(mappedBy = "ordersTaxStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Order> orders = new LinkedHashSet<>();

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getTaxStatusName() {
        return taxStatusName;
    }

    public void setTaxStatusName(String taxStatusName) {
        this.taxStatusName = taxStatusName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    //TODO Reverse Engineering! Migrate other columns to the entity
}