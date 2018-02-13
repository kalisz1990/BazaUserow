
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Users {

    public String name;
    public String pesel;
    public String address;
    public String email;

    public Users() {
        this.name = " ";
        this.address = " ";
        this.pesel = " ";
        this.email = " ";
    }

    public Users(String name, String pesel) {
        this();
        this.name = name;
        this.pesel = pesel;

        if (pesel.length() != 11)
            System.out.println("blad - " + name + ": podany zostal zly pesel");
    }

    public Users(String name, String pesel, String street, int buildingNumber, int apartmentNumber) {
        this(name, pesel);
        this.address = street + ", " + buildingNumber + "/" + apartmentNumber;
    }

    public Users(String name, String pesel, String street, int buildingNumber, int apartmentNumber, String email) {
        this(name, pesel, street, buildingNumber, apartmentNumber);
        this.email = email;
    }

    //getter
    public String getName() {
        return name;
    }

    public String getPesel() {
        return pesel;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    //setter
    public void setName() {
        Scanner inputName = new Scanner(System.in);
        System.out.print("name: ");
        this.name = inputName.nextLine();
    }

    public void setPesel() {
        Scanner inputPesel = new Scanner(System.in);

        do {
            System.out.print("pesel: ");
            this.pesel = inputPesel.nextLine();
            if (pesel.length() > 11){
                System.out.println("za dlugi pesel !!, sprawdz i wprowadz ponownie");}
            if (pesel.length() < 11){
                System.out.println("za krotki pesel !!, sprawdz i wprowadz ponownie");}
        }
        while (pesel.length() != 11);

    }

    public void  setAddress() {
        String street;
        int bNumber;
        int aNumber;

        System.out.println("addres details: ");
        Scanner inputAddress = new Scanner(System.in);
        System.out.print("  street: ");
        street = inputAddress.nextLine();
        System.out.print("  building number: ");
        bNumber = inputAddress.nextInt();
        System.out.print("  apartment number: ");
        aNumber = inputAddress.nextInt();

        this.address = street +" " + bNumber + "/" + aNumber;

    }

    public void setEmail() {
        Scanner inputEmail = new Scanner(System.in);
        System.out.print("e-mail: ");
        this.email = inputEmail.nextLine();
    }

    @Override
    public String toString() {
        return this.name + ", " + this.pesel + ", " + this.address + ", " + this.email;
    }



    public static String addUser() {

        int answer;

        Scanner writeAnswer = new Scanner(System.in);
        Users users = new Users();

        System.out.println("add another user: 1\nexit: 2\n");
        answer = writeAnswer.nextInt();

        try {
            FileManager fileManager = new FileManager();
            BufferedWriter writer = new BufferedWriter(new FileWriter("usersDatabase.txt",true));

            fileManager.createFile();

            while (answer != 2)
            {
                switch (answer)
                {
                    //creating new user
                    case 1: {
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


                        System.out.println("\nadd another user: 1\nexit: 2\n");
                        answer = writeAnswer.nextInt();
                        break;
                    }
                    default:
                        System.out.println("this is not correct, try again");
                        System.out.println("\nadd another user: 1\nexit: 2\n");
                        answer = writeAnswer.nextInt();
                        break;
                }
            }

            writer.close();

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }


       return users.pesel + users.name + users.address + users.email;
    }
}

