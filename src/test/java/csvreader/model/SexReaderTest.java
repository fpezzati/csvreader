package csvreader.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

class SexReaderTest {
	
	private CsvReader sut;
	
	@BeforeEach
	void initEach() {
		sut = new SexReader();
	}
	
	/**
	 * if given string is not "m" or "M" or "f" or "F", sut raises a {@link CsvFormatException} error.
	 */
	@ParameterizedTest
	@ValueSource(strings = { "MM", "hey", "181", "f or F"})
	void sutWontAcceptAnyValueThatIsNotMOrF(String csvsnippet) {
		Assertions.assertThrows(CsvFormatException.class, ()->{
			sut.read(csvsnippet);
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "M", "m", "f", "F" })
	void sutWillAcceptMOrFAsSnippet(String csvsnippet) throws CsvException {
		sut.read(csvsnippet);
		MatcherAssert.assertThat(sut.getValue(), Matchers.is(csvsnippet));
	}
}
