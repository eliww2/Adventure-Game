package student.adventure;

import java.util.List;
import java.util.Scanner;

public class Game {
    private String instructions;
    private String startingRoom, winningRoom, losingRoom;
    private Room[] rooms;

    /**
     * @return the instructions for this game.
     */
    public String getInstructions() { return instructions; }

    /**
     * @return the name of the room the user starts in.
     */
    public String getStartingRoom() { return startingRoom; }

    /**
     * @return the name of the room the user needs to enter to win.
     */
    public String getWinningRoom() { return winningRoom; }

    /**
     * @return the name of the room the user needs to enter to lose.
     */
    public String getLosingRoom() { return losingRoom; }

    /**
     * @return the rooms available in this game.
     */
    public Room[] getRooms() { return rooms; }

}
