package csvreader.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

public class DateReader extends CsvReader {

	private String pattern = "yyyy-MM-dd";
	private Date value;

	@Override
	protected void setValue(String anyString) throws CsvException {
		try {
			value = new SimpleDateFormat(getPattern()).parse(anyString);
		} catch (ParseException e) {
			throw new CsvFormatException(e);
		}
	}

	@Override
	public Date getValue() {
		return value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		DateReader dr = new DateReader();
		dr.value = getValue();
		return dr;
	}
}
