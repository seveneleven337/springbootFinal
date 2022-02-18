package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Customer;
import fi.vamk.database.northwind.entity.Employee;
import fi.vamk.database.northwind.repository.CustomerRepository;
import fi.vamk.database.northwind.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteById(Integer Id) {
        employeeRepository.deleteById(Id);
    }
}



