import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import models.petList;
import services.service;
import utils.function;

public class petOPia {

    static ArrayList<petList> lisPet = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        function.displayJudul();
        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                try {
                    function.displayMenu();
                    int pilihan = scanner.nextInt();
                    scanner.nextLine();
                    switch (pilihan) {
                        case 1:
                            service.displayPets(scanner);
                            break;
                        case 2:
                            service.displayCari(scanner);
                            break;
                        case 3:
                            service.editPpet(scanner);
                            break;
                        case 4:
                            service.beliPet(scanner);
                            break;
                        case 5:
                            function.displayFaizNation();
                            return;
                        default:

                            System.out.println(
                                    "╔═══════════════════════════════════════════════════════════════════════╗");
                            System.out.printf("║ %-69s ║\n", " [ERROR] | INPUT TIDAK VALID, SILAHKAN COBALAGI.");
                            System.out.println(
                                    "╚═══════════════════════════════════════════════════════════════════════╝");
                    }
                } catch (InputMismatchException e) {

                    System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                    System.out.printf("║ %-69s ║\n", " [ERROR] | INPUT HARUS ANGKA, SILAHKAN COBA ULANGI DARI MENU.");
                    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                    scanner.nextLine();

                }
            }

        }

    }

}
