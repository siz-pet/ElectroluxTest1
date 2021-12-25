import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class LogAnalyzer {
	
	public static void main(String[] args) {
		String logFileName = "src/test_log.txt";
		String resultedLogFile = "src/test_log_with_errors.txt";
		LogParser(logFileName, resultedLogFile);
	}
	
	public static void LogParser(String logFileName, String resultedLogFile) {
		Path logFilePath = Paths.get(logFileName);
		Stream<String> logFileLines;

		try {
			logFileLines = Files.lines(logFilePath);
			logFileLines.forEach(line -> {
				try {
					BufferedWriter output = new BufferedWriter(new FileWriter(resultedLogFile, true));
					// in current implementation only uppercase ERROR entries are being found
					if (line.contains("ERROR:")) {
						output.append(line);
						output.newLine();
					}
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
