package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Employee;
import fi.vamk.database.northwind.entity.EmployeePrivilege;
import fi.vamk.database.northwind.repository.EmployeePrivilegeRepository;
import fi.vamk.database.northwind.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeePrivilegeService {

    @Autowired
    private EmployeePrivilegeRepository employeePrivilegeRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<EmployeePrivilege> findAll() {
        return employeePrivilegeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<EmployeePrivilege> findAll(Pageable pageable) {
        return employeePrivilegeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<EmployeePrivilege> findById(Employee employeeid) {
        return employeePrivilegeRepository.findById(employeeid);
    }

    @Transactional
    public EmployeePrivilege save(EmployeePrivilege employeePrivilege) {
        return employeePrivilegeRepository.save(employeePrivilege);
    }

    @Transactional
    public void deleteById(Employee employeeId) {
        employeePrivilegeRepository.deleteById(employeeId);
    }

}
