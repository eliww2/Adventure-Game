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
    private final String[] nonStaticCommands = {"Take", "Go", "Drop", "Use"};

    public IllinoisAdventureService() {
        try {
            jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/illinois.json"));
        } catch (Exception e) { }
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

        AdventureState state = new AdventureState();

        Map<String, List<String>> commandOptions = new HashMap<>();

        //populates the command options map
        for (String nextString : nonStaticCommands) {
            List<String> commands = new ArrayList<>();
            if (nextString.equalsIgnoreCase("take")) {
                for (Item nextItem : currentGame.getCurrentRoom().getItems()) {
                    commands.add(nextItem.getItemName());
                }
            } else if (nextString.equalsIgnoreCase("drop") || nextString.equalsIgnoreCase("use")) {
                for (Item nextItem : currentGame.getInventory()) {
                    commands.add(nextItem.getItemName());
                }
            } else {
                for (Direction nextDirection : currentGame.getCurrentRoom().getDirections()) {
                    commands.add(nextDirection.getDirectionName());
                }
            }
            commandOptions.put(nextString, commands);
        }

        return new GameStatus(
                false, id, ChangeState.whereIsUser(currentGame),
                currentGame.getCurrentRoom().getImageUrl(), currentGame.getCurrentRoom().getVideoUrl(),
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

    }

    @Override
    public SortedMap<String, Integer> fetchLeaderboard() {
        return null;
    }
}
