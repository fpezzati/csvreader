package csvreader.exception;

public class CsvException extends Exception {

	private static final long serialVersionUID = -1752725799778591373L;

	public CsvException(String msg) {
		super(msg);
	}
	
	public CsvException(Exception e) {
		super(e);
	}
}
