package kz.mouzitoto.gransoftwaretest1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Есть вопрос по постановке задачи:
 * - если мы нашли число, которое превышает 32бита,
 *      то должны ли мы его кромсать по одному символу с конца, чтобы в итоге получить 32бит инт?
 *      Я решил, что нет.
 * - каково минимально допустимое количество пробелов в начале строки?
 *      Я решил, что 0.
 * - может ли строка начинаться не с пробела, но чтобы потом были пробелы и цифры
 *      и функция в этом случае должна вернуть число из тех цифр?
 *      К примеру для строки "abc   -123abc" ответом должно было бы быть -123.
 *      Я решил, что нет.
 */

@SpringBootApplication
public class GranSoftwareTest1Application implements CommandLineRunner {

    private static final String DIGIT_REGEXP = "^[ ]*([+-]?[0-9]+)";

    public static void main(String[] args) {
        SpringApplication.run(GranSoftwareTest1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("answer: " + findIntegerString("     -2147483648asdf4321 +12a") + ", '     -2147483648asdf4321 +12a'");
        System.out.println("answer: " + findIntegerString("     --2147483648asdf4321 +12a") + ", '     --2147483648asdf4321 +12a'");
        System.out.println("answer: " + findIntegerString("     -2147483649asdf4321 +12a") + ", '     -2147483649asdf4321 +12a'");
        System.out.println("answer: " + findIntegerString("     2147483647asdf4321 +12a") + ", '     2147483647asdf4321 +12a'");
        System.out.println("answer: " + findIntegerString("     2147483648asdf -55555 +12a") + ", '     2147483648asdf -55555 +12a'");
        System.out.println("answer: " + findIntegerString("     abc123 ") + ", '     abc123!! '");
        System.out.println("answer: " + findIntegerString("     -abc123 ") + ", '     -abc123 '");
        System.out.println("answer: " + findIntegerString("     +abc123 ") + ", '     +abc123 '");
        System.out.println("answer: " + findIntegerString("     -123!! ") + ", '     -123!! '");
        System.out.println("answer: " + findIntegerString("     +123abc ") + ", '     +123abc '");
        System.out.println("answer: " + findIntegerString("     +123   abc ") + ", '     +123   abc '");
        System.out.println("answer: " + findIntegerString("     +++12") + ", '     +++12'");
        System.out.println("answer: " + findIntegerString("     ---12") + ", '     ---12'");
        System.out.println("answer: " + findIntegerString("111 222 333") + ", '111 222 333'");
        System.out.println("answer: " + findIntegerString("-15a") + ", '-15a'");
        System.out.println("answer: " + findIntegerString("+15 a") + ", '+15 a'");
        System.out.println("answer: " + findIntegerString("") + ", ''");
        System.out.println("answer: " + findIntegerString("      ") + ", '      '");
        System.out.println("answer: " + findIntegerString("      a") + ", '      a'");
        System.out.println("answer: " + findIntegerString("      -a") + ", '      -a'");
        System.out.println("answer: " + findIntegerString("      -0") + ", '      -0'");
        System.out.println("answer: " + findIntegerString("      +0") + ", '      +0'");
        System.out.println("answer: " + findIntegerString("      0") + ", '      0'");
    }

    /**
     * returns 0 if no valid integers exists in first digit sequence in string or if no valid 32bit int found
     */
    public Integer findIntegerString(String str) {
        Pattern pattern = Pattern.compile(DIGIT_REGEXP);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            try {
                return (Integer.valueOf(matcher.group(1)));
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        return 0;
    }
}
