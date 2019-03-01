import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.animation.Transition;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Automat {
    private File confFile;
    ArrayList<Integer> finalStates;
    ArrayList<Transitions> transitions;


    public Automat(File file) {
        this.confFile = file;
        finalStates = new ArrayList<Integer>();
        transitions = new ArrayList<Transitions>();
    }

    public boolean setup() {
        boolean success = true;
        try {
            BufferedReader settingsReader = new BufferedReader(new FileReader(this.confFile));
            String currLine;

            while ((currLine = settingsReader.readLine()) != null) {
                String splittedString[];
                splittedString = currLine.split(" ");
                if (finalStates.isEmpty()) {
                    for (int i = 0; i < splittedString.length; i++) {
                        finalStates.add(Integer.parseInt(splittedString[i]));
                    }
                    continue;
                }
                if (splittedString[0].isEmpty() || splittedString[1].isEmpty() || splittedString[2].isEmpty()) {
                    System.out.println("missed argument in config file");
                    return false;
                }
                transitions.add(new Transitions(Integer.parseInt(splittedString[0]), splittedString[1].toCharArray()[0], Integer.parseInt(splittedString[2])));
            }
            settingsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("set a config file for automate;");
            return false;
        } catch (IOException e) {
            System.out.println("reading from settings file was interrupted");
            return false;
        }
        return success;
    }

    int getNextState(int state, char symbol) {
        for (Transitions transition : transitions) {
            if (transition.getStartState() == state && transition.getLentSymbol() == symbol)
                return transition.getFinalState();
        }
        return -1;
    }


    public void work(File inputFile) {
        try {
            int state = 1;
            BufferedReader lineReader = new BufferedReader(new FileReader(inputFile));
            String inputLine = lineReader.readLine();
            if (inputLine.isEmpty()) {
                System.out.println("input file is Empty!");
                return;
            }
            char[] inToChar = inputLine.toCharArray();
            for (int i = 0; i < inToChar.length; i++) {
                state = getNextState(state, inToChar[i]);
                if (state == 0) {
                    System.out.println("Invalid Config((");
                    return;
                }
            }
            if (finalStates.contains(state)) {
                System.out.println("Final state was achieved");
            } else {
                System.out.println("Final state was NOT achieved");
            }
        } catch (FileNotFoundException e) {
            System.out.println("input file not found");
            return;
        } catch (IOException e) {
            System.out.println("cannot read input line!");
            return;
        }

    }
}
