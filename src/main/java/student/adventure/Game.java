package student.adventure;

import java.util.Scanner;

public class Game {
    private String instructions;
    private String startingRoom, endingRoom;
    private Room[] rooms;

    public String getInstructions() { return instructions; }

    public String getStartingRoom() { return startingRoom; }

    public String getEndingRoom() { return endingRoom; }

    public Room[] getRooms() { return rooms; }

}
