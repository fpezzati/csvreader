package csvreader.model.filereader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import csvreader.exception.CsvException;
import csvreader.model.CsvRowReader;

class ComuniItalianiReaderIT {
	
	private ComuniItalianiReader sut;

	@BeforeEach
	void initEach() {
		sut = new ComuniItalianiReader();
	}
	
	@Test
	void sutReadUnexistingFile() throws Exception {
		Assertions.assertThrows(IOException.class, ()->{
			sut.readFile(new File("src/test/resources/comuni/nonexistingfile.csv").toURI());
		});
	}
	
	@Test
	void sutReadAnEmptyFile() throws Exception {
		sut.readFile(new File("src/test/resources/comuni/empty_file.csv").toURI());
		MatcherAssert.assertThat(sut.getCodeMap(), Matchers.is(Matchers.anEmptyMap()));
	}
	
	@Test
	void sutReadAWrongFormattedFile() {
		URI uri = new File("src/test/resources/comuni/wrong_file.csv").toURI();
		Assertions.assertThrows(CsvException.class, ()->{
			sut.readFile(uri);
		});
	}
	
	@Test
	void sutReadAFewRowsFile() throws Exception {
		sut.readFile(new File("src/test/resources/comuni/few_rows_file.csv").toURI());
		sutReadsFewRowsFileCorrectly(sut.getCodeMap());
	}
	
	private void sutReadsFewRowsFileCorrectly(Map<String, CsvRowReader> codeMap) {
		MatcherAssert.assertThat(codeMap.get("Codice").getReaders().get(1).getValue(), Matchers.is("Denominazione"));
		MatcherAssert.assertThat(codeMap.get("A074").getReaders().get(1).getValue(), Matchers.is("Agli�"));
		MatcherAssert.assertThat(codeMap.get("A109").getReaders().get(1).getValue(), Matchers.is("Airasca"));
		MatcherAssert.assertThat(codeMap.get("A117").getReaders().get(1).getValue(), Matchers.is("Ala di Stura"));
		MatcherAssert.assertThat(codeMap.get("A157").getReaders().get(1).getValue(), Matchers.is("Albiano d'Ivrea"));
		MatcherAssert.assertThat(codeMap.get("A218").getReaders().get(1).getValue(), Matchers.is("Almese"));
	}

	@Test
	void sutReadComuniItalianiFile() throws Exception {
		sut.readFile(new File("src/test/resources/comuni/comuni_italiani.csv").toURI());
		sutReadsComuniItalianiFileCorrectly(sut.getCodeMap());
	}
	
	private void sutReadsComuniItalianiFileCorrectly(Map<String, CsvRowReader> codeMap) {
		MatcherAssert.assertThat(codeMap.get("Codice").getReaders().get(1).getValue(), Matchers.is("Denominazione"));
		MatcherAssert.assertThat(codeMap.get("A074").getReaders().get(1).getValue(), Matchers.is("Agli�"));
		MatcherAssert.assertThat(codeMap.get("C992").getReaders().get(1).getValue(), Matchers.is("Cordignano"));
		MatcherAssert.assertThat(codeMap.get("D030").getReaders().get(1).getValue(), Matchers.is("Cornuda"));
		MatcherAssert.assertThat(codeMap.get("C670").getReaders().get(1).getValue(), Matchers.is("Crocetta del Montello"));
		MatcherAssert.assertThat(codeMap.get("M025").getReaders().get(1).getValue(), Matchers.is("Villasor"));
		MatcherAssert.assertThat(codeMap.get("M026").getReaders().get(1).getValue(), Matchers.is("Villaspeciosa"));
	}
}
