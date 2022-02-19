package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.Employee;
import fi.vamk.database.northwind.entity.EmployeePrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePrivilegeRepository extends JpaRepository<EmployeePrivilege, Employee> {
}
