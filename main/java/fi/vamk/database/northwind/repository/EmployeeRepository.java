package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
