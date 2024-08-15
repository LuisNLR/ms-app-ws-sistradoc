package pe.com.sistradoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.sistradoc.model.Tramite;
import pe.com.sistradoc.model.TramiteCode;

@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Long> {
	
	@Query(value = "SELECT IF(MAX(T.TXT_CODI_TRAM) IS NOT NULL, "
			+ "          CAST(substr(MAX(T.TXT_CODI_TRAM), 9) AS UNSIGNED) + 1, "
			+ "          '1') AS 'codigoTramite' "
			+ "  FROM tb_tram_padr T "
			+ " WHERE substr(T.TXT_CODI_TRAM, 3,6) = date_format(SYSDATE(), \"%y%m%d\") "
		    , nativeQuery = true)
	TramiteCode getCodeTramite();

}
