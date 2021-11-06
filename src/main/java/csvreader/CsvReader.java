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

	public void read(String csvsnippet) {
		if(csvsnippet == null || csvsnippet.isEmpty()) return;
		setValue(csvsnippet);
	}

	protected abstract void setValue(String anyString);
	
}
