package pe.com.sistradoc.utils;

public class ResponseService {
	
	private String mensaje;
	private boolean flag;
	private int status;
	
	public ResponseService() {
		super();
	}
	
	public ResponseService(String mensaje, boolean flag, int status) {
		super();
		this.mensaje = mensaje;
		this.flag = flag;
		this.status = status;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
