package student.adventure;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name, description;
    private Direction[] directions;
    private List<Item> items = new ArrayList<>();

    /**
     * @return name of the room.
     */
    public String getName() { return name; }

    /**
     * @return the description of the room.
     */
    public String getDescription() { return description; }

    /**
     * @return the directions the user can go from this room.
     */
    public Direction[] getDirections() { return directions; }

    /**
     * @return a list of the items you can take in this room.
     */
    public List<Item> getItems() { return items; }
}
