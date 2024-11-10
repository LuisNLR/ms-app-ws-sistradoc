package pe.com.sistradoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.Tramite;
import pe.com.sistradoc.model.TramiteByDeriver;
import pe.com.sistradoc.model.TramiteCode;

@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Long> {
	
	Tramite findByCodigoTramite(String codigoTramite);
	
	@Query(value = "SELECT IF(MAX(T.TXT_CODI_TRAM) IS NOT NULL, "
			+ "          CAST(substr(MAX(T.TXT_CODI_TRAM), 9) AS UNSIGNED) + 1, "
			+ "          '1') AS 'codigoTramite' "
			+ "  FROM tb_tram_padr T "
			+ " WHERE substr(T.TXT_CODI_TRAM, 3,6) = date_format(SYSDATE(), \"%y%m%d\") "
		    , nativeQuery = true)
	TramiteCode getCodeTramite();
	
	
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
			     + "  FROM tb_tram_padr t  \r\n"
			     + " INNER JOIN tb_tram_padr_movi tm on t.TXT_CODI_TRAM=tm.FK1_TRAM_IDX_TRAM \r\n"
			     + " INNER JOIN tb_soli_tram s  on t.FK1_SOLI_NUME_DOCU=s.TXT_NUME_DOCU \r\n"
			     + " INNER JOIN tb_depe_enti d  on tm.FK0_DEPE_ENTI_IDX=d.IDX_DEPE_ENTI \r\n"
			     + " INNER JOIN tb_tipo_tram tt on t.FK0_TIPO_TRAM_IDX=tt.IDX_TIPO_TRAM \r\n"
			     + " WHERE TM.TXT_UBIC_ACTU='1'  \r\n"
			     + "   AND T.TXT_ESTA_TRAM='EN TRAMITE' \r\n"
			     + "   AND TM.NUM_PASO_ACTU < (SELECT MAX(F.NUM_ORDE_FLUJ) \r\n"
			     + "                             FROM tb_fluj_tram_depe f \r\n"
			     + "                            WHERE f.FK1_TIPO_TRAM_IDX = tt.IDX_TIPO_TRAM) \r\n"
			     + " ORDER BY T.FEC_INGR_TRAM ASC, TT.IDX_TIPO_TRAM ASC  "
		    , nativeQuery = true)
	List<TramiteByDeriver> getListTramiteDerivar();
	
	
	@Query(value = "SELECT t.TXT_CODI_TRAM as 'codigoTramite',  \r\n"
			     + "       TM.NUM_PASO_ACTU, \r\n"
			     + "       DATE_FORMAT(T.FEC_INGR_TRAM,'%d/%m/%Y') AS 'fechaIngreso', \r\n"
			     + "       concat(tt.IDX_TIPO_TRAM , '. ', tt.TXT_NOMB_TRAM) AS 'tipoTramite',  \r\n"
			     + "       t.TXT_ASUN_TRAM AS 'asunto',  \r\n"
			     + "       IF(S.TXT_TIPO_SOLI='PERSONA', UPPER(concat(S.TXT_TIPO_DOCU, '. ', s.TXT_NUME_DOCU, ' - ', s.TXT_NOMB_SOLI, ' ', TXT_APEX_PATE, ' ', TXT_APEX_MATE)),  \r\n"
			     + "                                     UPPER(concat(S.TXT_TIPO_DOCU, '. ', s.TXT_NUME_DOCU, ' - ', s.TXT_NOMB_SOLI)) ) AS 'solicitante',  \r\n"
			     + "       d.TXT_NOMB_DEPE AS 'dependenciaActual',  \r\n"
			     + "       (SELECT D.TXT_NOMB_DEPE \r\n"
			     + "          FROM tb_depe_enti d  \r\n"
			     + "	     INNER JOIN tb_tram_padr_movi tm2 \r\n"
			     + "                 ON D.IDX_DEPE_ENTI=tm2.FK0_DEPE_ENTI_IDX  \r\n"
			     + "				AND tm2.NUM_PASO_ACTU=tm.NUM_PASO_ACTU - 1  \r\n"
			     + "	     LIMIT 1) AS 'dependenciaDestino' \r\n"
			     + "  FROM tb_tram_padr t  \r\n"
			     + " INNER JOIN tb_tram_padr_movi tm on t.TXT_CODI_TRAM=tm.FK1_TRAM_IDX_TRAM \r\n"
			     + " INNER JOIN tb_soli_tram s  on t.FK1_SOLI_NUME_DOCU=s.TXT_NUME_DOCU \r\n"
			     + " INNER JOIN tb_depe_enti d  on tm.FK0_DEPE_ENTI_IDX=d.IDX_DEPE_ENTI \r\n"
			     + " INNER JOIN tb_tipo_tram tt on t.FK0_TIPO_TRAM_IDX=tt.IDX_TIPO_TRAM \r\n"
			     + " WHERE TM.TXT_UBIC_ACTU='1'  \r\n"
			     + "   AND T.TXT_ESTA_TRAM='EN TRAMITE' \r\n"
			     + "   AND TM.NUM_PASO_ACTU >= 1 \r\n"
			     + " ORDER BY T.FEC_INGR_TRAM ASC, TT.IDX_TIPO_TRAM ASC  "
			, nativeQuery = true)
	List<TramiteByDeriver> getListTramiteDevolver();

	
	@Query(value = "SELECT t.TXT_CODI_TRAM as 'codigoTramite',  \r\n"
			     + "       DATE_FORMAT(T.FEC_INGR_TRAM,'%d/%m/%Y') AS 'fechaIngreso', \r\n"
			     + "       concat(tt.IDX_TIPO_TRAM , '. ', tt.TXT_NOMB_TRAM) AS 'tipoTramite',  \r\n"
			     + "       t.TXT_ASUN_TRAM AS 'asunto',  \r\n"
			     + "       IF(S.TXT_TIPO_SOLI='PERSONA', UPPER(concat(S.TXT_TIPO_DOCU, '. ', s.TXT_NUME_DOCU, ' - ', s.TXT_NOMB_SOLI, ' ', TXT_APEX_PATE, ' ', TXT_APEX_MATE)),  \r\n"
			     + "                                     UPPER(concat(S.TXT_TIPO_DOCU, '. ', s.TXT_NUME_DOCU, ' - ', s.TXT_NOMB_SOLI)) ) AS 'solicitante',  \r\n"
			     + "       d.TXT_NOMB_DEPE AS 'dependenciaActual' \r\n"
			     + "  FROM tb_tram_padr t  \r\n"
			     + " INNER JOIN tb_tram_padr_movi tm on t.TXT_CODI_TRAM=tm.FK1_TRAM_IDX_TRAM \r\n"
			     + " INNER JOIN tb_soli_tram s  on t.FK1_SOLI_NUME_DOCU=s.TXT_NUME_DOCU \r\n"
			     + " INNER JOIN tb_depe_enti d  on tm.FK0_DEPE_ENTI_IDX=d.IDX_DEPE_ENTI \r\n"
			     + " INNER JOIN tb_tipo_tram tt on t.FK0_TIPO_TRAM_IDX=tt.IDX_TIPO_TRAM \r\n"
			     + " WHERE TM.TXT_UBIC_ACTU='1'  \r\n"
			     + "   AND T.TXT_ESTA_TRAM='EN TRAMITE' \r\n"
			     + "   AND TM.NUM_PASO_ACTU = (SELECT MAX(F.NUM_ORDE_FLUJ) \r\n"
			     + "                             FROM tb_fluj_tram_depe f \r\n"
			     + "                            WHERE f.FK1_TIPO_TRAM_IDX = tt.IDX_TIPO_TRAM) \r\n"
			     + " ORDER BY T.FEC_INGR_TRAM ASC, TT.IDX_TIPO_TRAM ASC  "
		     , nativeQuery = true)
	List<TramiteByDeriver> getListTramiteFinished();

}
