package student.server;

import com.google.gson.Gson;
import student.adventure.*;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class IllinoisAdventureService implements AdventureService {

    private Map<Integer, Game> gameInstance = new HashMap<>();
    private int gameId = 0;
    private Gson gson = new Gson();
    private Reader jsonReader;
    private final String[] gameActions = {"Take", "Drop", "Use", "Go", "Exit", "Examine"};

    public IllinoisAdventureService() {
        try {
            jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/illinois.json"));
        } catch (Exception e) {
            System.out.println("That wasn't good!");
        }
    }

    @Override
    public void reset() {
        gameInstance.clear();
    }

    @Override
    public int newGame() throws AdventureException {
        Game newGame = gson.fromJson(jsonReader, Game.class);
        newGame.setCurrentRoom(newGame.getRooms()[0]);
        gameInstance.put(gameId, newGame);
        int thisGame = gameId;
        gameId++;
        return thisGame;
    }

    @Override
    public GameStatus getGame(int id) {
        Game currentGame = gameInstance.get(id);
        Room currentRoom = currentGame.getCurrentRoom();
        AdventureState state = new AdventureState();
        Map<String, List<String>> commandOptions = new HashMap<>();

        //populates the command options map
        for (String nextString : gameActions) {
            List<String> commands = new ArrayList<>();
            if (nextString.equalsIgnoreCase("take")) {
                for (Item nextItem : currentRoom.getItems()) {
                    commands.add(nextItem.getItemName());
                }
            } else if (nextString.equalsIgnoreCase("drop") || nextString.equalsIgnoreCase("use")) {
                for (Item nextItem : currentGame.getInventory()) {
                    commands.add(nextItem.getItemName());
                }
            } else if (nextString.equalsIgnoreCase("go")) {
                for (Direction nextDirection : currentRoom.getDirections()) {
                    commands.add(nextDirection.getDirectionName());
                }
            }
            commandOptions.put(nextString, commands);
        }

        return new GameStatus(
                false, id, ChangeState.whereIsUser(currentGame),
                currentRoom.getImageUrl(), currentRoom.getVideoUrl(),
                state, commandOptions
        );
    }

    @Override
    public boolean destroyGame(int id) {
        gameInstance.remove(id);
        return true;
    }

    @Override
    public void executeCommand(int id, Command command) {
        if (command.getCommandName().equalsIgnoreCase("go")) {
            ChangeState.updateRoom(command.getCommandValue(), gameInstance.get(id));
        } else if (command.getCommandName().equalsIgnoreCase("take")) {
            ChangeState.addToInventory(command.getCommandValue(), gameInstance.get(id));
        } else if (command.getCommandName().equalsIgnoreCase("drop")) {
            ChangeState.removeFromInventory(command.getCommandValue(), gameInstance.get(id));
        } else if (command.getCommandName().equalsIgnoreCase("use")) {
            ChangeState.useItem(command.getCommandValue(), gameInstance.get(id));
        }
    }

    @Override
    public SortedMap<String, Integer> fetchLeaderboard() {
        return null;
    }
}
