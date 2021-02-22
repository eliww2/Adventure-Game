package student.adventure;

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
        boolean noEnter = true;
        for (Direction nextDirection : currentGame.getCurrentRoom().getDirections()) {
            if (nextDirection.getDirectionName().equalsIgnoreCase(userRequest)) {
                for (Room nextRoom : currentGame.getRooms()) {
                    if (nextRoom.getName().equals(nextDirection.getRoomName())) {
                        if(!(nextRoom.getMaskRequired()) && !(nextRoom.getKeyRequired())) {
                            noEnter = false;
                           currentGame.setCurrentRoom(nextRoom);
                           break;
                        }
                    }
                }
            }
        }
        if (noEnter) {
            System.out.println("\nYou could not go \"" + userRequest + "\"");
        }
    }

    /**
     * Moves item in a room to the users inventory.
     * @param userRequest what the user wants to take.
     * @param currentGame the current game.
     */
    public static void addToInventory(String userRequest, Game currentGame) {
        boolean noAdd = true;
        for (Item nextItem : currentGame.getCurrentRoom().getItems()) {
            if (nextItem.getItemName().equalsIgnoreCase(userRequest)) {
                noAdd = false;
                currentGame.getCurrentRoom().getItems().remove(nextItem);
                currentGame.getInventory().add(nextItem);
                break;
            }
        }
        if (noAdd) {
            System.out.println("You can not add \"" + userRequest + "\" at this time");
        }
    }

    /**
     * Moves item in the users inventory to the room
     *
     * @param userRequest what item the user wants to drop.
     * @param currentGame the inventory of the user
     */
    public static void removeFromInventory(String userRequest, Game currentGame) {
        boolean noRemove = true;
        for (Item nextItem : currentGame.getInventory()) {
            if (nextItem.getItemName().equalsIgnoreCase(userRequest)) {
                noRemove = false;
                currentGame.getCurrentRoom().getItems().add(nextItem);
                currentGame.getInventory().remove(nextItem);
                break;
            }
        }
        if (noRemove) {
            System.out.println("You could not remove \"" + userRequest + "\" at this time");
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

    /**
     * Uses item and changes the state of the game.
     * @param userRequest what item the user wants to use.
     * @param currentGame the current instance of the game.
     */
    public static void useItem(String userRequest, Game currentGame) {
        boolean noUse = true;
        for (Item nextItem : currentGame.getInventory()) {
            if (nextItem.getItemName().equalsIgnoreCase(userRequest)) {
                if (nextItem.getItemName().equalsIgnoreCase("mask")) {
                    noUse = false;
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

        if (noUse) {
            System.out.println("You could not use \"" + userRequest + "\" at this time");
        }
    }

}
