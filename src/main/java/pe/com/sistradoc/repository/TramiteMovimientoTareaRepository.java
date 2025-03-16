package pe.com.sistradoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.TareaQueryByTramite;
import pe.com.sistradoc.model.TramiteMovimientoTarea;
import pe.com.sistradoc.model.TramiteQueryByDeriver;

@Repository
public interface TramiteMovimientoTareaRepository extends JpaRepository<TramiteMovimientoTarea, Long> {

	@Query(value = "SELECT t.TXT_CODI_TRAM as 'codigoTramite',  \r\n"
	         + "       DATE_FORMAT(T.FEC_INGR_TRAM,'%d/%m/%Y') AS 'fechaIngreso', \r\n"
	         + "       concat(tt.IDX_TIPO_TRAM , '. ', tt.TXT_NOMB_TRAM) AS 'tipoTramite',  \r\n"
	         + "       t.TXT_ASUN_TRAM AS 'asunto',  \r\n"
	         + "       IF(S.TXT_TIPO_SOLI='PERSONA', UPPER(concat(S.TXT_TIPO_DOCU, '. ', s.TXT_NUME_DOCU, ' - ', s.TXT_NOMB_SOLI, ' ', TXT_APEX_PATE, ' ', TXT_APEX_MATE)),  \r\n"
	         + "                                     UPPER(concat(S.TXT_TIPO_DOCU, '. ', s.TXT_NUME_DOCU, ' - ', s.TXT_NOMB_SOLI)) ) AS 'solicitante',  \r\n"
	         + "       d.TXT_NOMB_DEPE AS 'dependenciaActual',  \r\n"
	         + "       (SELECT D.TXT_NOMB_DEPE \r\n"
	         + "          FROM tb_depe_enti d  \r\n"
	         + "         INNER JOIN tb_fluj_tram_depe F \r\n"
	         + "            ON D.IDX_DEPE_ENTI=F.FK0_DEPE_ENTI_IDX  \r\n"
	         + "           AND F.NUM_ORDE_FLUJ=tm.NUM_PASO_ACTU + 1  \r\n"
	         + "         INNER JOIN tb_tipo_tram TT \r\n"
	         + "                 ON TT.IDX_TIPO_TRAM=F.FK1_TIPO_TRAM_IDX \r\n"
	         + "                AND TT.IDX_TIPO_TRAM=t.FK0_TIPO_TRAM_IDX  \r\n"
	         + "         LIMIT 1) AS 'dependenciaDestino' \r\n"
	         + "         , tt.NUM_NUME_DIAS as 'duracion' \r\n"
	         + "         , fnGetRequestTime(t.FEC_INGR_TRAM, sysdate()) as 'diasTranscurridos' "
	         + "  FROM tb_tram_padr t  \r\n"
	         + " INNER JOIN tb_tram_padr_movi tm on t.TXT_CODI_TRAM=tm.FK1_TRAM_IDX_TRAM \r\n"
	         + " INNER JOIN tb_soli_tram s  on t.FK1_SOLI_NUME_DOCU=s.TXT_NUME_DOCU \r\n"
	         + " INNER JOIN tb_depe_enti d  on tm.FK0_DEPE_ENTI_IDX=d.IDX_DEPE_ENTI \r\n"
	         + " INNER JOIN tb_tipo_tram tt on t.FK0_TIPO_TRAM_IDX=tt.IDX_TIPO_TRAM \r\n"
	         + " WHERE TM.TXT_UBIC_ACTU='1'  \r\n"
	         + "   AND T.TXT_ESTA_TRAM='EN TRAMITE' \r\n"
	         + "   AND d.IDX_DEPE_ENTI = :idDependencia \r\n"
	         + " ORDER BY T.FEC_INGR_TRAM ASC, TT.IDX_TIPO_TRAM ASC  "
    , nativeQuery = true)
	List<TramiteQueryByDeriver> getListTramiteAsigned(@Param("idDependencia") Long idDependencia);
	
	
	@Query(value = "SELECT upper(depe.TXT_NOMB_DEPE) as dependencia  \r\n"
			     + "     , tare.TXT_TIPO_TARE as tipoTarea  \r\n"
			     + "     , DATE_FORMAT(tare.FEC_REGI_TARE,'%d/%m/%Y') as fecha  \r\n"
			     + "     , tare.TXT_DESC_TARE as descripcion  \r\n"
			     + "  FROM db_tram_docu.tb_tram_padr_movi_tare tare  \r\n"
			     + " INNER JOIN tb_tram_padr_movi movi on tare.FK0_MOVI_TRAM_ID_MOVI=movi.IDX_MOVI_TRAM  \r\n"
			     + " INNER JOIN tb_depe_enti depe on movi.FK0_DEPE_ENTI_IDX=depe.IDX_DEPE_ENTI  \r\n"
			     + " WHERE movi.FK1_TRAM_IDX_TRAM=:codigoTramite  \r\n"
			     + " order by movi.IDX_MOVI_TRAM, tare.IDX_TARE_MOVI "
		 , nativeQuery = true)
	List<TareaQueryByTramite> getListTareasByTramite(@Param("codigoTramite") String codigoTramite);
	
	
}
