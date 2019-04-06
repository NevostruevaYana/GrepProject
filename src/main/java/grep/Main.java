package grep;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * @author NevostruevaYana
 * @version 1
 */

public class Main {

    /**
     * Точка входа в программу
     * @param args - параметры для фильтации файла
     */
    public static void main(String[] args) {
        if (args.length == 0 || args.length == 1) {
            System.out.println("Enter a string like : grep -flags([-r] - regex, " +
                    "[-i] - ignore case, [-v] - return lines that NOT matches) words file");
            return;
        }
        Grep grep = new Grep();
        CmdLineParser parser = new CmdLineParser(grep);
        try {
            parser.parseArgument(args);
            List<String> strings = grep.getFilteredFile();
            for (String line : strings)
                System.out.println(line);
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
