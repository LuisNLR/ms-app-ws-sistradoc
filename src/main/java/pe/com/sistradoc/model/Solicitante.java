package pe.com.sistradoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pe.com.sistradoc.utils.Utils;

@Entity
@Table(name = "TB_SOLI_TRAM")
@EntityListeners(AuditingEntityListener.class)
public class Solicitante {
	
	@Id
	@Column(name = "TXT_NUME_DOCU")
	private String numeroDocumento;
	
	@Column(name = "TXT_TIPO_DOCU")
	private String tipoDocumento;
	
	@Column(name = "TXT_TIPO_SOLI")
	private String tipoSolicitante;
	
	@Column(name = "TXT_NOMB_SOLI")
	private String nombreSolicitante;
	
	@Column(name = "TXT_APEX_PATE")
	private String apellidoPaterno;
	
	@Column(name = "TXT_APEX_MATE")
	private String apellidoMaterno;
	
	@Column(name = "TXT_MAIL_SOLI")
	private String mail;
	
	@Column(name = "TXT_DIRE_SOLI")
	private String direccion;
	
	@Column(name = "TXT_TELE_SOLI")
	private String telefono;
	
	@Column(name = "TXT_REPR_ENTI_SOLI")
	private String representante;

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoSolicitante() {
		return tipoSolicitante;
	}

	public void setTipoSolicitante(String tipoSolicitante) {
		this.tipoSolicitante = tipoSolicitante;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	
	public String getSolicitanteFullName() {
		String solicitante = tipoDocumento + ". " + numeroDocumento;
		String nombreCompleto = null;
		if(tipoSolicitante.equals(Utils.tipoSolicitantePersona) ) {
			nombreCompleto =  nombreSolicitante + " " + apellidoPaterno + " " + apellidoMaterno;
		}else {
			nombreCompleto =  nombreSolicitante;
		}
		solicitante = solicitante + " - " + nombreCompleto.toUpperCase();
		return solicitante;
	}

}
