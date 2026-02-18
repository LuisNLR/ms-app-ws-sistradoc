package pe.com.sistradoc.utils;

public interface ValidateService {
	
	public String getMessage();
	
	public void setMessage(String message);
	
	public boolean isValid();
	
	public void setValid(boolean valid);
	
	public Object getData();
	
	public void setData(Object data);

	public Integer getStatusCode();

	public void setStatusCode(Integer statusCode);

}
