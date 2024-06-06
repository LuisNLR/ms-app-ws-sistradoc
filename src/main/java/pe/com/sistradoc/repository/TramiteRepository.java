package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.Tramite;

@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Long> {

}
