package csvreader.model;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

public class SexReader extends CsvStringReader {

	@Override
	protected void setValue(String value) throws CsvException {
		if (!(value.equalsIgnoreCase("m") || value.equalsIgnoreCase("f")))
			throw new CsvFormatException(String.format("%s is not valid as sex mark.", value));
		super.setValue(value);
	}
}
