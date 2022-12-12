package util;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUIHelper {
    public static void clearScreen() {
       /* System.out.print("\033[H\033[2J");
        System.out.print("\033\143");
        System.out.flush();  não funciona*/
        System.out.println(System.lineSeparator().repeat(50));
    }

    public static String askSimpleInput(String message) {
        System.out.printf("%s%n# : ", message);
        return new Scanner(System.in).nextLine().trim();
    }

    public static String askNoEmptyInput(String message, int retries) {
        System.out.printf("%s%n# : ", message);
        Scanner sc = new Scanner(System.in);
        String input;
        int tries = 0;
        do {
            input = sc.nextLine().trim();
            tries++;
        } while (input.isBlank() && retries > 0 && tries < retries);
        return input;
    }

    public static int askChooseOption(String message, String... options) {
        System.out.printf("%s%n# : ", message);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d - %s%n# : ", i+1, options[i]);
        }
        Scanner sc = new Scanner(System.in);
        int choose;
        int cont = 0;
        do {
            try {
                choose = sc.nextInt()-1;
                cont++;
                if (cont == 3){
                    System.out.printf("%n%s%n# : ", message);
                    for (int i = 0; i < options.length; i++) {
                        System.out.printf("%d - %s%n# : ", i+1, options[i]);
                    }
                    cont = 0;
                }
            } catch (InputMismatchException e) {
                choose = -9;
            }
        } while (choose < 0 || choose >= options.length);
        return choose;
    }

    public static boolean askConfirm(String message, String yes, String no) {
        String[] op = new String[2];
        op[0] = yes;
        op[1] = no;
        //askChooseOption(message, op);
        return askChooseOption(message, op) == 0;
    }

    public static BigDecimal askNumberBigDecimal(String message) {
        System.out.printf("%s%n# : ", message);
        Scanner sc = new Scanner(System.in);
        BigDecimal number;
        do {
            try {
                number = sc.nextBigDecimal();
            } catch (InputMismatchException e) {
                number = null;
            }
        } while (number == null);
        return number;
    }
    public static int askNumberInt(String message) {
        System.out.printf("%s%n# : ", message);
        Scanner sc = new Scanner(System.in);
        int number;
        int cont = 0;
        do {
            try {
                number = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro! informe apenas numeros positivos.");
                cont++;
                if (cont == 5){
                    System.out.printf("%s%n# : ", message);
                    cont = 0;
                }
                number = -9;
            }
        } while (number < 0);
        return number;
    }

    public static int drawWithRightPadding(String text, int width, char pad) {
        int count = 0;
        do {
            int limit = Math.min(text.length(), width - 4);
            String row = text.substring(0, limit);
            text = text.substring(row.length());
            row = "# " + row + String.valueOf(pad).repeat(width - row.length() - 4) + " #";
            System.out.println(row);
            count++;
        } while (!text.isEmpty());
        return count;
    }

    public static int drawWithPadding(String text, int width) {
        int count = 0;
        do {
            int limit = Math.min(text.length(), width - 4);
            String row = text.substring(0, limit);
            text = text.substring(row.length());
            int padding = (width - row.length()) / 2;
            row = "#" + " ".repeat(padding-1) + row;
            row = row + " ".repeat(width - row.length() - 1) + "#";
            System.out.println(row);
            count++;
        } while (!text.isEmpty());
        return count;
    }

    public static void drawHeader(String title, int width) {
        drawLine(width);
        drawWithPadding(title, width);
        drawLine(width);
    }

    public static void drawLine(int width) {
        System.out.println("#".repeat(width));
    }

    public static void fillVSpace(int lines, int width) {
        drawWithPadding(" ".repeat(lines * width), width);
    }
}