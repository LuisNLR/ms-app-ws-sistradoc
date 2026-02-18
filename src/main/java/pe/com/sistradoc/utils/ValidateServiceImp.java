package pe.com.sistradoc.utils;

import org.springframework.stereotype.Service;

@Service
public class ValidateServiceImp implements ValidateService {

    private String message;
    private boolean valid;
    private Object data;
    private Integer statusCode;
    
    public ValidateServiceImp() {
    }

    public ValidateServiceImp(boolean valid, String message, Integer statusCode) {
        this.valid = valid;
        this.message = message;
        this.statusCode = statusCode;
    }

    public ValidateServiceImp(boolean valid, String message, Object data, Integer statusCode) {
        this.valid = valid;
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public Integer getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
