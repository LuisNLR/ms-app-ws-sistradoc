package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.TipoTramiteRequisito;

@Repository
public interface TipoTramiteRequisitoRepository extends JpaRepository<TipoTramiteRequisito, Long> {

}
