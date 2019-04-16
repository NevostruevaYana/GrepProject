package grep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

class GrepTest {

    @BeforeEach
    void before(){
        System.setOut(new PrintStream(myOut));
    }

    private ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @Test
    void test1() throws IOException{
        String[] argsEmpty = {};
        Main.main(argsEmpty);

        final String standardOutput = myOut.toString();

        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write("Enter a string like : grep -flags([-r] - regex, \" +\n" +
                "                \"[-i] - ignore case, [-v] - return lines that NOT matches) words file");
        bw.newLine();
        bw.flush();
        System.out.print(sw.getBuffer());
        sw.close();
        bw.close();

        assertEquals(standardOutput, "Enter a string like : grep -flags([-r] - regex, " +
                "[-i] - ignore case, [-v] - return lines that NOT matches) words file\r\n");
    }

    @Test
    void test2() throws IOException{
        String[] args2 = {"src/test/java/grep/test"};
        Main.main(args2);

        final String standardOutput = myOut.toString();

        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write("Enter a string like : grep -flags([-r] - regex, \" +\n" +
                "                \"[-i] - ignore case, [-v] - return lines that NOT matches) words file");
        bw.newLine();
        bw.flush();
        System.out.print(sw.getBuffer());
        sw.close();
        bw.close();

        assertEquals(standardOutput, "Enter a string like : grep -flags([-r] - regex, " +
                "[-i] - ignore case, [-v] - return lines that NOT matches) words file\r\n");
    }

    @Test
    void test3() throws IOException{
        String[] args3 = {"rfr", "src/test/java/grep/test"};
        Main.main(args3);

        final String standardOutput = myOut.toString();

        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write("rfr sdg ghjkl");
        bw.newLine();
        bw.flush();
        System.out.print(sw.getBuffer());
        sw.close();
        bw.close();

        assertEquals(standardOutput, "rfr sdg ghjkl\r\n");
    }

    @Test
    void test4() throws IOException {
        String[] args4 = {"-i", "rFr", "src/test/java/grep/test"};
        Main.main(args4);

        final String standardOutput = myOut.toString();

        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write("rfr sdg ghjkl");
        bw.newLine();
        bw.write("dxfghp RfR jkf");
        bw.newLine();
        bw.flush();
        System.out.print(sw.getBuffer());
        sw.close();
        bw.close();

        assertEquals(standardOutput, sw.toString());
    }

    @Test
    void test5() throws IOException{
        String[] args5 = {"-v", "-r", "(\\S*\\s*)*(j)(\\S*\\s*)*", "src/test/java/grep/test"};
        Main.main(args5);

        final String standardOutput = myOut.toString();

        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write("zsd sdftsdv fbg");
        bw.newLine();
        bw.write("dftg");
        bw.newLine();
        bw.write("fgkfl rrr");
        bw.newLine();
        bw.flush();
        System.out.print(sw.getBuffer());
        sw.close();
        bw.close();

        assertEquals(standardOutput, "zsd sdftsdv fbg\r\ndftg\r\nfgkfl rrr\r\n");
    }

    @Test
    void test6() throws IOException{
        String[] args6 = {"-r", "(\\S*\\s*)*(j)(\\S*\\s*)*", "src/test/java/grep/test"};
        Main.main(args6);

        final String standardOutput = myOut.toString();

        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write("rfr sdg ghjkl");
        bw.newLine();
        bw.write("dxfghp RfR jkf");
        bw.newLine();
        bw.write("dsjnvkdv dvjnfdrfr");
        bw.newLine();
        bw.flush();
        System.out.print(sw.getBuffer());
        sw.close();
        bw.close();

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
        Main.main(args11);
    }
}