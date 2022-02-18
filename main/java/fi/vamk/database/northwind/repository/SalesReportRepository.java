package fi.vamk.database.northwind.repository;

import fi.vamk.database.northwind.entity.SalesReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesReportRepository extends JpaRepository<SalesReport, String> {
}
