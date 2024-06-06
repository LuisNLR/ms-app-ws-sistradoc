package pe.com.sistradoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "TB_TRAM_PADR_MOVI")
@EntityListeners(AuditingEntityListener.class)
public class TramiteMovimientoTarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDX_TARE_MOVI")
	private Long idTarea;
	
	@Column(name = "FEC_REGI_TARE")
	@CreatedDate
	private Date fechaRegistro;
	
	@Column(name = "TXT_DESC_TARE")
	private String descripcion;
	
	@Column(name = "TXT_TIPO_TARE")
	private String tipoTarea;
	
	@OneToOne
	@JoinColumn(name = "FK0_MOVI_TRAM_ID_MOVI")
	private TramiteMovimiento movimiento;

	public Long getIdTarea() {
		return idTarea;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(String tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	public TramiteMovimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(TramiteMovimiento movimiento) {
		this.movimiento = movimiento;
	}

}
