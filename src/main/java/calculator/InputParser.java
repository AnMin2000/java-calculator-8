package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    private static final String DEFAULT_DELIMITERS = ",|:"; // 기본 구분자

    private InputParser() {}

    public static List<Integer> parse(String input) {
        String delimiter = DEFAULT_DELIMITERS;
        String numbers = input;

        if (input.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.)\\n(.*)").matcher(input);
            if (matcher.matches()) {
                delimiter = Pattern.quote(matcher.group(1));
                numbers = matcher.group(2);
            } else {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }
        }

        return splitToNumbers(numbers, delimiter);
    }

    private static List<Integer> splitToNumbers(String input, String delimiter) {
        String[] tokens = input.split(delimiter);
        List<Integer> result = new ArrayList<>();

        for (String token : tokens) {
            if (token.isBlank()) continue;
            try {
                result.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
            }
        }

        return result;
    }
}
