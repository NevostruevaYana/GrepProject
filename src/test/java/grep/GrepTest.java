package grep;

import org.junit.jupiter.api.Test;

class GrepTest {

    @Test
    void test1() {
        String[] argsEmpty = {};
        Main.main(argsEmpty); //should return description string
    }

    @Test
    void test2() {
        String[] args2 = {"src\\test\\java\\grep\\test"};
        Main.main(args2); //should return description string
    }

    @Test
    void test3() {
        String[] args1 = {"rfr", "src\\test\\java\\grep\\test"};
        Main.main(args1); //should return "rfr sdg ghjkl"
    }

    @Test
    void test4() {
        String[] args1 = {"-i", "rFr", "src\\test\\java\\grep\\test"};
        Main.main(args1); //should return "rfr sdg ghjkl"
        //              "dxfghp RfR jkf"
    }

    @Test
    void test5() {
        String[] args3 = {"-v", "-r", "(\\S*\\s*)*(j)(\\S*\\s*)*", "src\\test\\java\\grep\\test"};
        Main.main(args3); //should return "zsd sdftsdv fbg"
        //              "dftg"
        //              "fgkfl rrr"
    }

    @Test
    void test6() {
        String[] args1 = {"-i", "-r", "^(d)(\\S*\\s*)*", "src\\test\\java\\grep\\test"};
        Main.main(args1); //should return "dftg"
        //              "dxfghp RfR jkf"
        //              "dsjnvkdv dvjnfdrfr"
    }

    @Test
    void test7() {
        String[] args1 = {"-v", "sdg", "src\\test\\java\\grep\\test"};
        Main.main(args1); //should return "zsd sdftsdv fbg"
        //              "dftg"
        //              "dxfghp RfR jkf"
        //              "dsjnvkdv dvjnfdrfr"
        //              "fgkfl rrr"
    }

    @Test
    void test8() {
        String[] args2 = {"dfg", "fgh", "src\\test\\java\\grep\\test"};
        Main.main(args2); //should return "There should be 2 arguments: word file" message
    }

    @Test
    void test9() {
        String[] args2 = {"fgh", "src\\test\\java\\grep\\test.txt"};
        Main.main(args2); //should return "The file path is invalid, the object C://Users//test.tt
        // is not a file, or the file does not exist" message
    }

    @Test
    void test10() {
        String[] args2 = {"fgh", "null"};
        Main.main(args2); //should return "The file path is invalid, the object null
        // is not a file, or the file does not exist" message
    }
}