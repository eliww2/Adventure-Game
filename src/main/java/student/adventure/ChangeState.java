package student.adventure;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class ChangeState {

    /**
     * Prints out the players surroundings each round
     *
     * @param currentGame takes the room so it displays the right info.
     */
    public static void whereIsUser(Game currentGame) {
        System.out.println("\n" + currentGame.getCurrentRoom().getDescription());

        System.out.print("From here, you can go: ");
        for (Direction nextDirection : currentGame.getCurrentRoom().getDirections()) {
            System.out.print(nextDirection.getDirectionName() + ", ");
        }

        System.out.print("\nItems visible: ");
        for (Item nextItem : currentGame.getCurrentRoom().getItems()) {
            System.out.print(nextItem.getItemName() + ", ");
        }

        System.out.print("\nYou're Items are: ");
        for (Item nextItem : currentGame.getInventory()) {
            System.out.print(nextItem.getItemName() + ", ");
        }
        System.out.print("\n\n>");

    }

    /**
     * Takes the place the user wants to go and changes there room to it if it's valid
     *
     * @param userRequest the direction the user wants to go
     * @param currentGame the current game being ran
     */
    public static void updateRoom(String userRequest, Game currentGame) {

        for (Direction nextDirection : currentGame.getCurrentRoom().getDirections()) {
            if (nextDirection.getDirectionName().equalsIgnoreCase(userRequest)) {
                for (Room nextRoom : currentGame.getRooms()) {
                    if (nextRoom.getName().equals(nextDirection.getRoomName())) {
                        if(!(nextRoom.getMaskRequired()) && !(nextRoom.getKeyRequired())) {
                           currentGame.setCurrentRoom(nextRoom);
                           break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Moves item in a room to the users inventory.
     * @param userRequest what the user wants to take.
     * @param currentGame the current game.
     */
    public static void addToInventory(String userRequest, Game currentGame) {
        for (Item nextItem : currentGame.getCurrentRoom().getItems()) {

            if (nextItem.getItemName().equalsIgnoreCase(userRequest)) {
                currentGame.getCurrentRoom().getItems().remove(nextItem);
                currentGame.getInventory().add(nextItem);
                break;
            }
        }
    }

    /**
     * Moves item in the users inventory to the room
     *
     * @param userRequest what item the user wants to drop.
     * @param currentGame the inventory of the user
     */
    public static void removeFromInventory(String userRequest, Game currentGame) {
        for (Item nextItem : currentGame.getInventory()) {
            if (nextItem.getItemName().equalsIgnoreCase(userRequest)) {
                currentGame.getCurrentRoom().getItems().add(nextItem);
                currentGame.getInventory().remove(nextItem);
                break;
            }
        }
    }

    /**
     * Takes the game and the room and checks if the user is in a winning or losing room.
     *
     * @param currentGame the current game.
     * @return a boolean that updates if the game loop should still run.
     */
    public static boolean checkForEnd(Game currentGame) {
        if (currentGame.getCurrentRoom().getName().equals(currentGame.getWinningRoom())) {
            System.out.println("Congratulations You Won!");
            return false;
        } else if (currentGame.getCurrentRoom().getName().equals(currentGame.getLosingRoom())) {
            System.out.println(currentGame.getCurrentRoom().getDescription());
            return false;
        }
        return true;
    }

    public static void useItem(String userRequest, Game currentGame) {
        for (Item nextItem : currentGame.getInventory()) {

            if (nextItem.getItemName().equalsIgnoreCase(userRequest)) {
                System.out.println("\nUsing " + nextItem.getItemName());
                if (nextItem.getItemName().equalsIgnoreCase("mask")) {
                    for (Room nextRoom : currentGame.getRooms()) {
                        nextRoom.setMaskRequired(false);
                    }
                } else if (nextItem.getItemName().equalsIgnoreCase("key")) {
                    for (Room nextRoom : currentGame.getRooms()) {
                        nextRoom.setKeyRequired(false);
                    }
                }
                currentGame.getInventory().remove(nextItem);
                break;
            }
        }
    }

}
