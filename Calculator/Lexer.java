import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private Reader reader;
    private int current;

    public Lexer(Reader reader) {
        this.reader = reader;
        try {
            this.current = this.reader.read();
        } catch (IOException e) {
            System.out.println("error (Lexeme) in reader.read");
        }
    }

    public Lexeme getLexeme() throws Exception {
        StringBuilder buffer = new StringBuilder();
        while (current == 32) {
            try {
               current = reader.read();
            } catch (IOException e) {
                System.out.println("error (getLexeme) in reader.read");
            }
        }
        switch (current) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                while (current >= '0' && current <= '9') {
                    buffer.append((char) current);
                    current = reader.read();
                }
                return new Lexeme(new String(buffer), LexemeType.NUM);
            case '(':
                current = reader.read();
                return new Lexeme(Parser.OPEN, LexemeType.OPEN);
            case ')':
                current = reader.read();
                return new Lexeme(Parser.CLOSE, LexemeType.CLOSE);
            case '+':
                current = reader.read();
                return new Lexeme(Parser.PLUS, LexemeType.PLUS);
            case '-':
                current = reader.read();
                return new Lexeme(Parser.MINUS, LexemeType.MINUS);
            case '*':
                current = reader.read();
                return new Lexeme(Parser.MULTY, LexemeType.MULTY);
            case '/':
                current = reader.read();
                return new Lexeme(Parser.DIVISION, LexemeType.DIVISION);
            case '^':
                current = reader.read();
                return new Lexeme(Parser.POWER, LexemeType.POWER);
            case -1:
                current = reader.read();
                return new Lexeme(new String("EOF"), LexemeType.EOF);
            default:
                throw new Exception("Exception in Lexer default");
        }
    }

    public int getCurrent() {
        return current;
    }
}
