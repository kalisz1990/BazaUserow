
import java.io.*;


public class Main {
    
        public static void main(String[] args) {

        File plik = new File("usersDatabase.txt");


        Users users = new Users();
       // Users.addUser();

//       users = new Users("Pawel Kaliszewski", "12345678901", "Bolek", 327, 1);
//       users = new Users("Jacek Placek", "12345678902", "Wojska Polskiego", 2, 5, "jplacek@gmail.com");

            String a = Users.addUser();
            

        try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(plik));
        writer.write(a);
        writer.newLine();

        writer.close();

        //PrintWriter writer = new PrintWriter(new FileWriter(plik));
        //Users.toFile(users, writer);
        //writer.close();
    }
        catch (IOException e) {
        System.out.println(e.getMessage());
        }
    }
}

