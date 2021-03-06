package csvreader.model;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

public class ComuneCodeReader extends CsvReader {

	private String value;

	@Override
	public void setValue(String code) throws CsvException {
		if(!code.matches("[A-Z]\\d{3}")) {
			throw new CsvFormatException(String.format("%s is not a valid comune code.", code));
		}
		value = code;
	}

	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ComuneCodeReader ccr = new ComuneCodeReader();
		try {
			ccr.setValue(getValue());
		} catch (CsvException e) {
			throw new RuntimeException(e);
		}
		return ccr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ComuneCodeReader other = (ComuneCodeReader) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
