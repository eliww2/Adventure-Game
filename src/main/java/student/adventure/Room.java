package student.adventure;

public class Room {
    private String name, description;
    private Direction[] directions;
    private Item[] items;

    public String getName() { return name; }

    public String getDescription() { return description; }

    public Direction[] getDirections() { return directions; }

    public Item[] getItems() { return items; }
}
