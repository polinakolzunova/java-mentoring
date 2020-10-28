import java.util.regex.*;

public class RomanDigit{

    public static boolean isRoman(String str){
        return Pattern.compile("^[VIXLCMD]+$").matcher(str).matches();
    }

    public static int convertToDecimal(String str) {
        int s1, s2, res = 0;
        for (int i = 0; i < str.length(); i++) {
            s1 = value(str.charAt(i));
            if (i + 1 < str.length()) {
                s2 = value(str.charAt(i + 1));
                if (s1 >= s2) {
                    res += s1;
                }
                else {
                    res += s2 - s1;
                    i++;
                }
            }
            else {
                res += s1;
            }
        }

        return res;
    }

    public static String convertToRoman(int number) {
        String result = "";

        if (number == 0) return "nulla";

        if (number < 0) {
            number *= -1;
            result += "-";
        }

        while (number != 0) {
            if (number >= 100) {
                if (number < 400) {
                    result = digit('C', number / 100, result);
                    number = number % 100;
                }
                else {
                    result = subDigit('C', 'D', result);
                    number = number % 100;
                }
            }
            else if (number >= 50) {
                if (number < 90) {
                    result = digit('L', number / 50, result);
                    number = number % 50;
                }
                else {
                    result = subDigit('X', 'C', result);
                    number = number % 10;
                }
            }
            else if (number >= 10) {
                if (number < 40) {
                    result = digit('X', number / 10, result);
                    number = number % 10;
                }
                else {
                    result = subDigit('X', 'L', result);
                    number = number % 10;
                }
            }
            else if (number >= 5) {
                if (number < 9) {
                    result = digit('V', number / 5, result);
                    number = number % 5;
                }
                else {
                    result = subDigit('I', 'X', result);
                    number = 0;
                }
            }
            else if (number >= 1) {
                if (number < 4) {
                    result = digit('I', number, result);
                    number = 0;
                }
                else {
                    result = subDigit('I', 'V', result);
                    number = 0;
                }
            }
        }

        return result;
    }

    private static int value(char r) {
        switch(r) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return -1;
        }
    }

    private static String subDigit(char num1, char num2, String result) {
        result += num1;
        result += num2;

        return result;
    }

    private static String digit(char ch, int n, String result) {
        for (int j = 0; j < n; j++) {
            result += ch;
        }

        return result;
    }
}