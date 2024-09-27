package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.MovimientoPasoByTramiteAndDependencia;
import pe.com.sistradoc.model.TramiteMovimiento;

@Repository
public interface TramiteMovimientoRepository extends JpaRepository<TramiteMovimiento, Long> {

	@Query("SELECT m FROM TramiteMovimiento m  " + 
		   " WHERE m.tramite.codigoTramite =:codigoTramite  "+
		   "   AND m.ubicacionActual =:ubicacionActual " ) 
	TramiteMovimiento findByTramiteCodigoTramiteAndUbicacionActual(String codigoTramite, String ubicacionActual);
	
	
	@Query(value = "select m.num_paso_actu as pasoActual from tb_tram_padr_movi m "
			     + " where m.fk1_tram_idx_tram=:codigoTramite "
			     + "   and m.txt_ubic_actu<>'1' "
			     + "   and m.fk0_depe_enti_idx=:idDependencia "
			     + " LIMIT 0,1 "
		       , nativeQuery = true)
	MovimientoPasoByTramiteAndDependencia findPasoByTramiteAndDependencia(
		     @Param("codigoTramite") String codigoTramite,
		     @Param("idDependencia") Long   idDependencia);
	
}
