package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.TramiteMovimientoTarea;

@Repository
public interface TramiteMovimientoTareaRepository extends JpaRepository<TramiteMovimientoTarea, Long> {

}
