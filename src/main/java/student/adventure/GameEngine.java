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
        //String currentRoom = currentGame.getStartingRoom();
        //String roomDescription = currentGame.getRooms()[0].getDescription();

        while (running) {
            /*
            System.out.println("You are now i");
            System.out.print("\n>");
            String userInput = in.nextLine();

            /*
            System.out.println(userInput);
            if (userInput.equals("poop")) {
                running = false;
            } */
        }
    }
}
