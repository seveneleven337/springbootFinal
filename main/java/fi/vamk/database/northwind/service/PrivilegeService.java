package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Privilege;
import fi.vamk.database.northwind.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PrivilegeService {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Privilege> findAll(Pageable pageable) {
        return privilegeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Privilege> findById(Integer id) {
        return privilegeRepository.findById(id);
    }

    @Transactional
    public Privilege save(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Transactional
    public void deleteById(Integer Id) {
        privilegeRepository.deleteById(Id);
    }
}
