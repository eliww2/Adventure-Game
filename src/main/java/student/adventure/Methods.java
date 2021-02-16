package student.adventure;

import java.sql.SQLOutput;

public class Methods {

    public static void whereIsUser(Room currentRoom) {
        System.out.println(currentRoom.getDescription());

        System.out.print("From here, you can go: ");
        for (Direction nextDirection : currentRoom.getDirections()) {
            System.out.print(nextDirection.getDirectionName() + " ");
        }
        System.out.print("\nItems visible: ");
        for (Item nexItem : currentRoom.getItems()) {
            System.out.print(nexItem.getItemName() + " ");
        }
        System.out.print("\n\n>");

    }

    private static void updateRoom(String userInput) {

    }

    public static void actionRequested(String userInput) {
        if (userInput.split(" ")[0].equalsIgnoreCase("quit")) {
            System.exit(0);
        } else if (userInput.split(" ")[0].equalsIgnoreCase("go")) {
            updateRoom(userInput);
        } else if (userInput.split(" ")[0].equalsIgnoreCase("examine")) {

        } else if (userInput.split(" ")[0].equalsIgnoreCase("take")) {

        } else if (userInput.split(" ")[0].equalsIgnoreCase("drop")) {

        } else if (userInput.split(" ")[0].equalsIgnoreCase("use")) {

        } else {

        }
    }

}
