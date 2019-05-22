import main.twitter.infrastructure.FileReaders;

public class MainClass {

    public static void main(String[] args) {
        new FileReaders().start(args[0], args[1]);
    }
}

