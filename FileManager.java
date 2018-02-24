import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

interface manager {
    void createFile() throws IOException;
    void addUser() throws IOException;
    void findUser() throws IOException;
    void deleteUser() throws IOException;
}

public class FileManager implements manager {

    public void createFile() throws IOException {

        File plik = new File("db" + File.separator + "usersDatabase.txt");

        if (!plik.exists()) {
            plik.createNewFile();
        }
    }

    public void addUser() throws IOException {
        User           user    = new User();
        BufferedWriter writer  = new BufferedWriter(new FileWriter("db" + File.separator + "usersDatabase.txt", true));
        Gson           json    = new Gson();
        Scanner        scanner = new Scanner(System.in);

        System.out.print("pesel: ");
        user.setPesel(scanner.nextLine());
        if (!user.getPesel().equals("")) {
            System.out.print("name: ");
            user.setName(scanner.nextLine());
            System.out.println("addres details: ");
            System.out.print("    street: ");
            user.setStreet(scanner.nextLine());
            System.out.print("    building number: ");
            user.setbNumber(scanner.nextLine());
            System.out.print("    apartment number: ");
            user.setaNumber(scanner.nextLine());
            System.out.print("email: ");
            user.setEmail(scanner.nextLine());
            user.setAddress(user.getStreet(), user.getbNumber(), user.getaNumber());


           // serializacja JSON do pliku

            writer.write(json.toJson(user));
            writer.newLine();

            /*
             ZAPASOWY KOD
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
            */

            writer.close();
        }
    }

    public void findUser() throws IOException {
        String search = "";
        String line = "";

        Scanner searchUser = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));

        System.out.print("\npesel of user you want to find: ");
        search = searchUser.nextLine();
        System.out.println();

            while ((line = reader.readLine()) != null) {
                if (line.contains(search)) {
                    System.out.println(line + "\n");
                    break;
                }
            }
                if (reader.readLine() == null) {
                    System.out.println("no user in database\n");
                }
        reader.close();
    }

    public void deleteUser() throws IOException {
        String userToDelete;
        String line = "";

        RandomAccessFile file    = new RandomAccessFile("db" + File.separator + "usersDatabase.txt", "rw");
        BufferedWriter   writer  = new BufferedWriter(new FileWriter("temp" + File.separator + "usersDatabaseTemp.txt"));
        Scanner          scanner = new Scanner(System.in);

        System.out.print("\npesel of user to delete: ");
        userToDelete = scanner.nextLine();

        while ((line = file.readLine()) != null) {
            if (line.contains(userToDelete)) {
                line = file.readLine();
            }
            writer.write(line);
            writer.newLine();
        }
        writer.close();
        file.close();

        BufferedReader reader = new BufferedReader(new FileReader("temp" + File.separator + "usersDatabaseTemp.txt"));
        writer                = new BufferedWriter(new FileWriter("db" + File.separator + "usersDatabase.txt"));

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
