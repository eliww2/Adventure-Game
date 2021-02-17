package student.adventure;

import java.util.List;
import java.util.Scanner;

public class Game {
    private String instructions;
    private String startingRoom, winningRoom, losingRoom;
    private Room[] rooms;

    public String getInstructions() { return instructions; }

    public String getStartingRoom() { return startingRoom; }

    public String getWinningRoom() { return winningRoom; }

    public String getLosingRoom() { return losingRoom; }

    public Room[] getRooms() { return rooms; }

}
