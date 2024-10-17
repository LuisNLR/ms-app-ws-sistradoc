package pe.com.sistradoc.utils;

public class ResponseService {
	
	private String mensaje;
	private boolean flag;
	private int status;
	private Object data;
	
	public ResponseService() {
		super();
	}
	
	public ResponseService(String mensaje, boolean flag, int status) {
		super();
		this.mensaje = mensaje;
		this.flag = flag;
		this.status = status;
	}
	
	public ResponseService(String mensaje, boolean flag, int status, Object data) {
		super();
		this.mensaje = mensaje;
		this.flag = flag;
		this.status = status;
		this.data = data;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
