package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        try {
            String input = Console.readLine();
            int result = StringCalculator.calculate(input);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            throw e; // 테스트에서 예외 검증하므로 다시 던짐
        }
    }
}
