package main.twitter.infrastructure;


import javafx.util.Pair;
import main.twitter.domain.TwitterComponent;
import main.twitter.error.InvalidTweetException;
import main.twitter.error.TwitterOperationException;
import main.twitter.error.UserException;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

public class FileReaders {

    private TwitterComponent twitter;

    private final String FOLLOWER_DELIMITER = "follows";
    private final String FOLLOWEE_DELIMITER = ",";
    private final String TWEET_DELIMITER = ">";

    public FileReaders() {
        twitter = new TwitterComponent();
    }

    public void start(final String usersFile, final String tweetsFile) {
        try {
            processUserNameFile(getInputFile(usersFile));
            processTweetFile(getInputFile(tweetsFile));
            twitter.generateTwitterFeed();
        } catch (UserException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (InvalidTweetException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (TwitterOperationException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private FileReader getInputFile(final String fileName) {
        FileReader fileReader = null;
        try {
            File file = new File(fileName);
            fileReader = new FileReader(file);
            if (!file.exists()) {
                throw new FileNotFoundException(fileName);
            }
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return fileReader;
    }

    private void processUserNameFile(final FileReader fileReader) throws UserException {
        try {
            BufferedReader reader = new BufferedReader(fileReader);
            String input_line = reader.readLine();

            while (input_line != null) {
                if (!input_line.isEmpty()) {
                    Pair<String, String> extracted_values = generatePair(input_line, FOLLOWER_DELIMITER);
                    twitter.addUser(extracted_values.getKey());

                    for (String follower : extracted_values.getValue().split(FOLLOWEE_DELIMITER)) {
                        twitter.addFollower(extracted_values.getKey(), follower.trim());
                    }
                }
                input_line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: ADD AN ERROR WHEN FILE IS EMPTY
    private void processTweetFile(final FileReader fileReader) throws UserException, InvalidTweetException, TwitterOperationException {
        try {
            BufferedReader reader = new BufferedReader(fileReader);
            String input_line = reader.readLine();

            while (input_line != null) {
                if (!input_line.isEmpty()) {
                    Pair<String, String> extracted_values = generatePair(input_line, TWEET_DELIMITER);
                    //TODO: ASSUMPTION THE USER-NAME NOT PART OF TWEET
                    //TODO: EXCEPTION WHEN TWEET > 140 CHAR
                    twitter.addTweet(
                            extracted_values.getKey(),
                            extracted_values.getValue()
                    );
                }
                input_line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pair<String, String> generatePair(final String inputLine, final String delimiter) {
        String result[] = inputLine.split(delimiter);
        return new Pair<String, String>(
                result[0].trim(),
                result[1].trim()
        );
    }
}