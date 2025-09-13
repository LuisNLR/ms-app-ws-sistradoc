package pe.com.sistradoc.model;

public interface TramiteQueryExport {
	
	public String getCodigoTramite();
	public String getFechaIngreso();
	public String getFechaTermino();
	public String getTipoTramite();
	public String getAsunto();
	public String getSolicitante();
	public Integer getNumeroFolios();
	public String getEstadoTramite();
	public Integer getDuracion();
	public Integer getDiasTranscurridos();
	public String getSeparatorTramiteFlow();
	public Integer getNumeroMovimiento();
	public String getEstadoMovimiento();
	public String getMotivoMovimiento();
	public String getDependenciaOrigen();
	public String getDependenciaDestino();
	public String getFechaAsignacion();
	public String getFechaFin();
	public Integer getDiasAsignacionTranscurrido();
	public String getSeparatorTramiteActivities();
	public String getTipoTarea();
	public String getDescripcionTarea();
	public String getFechaRegistroTarea();

}
