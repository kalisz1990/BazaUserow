import java.io.*;
import java.util.Scanner;

public class FileManager {

    public static void createFile() throws IOException
        {
            File plik = new File("src" + File.separator + "usersDatabase.txt");
            if (!plik.exists())
                plik.createNewFile();
        }

    public static void searchMenu() throws IOException
    {
        int answer;

        Scanner writeAnswer = new Scanner(System.in);

        System.out.println("add another user: 1\nfind user: 2 \nexit: 3\n");
        answer = writeAnswer.nextInt();

        while (answer !=3)
        {
            switch (answer)
            {
                case 1:
                    FileManager.addUser();
                    System.out.println("\nadd another user: 1\nfind user: 2 \nexit: 3\n");
                    answer = writeAnswer.nextInt();
                    break;

                case 2:
                    FileManager.findUser();
                    System.out.println("\nadd another user: 1\nfind user: 2 \nexit: 3\n");
                    answer = writeAnswer.nextInt();
                    break;

                default:
                    System.out.println("\nthis is not correct, try again\n\n");
                    System.out.println("add another user: 1\nfind user: 2 \nexit: 3\n");
                    answer = writeAnswer.nextInt();
                    break;
            }
        }
    }

    public static void addUser() throws IOException
    {
        Users users = new Users();
        BufferedWriter writer = new BufferedWriter(new FileWriter("src" + File.separator + "usersDatabase.txt",true));

        users.setPesel();
        writer.write("pesel: " + users.pesel);
        writer.newLine();
        users.setName();
        writer.write("name: " + users.name);
        writer.newLine();
        users.setAddress();
        writer.write("address: " + users.address);
        writer.newLine();
        users.setEmail();
        writer.write("email: " + users.email);
        writer.newLine();
        writer.write("------------");
        writer.newLine();

        writer.close();
    }

    public static void findUser() throws IOException
    {
        String search = "";
        String line = "";

        Scanner searchUser = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader("src" + File.separator + "usersDatabase.txt"));

        System.out.print("\npesel of person you want to find: ");
        search = searchUser.nextLine();
        System.out.println();

        while ((line = reader.readLine()) != null)
        {
            if (line.contains(search)) {
                System.out.println(line);
              for (int i = 0; i < 3; i++)
                  System.out.println(reader.readLine());
                break;
            }
        }
        reader.close();
    }
}
