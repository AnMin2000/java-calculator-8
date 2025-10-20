package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIMITERS = ",|:"; // 기본 구분자

    public static int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String numbers = input;
        String delimiter = DEFAULT_DELIMITERS;

        // 커스텀 구분자 패턴 검사: //;\n1,2,3 형태
        Matcher matcher = Pattern.compile("//(.)\\\\n(.*)").matcher(input);
        if (matcher.matches()) {
            delimiter = Pattern.quote(matcher.group(1)); // 특수문자 대응
            numbers = matcher.group(2);
        }

        String[] tokens = numbers.split(delimiter);
        int sum = 0;
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            int number = parseNumber(token);
            validateNonNegative(number);
            sum += number;
        }

        return sum;
    }

    private static int parseNumber(String token) {
        try {
            return Integer.parseInt(token.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 숫자 형식입니다: " + token);
        }
    }

    private static void validateNonNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
        }
    }
}
