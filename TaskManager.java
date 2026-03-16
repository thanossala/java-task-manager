import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    // Lista me oles tis tasks
    static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Task Manager ===");

        while (true) {
            System.out.println("\n1. Add task");
            System.out.println("2. Show tasks");
            System.out.println("3. Remove task");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Task: ");
                String task = scanner.nextLine();
                tasks.add(task);
                System.out.println("Added!");

            } else if (choice.equals("2")) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks!");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }

            } else if (choice.equals("3")) {
                System.out.print("Which number to remove? ");
                int num = Integer.parseInt(scanner.nextLine());
                tasks.remove(num - 1);
                System.out.println("Removed!");

            } else if (choice.equals("4")) {
                System.out.println("Bye!");
                break;
            }
        }
    }
}