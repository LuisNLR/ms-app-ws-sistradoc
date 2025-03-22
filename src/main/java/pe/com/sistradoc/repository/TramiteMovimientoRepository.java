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
	
	
	@Query(value = "select m.num_paso_actu as pasoActual from tb_tram_padr_movi m "
			     + " where m.fk1_tram_idx_tram=:codigoTramite "
			     + "   and m.txt_ubic_actu<>'1' "
			     + "   and m.fk0_depe_enti_idx=:idDependencia "
			     + " LIMIT 0,1 "
		       , nativeQuery = true)
	MovimientoPasoByTramiteAndDependencia findPasoByTramiteAndDependencia(
		     @Param("codigoTramite") String codigoTramite,
		     @Param("idDependencia") Long   idDependencia);
	
	
	@Query(value = "SELECT movi.TXT_ESTA_MOVI as 'proceso'  \r\n"
			+ "     , movi.TXT_MOTI_ENVI as 'motivoEnvio' \r\n"
			+ "     , depe.TXT_NOMB_DEPE AS 'dependenciaAsignada'  \r\n"
			+ "     , DATE_FORMAT(movi.FEC_DERI_MOVI, '%d/%m/%y %h:%i %p') as 'fechaAsignacion'  \r\n"
			+ "     , DATE_FORMAT(movi.FEC_DERI_MOVI_POST, '%d/%m/%y %h:%i %p') as 'fechaFin'  \r\n"
			+ "     , IF(movi.FEC_DERI_MOVI_POST is null  \r\n"
			+ "          , if(  fnGetRequestTime(movi.FEC_DERI_MOVI, sysdate())  < 1  \r\n"
			+ "               , concat(fnGetRequestTime(movi.FEC_DERI_MOVI, sysdate()) , '  ', TIMEDIFF(sysdate(), movi.FEC_DERI_MOVI))  \r\n"
			+ "               , fnGetRequestTime(movi.FEC_DERI_MOVI, sysdate()))  \r\n"
			+ "          , if(  fnGetRequestTime(movi.FEC_DERI_MOVI, movi.FEC_DERI_MOVI_POST) < 1  \r\n"
			+ "               , concat(fnGetRequestTime(movi.FEC_DERI_MOVI, movi.FEC_DERI_MOVI_POST), '  ', TIMEDIFF(movi.FEC_DERI_MOVI_POST, movi.FEC_DERI_MOVI))  \r\n"
			+ "               , fnGetRequestTime(movi.FEC_DERI_MOVI, movi.FEC_DERI_MOVI_POST))  \r\n"
			+ "         ) as 'tiempoTranscurrido'  \r\n"
			+ " FROM db_tram_docu.tb_tram_padr_movi movi  \r\n"
			+ " inner join tb_depe_enti depe on depe.IDX_DEPE_ENTI=movi.FK0_DEPE_ENTI_IDX  \r\n"
			+ " inner join tb_tram_padr tram on tram.TXT_CODI_TRAM=movi.FK1_TRAM_IDX_TRAM  \r\n"
			+ " where tram.TXT_CODI_TRAM=:codigoTramite  \r\n"
			+ " order by movi.FK1_TRAM_IDX_TRAM, movi.NUM_NUME_MOVI  "
	     , nativeQuery = true)
	List<TramiteMovimientoQueryFlujo> getListFlujosMovimientoTramite(@Param("codigoTramite") String codigoTramite);
	
}
