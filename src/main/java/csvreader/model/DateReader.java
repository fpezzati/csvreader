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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateReader other = (DateReader) obj;
		if (pattern == null) {
			if (other.pattern != null)
				return false;
		} else if (!pattern.equals(other.pattern))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
