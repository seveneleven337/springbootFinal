package fi.vamk.database.northwind.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee_privileges", indexes = {
        @Index(name = "privilege_id", columnList = "privilege_id"),
        @Index(name = "employee_id", columnList = "employee_id")
})
public class EmployeePrivilege {
    @EmbeddedId
    private EmployeePrivilegeId id;

    @MapsId("employeeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @MapsId("privilegeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "privilege_id", nullable = false)
    private Privilege privilege;

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeePrivilegeId getId() {
        return id;
    }

    public void setId(EmployeePrivilegeId id) {
        this.id = id;
    }
}