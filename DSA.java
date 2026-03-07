import java.util.*;

class Task {
    String name;
    String date;
    String time;
    int priority;
    boolean done;

    Task(String name, String date, String time, int priority) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.done = false;
    }

    public String toString() {
        return "Task: " + name + " | Date: " + date + " | Time: " + time + " | Priority: " + priority;
    }
}

public class DSA {

    static PriorityQueue<Task> taskQueue =
            new PriorityQueue<>((a, b) -> a.priority - b.priority);

    static LinkedList<Task> completedTasks = new LinkedList<>();

    static Scanner sc = new Scanner(System.in);

    // TIME VALIDATION
    static boolean validTime(String time) {

        time = time.trim().toUpperCase();

        try {

            if (time.contains("AM") || time.contains("PM")) {

                String t = time.replace("AM", "").replace("PM", "").trim();
                String[] parts = t.split(":");

                int hour = Integer.parseInt(parts[0]);
                int min = Integer.parseInt(parts[1]);

                if (hour < 1 || hour > 12 || min < 0 || min > 59)
                    return false;

            } else {

                String[] parts = time.split(":");

                int hour = Integer.parseInt(parts[0]);
                int min = Integer.parseInt(parts[1]);

                if (hour < 0 || hour > 23 || min < 0 || min > 59)
                    return false;
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // ADD MULTIPLE TASKS
    static void addTask() {

        while (true) {

            System.out.print("Enter Task Name (or type 'done'): ");
            String name = sc.nextLine();

            if (name.equalsIgnoreCase("done"))
                break;

            System.out.print("Enter Date (DD-MM-YYYY): ");
            String date = sc.nextLine();

            String time;

            while (true) {

                System.out.print("Enter Time (24hr 18:30 or 12hr 6:30 PM): ");
                time = sc.nextLine();

                if (validTime(time))
                    break;

                System.out.println("Invalid Time Format! Try Again.");
            }

            int priority;

            while (true) {

                try {
                    System.out.print("Enter Priority (1 High, 2 Medium, 3 Low): ");
                    priority = Integer.parseInt(sc.nextLine());

                    if (priority < 1 || priority > 3)
                        throw new Exception();

                    break;

                } catch (Exception e) {
                    System.out.println("Invalid Priority! Enter 1, 2 or 3.");
                }
            }

            Task t = new Task(name, date, time, priority);
            taskQueue.add(t);

            System.out.println("Task Added\n");
        }
    }

    // VIEW TASKS
    static void viewTasks() {

        if (taskQueue.isEmpty()) {
            System.out.println("No Tasks Available");
            return;
        }

        PriorityQueue<Task> temp = new PriorityQueue<>(taskQueue);

        System.out.println("\nTasks by Priority:");

        while (!temp.isEmpty())
            System.out.println(temp.poll());
    }

    // MARK TASK DONE
    static void markDone() {

        System.out.print("Enter Task Name to mark done: ");
        String name = sc.nextLine();

        Task found = null;

        for (Task t : taskQueue) {
            if (t.name.equalsIgnoreCase(name)) {
                found = t;
                break;
            }
        }

        if (found != null) {

            taskQueue.remove(found);
            found.done = true;
            completedTasks.add(found);

            System.out.println("Task Completed\n");

        } else {
            System.out.println("Task Not Found\n");
        }
    }

    // SHOW COMPLETED TASKS
    static void showCompleted() {

        if (completedTasks.isEmpty()) {
            System.out.println("No Completed Tasks");
            return;
        }

        System.out.println("\nCompleted Tasks:");

        for (Task t : completedTasks)
            System.out.println(t + " | STATUS: DONE");
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== SMART DAILY PLANNER =====");
            System.out.println("1 Add Tasks");
            System.out.println("2 View Tasks");
            System.out.println("3 Mark Task Done");
            System.out.println("4 Show Completed Tasks");
            System.out.println("0 Exit");

            System.out.print("Enter Choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid Input");
                continue;
            }

            switch (choice) {

                case 1:
                    addTask();
                    break;

                case 2:
                    viewTasks();
                    break;

                case 3:
                    markDone();
                    break;

                case 4:
                    showCompleted();
                    break;

                case 0:
                    System.out.println("Planner Closed");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}