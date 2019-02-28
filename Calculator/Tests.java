import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class Tests {

    @Test
    public void spaceseExpressionTest() {
        StringReader reader = new StringReader("   105 /    10");
        Lexer lexer = new Lexer(reader);
        Lexeme lexeme1 = new Lexeme("105", LexemeType.NUM);
        Lexeme lexeme2 = new Lexeme("/", LexemeType.DIVISION);
        Lexeme lexeme3 = new Lexeme("10", LexemeType.NUM);

        List<String> real = new ArrayList<String>();
        while (lexer.getCurrent() != -1) {
            try {
                Lexeme lex = lexer.getLexeme();
                real.add(lex.getLexem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<String> expected = new ArrayList<String>();
        expected.add(lexeme1.getLexem());
        expected.add(lexeme2.getLexem());
        expected.add(lexeme3.getLexem());
        Assert.assertEquals(expected, real);
    }

    @Test
    public void incorrectLexemeTest() {
        Throwable caught = null;
        try {
            StringReader reader = new StringReader("2+3^2*w");
            Lexer lexer = new Lexer(reader);
            List<String> real = new ArrayList<String>();
            while (lexer.getCurrent() != -1) {
                Lexeme lexeme = lexer.getLexeme();
                real.add(lexeme.getLexem());
            }
        } catch (Exception e) {
            caught = e;
        }
    assertNotNull(caught);
    }

    @Test
    public void lontLexemeTest() {
        StringReader reader = new StringReader("2*/43^2()+-1");
        Lexer lexer = new Lexer(reader);
        Lexeme lexeme1 = new Lexeme("2", LexemeType.NUM);
        Lexeme lexeme2 = new Lexeme("*", LexemeType.MULTY);
        Lexeme lexeme3 = new Lexeme("/", LexemeType.DIVISION);
        Lexeme lexeme4 = new Lexeme("43", LexemeType.NUM);
        Lexeme lexeme5 = new Lexeme("^", LexemeType.POWER);
        Lexeme lexeme6 = new Lexeme("2", LexemeType.NUM);
        Lexeme lexeme7 = new Lexeme("(", LexemeType.OPEN);
        Lexeme lexeme8 = new Lexeme(")", LexemeType.CLOSE);
        Lexeme lexeme9 = new Lexeme("+", LexemeType.PLUS);
        Lexeme lexeme10 = new Lexeme("-", LexemeType.MINUS);
        Lexeme lexeme11 = new Lexeme("1", LexemeType.NUM);

        List<String> expected = new ArrayList<String>();
        expected.add(lexeme1.getLexem());
        expected.add(lexeme2.getLexem());
        expected.add(lexeme3.getLexem());
        expected.add(lexeme4.getLexem());
        expected.add(lexeme5.getLexem());
        expected.add(lexeme6.getLexem());
        expected.add(lexeme7.getLexem());
        expected.add(lexeme8.getLexem());
        expected.add(lexeme9.getLexem());
        expected.add(lexeme10.getLexem());
        expected.add(lexeme11.getLexem());

        List<String> real = new ArrayList<String>();
        while (lexer.getCurrent() != -1) {
            Lexeme lex = null;
            try {
                lex = lexer.getLexeme();
            } catch (Exception e) {
                e.printStackTrace();
            }
            real.add(lex.getLexem());
        }
        Assert.assertEquals(expected, real);
    }
}
