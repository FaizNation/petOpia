import java.util.ArrayList; // 📦 Import ArrayList untuk menyimpan daftar pet yang tersedia 📦
import java.util.InputMismatchException; // 📦 Import InputMismatchException untuk menangani kesalahan input pengguna 📦
import java.util.Scanner; // 📦 Import Scanner untuk membaca input dari pengguna 📦
import models.petList; // 📦 Import petList untuk menyimpan data pet yang tersedia 📦
import services.service; // 📦 Import service untuk mengelola logika aplikasi 📦
import utils.function; // 📦 Import function untuk menampilkan menu dan judul aplikasi 📦

public class petOPia {
    // 🐾 ArrayList untuk menyimpan daftar pet yang tersedia 🐾
    static ArrayList<petList> lisPet = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        function.displayJudul();// 🎉 menampilkan judul aplikasi 🎉
        try (Scanner scanner = new Scanner(System.in)) { 

            while (true) { // 🔄 Looping menu utama 🔄 
                try { 
                    function.displayMenu(); // 📜 menampilkan menu utama 📜
                    int pilihan = scanner.nextInt();
                    scanner.nextLine();
                    switch (pilihan) { // 🔀 switch case untuk percabangan 🔀
                        case 1:
                            service.displayPets(scanner); // 🐶 menampilkan menu pet 🐶
                            break;
                        case 2:
                            service.displayCari(scanner); // 🔍 menampilkan menu cari pet 🔍
                            break;
                        case 3:
                            service.editPpet(scanner); // ✏️ menampilkan menu edit pet ✏️
                            break;
                        case 4:
                            service.beliPet(scanner); // 💰 menampilkan menu beli pet 💰
                            break;
                        case 5:
                            function.displayFaizNation(); // 😎 menampilkan by faiz nation 😎
                            return;
                        default:
                            // ❌ Menampilkan pesan error jika pilihan tidak valid 
                            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                            System.out.printf("║ %-69s ║\n", " [ERROR] | INPUT TIDAK VALID, SILAHKAN COBALAGI.");
                            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                    }
                } catch (InputMismatchException e) {
                    // 🛑 Catch untuk menangani kesalahan jika input bukan angka 🛑
                    System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                    System.out.printf("║ %-69s ║\n", " [ERROR] | INPUT HARUS ANGKA, SILAHKAN COBA ULANGI DARI MENU.");
                    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                    scanner.nextLine();

                }
            }

        }

    }

}
