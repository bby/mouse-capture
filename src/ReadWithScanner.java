import java.io.*;
import java.util.Scanner;

public class ReadWithScanner {

	public static void main(String... aArgs) throws FileNotFoundException {
		ReadWithScanner parser = new ReadWithScanner("distances.txt");
		parser.processLineByLine();
		log("Done");
	}

	public ReadWithScanner(String aFileName) {
		fFile = new File(aFileName);
	}

	public final void processLineByLine() throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader(fFile));
		try {
			while(scanner.hasNextLine()) {
				processLine(scanner.nextLine());
			}
		}
		finally {
			scanner.close();
		}
	}

	protected void processLine(String aLine) {
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("=");
		if(scanner.hasNext()) {
			String name = scanner.next();
			String value = scanner.next();
			log("name is:" + quote(name.trim()) + " value is:" + quote(value.trim()));
		} else {
			log("Empty or invalid line");
		}
	}

	private final File fFile;

	private static void log(Object aObject) {
		System.out.println(String.valueOf(aObject));
	}

	private String quote(String aText) {
		String QUOTE ="'";
		return QUOTE + aText + QUOTE;
	}
}