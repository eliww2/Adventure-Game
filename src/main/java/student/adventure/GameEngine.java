package student.adventure;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GameEngine {

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


        while (running) {
            Methods.whereIsUser(currentRoom);

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
                currentRoom = Methods.updateRoom(userRequest, currentRoom, currentGame);
            } else if (userCommand.equalsIgnoreCase("take")) {
                //Methods.updateInventory();
            } else if (userCommand.equalsIgnoreCase("drop")) {
                //  Methods.updateInventory();
            } else if (userCommand.equalsIgnoreCase("use")) {
                //Methods.updateItem()
            } else {
                System.out.println("\nCommand not found!!!");
            }

            //Checks if the user is either in the winning room or losing win
            // move to separate method
            if (currentRoom.getName().equals(currentGame.getWinningRoom())) {
                System.out.println("Congratulations You Won!");
                running = false;
            } else if (currentRoom.getName().equals(currentGame.getLosingRoom())) {
                System.out.println(currentRoom.getDescription());
                running = false;
            }

        }
    }
}
