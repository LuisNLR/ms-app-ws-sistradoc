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
	
	
	@Query(value = " select m.num_paso_actu as pasoactual \r\n"
			+ "        from tb_tram_padr_movi m \r\n"
			+ "       where m.fk1_tram_idx_tram = :codigoTramite \r\n"
			+ "         and m.txt_ubic_actu <> '1' \r\n"
			+ "         and m.fk0_depe_enti_idx = :idDependencia \r\n"
			+ "       limit 1 "
		       , nativeQuery = true)
	MovimientoPasoByTramiteAndDependencia findPasoByTramiteAndDependencia(
		     @Param("codigoTramite") String codigoTramite,
		     @Param("idDependencia") Long   idDependencia);
	
	
	@Query(value = "select movi.txt_esta_movi as proceso,  \r\n"
                 + "       movi.txt_moti_envi as motivoenvio,  \r\n"
                 + "       depe.txt_nomb_depe as dependenciaasignada,  \r\n"
                 + "       to_char(movi.fec_deri_movi, 'DD/MM/YY HH12:MI AM') as fechaasignacion,  \r\n"
                 + "       to_char(movi.fec_deri_movi_post, 'DD/MM/YY HH12:MI AM') as fechafin,  \r\n"
                 + "       CASE  \r\n"
                 + "          WHEN movi.fec_deri_movi_post IS NULL   \r\n"
                 + "          THEN  \r\n"
                 + "             CASE  \r\n"
                 + "                WHEN fnGetRequestTime(CAST(movi.fec_deri_movi AS date), CAST(CURRENT_TIMESTAMP AS date)) < 1   \r\n"
                 + "                THEN CAST(fnGetRequestTime(CAST(movi.fec_deri_movi AS date), CAST(CURRENT_TIMESTAMP AS date) ) AS text)   \r\n"
                 + "                     || '  ' || (CURRENT_TIMESTAMP - movi.fec_deri_movi)   \r\n"
                 + "                ELSE CAST(fnGetRequestTime(CAST(movi.fec_deri_movi AS date), CAST(CURRENT_TIMESTAMP AS date)) AS text)   \r\n"
                 + "             END  \r\n"
                 + "             ELSE  \r\n"
                 + "                CASE  \r\n"
                 + "                   WHEN fnGetRequestTime(CAST(movi.fec_deri_movi AS date), CAST(movi.fec_deri_movi_post AS date)) < 1   \r\n"
                 + "                   THEN CAST(fnGetRequestTime(CAST(movi.fec_deri_movi AS date), CAST(movi.fec_deri_movi_post AS date)) AS text)   \r\n"
                 + "                        || '  ' || (movi.fec_deri_movi_post - movi.fec_deri_movi)   \r\n"
                 + "                   ELSE CAST(fnGetRequestTime(CAST(movi.fec_deri_movi AS date), CAST(movi.fec_deri_movi_post AS date)) AS text)   \r\n"
                 + "                END  \r\n"
                 + "       END  \r\n"
                 + "       AS tiempotranscurrido   \r\n"
                 + "  from tb_tram_padr_movi movi   \r\n"
                 + " inner join tb_depe_enti depe   \r\n"
                 + "    on depe.idx_depe_enti = movi.fk0_depe_enti_idx   \r\n"
                 + " inner join tb_tram_padr tram   \r\n"
                 + "    on tram.txt_codi_tram = movi.fk1_tram_idx_tram   \r\n"
                 + " where tram.txt_codi_tram =:codigoTramite   \r\n"
                 + " order by movi.fk1_tram_idx_tram, movi.num_nume_movi   "
	     , nativeQuery = true)
	List<TramiteMovimientoQueryFlujo> getListFlujosMovimientoTramite(@Param("codigoTramite") String codigoTramite);
	
}
