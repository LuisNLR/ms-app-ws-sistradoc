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
	
	
	@Query(value = "SELECT t.TXT_CODI_TRAM as 'codigoTramite',  "
			+ "            IF(S.TXT_TIPO_SOLI='PERSONA', UPPER(concat(S.TXT_TIPO_DOCU, '. ', s.TXT_NUME_DOCU, ' - ', s.TXT_NOMB_SOLI, ' ', TXT_APEX_PATE, ' ', TXT_APEX_MATE)),  "
			+ "                                          UPPER(concat(S.TXT_TIPO_DOCU, '. ', s.TXT_NUME_DOCU, ' - ', s.TXT_NOMB_SOLI)) ) AS 'solicitante',  "
			+ "            tt.TXT_NOMB_TRAM AS 'tipoTramite',  "
			+ "            t.TXT_ASUN_TRAM AS 'asunto',  "
			+ "            t.TXT_OBSE_TRAM as 'observacion', "
			+ "            d.TXT_NOMB_DEPE AS 'dependencoiaActual',  "
			+ "            (SELECT D.TXT_NOMB_DEPE FROM tb_depe_enti d  "
			+ "	                                   INNER JOIN tb_fluj_tram_depe F ON D.IDX_DEPE_ENTI=F.FK0_DEPE_ENTI_IDX  "
			+ "                                                                  AND F.NUM_ORDE_FLUJ=tm.NUM_PASO_ACTU + 1  "
			+ "	                                   INNER JOIN tb_tipo_tram TT     ON TT.IDX_TIPO_TRAM=F.FK1_TIPO_TRAM_IDX "
			+ "                                                                  AND TT.IDX_TIPO_TRAM=t.FK0_TIPO_TRAM_IDX  "
			+ "	           LIMIT 1) AS 'dependenciaDestino' "
			+ "       FROM tb_tram_padr t  "
			+ " INNER JOIN tb_tram_padr_movi tm on t.TXT_CODI_TRAM=tm.FK1_TRAM_IDX_TRAM "
			+ " INNER JOIN tb_soli_tram s  on t.FK1_SOLI_NUME_DOCU=s.TXT_NUME_DOCU "
			+ " INNER JOIN tb_depe_enti d  on tm.FK0_DEPE_ENTI_IDX=d.IDX_DEPE_ENTI "
			+ " INNER JOIN tb_tipo_tram tt on t.FK0_TIPO_TRAM_IDX=tt.IDX_TIPO_TRAM "
			+ "      WHERE TM.TXT_UBIC_ACTU='1' "
			+ "        AND T.TXT_ESTA_TRAM='EN TRAMITE' "
		    , nativeQuery = true)
	List<TramiteByDeriver> getListTramiteDerivar();

}
