import java.util.Scanner;

public class App {

    public static void main(String[] args){

        try{
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            Expression exp = new Expression(input);
            System.out.println(exp.getResult());
            in.close();
        }
        catch (Exception ex){
            System.out.printf("Exception was throw!\n%s", ex.getMessage());
            //ex.printStackTrace();
        }

    }

}