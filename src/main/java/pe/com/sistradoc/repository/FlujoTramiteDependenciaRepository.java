package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.FlujoTramiteDependencia;

@Repository
public interface FlujoTramiteDependenciaRepository extends JpaRepository<FlujoTramiteDependencia, Long> {

}
