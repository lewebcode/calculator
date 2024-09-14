import java.util.Scanner;

public class Calculator {
    private static double result = 0;
    private static boolean isRunning = false;
    private static String mathOperation = "";
    private static Scanner scanner;

    public static void main(String[] args) {
        Calculator.scanner = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while (true) {
            double numberFirst = result;

            if (!isRunning) {
                System.out.println("Введите первое число:");
                numberFirst = scanner.nextDouble();
            }

            System.out.println("Введите математическую операцию: +, -, *, /\nДля сброса результата введите C\nДля завершения программы введите s");

            if (!getOperation(scanner.next())) {
                isRunning = false;
                result = 0;
                System.out.println("----------------");
                System.out.println("Очищаем значения");
                System.out.println("----------------");
                continue;
            }

            System.out.println("Введите второе число:");
            double numberSecond = scanner.nextDouble();

            result = calculate(numberFirst, numberSecond, scanner);

            System.out.print("Результат: ");
            System.out.println(result);
            isRunning = true;
        }
    }

    private static boolean getOperation(String command) {
        switch (command) {
            case "+":
            case "-":
            case "*":
            case "/":
                mathOperation = command;
                return true;
            case "C":
                return false;
            case "s":
                stopProgram();
                return false;
            default:
                System.out.println("Указана неверная математическая операция");
                System.out.println("Допустимые математические операции: +, -, *, /");
                return getOperation(Calculator.scanner.next());
        }
    }

    private static double calculate(double numberFirst, double numberSecond, Scanner scanner) {
        return switch (mathOperation) {
            case "+" -> numberFirst + numberSecond;
            case "-" -> numberFirst - numberSecond;
            case "*" -> numberFirst * numberSecond;
            case "/" -> {
                while (numberSecond == 0) {
                    System.out.println("Деление на ноль невозможно.");
                    System.out.println("Введите второе число заново");
                    numberSecond = scanner.nextDouble();
                }
                yield numberFirst / numberSecond;
            }
            default -> 0;
        };
    }

    private static void stopProgram() {
        System.out.println("Завершаем работу.");
        System.exit(0);
    }
}
