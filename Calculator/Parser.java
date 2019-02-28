public class Parser {

    private Lexer lexer;
    private Lexeme current;

    static String MINUS = "-";
    static String PLUS = "+";
    static String MULTY = "*";
    static String DIVISION = "/";
    static String OPEN = "(";
    static String CLOSE = ")";
    static String POWER = "^";
    static String NUM = "num";
    static String EOF = "eof";

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        try {
            current = this.lexer.getLexeme();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int parseExpression() throws Exception {
        int term = parseTerm();
        while (current.getType() == LexemeType.MINUS || current.getType() == LexemeType.PLUS) {
            if (current.getType() == LexemeType.PLUS) {
                current = lexer.getLexeme();
                term += parseTerm();
            }
            if (current.getType() == LexemeType.MINUS) {
                current = lexer.getLexeme();
                term -= parseTerm();
            }
        }
        return term;
    }

    public int parseTerm() throws Exception {
        int factor = parseFactor();
        while (current.getType() == LexemeType.DIVISION || current.getType() == LexemeType.MULTY) {
            if (current.getType() == LexemeType.DIVISION) {
                current = lexer.getLexeme();
                factor /= parseFactor();
            }
            if (current.getType() == LexemeType.MULTY) {
                current = lexer.getLexeme();
                factor *= parseFactor();
            }
        }
        return factor;
    }

    public int parseFactor() throws Exception {
        int power = parsePower();
        if (current.getType() == LexemeType.POWER) {
            current = lexer.getLexeme();
            power = (int) Math.pow(power, parseFactor());
        }
        return power;
    }

    public int parsePower() throws Exception {
        if (current.getType() == LexemeType.MINUS) {
            current = lexer.getLexeme();
            return -parseAtom();
        }
        return parseAtom();
    }

    public int parseAtom() throws Exception {
        if (current.getType() == LexemeType.NUM) {
            int tmp = Integer.parseInt(current.getLexem());
            current = lexer.getLexeme();
            return tmp;
        }
        if (current.getType() == LexemeType.OPEN) {
            current = lexer.getLexeme();
            int tmp = parseExpression();
            if (current.getType() != LexemeType.CLOSE) {
                throw new Exception("Exception in parseAtom!");
            } else {
                current = lexer.getLexeme();
                return tmp;
            }
        }

        throw new Exception("Error in Parser((");
    }
}
