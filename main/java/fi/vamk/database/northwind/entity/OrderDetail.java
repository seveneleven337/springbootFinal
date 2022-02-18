package fi.vamk.database.northwind.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "order_details", indexes = {
        @Index(name = "fk_order_details_order_details_status1_idx", columnList = "status_id"),
        @Index(name = "fk_order_details_orders1_idx", columnList = "order_id"),
        @Index(name = "inventory_id", columnList = "inventory_id"),
        @Index(name = "product_id", columnList = "product_id"),
        @Index(name = "purchase_order_id", columnList = "purchase_order_id")
})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order orders;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product products;

    @Column(name = "quantity", nullable = false, precision = 18, scale = 4)
    private BigDecimal quantity;

    @Column(name = "unit_price", precision = 19, scale = 4)
    private BigDecimal unitPrice;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private OrderDetailsStatus orderDetailsStatus;

    @Column(name = "date_allocated")
    private Instant dateAllocated;

    @Column(name = "purchase_order_id")
    private Integer purchaseOrderId;

    @Column(name = "inventory_id")
    private Integer inventoryId;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Instant getDateAllocated() {
        return dateAllocated;
    }

    public void setDateAllocated(Instant dateAllocated) {
        this.dateAllocated = dateAllocated;
    }

    public OrderDetailsStatus getOrderDetailsStatus() {
        return orderDetailsStatus;
    }

    public void setOrderDetailsStatus(OrderDetailsStatus orderDetailsStatus) {
        this.orderDetailsStatus = orderDetailsStatus;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}