package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.TipoTramite;

@Repository
public interface TipoTramiteRepository extends JpaRepository<TipoTramite, Long> {

}
