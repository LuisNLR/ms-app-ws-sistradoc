package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.AreaEntidad;


@Repository
public interface AreaEntidadRepository extends JpaRepository<AreaEntidad, Long>{

}
