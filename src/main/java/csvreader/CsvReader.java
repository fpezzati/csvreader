package csvreader;

abstract class CsvReader {

	private char separator;

	CsvReader() {
		this.setSeparator(',');
	}

	public char getSeparator() {
		return separator;
	}

	public void setSeparator(char separator) {
		this.separator = separator;
	}
	
}
