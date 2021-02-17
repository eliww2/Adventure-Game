package student.adventure;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name, description;
    private Direction[] directions;
    private List<Item> items = new ArrayList<>();

    public String getName() { return name; }

    public String getDescription() { return description; }

    public Direction[] getDirections() { return directions; }

    public List<Item> getItems() { return items; }
}
