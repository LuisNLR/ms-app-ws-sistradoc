package pe.com.sistradoc.dto;

import java.util.Date;

public class DiaNoLaboralDto {
	
	private Date diaNoLaboral;
	private String motivoFeriado;
	
	public Date getDiaNoLaboral() {
		return diaNoLaboral;
	}
	
	public void setDiaNoLaboral(Date diaNoLaboral) {
		this.diaNoLaboral = diaNoLaboral;
	}
	
	public String getMotivoFeriado() {
		return motivoFeriado;
	}
	
	public void setMotivoFeriado(String motivoFeriado) {
		this.motivoFeriado = motivoFeriado;
	}

}
