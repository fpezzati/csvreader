package csvreader.model;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

public class DoubleReader extends CsvReader {

	private Double value;

	@Override
	protected void setValue(String anyString) throws CsvException {
		try {
			value = Double.parseDouble(anyString);
		} catch (NumberFormatException e) {
			throw new CsvFormatException(e);
		}
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		DoubleReader dr = new DoubleReader();
		dr.value = getValue();
		return dr;
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
		DoubleReader other = (DoubleReader) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
