package pe.com.sistradoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.DependenciaByTipoTramite;

@Repository
public interface DependenciaRepository extends JpaRepository<Dependencia, Long> {
	
	Dependencia findByIdDependencia(Long id);
	
	
	@Query(value = "SELECT D.IDX_DEPE_ENTI AS idDependencia, "
		      	 + "       D.TXT_NOMB_DEPE AS nombreDependencia, "
			     + "       A.TXT_NOMB_AREA AS nombreArea, "
			     + "       A.IDX_AREA_ENTI as idArea "
			     + "  FROM tb_depe_enti d "
			     + " INNER JOIN tb_area_enti a      ON a.IDX_AREA_ENTI=d.FK0_AREA_IDEX "
			     + " INNER JOIN tb_fluj_tram_depe F ON D.IDX_DEPE_ENTI=F.FK0_DEPE_ENTI_IDX "
			     + "                               AND F.NUM_ORDE_FLUJ=:nroPaso "
			     + " INNER JOIN tb_tipo_tram TT     ON TT.IDX_TIPO_TRAM=F.FK1_TIPO_TRAM_IDX "
			     + "                               AND TT.IDX_TIPO_TRAM=:idTipoTramite "
			     + " LIMIT 1 "
			       , nativeQuery = true)
	DependenciaByTipoTramite findDependenciaByTipoTramite(
	      @Param("nroPaso") Integer nroPasoObtener,
	      @Param("idTipoTramite") Long idTipoTramite);
	
	
	@Query(value = " SELECT ft.dependencia FROM FlujoTramiteDependencia ft  " + 
	               "  WHERE ft.ordenFlujo=:nroPaso" + 
				   "    AND ft.tipoTramite.idTipoTramite=:idTipoTramite ")
	Dependencia findDependenciaByPasoAndTipoTramite(
		      @Param("nroPaso") Integer nroPasoObtener,
		      @Param("idTipoTramite") Long idTipoTramite);
	
	@Query(value = " SELECT mt.dependencia FROM TramiteMovimiento mt  " + 
                   "  WHERE mt.tramite.codigoTramite=:codigoTramite "  + 
			       "    AND mt.ubicacionActual != '1' " )
	List<Dependencia> listDependenciasByDevolver(
			  @Param("codigoTramite") String codigoTramite);

}
