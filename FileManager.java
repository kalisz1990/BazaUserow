import java.io.*;
import java.util.Scanner;

public class FileManager {

    public static void createFile() throws IOException {

        File plik = new File("db" + File.separator + "usersDatabase.txt");

        if (!plik.exists()) {
            plik.createNewFile();
        }
    }

    public static void addUser() throws IOException {
        UserManager userManager = new UserManager();
        BufferedWriter writer = new BufferedWriter(new FileWriter("db" + File.separator + "usersDatabase.txt", true));

        userManager.pesel();

        if (!userManager.getPesel().equals("")) {
            userManager.name();
            userManager.address();
            userManager.email();

            writer.write("pesel: " + userManager.getPesel());
            writer.newLine();
            writer.write("name: " + userManager.getName());
            writer.newLine();
            writer.write("address: " + userManager.getAddress());
            writer.newLine();
            writer.write("email: " + userManager.getEmail());
            writer.newLine();
            writer.write("------------");
            writer.newLine();

            writer.close();
        }
    }

    public static void findUser() throws IOException {
        String search = "";
        String line   = "";

        Scanner        searchUser = new Scanner(System.in);
        BufferedReader reader     = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));

        System.out.print("\npesel of user you want to find: ");
        search = searchUser.nextLine();
        System.out.println();

        while ((line = reader.readLine()) != null) {
            if (line.contains(search)) {
                System.out.println(line);
                for (int i = 0; i < 3; i++) {
                    System.out.println(reader.readLine());
                }
                System.out.println();
                break;
            }
            else if (reader.readLine() != null){
                System.out.println("no user in database\n");
                break;
            }
        }

        reader.close();
    }

    public static void deleteUser() throws IOException {
        String userToDelete;
        String line = "";

        RandomAccessFile  file = new RandomAccessFile("db" + File.separator + "usersDatabase.txt", "rw");

        BufferedWriter writer  = new BufferedWriter(new FileWriter("temp" + File.separator + "usersDatabaseTemp.txt"));
        Scanner        scanner = new Scanner(System.in);

        System.out.print("\npesel of user to delete: ");
        userToDelete = scanner.nextLine();

        while ((line = file.readLine()) != null) {
            if (line.contains(userToDelete)) {
                for (int i = 0; i < 4; i++) {
                    file.readLine();
                }
                continue;
            }
            if (line.contains("null")) {
                line = "------------";
            }

            writer.write(line);
            writer.newLine();
        }

        writer.close();
        file.close();

        BufferedReader  reader = new BufferedReader(new FileReader("temp" + File.separator + "usersDatabaseTemp.txt"));
        writer = new BufferedWriter(new FileWriter("db" + File.separator + "usersDatabase.txt"));

        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();

        writer = new BufferedWriter(new FileWriter("temp" + File.separator + "usersDatabaseTemp.txt"));

        writer.write("jestem pusty w środku :(");
        writer.flush();
        writer.close();
    }
}
