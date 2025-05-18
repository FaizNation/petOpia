package FaizNation.petopia_dev.services;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import FaizNation.petopia_dev.models.ikan;
import FaizNation.petopia_dev.models.petList;
import FaizNation.petopia_dev.utils.function;
public class serviceFish {
    static ArrayList<ikan> listIkan = services.listIkan;

    public static void displayiwak() {

        if (listIkan == null || listIkan.isEmpty()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                     Tidak ada data pet yang tersedia                  ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        } else {
            int i = 1;
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           Daftar Semua Pet                            ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-10s | %-14s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon");
            System.out.println("╠═════|═══════════════|═══════════════════|════════════|════════════════╣");

            for (petList poisson : listIkan) {
                if (poisson instanceof ikan) {
                    String hargaFormatted = String.format("%, .2f",
                            poisson.getHargaPet()).replace(",", ".");
                    System.out.printf("║ %-3s | %-13s | Rp%-15s | %-10d | %-3.0f%%           ║\n", i,
                            poisson.getrasPet(), hargaFormatted,
                            poisson.getStokPet(),
                            poisson.getDiskonPet() * 100);
                    i++;
                }
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    public static void displayDetailIwak(Scanner scanner) {

        displayiwak();
        if (listIkan == null || listIkan.isEmpty()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║              [ERROR] | Tidak ada data pet yang tersedia               ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }

        System.out.print("\nApakah ingin melihat detail salah satu pet? (y/n): ");
        String konfirmasi = scanner.nextLine().trim().toLowerCase();

        if (!konfirmasi.equals("y")) {
            return;
        }
        System.out.print("Masukkan nomor pet ikan yang ingin dilihat: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        if (pilihan < 1 || pilihan > listIkan.size()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║        [ERROR] | Nomor tidak tersedia. Silakan coba lagi.             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }

        petList petDipilih = listIkan.get(pilihan - 1);

        if (petDipilih instanceof ikan) {
            ikan poisson = (ikan) petDipilih;
            function.spasi();
            function.spinnerLoading("MenCari data pet ", 2000);

            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n", "  [SUCCESS] | Menampilkan Detail ikan " + poisson.getrasPet());
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-69s ║\n", " Ras ikan           : " + poisson.getrasPet());
            String hargaFormatted = String.format("%, .2f", poisson.getHargaPet()).replace(",", ".");
            System.out.printf("║ %-69s ║\n", " Harga                : Rp" + hargaFormatted);
            System.out.printf("║ %-69s ║\n", " Stok                 : " + poisson.getStokPet());
            System.out.printf("║ %-69s ║\n", " Jenis                : " + poisson.getjenisPet());
            System.out.printf("║ %-69s ║\n", " Diskon               : " + (poisson.getDiskonPet() * 100) + "%");
            System.out.printf("║ %-69s ║\n", " Nama Ilmiah          : " + poisson.getIlmiah());
            System.out.printf("║ %-69s ║\n", " Suhu Air             : " + poisson.getSuhuAir());

            petList.HealthRecord health = poisson.getHealthRecord();
            System.out.printf("║ %-69s ║\n", " Pemeriksaan Terakhir : " + health.getLastCheckup());
            System.out.printf("║ %-69s ║\n", " Status Vaksin        : " + health.getVaccineStatus());
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                [ERROR] | Data bukan tipe ikan!!!.                     ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    public static void editIwak(Scanner scanner) {
        displayiwak();
        System.out.print("\nMasukkan nomor pet yang ingin diedit: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < listIkan.size()) {
            petList petEdit = listIkan.get(index);
            boolean editing = true;

            while (editing) {
                System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║ %-70s ║\n", "                         Mengedit pet: " + petEdit.getrasPet());
                function.displayMenuEditFish();
                int subPilihan = scanner.nextInt();
                scanner.nextLine();

                switch (subPilihan) {
                    case 1:
                        System.out.print("Masukkan nama baru: ");
                        String namaBaru = scanner.nextLine();
                        petEdit.setrasPet(namaBaru);
                        break;
                    case 2:
                        System.out.print("Masukkan harga baru: ");
                        double hargaBaru = scanner.nextDouble();
                        petEdit.setHargaPet(hargaBaru);
                        break;
                    case 3:
                        System.out.print("Masukkan stok baru: ");
                        int stokBaru = scanner.nextInt();
                        petEdit.setStokPet(stokBaru);
                        break;
                    case 4:
                        System.out.print("Masukkan jenis baru: ");
                        String jenisBaru = scanner.nextLine();
                        petEdit.setjenisPet(jenisBaru);
                        break;
                    case 5:
                        System.out.print("Masukkan diskon baru (dalam %): ");
                        double diskonBaru = scanner.nextDouble() / 100;
                        petEdit.setDiskonPet(diskonBaru);
                        break;
                    case 6:
                        if (petEdit instanceof ikan) {
                            System.out.print("Masukkan nama ilmiah baru: ");
                            String ilmiahBaru = scanner.nextLine();
                            ((ikan) petEdit).setIlmiah(ilmiahBaru);
                        }
                        break;
                    case 7:
                        if (petEdit instanceof ikan) {
                            System.out.print("Masukkan Suhu air baru: ");
                            String suhuBaru = scanner.nextLine();
                            ((ikan) petEdit).setSuhuAir(suhuBaru);
                        }
                        break;
                    case 8:
                        petList.HealthRecord record = petEdit.getHealthRecord();
                        System.out.print("Masukkan tanggal pemeriksaan terakhir (yyyy-mm-dd): ");
                        String tanggalString = scanner.nextLine();
                        try {
                            LocalDate tanggal = LocalDate.parse(tanggalString);
                            record.setLastCheckup(tanggal);
                        } catch (DateTimeParseException e) {
                            System.out.println(
                                    "\n╔═══════════════════════════════════════════════════════════════════════╗");
                            System.out.println(
                                    "║        [ERROR] | Format tanggal salah. Gunakan format yyyy-mm-dd      ║");
                            System.out.println(
                                    "╚═══════════════════════════════════════════════════════════════════════╝");
                        }
                        function.displayVaksin();
                        System.out.print("Masukkan status vaksin (Copy Statusnya): ");
                        String vaksinStatus = scanner.nextLine();
                        record.setVaccineStatus(vaksinStatus);
                        break;
                    case 0:
                        editing = false;
                        function.spasi();
                        function.retroSpinner("Menyimpan perubahan", 1500);
                        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                        System.out.println("║                   [SUCCESS] | BERHASIL MENGEDIT PET                   ║");
                        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                        break;
                    default:
                        System.out
                                .println("\n╔═══════════════════════════════════════════════════════════════════════╗");
                        System.out.println("║                      [ERROR] | PILIHAN TIDAK VALID                    ║");
                        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                }
            }
            System.out.println("\nSetelah mengubah data, pet menjadi:");
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n", "  [SUCCESS] | Menampilkan Detail ikan " + petEdit.getrasPet());
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-69s ║\n", " Ras ikan           : " + petEdit.getrasPet());
            String hargaFormatted = String.format("%, .2f", petEdit.getHargaPet()).replace(",", ".");
            System.out.printf("║ %-69s ║\n", " Harga                : Rp" + hargaFormatted);
            System.out.printf("║ %-69s ║\n", " Stok                 : " + petEdit.getStokPet());
            System.out.printf("║ %-69s ║\n", " Jenis                : " + petEdit.getjenisPet());
            System.out.printf("║ %-69s ║\n", " Diskon               : " + (petEdit.getDiskonPet() * 100) + "%");
            if (petEdit instanceof ikan) {
                String ilmiah = ((ikan) petEdit).getIlmiah();
                System.out.printf("║ %-69s ║\n", " Nama ilmiah          : " + ilmiah);
            }
            if (petEdit instanceof ikan) {
                String suhu = ((ikan) petEdit).getSuhuAir();
                System.out.printf("║ %-69s ║\n", " Suhu Air             : " + suhu);
            }

            petList.HealthRecord health = petEdit.getHealthRecord();
            System.out.printf("║ %-69s ║\n", " Pemeriksaan Terakhir : " + health.getLastCheckup());
            System.out.printf("║ %-69s ║\n", " Status Vaksin        : " + health.getVaccineStatus());
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");

        } else {
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                   [ERROR] | NOMOR PET TIDAK VALID                     ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }
}
