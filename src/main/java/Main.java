import java.io.IOException;

class Main {

    public static void main(String[] args) {

        try {
            new Menu().searchMenu();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
