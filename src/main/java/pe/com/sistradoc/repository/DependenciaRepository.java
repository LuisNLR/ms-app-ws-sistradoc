package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.Dependencia;

@Repository
public interface DependenciaRepository extends JpaRepository<Dependencia, Long> {
	
	Dependencia findByIdDependencia(Long id);

}
