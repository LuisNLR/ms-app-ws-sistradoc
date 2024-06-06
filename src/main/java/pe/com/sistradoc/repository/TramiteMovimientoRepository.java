package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.TramiteMovimiento;

@Repository
public interface TramiteMovimientoRepository extends JpaRepository<TramiteMovimiento, Long> {

}
