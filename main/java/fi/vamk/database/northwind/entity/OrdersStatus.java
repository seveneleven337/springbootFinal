package fi.vamk.database.northwind.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders_status")
public class OrdersStatus {
    @Id
    @Column(name = "id", nullable = false)
    private Boolean id = false;


    @Column(name = "status_name", nullable = false, length = 50)
    private String statusName;

    @JsonIgnore
    @OneToMany(mappedBy = "ordersStatus")
    private Set<Order> orders = new LinkedHashSet<>();

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}