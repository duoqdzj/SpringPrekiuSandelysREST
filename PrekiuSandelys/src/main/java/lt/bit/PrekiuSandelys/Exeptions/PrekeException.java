package lt.bit.PrekiuSandelys.Exeptions;

public class PrekeException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private Integer code;
	
	public PrekeException(String message, Integer code) {
		super(message);
		this.code=code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
