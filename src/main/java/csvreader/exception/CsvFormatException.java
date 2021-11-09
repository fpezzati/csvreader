package csvreader.exception;

public class CsvFormatException extends CsvException {

	private static final long serialVersionUID = -2760769060298965206L;

	public CsvFormatException(String msg) {
		super(msg);
	}
	
	public CsvFormatException(Exception e) {
		super(e);
	}
}
