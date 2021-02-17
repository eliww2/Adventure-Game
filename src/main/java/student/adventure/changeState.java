package student.adventure;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class changeState {

    /**
     * Prints out the players surroundings each round
     *
     * @param currentRoom takes the room so it displays the right info.
     * @param inventory takes the inventory so it displays the right info.
     */
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

    /**
     * Takes the place the user wants to go and changes there room to it if it's valid
     *
     * @param userRequest the direction the user wants to go
     * @param currentRoom the current room
     * @param game the current game being ran
     * @return returns the new current room
     */
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

    /**
     * Moves item in a room to the users inventory.
     * @param userRequest what the user wants to take.
     * @param currentRoom what room the user is in.
     * @return the item to be added to the inventory.
     */
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

    /**
     * Moves item in the users inventory to the room
     *
     * @param userRequest what item the user wants to drop.
     * @param currentRoom what room the user is
     * @param inventory the inventory of the user
     * @return the item that will be removed.
     */
    public static Item removeFromInventory(String userRequest, Room currentRoom, List<Item> inventory) {

        for (Item nextItem : inventory) {
            if (nextItem.getItemName().equalsIgnoreCase(userRequest)) {
                currentRoom.getItems().add(nextItem);
                return nextItem;
            }
        }
        return null;
    }

    /**
     * Takes the game and the room and checks if the user is in a winning or losing room.
     *
     * @param currentGame the current game.
     * @param currentRoom the users current room.
     * @return a boolean that updates if the game loop should still run.
     */
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
