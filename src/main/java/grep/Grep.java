package grep;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 * @author NevostruevaYana
 * @version 1
 *
 * Класс, фильтрующий входной файл на выходные по заданным правилам
 *
 * Поля:
 *          <b>boolean</b> fi
 *             флаг игнорирования регистра(true в случае наличия)
 *
 *          <b>boolean</b> fv
 *             флаг инверсии(true в случае наличия)
 *
 *          <b>boolean</b> fr
 *             флаг регулярноговырадения(true в случае наличия)
 *
 *          <b>ArrayList<String></b> wordAndFile
 *              слово или регулярное выражение и имя входного файла
 *
 */

class Grep {

    /** [-i]
     * флаг игнорирования регистра
     * игнорирует регистр слов
     */
    @Option(name = "-i", usage = "enables case ignore", forbids = {"-r"})
    private boolean fi;

    /** [-v]
     * флаг инверсии
     * инвертирует условие фильтрации (выводится только то, что ему НЕ соответствует)
     */
    @Option(name = "-v", usage = "allows you to invert the filter condition")
    private boolean fv;

    /** [-r]
     * флаг регулярного выражения
     * вместо слова задаёт регулярное выражение для поиска
     */
    @Option(name = "-r", usage = "allows you to specify a regular expression for filtering", forbids = {"-i"})
    private boolean fr;

    /**
     * получает параметры командной строки, отличные от опций
     * (слово или регулярное выражение и имя входного файла)
     */
    @Argument(index = 0)
    private String word;

    @Argument(index = 1)
    private String file;

    /**
     * Проверка корректности ввода аргументов
     *
     * @throws IllegalArgumentException
     *         Если размер {@code wordAndFile} не равен 2
     */


    /**
     * Получение отфильтрованного заданными параметрами списка строк входного файла
     *
     * @throws IOException
     *         Если не удалась попытка открыть файл, обозначенный указанным путем
     */
    List<String> getFilteredFile() throws IOException {

        // проверка правильности ввода аргументов

        String inputFile = file;
        if (!new File(inputFile).exists() || !new File(inputFile).isFile())
            System.out.println("The file path is invalid, the object " +
                    inputFile + " is not a file, or the file does not exist");

        List<String> result;

        // записываем строки файла в массив
        List<String> currentLines = new ArrayList<>();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(inputFile))){

            bufferedReader.lines().forEach(currentLines::add);
        }

        if (fr)
            result = grepRegex(currentLines);
        else
            result = grepWord(currentLines);
        return result;
    }

    /**
     * Фильтрация строк входного файла с помощью регулярного выражения
     *
     * @param lines Контейнер со строками, содержащимися в исходном файле
     *
     * @return Отфильтрованный список строк
     */
    private List<String> grepRegex(List<String> lines) {

        // из массива аргументов получаем регулярное выражение


        List<String> result = new LinkedList<>();

        // проходимся по каждый строке для последующей фильтрации
        for(String line: lines){
            // в случае отсутствия флага инверсии условия фильтрации добавляем в result строки,
            // соответствующие регулярному выражению
            // в случае наличия флага  инверсии условия фильтрации добавляем в result строки,
            // не соответствующие регулярному выражению
            if (!fv) {
                if (Pattern.compile(word).matcher(line).matches())
                    result.add(line);
            } else {
                if (!Pattern.compile(word).matcher(line).matches())
                    result.add(line);
            }
        }

        // возвращаем отфильтрованные строки
        return result;
    }

    /**
     * Фильтрация строк входного файла с помощью введенного слова
     *
     * @param lines Контейнер со строками, содержащимися в исходном файле
     *
     * @return Отфильтрованный список строк
     */
    private List<String> grepWord(List<String> lines) {

        // из массива аргументов получаем регулярное выражение


        // в случае наличия флага игнорирования регистра переводим слово в нижний регистр
        if (fi)
            word = word.toLowerCase();

        List<String> result = new LinkedList<>();

        // проходимся по каждый строке для последующей фильтрации
        for(String line: lines) {

            // флаг наличия слова word в строке
            boolean f = false;

            String newLine = line;

            // в случае наличия флага игнорирования регистра переводим newLine в нижний регистр
            if (fi)
                newLine = newLine.toLowerCase();

            String[] stringWords = newLine.split("\\s+");

            // проходимся по каждому слову в строке
            for (String currentWord : stringWords) {
                if (word.equals(currentWord))
                    f = true;
            }

            // в случае наличия флага инверсии условия фильтрации и отсутствии слова word
            // добавляем строку в result
            if (fv && !f)
                result.add(line);

            // в случае отсутствия флага инверсии условия фильтрации и наличии слова word
            // добавляем строку в result
            if (!fv && f)
                result.add(line);
        }

        // возвращаем отфильтрованные строки
        return result;
    }
}
