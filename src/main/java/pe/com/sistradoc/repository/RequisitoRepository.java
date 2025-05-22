package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.Requisito;

@Repository
public interface RequisitoRepository extends JpaRepository<Requisito, Long> {

}
