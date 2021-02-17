package student.adventure;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class changeState {

    public static void whereIsUser(Room currentRoom, List<Item> inventory) {
        System.out.println("\n" + currentRoom.getDescription());

        System.out.print("From here, you can go: ");
        for (Direction nextDirection : currentRoom.getDirections()) {
            System.out.print(nextDirection.getDirectionName() + ", ");
        }
        System.out.print("\nItems visible: ");
        for (Item nextItem : currentRoom.getItems()) {
            System.out.print(nextItem.getItemName() + ", ");
        }

        System.out.print("\nYou're Items are: ");
        for (Item nextItem : inventory) {
            System.out.print(nextItem.getItemName() + ", ");
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

        System.out.println("\nI can't go" + userRequest);
        return currentRoom;
    }

    public static Item addToInventory(String userRequest, Room currentRoom) {
        int itemIndex = 0;
        for (Item nextItem : currentRoom.getItems()) {

            if (nextItem.getItemName().equalsIgnoreCase(userRequest)) {
                currentRoom.getItems().remove(itemIndex);
                return nextItem;
            }
            itemIndex++;
        }
        return null;
    }

    public static Item removeFromInventory(String userRequest, Room currentRoom, List<Item> inventory) {

        for (Item nextItem : inventory) {
            if (nextItem.getItemName().equalsIgnoreCase(userRequest)) {
                currentRoom.getItems().add(nextItem);
                return nextItem;
            }
        }
        return null;
    }

    public static boolean checkForEnd(Game currentGame, Room currentRoom) {
        if (currentRoom.getName().equals(currentGame.getWinningRoom())) {
            System.out.println("Congratulations You Won!");
            return false;
        } else if (currentRoom.getName().equals(currentGame.getLosingRoom())) {
            System.out.println(currentRoom.getDescription());
            return false;
        }
        return true;
    }

}
