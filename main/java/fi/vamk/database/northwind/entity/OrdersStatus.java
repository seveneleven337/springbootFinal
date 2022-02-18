package fi.vamk.database.northwind.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders_status")
public class OrdersStatus {
    @Id
    @Column(name = "id", nullable = false, columnDefinition="TINYINT")
    private Integer id;


    @Column(name = "status_name", nullable = false, length = 50)
    private String statusName;

    @JsonIgnore
    @OneToMany(mappedBy = "ordersStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//TODO Reverse Engineering! Migrate other columns to the entity
}