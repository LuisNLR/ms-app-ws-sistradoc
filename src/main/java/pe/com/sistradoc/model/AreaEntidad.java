package pe.com.sistradoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_AREA_ENTI")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AreaEntidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDX_AREA_ENTI")
	private Long idArea;
	
	@Column(name = "TXT_NOMB_AREA")
	private String nombreArea;

}
