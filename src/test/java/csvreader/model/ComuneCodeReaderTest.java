package csvreader.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

public class ComuneCodeReaderTest {
	
	private CsvReader sut;
	
	@BeforeEach
	void initEach() {
		sut = new ComuneCodeReader();
	}

	/**
	 * sut throws a {@link CsvFormatException} when the given string isn't a proper comune code.
	 */
	@ParameterizedTest
	@ValueSource(strings = { "MM", "10/09/2020", "Jan 4,1999", "not a date at all.", "2000-01-A1"})
	void sutCannotParseStringThatNotSatisfyHisFormatPattern(String code) {
		Assertions.assertThrows(CsvFormatException.class, ()->{
			sut.read(code);
		});
	}
	
	/**
	 * sut parses all the given strings fullfilling the comune code regex.
	 * @throws Exception 
	 */
	@ParameterizedTest
	@ValueSource(strings = { "D508", "A944", "A326" })
	void sutParsesStringsThatRepresentCorrectlyAComuneCode(String code) throws Exception {
		sut.read(code);
		MatcherAssert.assertThat(sut.getValue(), Matchers.is(code));
	}
}
