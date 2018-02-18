import java.io.IOException;
import java.util.Scanner;

class UserManager extends User {

    UserManager(){
    }

    public void pesel() throws IOException {
        Scanner inputPesel = new Scanner(System.in);

        System.out.print("pesel: ");
        setPesel(inputPesel.next());
    }

    public void name() {
        Scanner inputName = new Scanner(System.in);

        System.out.print("name: ");
        setName(inputName.next());
    }

    public void address() {
        String street;
        String buildingNumber;
        String apartmentNumber;

        Scanner inputAddress = new Scanner(System.in);

        System.out.println("addres details: ");
        System.out.print("    street: ");
        street = inputAddress.next();
        System.out.print("    building number: ");
        buildingNumber = inputAddress.next();
        System.out.print("    apartment number: ");
        apartmentNumber = inputAddress.next();

        setAddress(street + " " + buildingNumber + "/" + apartmentNumber);
    }

    public void email() {
        Scanner inputEmail = new Scanner(System.in);

        System.out.print("e-mail: ");
        setEmail(inputEmail.next());
    }
}
