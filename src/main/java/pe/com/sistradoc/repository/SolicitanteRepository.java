package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.Solicitante;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
	
	Solicitante findByNumeroDocumento(String numeroDocumento);

}
