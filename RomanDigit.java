import java.util.regex.*;

public class RomanDigit{

    public static boolean isRoman(String str){
        return Pattern.compile("(?i)^[VIXLCMD]+$").matcher(str).matches();
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
        char c[] = new char[10];
        int i = 0;
        while (number != 0) {
            if (number >= 10) {
                if (number < 40) {
                    i = digit('X', number / 10, i, c);
                    number = number % 10;
                }
                else {
                    i = subDigit('X', 'L', i, c);
                    number = number % 10;
                }
            }
            else if (number >= 5) {
                if (number < 9) {
                    i = digit('V', number / 5, i, c);
                    number = number % 5;
                }
                else {
                    i = subDigit('I', 'X', i, c);
                    number = 0;
                }
            }
            else if (number >= 1) {
                if (number < 4) {
                    i = digit('I', number, i, c);
                    number = 0;
                }
                else {
                    i = subDigit('I', 'V', i, c);
                    number = 0;
                }
            }
        }

        return new String(c);
    }

    private static int value(char r) {
        switch(r) {
            case 'I': case 'i': return 1;
            case 'V': case 'v': return 5;
            case 'X': case 'x': return 10;
            case 'L': case 'l': return 50;
            case 'C': case 'c': return 100;
            case 'D': case 'd': return 500;
            case 'M': case 'm': return 1000;
            default: return -1;
        }
    }

    private static int subDigit(char num1, char num2, int i, char[] c) {
        c[i++] = num1;
        c[i++] = num2;
        return i;
    }

    private static int digit(char ch, int n, int i, char[] c) {
        for (int j = 0; j < n; j++) {
            c[i++] = ch;
        }
        return i;
    }
}