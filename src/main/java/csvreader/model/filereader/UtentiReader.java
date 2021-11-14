package csvreader.model.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import csvreader.exception.CsvException;
import csvreader.model.Collector;
import csvreader.model.ComuneCodeReader;
import csvreader.model.CsvReader;
import csvreader.model.CsvRowReader;
import csvreader.model.DateReader;
import csvreader.model.SexReader;
import csvreader.model.UserIdReader;

public class UtentiReader extends CsvFileReader {

	private CsvRowReader reader;
	private Collector collector;
	
	public UtentiReader() {
		reader = new CsvRowReader();
		reader.getReaders().add(new UserIdReader());
		reader.getReaders().add(new SexReader());
		reader.getReaders().add(new DateReader());
		reader.getReaders().add(new ComuneCodeReader());
		
		collector = new DoNothing();
	}

	public CsvReader getCsvReader() {
		return reader;
	}

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

	public void readFile(URI input) throws IOException, CsvException {
		try(BufferedReader br = new BufferedReader(new FileReader(new File(input)))) {
			for(String line; (line = br.readLine()) != null; ) {
				manageTheLine(line);
			}
		}
	}

	@Override
	protected void manageTheLine(String line) throws CsvException {
		
	}
}
