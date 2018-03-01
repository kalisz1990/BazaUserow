package src;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

interface manager {
    void createFile() throws IOException;
    void addUser()    throws IOException;
    void findUser()   throws IOException;
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
        String line = "";
        String tString = "";

        User    user    = new User();
        Gson    json    = new GsonBuilder().setPrettyPrinting().create();
        Scanner scanner = new Scanner(System.in);

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

           // serializacja JSON do pliku //

            // zapis z pliku do Stringa
           BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));
           while ((line = reader.readLine()) != null) {
               tString += line;
           }
           reader.close();

           //deserializacja
           LinkedList <User> linkedList = json.fromJson(tString, new TypeToken <LinkedList<User>> (){}.getType());
           linkedList.addLast(user);
           line = json.toJson(linkedList);

           //zapis do pliku
           BufferedWriter writer = new BufferedWriter(new FileWriter("db" + File.separator + "usersDatabase.txt"));
           writer.write(line);
           writer.newLine();
           writer.close();
        }
    }

    public void findUser() throws IOException {
        String search = "12345678914";
        String line = "";
        String tString = "";

        Gson json = new GsonBuilder().setPrettyPrinting().create();

//        Scanner searchUser = new Scanner(System.in);
//
//        System.out.print("\npesel of user you want to find: ");
//        search = searchUser.nextLine();
//        System.out.println();

        // zapis z pliku do Stringa
        BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));
        while ((line = reader.readLine()) != null) {
            tString += line;
        }
        reader.close();
        // deserializacja ze Stringa z pliku do ArrayList
        ArrayList <User> arrayList = json.fromJson(tString, new TypeToken <ArrayList<User>> (){}.getType());

        for (User user : arrayList) {
            if (user.getPesel() != null && user.getPesel().contains(search)) {
                String find = json.toJson(user);
                System.out.println(find);
            }
        }
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
