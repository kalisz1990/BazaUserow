import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

interface menuSearch {
    void searchMenu() throws IOException;
}

public class Menu implements menuSearch {

    private manager manager = null;
    {
        try {
            manager = new FileManager();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchMenu() throws IOException {
        int answer;

        Scanner writeAnswer = new Scanner(System.in);
        printMenuStart();
        answer = writeAnswer.nextInt();

        while (answer != 4) {
            switch (answer) {
                case 1:
                    manager.addUser();
                    break;
                case 2:
                    manager.findUser();
                    break;
                case 3:
                    manager.deleteUser();
                    break;
                default:
                    System.out.println("\nthis is not correct, try again\n\n");
                    break;
            }
            printMenuStart();
            answer = writeAnswer.nextInt();
        }
    }

    private void printMenuStart() {
        System.out.print("|----------------------|" +
                "\n|1: add another user:  |" +
                "\n|2: find user:         |" +
                "\n|3: delete user:       |" +
                "\n|4: exit               |" +
                "\n|----------------------|\n" + ": ");
    }

}
