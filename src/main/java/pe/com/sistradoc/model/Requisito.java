package pe.com.sistradoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "TB_REQX_PADR")
@EntityListeners(AuditingEntityListener.class)
public class Requisito {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDX_REQX")
	private Long idRequisito;
	
	@Column(name = "TXT_NOMB_REQX")
	private String nombreRequisito;

	public Long getIdRequisito() {
		return idRequisito;
	}

	public String getNombreRequisito() {
		return nombreRequisito;
	}

	public void setNombreRequisito(String nombreRequisito) {
		this.nombreRequisito = nombreRequisito;
	}
	
}
