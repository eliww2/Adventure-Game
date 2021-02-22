package student.adventure;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GameEngine {

    /**
     * @param gameFile takes the game file in json and runs it.
     * @throws IOException if json can't be processed.
     */
    public static void startGame(String gameFile) throws IOException {
        //initialize game
        Gson gson = new Gson();
        Reader jsonReader = Files.newBufferedReader(Paths.get(gameFile));
        Game currentGame = gson.fromJson(jsonReader, Game.class);
        currentGame.setCurrentRoom(currentGame.getRooms()[0]);
        boolean running = true;
        Scanner in = new Scanner(System.in);

        System.out.println(currentGame.getInstructions());
        Room currentRoom = currentGame.getRooms()[0];

        while (running) {
            ChangeState.whereIsUser(currentGame);

            //Strings for parsing the user input
            String userInput = in.nextLine().trim();
            String[] userInputParts = userInput.split(" ");
            String userCommand = userInputParts[0];
            String userRequest = userInputParts[userInputParts.length - 1];

            if (userCommand.equalsIgnoreCase("quit")) {
                System.out.println("GoodBye :)");
                System.exit(0);

            } else if (userCommand.equalsIgnoreCase("examine")) {
                continue;
            } else if (userCommand.equalsIgnoreCase("go")) {
                ChangeState.updateRoom(userRequest, currentGame);
            } else if (userCommand.equalsIgnoreCase("take")) {
                ChangeState.addToInventory(userRequest, currentGame);
            } else if (userCommand.equalsIgnoreCase("drop")) {
                ChangeState.removeFromInventory(userRequest, currentGame);
            } else if (userCommand.equalsIgnoreCase("use")) {
                ChangeState.useItem(userRequest, currentGame);
            } else {
                System.out.println("\nI don't understand \"" + userInput + "\"");
            }

            running = ChangeState.checkForEnd(currentGame);
        }
    }
}
