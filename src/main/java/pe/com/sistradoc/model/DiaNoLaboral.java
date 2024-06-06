package pe.com.sistradoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "TB_FERI_NOT_LABO")
@EntityListeners(AuditingEntityListener.class)
public class DiaNoLaboral {
	
	@Id
	@Column(name = "FEC_FERI_NOT_LABO")
	@CreatedDate
	private Date diaNoLaboral;
	
	@Column(name = "TXT_MOTI_DIA_FERI")
	private String motivoFeriado;

	public Date getDiaNoLaboral() {
		return diaNoLaboral;
	}

	public String getMotivoFeriado() {
		return motivoFeriado;
	}

	public void setMotivoFeriado(String motivoFeriado) {
		this.motivoFeriado = motivoFeriado;
	}
	
}
