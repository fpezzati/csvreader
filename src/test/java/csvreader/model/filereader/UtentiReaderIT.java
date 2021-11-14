package csvreader.model.filereader;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;
import csvreader.model.CsvRowReader;
import csvreader.model.CsvStringReader;
import csvreader.model.DoubleReader;
import csvreader.task.UtentiInput;
import csvreader.task.UtentiOutput;

class UtentiReaderIT {

	private UtentiReader sut;
	
	@BeforeEach
	void initEach() {
		sut = new UtentiReader();
	}
	
	/**
	 * when sut read an empty file, he produces an empty statistic map.
	 * 
	 * @throws Exception 
	 */
	@Test
	void emptyFileCausesSutToProduceAnEmptyStatisticMap() throws Exception {
		UtentiInput input = getUtentiInput(new File("src/test/resources/utenti/empty_file.csv").toURI(), getFilledCodeMap());
		UtentiOutput output = Mockito.spy(getEmptyUtentiOutput());
		sut.execute(input, output);
		Mockito.verify(output, Mockito.times(1)).setStatisticalMap(ArgumentMatchers.eq(emptyStatisticalMap()));
	}
	
	/**
	 * when sut read a wrongly formatted file, he bubbles up a {@link CsvFormatException} error to caller.
	 * 
	 * @throws CsvException 
	 */
	@Test
	void wrongFileCausesSutToRaiseACsvFormatException() throws CsvException {
		UtentiInput input = getUtentiInput(new File("src/test/resources/utenti/empty_file.csv").toURI(), getFilledCodeMap());
		UtentiOutput output = getEmptyUtentiOutput();
		Assertions.assertThrows(CsvFormatException.class, ()->{
			sut.execute(input, output);
		});
	}
	
	/**
	 * when sut get an empty code map, he produces an empty statistic map even if users file is ok.
	 * 
	 * @throws Exception 
	 */
	@Test
	void emptyCodeMapCausesSutToProduceEmptyStatisticMapEvenIfUserFileHasEntries() throws Exception {
		UtentiInput input = getUtentiInput(new File("src/test/resources/utenti/few_rows_file.csv").toURI(), getEmptyCodeMap());
		UtentiOutput output = Mockito.spy(getEmptyUtentiOutput());
		sut.execute(input, output);
		Mockito.verify(output, Mockito.times(1)).setStatisticalMap(ArgumentMatchers.eq(emptyStatisticalMap()));
	}
	
	/**
	 * when sut read a fine user file, he produces a statistic map and put it in the output.
	 * 
	 * @throws Exception 
	 */
	@Test
	void whenBothCodeMapAndUserFileAreFeasibleSutProducesAStatisticMap() throws Exception {
		UtentiInput input = getUtentiInput(new File("src/test/resources/utenti/few_rows_file.csv").toURI(), getEmptyCodeMap());
		UtentiOutput output = Mockito.spy(getEmptyUtentiOutput());
		sut.execute(input, output);
		Mockito.verify(output, Mockito.times(1)).setStatisticalMap(ArgumentMatchers.eq(fullStatisticalMap()));
	}
	
	private List<CsvRowReader> emptyStatisticalMap() {
		return new ArrayList<CsvRowReader>();
	}
	
	private List<CsvRowReader> fullStatisticalMap() throws CsvException {
		List<CsvRowReader> rows = new ArrayList<CsvRowReader>();
		rows.add(getStatisticRow("Toscana,,0"));
		rows.add(getStatisticRow("Toscana,FI,33.33"));
		rows.add(getStatisticRow("Piemonte,,0"));
		rows.add(getStatisticRow("Piemonte,TO,50"));
		rows.add(getStatisticRow("Lazio,RM,0"));
		rows.add(getStatisticRow("Lazio,RM,16.66"));
		return rows;
	}

	private UtentiOutput getEmptyUtentiOutput() {
		return new UtentiOutput() {

			@Override
			public void setStatisticalMap(List<CsvRowReader> statisticalMap) {
				// NOTHING TO DO.
			}};
	}

	private UtentiInput getUtentiInput(URI uri, Map<String, CsvRowReader> codeMap) {
		return new UtentiInput() {

			@Override
			public URI getURI() {
				return uri;
			}

			@Override
			public Map<String, CsvRowReader> getCodeMap() {
				return codeMap;
			}
		};
	}
	
	private Map<String, CsvRowReader> getEmptyCodeMap() {
		return new HashMap<String, CsvRowReader>();
	}

	private Map<String, CsvRowReader> getFilledCodeMap() throws CsvException {
		Map<String, CsvRowReader> codeMap = new HashMap<String, CsvRowReader>();
		codeMap.put("A117", getComuniRow("A117,Ala di Stura,TO,Piemonte"));
		codeMap.put("A218", getComuniRow("A218,Almese,TO,Piemonte"));
		codeMap.put("D612", getComuniRow("D612,Firenze,FI,Toscana"));
		codeMap.put("H501", getComuniRow("H501,Roma,RM,Lazio"));
		return codeMap;
	}
	
	private CsvRowReader getComuniRow(String string) throws CsvException {
		CsvRowReader row = new CsvRowReader();
		row.getReaders().add(new CsvStringReader());
		row.getReaders().add(new CsvStringReader());
		row.getReaders().add(new CsvStringReader());
		row.getReaders().add(new CsvStringReader());
		row.read(string);
		return row;
	}

	private CsvRowReader getStatisticRow(String string) throws CsvException {
		CsvRowReader row = new CsvRowReader();
		row.getReaders().add(new CsvStringReader());
		row.getReaders().add(new CsvStringReader());
		row.getReaders().add(new DoubleReader());
		row.read(string);
		return row;
	}
}
