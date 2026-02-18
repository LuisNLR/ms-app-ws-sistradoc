package pe.com.sistradoc.utils;

public class Utils {
	
	//Estados que tendrá un trámite
	public static final String estadoTramiteEnTramite = "EN TRAMITE";
	public static final String estadoTramiteFinalizadoAprobado = "APROBADO";
	public static final String estadoTramiteFinalizadoDesaprobado = "DESAPROBADO";
	public static final String estadoTramiteObservado = "OBSERVADO";
	public static final String estadoTramiteAnulado = "ANULADO";
	
	//Estados que tendrá cada movimiento de trámite
	public static final String estadoMovimientoRegistrado = "REGISTRADO";
	public static final String estadoMovimientoDerivado = "DERIVADO";
	public static final String estadoMovimientoDevuelto = "DEVUELTO";
	public static final String estadoMovimientoRecepcionado = "RECEPCIONADO";
	
	//Tipos de documentos para personas y/o instituciones
	public static final String tipoDocumentoDNI = "DNI";
	public static final String tipoDocumentoRUC = "RUC";
	public static final String tipoDocumentoCE = "CE";
	public static final String tipoDocumentoPAS = "PAS";
	
	//Tipos de solicitante
	public static final String tipoSolicitantePersona = "PERSONA";
	public static final String tipoSolicitanteEntidad = "ENTIDAD";
	
	public static final String estadoGenericoHabilitado = "H";
	public static final String estadoGenericoDeshabilitado = "D";
	
	public static final String motivoEnvioRegistro = "Se registró como inicio de trámite";
	
	public static final String flagEstadoActivo = "1";
	public static final String flagEstadoInactivo = "0";
	
	public static final Long valueDefaultLongOne = Long.valueOf(1);
	public static final Integer valueDefaultIntegerOne = Integer.valueOf(1);
	public static final Integer valueDefaultIntegerZero = Integer.valueOf(0);

	public static final Integer STATUS_CODE_OK = Integer.valueOf(200);

	public static final Integer STATUS_CODE_ERROR = Integer.valueOf(500);
	public static final Integer STATUS_CODE_NOT_FOUND = Integer.valueOf(404);

	public static final Integer STATUS_CODE_UNAUTHORIZED = Integer.valueOf(401);
	public static final Integer STATUS_CODE_FORBIDDEN = Integer.valueOf(403);
	public static final Integer STATUS_CODE_BAD_REQUEST = Integer.valueOf(400);
	public static final Integer STATUS_CODE_CONFLICT = Integer.valueOf(409);
	public static final Integer STATUS_CODE_UNPROCESSABLE_ENTITY = Integer.valueOf(422);
	public static final Integer STATUS_CODE_LOCKED = Integer.valueOf(423);
	public static final Integer STATUS_CODE_FAILED_DEPENDENCY = Integer.valueOf(424);
	public static final Integer STATUS_CODE_INSUFFICIENT_STORAGE = Integer.valueOf(507);
	
	
}
