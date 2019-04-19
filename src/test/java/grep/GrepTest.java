package grep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

class GrepTest {

    private ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    private ByteArrayOutputStream errOut = new ByteArrayOutputStream();

    @BeforeEach
    void before(){
        System.setOut(new PrintStream(myOut));
        System.setErr(new PrintStream(errOut));
    }

    @Test
    void test1() {
        String[] argsEmpty = {};
        Main.main(argsEmpty);

        assertEquals(myOut.toString(), "Enter a string like : grep -flags([-r] - regex, " +
                "[-i] - ignore case, [-v] - return lines that NOT matches) words file" + System.lineSeparator());
    }

    @Test
    void test2() {
        String[] args2 = {"src/test/java/grep/test"};
        Main.main(args2);

        assertEquals(myOut.toString(), "Enter a string like : grep -flags([-r] - regex, " +
                "[-i] - ignore case, [-v] - return lines that NOT matches) words file" + System.lineSeparator());
    }

    @Test
    void test3() {
        String[] args3 = {"rfr", "src/test/java/grep/test"};
        Main.main(args3);

        assertEquals(myOut.toString(), "rfr sdg ghjkl" + System.lineSeparator());
    }

    @Test
    void test4() {
        String[] args4 = {"-i", "rFr", "src/test/java/grep/test"};
        Main.main(args4);

        assertEquals(myOut.toString(), "rfr sdg ghjkl" + System.lineSeparator() + "dxfghp RfR jkf" + System.lineSeparator());
    }

    @Test
    void test5() {
        String[] args5 = {"-v", "-r", "(\\S*\\s*)*(j)(\\S*\\s*)*", "src/test/java/grep/test"};
        Main.main(args5);

        assertEquals(myOut.toString(), "zsd sdftsdv fbg" + System.lineSeparator() +
                "dftg" + System.lineSeparator() + "fgkfl rrr" + System.lineSeparator());
    }

    @Test
    void test6() {
        String[] args6 = {"-r", "(\\S*\\s*)*(j)(\\S*\\s*)*", "src/test/java/grep/test"};
        Main.main(args6);

        assertEquals(myOut.toString(), "rfr sdg ghjkl" + System.lineSeparator() + "dxfghp RfR jkf"
                + System.lineSeparator() + "dsjnvkdv dvjnfdrfr" + System.lineSeparator());
    }

    @Test
    void test7() {
        String[] args7 = {"-i", "-r", "^(d)(\\S*\\s*)*", "src/test/java/grep/test"};
        Main.main(args7);

        assertEquals(errOut.toString(),"option \"-r\" cannot be used with the option(s) [-i]" + System.lineSeparator());
    }

    @Test
    void test8() {
        String[] args8 = {"-v", "sdg", "src/test/java/grep/test"};
        Main.main(args8);

        assertEquals(myOut.toString(), "zsd sdftsdv fbg" + System.lineSeparator() + "dftg" +
                System.lineSeparator() + "dxfghp RfR jkf" + System.lineSeparator() + "dsjnvkdv dvjnfdrfr"
                + System.lineSeparator() + "fgkfl rrr" + System.lineSeparator());
    }

    @Test
    void test9() {
        String[] args9 = {"dfg", "fgh", "src/test/java/grep/test"};
        Main.main(args9);

        assertEquals(errOut.toString(),"Too many arguments: src/test/java/grep/test" + System.lineSeparator());
    }

    @Test
    void test10() {
        String[] args10 = {"fgh", "src/test/java/grep/test.txt"};
        Main.main(args10);

        assertEquals(errOut.toString(),"src\\test\\java\\grep\\test.txt (Не удается найти указанный файл)" + System.lineSeparator());
    }

    @Test
    void test11() {
        String[] args11 = {"fgh", "null"};
        Main.main(args11);

        assertEquals(errOut.toString(),"null (Не удается найти указанный файл)" + System.lineSeparator());
    }
}