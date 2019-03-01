import java.io.File;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid input");
        }
        File automatSettings = new File(args[0]);
        File inputFile = new File(args[1]);

        Automat automat = new Automat(automatSettings);
        automat.setup();
        automat.work(inputFile);
    }
}
