package pe.com.sistradoc.utils;

public class ValidateServiceImp implements ValidateService {

	private String msj;
	private boolean isvalid;
	private Object data;
	
	public String getMsj() {
		return msj;
	}
	public void setMsj(String msj) {
		this.msj = msj;
	}
	public boolean isIsvalid() {
		return isvalid;
	}
	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
