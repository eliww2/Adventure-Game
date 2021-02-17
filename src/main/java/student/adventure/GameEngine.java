package student.adventure;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
        boolean running = true;
        Scanner in = new Scanner(System.in);

        //variables specific to the game
        System.out.println(currentGame.getInstructions());
        Room currentRoom = currentGame.getRooms()[0];
        List<Item> inventory = new ArrayList<>();

        while (running) {
            changeState.whereIsUser(currentRoom, inventory);

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
                currentRoom = changeState.updateRoom(userRequest, currentRoom, currentGame);

            } else if (userCommand.equalsIgnoreCase("take")) {

               Item toAdd = changeState.addToInventory(userRequest, currentRoom);
               if (toAdd == null) {
                   System.out.println("There is no " + userRequest + "in the room");
               } else {
                   inventory.add(toAdd);
               }

            } else if (userCommand.equalsIgnoreCase("drop")) {

                Item toRemove = changeState.removeFromInventory(userRequest, currentRoom, inventory);
                  if (toRemove == null) {
                      System.out.println("You don't have" + userRequest);
                  }
                inventory.remove(toRemove);

            } else {
                System.out.println("\nI don't understand \"" + userInput + "\"");
            }

            running = changeState.checkForEnd(currentGame, currentRoom);
        }
    }
}
