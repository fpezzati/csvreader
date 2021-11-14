package csvreader.model;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

public class ComuneCodeReader extends CsvReader {

	private String value;

	@Override
	protected void setValue(String code) throws CsvException {
		if(!code.matches("[A-Z]\\d{3}")) {
			throw new CsvFormatException(String.format("%s is not a valid comune code.", code));
		}
		value = code;
	}

	@Override
	public String getValue() throws CsvException {
		return value;
	}

}
