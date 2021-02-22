package student.adventure;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String instructions;
    private String winningRoom, losingRoom;
    private Room[] rooms;
    private List<Item> inventory = new ArrayList<>();
    private Room currentRoom;


    /**
     * @return the instructions for this game.
     */
    public String getInstructions() { return instructions; }

    /**
     * @return the name of the room the user starts in.
     */
    public Room getCurrentRoom() { return currentRoom; }

    /**
     * @param setCurrentRoom sets the room that is currently in.
     */
    public void setCurrentRoom(Room setCurrentRoom) { currentRoom = setCurrentRoom; }

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

    /**
     * @return the inventory for this game.
     */
    public List<Item> getInventory() { return inventory; }

}
