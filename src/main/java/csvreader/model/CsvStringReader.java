package csvreader.model;

public class CsvStringReader extends CsvReader {

	private String value;

	@Override
	protected void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
