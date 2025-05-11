package FaizNation.petOpia_dev;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import FaizNation.petOpia_dev.models.*;
import FaizNation.petOpia_dev.utils.*;
import FaizNation.petOpia_dev.services.*;

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
                            services.displayPets(scanner);
                            break;
                        case 2:
                            services.displayCari(scanner);
                            break;
                        case 3:
                            services.editPpet(scanner);
                            break;
                        case 4:
                            services.beliPet(scanner);
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
