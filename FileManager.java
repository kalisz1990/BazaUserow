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

        System.out.println("\nadd another user 1: \nfind user 2: \ndelete user 3: \nexit 4:\n");
        answer = writeAnswer.nextInt();

        while (answer !=4)
        {
            switch (answer)
            {
                case 1:
                    FileManager.addUser();
                    System.out.println("\nadd another user 1: \nfind user 2: \ndelete user 3: \nexit 4:\n");
                    answer = writeAnswer.nextInt();
                    break;

                case 2:
                    FileManager.findUser();
                    System.out.println("\nadd another user 1: \nfind user 2: \ndelete user 3: \nexit 4:\n");
                    answer = writeAnswer.nextInt();
                    break;

                case 3:
                    FileManager.deleteUser();
                    System.out.println("\nadd another user 1: \nfind user 2: \ndelete user 3: \nexit 4:\n");
                    answer = writeAnswer.nextInt();
                    break;

                default:
                    System.out.println("\nthis is not correct, try again\n\n");
                    System.out.println("\nadd another user 1: \nfind user 2: \ndelete user 3: \nexit 4:\n");
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
        users.setName();
        users.setAddress();
        users.setEmail();

        writer.write("pesel: " + users.pesel);
        writer.newLine();
        writer.write("name: " + users.name);
        writer.newLine();
        writer.write("address: " + users.address);
        writer.newLine();
        writer.write("email: " + users.email);
        writer.newLine();
        writer.write("------------");
        writer.newLine();

        writer.close();
    }

    public static void deleteUser() throws IOException
    {
        String userToDelete;
        String line = "";

        RandomAccessFile file = new RandomAccessFile("src" + File.separator + "usersDatabase.txt", "rw");
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp" +File.separator + "usersDatabaseTemp.txt"));

        Scanner scanner = new Scanner(System.in);

        System.out.print("\npesel of user to delete: ");
         userToDelete = scanner.nextLine();

        while ((line = file.readLine()) != null) {
            if (line.contains("------------")) {
                line += "\n";
                line += file.readLine();
                if (line.contains(userToDelete)) {
                    for (int i = 0; i < 3; i++) {
                        file.readLine();
                    }
                    continue;
                }
            }
            if (line.contains("null"))
                line = "------------";

            writer.write(line);
            writer.newLine();
        }

        writer.close();
        file.close();

        BufferedReader reader = new BufferedReader(new FileReader("temp" +File.separator + "usersDatabaseTemp.txt"));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("src" + File.separator + "usersDatabase.txt"));

        while ((line = reader.readLine()) != null)
        {
            writer2.write(line);
            writer2.newLine();
        }
        reader.close();
        writer2.close();

        BufferedWriter toCleanTempFile = new BufferedWriter(new FileWriter("temp" +File.separator + "usersDatabaseTemp.txt"));
        toCleanTempFile.write("jestem pusty w środku :(");
        toCleanTempFile.flush();
        toCleanTempFile.close();
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
