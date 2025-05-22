package pe.com.sistradoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.MovimientoPasoByTramiteAndDependencia;
import pe.com.sistradoc.model.TramiteMovimiento;
import pe.com.sistradoc.model.TramiteMovimientoQueryFlujo;

@Repository
public interface TramiteMovimientoRepository extends JpaRepository<TramiteMovimiento, Long> {

	@Query("SELECT m FROM TramiteMovimiento m  " + 
		   " WHERE m.tramite.codigoTramite =:codigoTramite  "+
		   "   AND m.ubicacionActual =:ubicacionActual " ) 
	TramiteMovimiento findByTramiteCodigoTramiteAndUbicacionActual(String codigoTramite, String ubicacionActual);
	
	
	@Query(value = "select m.num_paso_actu as pasoactual from tb_tram_padr_movi m "
			     + " where m.fk1_tram_idx_tram=:codigoTramite "
			     + "   and m.txt_ubic_actu<>'1' "
			     + "   and m.fk0_depe_enti_idx=:idDependencia "
			     + " limit 0,1 "
		       , nativeQuery = true)
	MovimientoPasoByTramiteAndDependencia findPasoByTramiteAndDependencia(
		     @Param("codigoTramite") String codigoTramite,
		     @Param("idDependencia") Long   idDependencia);
	
	
	@Query(value = "select movi.txt_esta_movi as 'proceso'  \r\n"
			+ "     , movi.txt_moti_envi as 'motivoenvio' \r\n"
			+ "     , depe.txt_nomb_depe as 'dependenciaasignada'  \r\n"
			+ "     , date_format(movi.fec_deri_movi, '%d/%m/%y %h:%i %p') as 'fechaasignacion'  \r\n"
			+ "     , date_format(movi.fec_deri_movi_post, '%d/%m/%y %h:%i %p') as 'fechafin'  \r\n"
			+ "     , if(movi.fec_deri_movi_post is null  \r\n"
			+ "          , if(  fnGetRequestTime(movi.fec_deri_movi, sysdate())  < 1  \r\n"
			+ "               , concat(fnGetRequestTime(movi.fec_deri_movi, sysdate()) , '  ', timediff(sysdate(), movi.fec_deri_movi))  \r\n"
			+ "               , fnGetRequestTime(movi.fec_deri_movi, sysdate()))  \r\n"
			+ "          , if(  fnGetRequestTime(movi.fec_deri_movi, movi.fec_deri_movi_post) < 1  \r\n"
			+ "               , concat(fnGetRequestTime(movi.fec_deri_movi, movi.fec_deri_movi_post), '  ', timediff(movi.fec_deri_movi_post, movi.fec_deri_movi))  \r\n"
			+ "               , fnGetRequestTime(movi.fec_deri_movi, movi.fec_deri_movi_post))  \r\n"
			+ "         ) as 'tiempotranscurrido'  \r\n"
			+ " from db_tram_docu.tb_tram_padr_movi movi  \r\n"
			+ " inner join tb_depe_enti depe on depe.idx_depe_enti=movi.fk0_depe_enti_idx  \r\n"
			+ " inner join tb_tram_padr tram on tram.txt_codi_tram=movi.fk1_tram_idx_tram  \r\n"
			+ " where tram.txt_codi_tram=:codigoTramite  \r\n"
			+ " order by movi.fk1_tram_idx_tram, movi.num_nume_movi  "
	     , nativeQuery = true)
	List<TramiteMovimientoQueryFlujo> getListFlujosMovimientoTramite(@Param("codigoTramite") String codigoTramite);
	
}
