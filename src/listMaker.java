import java.util.ArrayList;
import java.util.Scanner;

public class listMaker {
    private static ArrayList<String> myList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String choice;

        do {
            displayMenu();
            choice = getRegExString("Enter your choice (A, I, D, P, or Q): ", "[AaIiDdPpQq]");
            switch (choice.toUpperCase()) {
                case "A":
                    addItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    if (getYNConfirm("Are you sure you want to quit? (Y/N): ")) {
                        System.out.println("Exiting program...");
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equalsIgnoreCase("Q"));
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("A - Add an item to the list");
        System.out.println("I - Insert an item in the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
        System.out.println("\nCurrent list items:");

        for (int i = 0; i < myList.size(); i++) {
            System.out.println((i + 1) + ". " + myList.get(i));
        }
    }

    private static void addItem() {
        String newItem = getString("Enter a new item to add: ");
        myList.add(newItem);
        System.out.println("'" + newItem + "' has been added to the list.");
    }

    private static void deleteItem() {
        int itemNumber = getRangedInt("Enter the number of the item to delete: ", 1, myList.size());
        String deletedItem = myList.remove(itemNumber - 1);
        System.out.println("'" + deletedItem + "' has been removed from the list.");
    }

    private static void insertItem() {
        int itemNumber = getRangedInt("Enter the location of the item to insert: ", 1, myList.size());
        String newItem = getString("Enter a new item to add: ");
        String insertItem = myList.set(itemNumber - 1, newItem);
        System.out.println("'" + newItem + "' has been added to the list.");
    }

    private static void printList() {
        if (myList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("List items:");
            for (String item : myList) {
                System.out.println("- " + item);
            }
        }
    }

    private static String getString(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    private static int getRangedInt(String prompt, int min, int max) {
        int number;
        do {
            System.out.print(prompt);
            while (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                input.next();
            }
            number = input.nextInt();
            input.nextLine();
        } while (number < min || number > max);
        return number;
    }

    private static boolean getYNConfirm(String prompt) {
        String answer;
        do { System.out.print(prompt);
            answer = input.nextLine();
        } while (!answer.equalsIgnoreCase("Y") && ! answer.equalsIgnoreCase("N"));
        return answer.equalsIgnoreCase("Y");
    }
    private static String getRegExString(String prompt, String regex) {
        String inputStr;
        boolean validInput = false;
        do { System.out.print(prompt);
            inputStr = input.nextLine();
            if (inputStr.matches(regex)) { validInput = true;
            } else { System.out.println("Invalid input. Please try again.");
            }
        } while (!validInput);
        return inputStr; }
    }