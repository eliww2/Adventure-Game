package student.adventure;

import java.util.Locale;

public class Methods {

    public static void whereIsUser(Room currentRoom) {
        System.out.println("\n" + currentRoom.getDescription());

        System.out.print("From here, you can go: ");
        for (Direction nextDirection : currentRoom.getDirections()) {
            System.out.print(nextDirection.getDirectionName() + " ");
        }
        System.out.print("\nItems visible: ");
        for (Item nextItem : currentRoom.getItems()) {
            System.out.print(nextItem.getItemName() + " ");
        }
        System.out.print("\n\n>");

    }

    public static Room updateRoom(String userRequest, Room currentRoom, Game game) {

        for (Direction nextDirection : currentRoom.getDirections()) {
            if (nextDirection.getDirectionName().equalsIgnoreCase(userRequest)) {
                for (Room nextRoom : game.getRooms()) {
                    if (nextRoom.getName().equals(nextDirection.getRoomName())) {
                        return nextRoom;
                    }
                }
            }
        }

        System.out.println("\nSorry, that's not a room :(");
        return currentRoom;
    }


}
