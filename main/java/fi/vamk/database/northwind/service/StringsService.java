package fi.vamk.database.northwind.service;

import fi.vamk.database.northwind.entity.Strings;
import fi.vamk.database.northwind.repository.StringsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StringsService {
    @Autowired
    private StringsRepository stringsRepository;

    @Transactional(readOnly = true) 									//para no modifical base de datos
    public Iterable<Strings> findAll() {
        return stringsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Strings> findAll(Pageable pageable) {
        return stringsRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Strings> findById(Integer id) {
        return stringsRepository.findById(id);
    }

    @Transactional
    public Strings save(Strings strings) {
        return stringsRepository.save(strings);
    }

    @Transactional
    public void deleteById(Integer Id) {
        stringsRepository.deleteById(Id);
    }
}
