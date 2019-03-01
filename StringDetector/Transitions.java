public class Transitions {

    private Integer startState;
    private Integer finalState;
    private char lentSymbol;

    public Transitions(Integer start, char symbol, Integer finish) {
        this.finalState = finish;
        this.startState = start;
        this.lentSymbol = symbol;
    }
    public int getStartState() {
        return startState;
    }

    public void setStartState(int startState) {
        this.startState = startState;
    }

    public int getFinalState() {
        return finalState;
    }

    public void setFinalState(int finalState) {
        this.finalState = finalState;
    }

    public char getLentSymbol() {
        return lentSymbol;
    }

    public void setLentSymbol(char lentSymbol) {
        this.lentSymbol = lentSymbol;
    }

}
