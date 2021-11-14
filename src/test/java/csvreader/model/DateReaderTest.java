package csvreader.model;

import java.text.SimpleDateFormat;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import csvreader.exception.CsvFormatException;

class DateReaderTest {
	
	private CsvReader sut;
	
	@BeforeEach
	void init() {
		sut = new DateReader();
	}

	/**
	 * sut can parse strings as date by default accordingly to its format pattern.
	 */
	@Test
	void sutHasADefautlFormatPattern() {
		MatcherAssert.assertThat(((DateReader)sut).getPattern(), Matchers.is("yyyy-MM-dd"));
	}
	
	/**
	 * sut can receive a format pattern to parse specific strings.
	 */
	@Test
	void sutAllowsToSpecifyFormatDatePattern() {
		((DateReader)sut).setPattern("dd-MM-yyyy");
		MatcherAssert.assertThat(((DateReader)sut).getPattern(), Matchers.is("dd-MM-yyyy"));
	}
	
	/**
	 * sut cannot parse strings not representing a valid date accordingly to his format pattern, a {@link CsvFormatException} is raised.
	 */
	@ParameterizedTest
	@ValueSource(strings = { "MM", "10/09/2020", "Jan 4,1999", "not a date at all.", "2000-01-A1"})
	void sutCannotParseStringThatNotSatisfyHisFormatPattern(String date) {
		Assertions.assertThrows(CsvFormatException.class, ()->{
			sut.read(date);
		});
	}
	
	/**
	 * sut can parse strings representing a valid date and satisfy his format pattern.
	 */
	@ParameterizedTest
	@ValueSource(strings = { "2000-01-10", "2998-03-05"})
	void sutParseStringThatSatisfyHisFormatPattern(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(((DateReader) sut).getPattern());
		sut.read(date);
		MatcherAssert.assertThat(((DateReader)sut).getValue(), Matchers.is(sdf.parse(date)));
	}
}
