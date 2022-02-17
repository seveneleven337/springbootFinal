package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.Strings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StringsRepository extends JpaRepository<Strings,Integer> {
}
