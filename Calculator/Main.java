import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //BufferedReader consoleReader = new BufferedReader(inputStreamReader);
        BufferedReader fileReader = new BufferedReader(new FileReader("C:\\Users\\crysn\\Desktop\\expr.txt"));
        Lexer lexer = new Lexer(fileReader);
        Parser parser = new Parser(lexer);
        try {
            System.out.println("" + parser.parseExpression());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
