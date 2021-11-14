package csvreader.model;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

public class UserIdReader extends CsvStringReader {

	@Override
	protected void setValue(String value) throws CsvException {
		if(value.length() != 9) throw new CsvFormatException(String.format("%s is too long.", value));
		if(!value.startsWith("USER")) throw new CsvFormatException(String.format("%s has wrong prefix", value));
		try {
			Integer.parseInt(value.replaceFirst("^USER", "").replaceFirst("^0+(?!$)", ""));
		} catch (NumberFormatException nfe) {
			throw new CsvFormatException(nfe);
		}
		super.setValue(value);
	}
}
