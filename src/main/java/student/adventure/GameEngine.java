package student.adventure;

import com.google.gson.Gson;

import javax.imageio.IIOException;
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

        System.out.println(currentGame.getInstructions());
        Room currentRoom = currentGame.getRooms()[0];

        while (running) {
            Methods.whereIsUser(currentRoom);

            String userInput = in.nextLine().trim();

            if (userInput.split(" ")[0].equalsIgnoreCase("quit")) {
                System.out.println("GoodBye :)");
                System.exit(0);
            } else if (userInput.split(" ")[0].equalsIgnoreCase("examine")) {
                continue;
            } else if (userInput.split(" ")[0].equalsIgnoreCase("go")) {
                Methods.updateRoom();
            } else if (userInput.split(" ")[0].equalsIgnoreCase("take")) {
                //Methods.updateInventory();
            } else if (userInput.split(" ")[0].equalsIgnoreCase("drop")) {
                //  Methods.updateInventory();
            } else if (userInput.split(" ")[0].equalsIgnoreCase("use")) {
                //Methods.updateItem()
            } else {
                System.out.println("Command not found!!!");
            }
        }
    }
}
