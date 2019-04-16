package grep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GrepTest {

    @BeforeEach
    void before(){
        System.setOut(new PrintStream(myOut));
    }

    private ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @Test
    void test1() {
        String[] argsEmpty = {};
        Main.main(argsEmpty);

        final String standardOutput = myOut.toString();
        assertEquals(standardOutput, "Enter a string like : grep -flags([-r] - regex, " +
                "[-i] - ignore case, [-v] - return lines that NOT matches) words file\r\n");
    }

    @Test
    void test2() {
        String[] args2 = {"src/test/java/grep/test"};
        Main.main(args2);

        final String standardOutput = myOut.toString();
        assertEquals(standardOutput, "Enter a string like : grep -flags([-r] - regex, " +
                "[-i] - ignore case, [-v] - return lines that NOT matches) words file\r\n");
    }

    @Test
    void test3() {
        String[] args3 = {"rfr", "src/test/java/grep/test"};
        Main.main(args3);

        final String standardOutput = myOut.toString();
        assertEquals(standardOutput, "rfr sdg ghjkl\r\n");
    }

    @Test
    void test4() {
        String[] args4 = {"-i", "rFr", "src/test/java/grep/test"};
        Main.main(args4);

        final String standardOutput = myOut.toString();
        assertEquals(standardOutput, "rfr sdg ghjkl\r\ndxfghp RfR jkf\r\n");
    }

    @Test
    void test5() {
        String[] args5 = {"-v", "-r", "(\\S*\\s*)*(j)(\\S*\\s*)*", "src/test/java/grep/test"};
        Main.main(args5);

        final String standardOutput = myOut.toString();
        assertEquals(standardOutput, "zsd sdftsdv fbg\r\ndftg\r\nfgkfl rrr\r\n");
    }

    @Test
    void test6() {
        String[] args6 = {"-r", "(\\S*\\s*)*(j)(\\S*\\s*)*", "src/test/java/grep/test"};
        Main.main(args6);

        final String standardOutput = myOut.toString();
        assertEquals(standardOutput, "rfr sdg ghjkl\r\ndxfghp RfR jkf\r\ndsjnvkdv dvjnfdrfr\r\n");
    }

    @Test
    void test7() {
        System.out.println(System.getProperty("os.name"));

        String[] args7 = {"-i", "-r", "^(d)(\\S*\\s*)*", "src/test/java/grep/test"};
        Main.main(args7);
    }

    @Test
    void test8() {
        String[] args8 = {"-v", "sdg", "src/test/java/grep/test"};
        Main.main(args8);

        final String standardOutput = myOut.toString();
        assertEquals(standardOutput, "zsd sdftsdv fbg\r\ndftg\r\ndxfghp RfR jkf" +
                "\r\ndsjnvkdv dvjnfdrfr\r\nfgkfl rrr\r\n");
    }

    @Test
    void test9() {
        String[] args9 = {"dfg", "fgh", "src/test/java/grep/test"};
        Main.main(args9);

        final String standardOutput = myOut.toString();
        assertEquals(standardOutput, "");
    }

    @Test
    void test10() {
        String[] args10 = {"fgh", "src/test/java/grep/test.txt"};
        Main.main(args10);
    }

    @Test
    void test11() {
        String[] args11 = {"fgh", "null"};
        Main.main(args11); //should return "The file path is invalid, the object null
        // is not a file, or the file does not exist" message
    }
}