package pe.com.sistradoc.model;

public interface TramiteQueryByDeriver {
	
	public String getCodigoTramite();
	
	public String getFechaIngreso();
	
	public String getTipoTramite();
	
	public String getAsunto();
	
	public String getSolicitante();
	
	public String getDependenciaActual();
	
	public String getDependenciaDestino();
	
	public Integer getDuracion();
	
	public Integer getDiasTranscurridos();
	
	public String getFlujoRealizar();
	
	public String getEstadoTramite();
	
	public Integer getNroFolios();
	
	public String getTipoDocumentoTramite();
	
	public String getFechaFin();

}
