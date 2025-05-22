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

	@Query(value = "select t.txt_codi_tram as 'codigotramite',  \r\n"
	         + "       date_format(t.fec_ingr_tram,'%d/%m/%y') as 'fechaingreso', \r\n"
	         + "       concat(tt.idx_tipo_tram , '. ', tt.txt_nomb_tram) as 'tipotramite',  \r\n"
	         + "       t.txt_asun_tram as 'asunto',  \r\n"
	         + "       if(s.txt_tipo_soli='persona', upper(concat(s.txt_tipo_docu, '. ', s.txt_nume_docu, ' - ', s.txt_nomb_soli, ' ', txt_apex_pate, ' ', txt_apex_mate)),  \r\n"
	         + "                                     upper(concat(s.txt_tipo_docu, '. ', s.txt_nume_docu, ' - ', s.txt_nomb_soli)) ) as 'solicitante',  \r\n"
	         + "       d.txt_nomb_depe as 'dependenciaactual',  \r\n"
	         + "       (select d.txt_nomb_depe \r\n"
	         + "          from tb_depe_enti d  \r\n"
	         + "         inner join tb_fluj_tram_depe f \r\n"
	         + "            on d.idx_depe_enti=f.fk0_depe_enti_idx  \r\n"
	         + "         inner join tb_tipo_tram tt \r\n"
	         + "            on tt.idx_tipo_tram=f.fk1_tipo_tram_idx \r\n"
	         + "         where f.num_orde_fluj=tm.num_paso_actu + 1  \r\n"
	         + "           and tt.idx_tipo_tram=t.fk0_tipo_tram_idx  \r\n"
	         + "         limit 1) as 'dependenciadestino' \r\n"
	         + "         , tt.num_nume_dias as 'duracion' \r\n"
	         + "         , fnGetRequestTime(t.fec_ingr_tram, sysdate()) as 'diastranscurridos' "
	         + "  from tb_tram_padr t  \r\n"
	         + " inner join tb_tram_padr_movi tm on t.txt_codi_tram=tm.fk1_tram_idx_tram \r\n"
	         + " inner join tb_soli_tram s  on t.fk1_soli_nume_docu=s.txt_nume_docu \r\n"
	         + " inner join tb_depe_enti d  on tm.fk0_depe_enti_idx=d.idx_depe_enti \r\n"
	         + " inner join tb_tipo_tram tt on t.fk0_tipo_tram_idx=tt.idx_tipo_tram \r\n"
	         + " where tm.txt_ubic_actu='1'  \r\n"
	         + "   and t.txt_esta_tram='EN TRAMITE' \r\n"
	         + "   and d.idx_depe_enti = :idDependencia \r\n"
	         + " order by t.fec_ingr_tram asc, tt.idx_tipo_tram asc  "
    , nativeQuery = true)
	List<TramiteQueryByDeriver> getListTramiteAsigned(@Param("idDependencia") Long idDependencia);
	
	
	@Query(value = "select upper(depe.txt_nomb_depe) as dependencia  \r\n"
			     + "     , tare.txt_tipo_tare as tipotarea  \r\n"
			     + "     , date_format(tare.fec_regi_tare,'%d/%m/%y') as fecha  \r\n"
			     + "     , tare.txt_desc_tare as descripcion  \r\n"
			     + "  from db_tram_docu.tb_tram_padr_movi_tare tare  \r\n"
			     + " inner join tb_tram_padr_movi movi on tare.fk0_movi_tram_id_movi=movi.idx_movi_tram  \r\n"
			     + " inner join tb_depe_enti depe on movi.fk0_depe_enti_idx=depe.idx_depe_enti  \r\n"
			     + " where movi.fk1_tram_idx_tram=:codigoTramite  \r\n"
			     + " order by movi.idx_movi_tram, tare.idx_tare_movi "
		 , nativeQuery = true)
	List<TareaQueryByTramite> getListTareasByTramite(@Param("codigoTramite") String codigoTramite);
	
	
}
