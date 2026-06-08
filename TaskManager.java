import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TaskManager {

    private static final String FILE_NAME = "tasks.txt";
    private static final List<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    static class Task {
        String title;
        boolean completed;

        Task(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return (completed ? "[✓] " : "[ ] ") + title;
        }
    }

    public static void main(String[] args) {
        loadTasks();

        while (true) {
            System.out.println("\\n=== TASK MANAGER PRO ===");
            System.out.println("1. Add Task");
            System.out.println("2. Show Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Remove Task");
            System.out.println("5. Save Tasks");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            switch (scanner.nextLine()) {
                case "1" -> addTask();
                case "2" -> showTasks();
                case "3" -> completeTask();
                case "4" -> removeTask();
                case "5" -> saveTasks();
                case "6" -> {
                    saveTasks();
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Task title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Task cannot be empty.");
            return;
        }

        tasks.add(new Task(title));
        System.out.println("Task added.");
    }

    private static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void completeTask() {
        showTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Task number to complete: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            tasks.get(index).completed = true;
            System.out.println("Task completed.");
        } catch (Exception e) {
            System.out.println("Invalid task number.");
        }
    }

    private static void removeTask() {
        showTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Task number to remove: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            tasks.remove(index);
            System.out.println("Task removed.");
        } catch (Exception e) {
            System.out.println("Invalid task number.");
        }
    }

    private static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            for (Task task : tasks) {
                writer.println(task.completed + "|" + task.title);
            }
            System.out.println("Tasks saved.");
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) return;

        try {
            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split("\\\\|", 2);
                if (parts.length == 2) {
                    Task task = new Task(parts[1]);
                    task.completed = Boolean.parseBoolean(parts[0]);
                    tasks.add(task);
                }
            }
        } catch (IOException ignored) {}
    }
}
