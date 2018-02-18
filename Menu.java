import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void searchMenu() throws IOException {
        int answer;

        Scanner writeAnswer = new Scanner(System.in);

        System.out.print("|----------------------|" +
                           "\n|1: add another user:  |" +
                           "\n|2: find user:         |" +
                           "\n|3: delete user:       |" +
                           "\n|4: exit:              |" +
                           "\n|----------------------|" +
                           "\n: ");
        answer = writeAnswer.nextInt();

        while (answer != 4) {
            switch (answer) {
                case 1:
                    //!!!!!!!!!!!!!!!!!! // @mk: niby wszystko fajnie ale wszystko na statycznych metodach
                    FileManager.addUser();
                    break;

                case 2:
                    FileManager.findUser();
                    break;

                case 3:
                    FileManager.deleteUser();
                    break;

                default:
                    System.out.println("\nthis is not correct, try again\n\n");
                    break;
            }

            System.out.print("|----------------------|" +
                               "\n|1: add another user:  |" +
                               "\n|2: find user:         |" +
                               "\n|3: delete user:       |" +
                               "\n|4: exit               |" +
                               "\n|----------------------|\n" + ": ");
            answer = writeAnswer.nextInt();
        }
    }
}
