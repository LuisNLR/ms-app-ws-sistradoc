package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.DiaNoLaboral;

@Repository
public interface DiaNoLaboralRepository extends JpaRepository<DiaNoLaboral, Long>{
	
}
