package csvreader.model.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import csvreader.exception.CsvException;
import csvreader.model.ComuneCodeReader;
import csvreader.model.CsvReader;
import csvreader.model.CsvRowReader;
import csvreader.model.CsvStringReader;
import csvreader.model.DateReader;
import csvreader.model.DoubleReader;
import csvreader.model.SexReader;
import csvreader.model.UserIdReader;
import csvreader.task.CsvTaskExecutor;
import csvreader.task.DoNothing;
import csvreader.task.UtentiInput;
import csvreader.task.UtentiOutput;
import javafx.util.Pair;

public class UtentiReader extends CsvFileReader implements CsvTaskExecutor<UtentiInput, UtentiOutput> {

	private CsvRowReader reader;
	private Map<String, CsvRowReader> codeMap;
	private List<CsvRowReader> statisticalMap;
	private Map<Pair<String, String>, Integer> tmpMap;

	private int readRows;

	public UtentiReader() {
		reader = new CsvRowReader();
		reader.getReaders().add(new UserIdReader());
		reader.getReaders().add(new SexReader());
		reader.getReaders().add(new DateReader());
		reader.getReaders().add(new ComuneCodeReader());
	}

	public CsvReader getCsvReader() {
		return reader;
	}

	public void readFile(URI input) throws IOException, CsvException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(input)))) {
			for (String line; (line = br.readLine()) != null;) {
				manageTheLine(line);
			}
		}
	}

	@Override
	protected void manageTheLine(String line) throws CsvException {
		reader.read(line);
		readRows++;
		String comuneCode = ((UserIdReader) reader.getReaders().get(0)).getValue();
		if (codeMap.containsKey(comuneCode)) {
			String regione = ((CsvStringReader) codeMap.get(comuneCode).getReaders().get(3)).getValue();
			String provincia = ((CsvStringReader) codeMap.get(comuneCode).getReaders().get(2)).getValue();
			Pair<String, String> regioneProvincia = new Pair<String, String>(regione, provincia);
			if (tmpMap.containsKey(regioneProvincia)) {
				tmpMap.put(regioneProvincia, tmpMap.get(regioneProvincia) + 1);
			} else {
				tmpMap.put(regioneProvincia, 0);
			}
		}
	}

	@Override
	public void execute(UtentiInput input, UtentiOutput output) throws Exception {
		statisticalMap = new ArrayList<CsvRowReader>();
		tmpMap = new HashMap<>();
		readRows = 0;
		codeMap = input.getCodeMap();
		readFile(input.getURI());

		moveFromTmpMapToStatisticMap();

		output.setStatisticalMap(statisticalMap);
	}

	private void moveFromTmpMapToStatisticMap() throws CsvException {
		for (Pair<String, String> key : tmpMap.keySet()) {
			CsvRowReader row = new CsvRowReader();
			CsvStringReader regione = new CsvStringReader();
			regione.read(key.getKey());
			CsvStringReader provincia = new CsvStringReader();
			provincia.read(key.getValue());
			DoubleReader percentage = new DoubleReader();
			percentage.read(new DecimalFormat("#.00").format((tmpMap.get(key) * 100)/readRows));
			row.getReaders().add(regione);
			row.getReaders().add(provincia);
			row.getReaders().add(percentage);
			
		}
	}
}
