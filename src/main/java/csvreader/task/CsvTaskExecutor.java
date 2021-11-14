package csvreader.task;

/**
 * Base component about some complex operations. Operation pick an input and provides result by filling output.
 * 
 * @author fpezzati
 *
 */
public interface CsvTaskExecutor<I,O> {

	void execute(I input, O output) throws Exception;
}
