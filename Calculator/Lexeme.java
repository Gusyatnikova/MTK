public class Lexeme {
    private String lexem;
    private LexemeType type;

    public Lexeme(String lexem, LexemeType type) {
        this.type = type;
        this.lexem = lexem;
    }

    public String getLexem() {
        return lexem;
    }

    public LexemeType getType() {
        return type;
    }
}
