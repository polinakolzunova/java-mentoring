import java.util.regex.*;

public class Expression {

    private int a;
    private int b;
    private int result;
    private boolean isRoman;
    private String operation;

    Expression(String str) throws Exception {
        this.disassemble(str);
        this.check();
    }

    private void check() throws Exception {
        if(!(this.a >=1 && this.a <= 10) || !(this.b >=1 && this.b <= 10))
            throw new Exception("Both numbers must be between 1 and 10");
    }

    private void disassemble(String str) throws Exception {
        Matcher match = Pattern.compile("^(\\w+)\\s*(\\*|-|\\+|\\/)\\s*(\\w+)$").matcher(str);

        if (match.find()) {

            boolean isARoman = RomanDigit.isRoman(match.group(1)),
                    isBRoman = RomanDigit.isRoman(match.group(3));

            if(isARoman != isBRoman)
                throw new Exception("Both numbers must be either Arabic or Roman at the same time");

            this.operation = match.group(2);
            this.isRoman = (isARoman && isBRoman) ? true : false;
            this.a = (this.isRoman) ? RomanDigit.convertToDecimal(match.group(1)) : Integer.parseInt(match.group(1));
            this.b = (this.isRoman) ? RomanDigit.convertToDecimal(match.group(3)) : Integer.parseInt(match.group(3));
        } else {
            throw new Exception("The incoming string does not match the expected format");
        }
    }

    private void calculate(){
        this.result = this.a;

        if(this.operation.equals("+")) this.result += this.b;
        else if(this.operation.equals("-")) this.result -= this.b;
        else if(this.operation.equals("*")) this.result *= this.b;
        else if(this.operation.equals("/")) this.result /= this.b;
    }

    public String getResult(){
        this.calculate();

        return (this.isRoman) ? RomanDigit.convertToRoman(this.result) : Integer.toString(this.result);
    }
}