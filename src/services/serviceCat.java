package services; // package services

import java.time.LocalDate; // 📅 Import LocalDate untuk menangani tanggal pemeriksaan terakhir 📅
import java.time.format.DateTimeParseException; // ❗ Import DateTimeParseException untuk menangani kesalahan format tanggal ❗
import java.util.ArrayList; // 📦 Import ArrayList untuk menyimpan daftar pet yang tersedia 📦
import java.util.Scanner; // 📦 Import Scanner untuk membaca input dari pengguna 📦

import models.kucing; // 🐱 Import kucing untuk menyimpan data kucing yang tersedia 🐱
import utils.function; // 📦 Import function untuk menampilkan menu dan judul aplikasi 📦
import models.petList; // 📦 Import petList untuk menyimpan data pet yang tersedia 📦

public class serviceCat {
    static ArrayList<kucing> listKucing = service.listKucing; // 📦 ArrayList untuk menyimpan daftar pet kucing yang tersedia 📦
    //function display kucing
    public static void displayAllCats() {
        // 📦 Cek apakah list kucing kosong
        if (listKucing == null || listKucing.isEmpty()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                     Tidak ada data pet yang tersedia                  ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        } else { // 📋 Menampilkan daftar kucing
            int i = 1;
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           Daftar Semua Pet                            ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-10s | %-14s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon");
            System.out.println("╠═════|═══════════════|═══════════════════|════════════|════════════════╣");
            // 🔄 Loop untuk menampilkan setiap pet dalam daftar 🔄
            for (petList cat : listKucing) {
                if (cat instanceof kucing) {
                    String hargaFormatted = String.format("%, .2f", cat.getHargaPet()).replace(",", ".");
                    System.out.printf("║ %-3s | %-13s | Rp%-15s | %-10d | %-3.0f%%           ║\n", i,
                            cat.getrasPet(), hargaFormatted, cat.getStokPet(),
                            cat.getDiskonPet() * 100);
                    i++; // 🔢 Menambah nomor urut
                }
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // function Menampilkan detail kucing berdasarkan pilihan pengguna
    public static void displayDetailCat(Scanner scanner) {
        // 📋 Menampilkan seluruh daftar kucing terlebih dahulu
        displayAllCats();
        if (listKucing == null || listKucing.isEmpty()) { // ❗ Cek apakah daftar kucing kosong
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║              [ERROR] | Tidak ada data pet yang tersedia               ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }
        // ❓ Konfirmasi apakah user ingin melihat detail salah satu kucing
        System.out.print("\nApakah ingin melihat detail salah satu pet? (y/n): ");
        String konfirmasi = scanner.nextLine().trim().toLowerCase();

        if (!konfirmasi.equals("y")) {
            return; // 🔙 Jika tidak, kembali ke menu sebelumnya
        }
        System.out.print("Masukkan nomor pet kucing yang ingin dilihat: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); 
        // ⚠️ apakah nomor yang dipilih valid
        if (pilihan < 1 || pilihan > listKucing.size()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║        [ERROR] | Nomor tidak tersedia. Silakan coba lagi.             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }
        // 🎯 Mengambil objek kucing yang dipilih berdasarkan nomor
        petList petDipilih = listKucing.get(pilihan - 1);
        // ✅ Pastikan objek tersebut benar-benar instance dari class kucing
        if (petDipilih instanceof kucing) { // instaceof untuk mengecek apakah petDipilih adalah kucing
            kucing elgato = (kucing) petDipilih;
            function.spasi();
            function.spinnerLoading("MenCari data pet ", 2000); // 🐱 Mengkonversi petDipilih menjadi objek kucing
            // 🖼️ Menampilkan header detail
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n", "  [SUCCESS] | Menampilkan Detail Kucing " + elgato.getrasPet());
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-69s ║\n", " Ras Kucing           : " + elgato.getrasPet());
            String hargaFormatted = String.format("%, .2f", elgato.getHargaPet()).replace(",", ".");
            System.out.printf("║ %-69s ║\n", " Harga                : Rp" + hargaFormatted);
            System.out.printf("║ %-69s ║\n", " Stok                 : " + elgato.getStokPet());
            System.out.printf("║ %-69s ║\n", " Jenis                : " + elgato.getjenisPet());
            System.out.printf("║ %-69s ║\n", " Diskon               : " + (elgato.getDiskonPet() * 100) + "%");
            System.out.printf("║ %-69s ║\n", " Mantel Bulu          : " + elgato.getManteBulu());
            System.out.printf("║ %-69s ║\n", " Keramahan            : " + elgato.getKeramahan());
            // 💉 Menampilkan informasi kesehatan kucing
            petList.HealthRecord health = elgato.getHealthRecord();
            System.out.printf("║ %-69s ║\n", " Pemeriksaan Terakhir : " + health.getLastCheckup());
            System.out.printf("║ %-69s ║\n", " Status Vaksin        : " + health.getVaccineStatus());
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else { // ❌ Jika data bukan kucing, tampilkan pesan error
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                [ERROR] | Data bukan tipe kucing!!!.                   ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // function untuk menampilkan menu edit kucing
    public static void editCats(Scanner scanner) {
        displayAllCats(); // 📋 Tampilkan semua kucing yang tersedia terlebih dahulu
        System.out.print("\nMasukkan nomor pet yang ingin diedit: "); // 🔢 Input nomor kucing yang ingin diedit
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        // ✅ Cek apakah indeks valid 
        if (index >= 0 && index < listKucing.size()) { // 🎯 Ambil objek kucing berdasarkan indeks
            petList petEdit = listKucing.get(index);
            boolean editing = true;

            while (editing) { // 🖊️ Tampilkan menu edit kucing
                System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║ %-70s ║\n", "                         Mengedit pet: " + petEdit.getrasPet());
                function.displayMenuEditCats(); // 📜 Menampilkan menu edit kucing 📜
                int subPilihan = scanner.nextInt();
                scanner.nextLine();
                // ✏️ Gunakan switch-case untuk memilih bagian yang ingin diedit
                switch (subPilihan) {
                    case 1: // 📝 Edit nama/ras kucing
                        System.out.print("Masukkan nama baru: ");
                        String namaBaru = scanner.nextLine();
                        petEdit.setrasPet(namaBaru);
                        break;
                    case 2: // 💸 Edit harga
                        System.out.print("Masukkan harga baru: ");
                        double hargaBaru = scanner.nextDouble();
                        petEdit.setHargaPet(hargaBaru);
                        break;
                    case 3: // 📦 Edit stok
                        System.out.print("Masukkan stok baru: ");
                        int stokBaru = scanner.nextInt();
                        petEdit.setStokPet(stokBaru);
                        break;
                    case 4: // 🧬 Edit jenis
                        System.out.print("Masukkan jenis baru: ");
                        String jenisBaru = scanner.nextLine();
                        petEdit.setjenisPet(jenisBaru);
                        break;
                    case 5: // 🎁 Edit diskon
                        System.out.print("Masukkan diskon baru (dalam %): ");
                        double diskonBaru = scanner.nextDouble() / 100;
                        petEdit.setDiskonPet(diskonBaru);
                        break;
                    case 6: // 🐾 Edit mantel bulu (khusus kucing)
                        if (petEdit instanceof kucing) {
                            System.out.print("Masukkan mantel bulu baru: ");
                            String mantelBaru = scanner.nextLine();
                            ((kucing) petEdit).setMantelBulu(mantelBaru);
                        }
                        break;
                    case 7: // ⭐ Edit rating (khusus kucing)
                        function.displayRating(); // 📊 Menampilkan rating kucing 📊
                        if (petEdit instanceof kucing) {
                            System.out.print("Masukkan Rating keramahan (Copy Ratingnya): ");
                            String ramahBaru = scanner.nextLine();
                            ((kucing) petEdit).setMantelBulu(ramahBaru);
                        }
                        break;
                    case 8: // 🩺 Edit data kesehatan
                        petList.HealthRecord record = petEdit.getHealthRecord();
                        System.out.print("Masukkan tanggal pemeriksaan terakhir (yyyy-mm-dd): ");
                        String tanggalString = scanner.nextLine();
                        try {
                            LocalDate tanggal = LocalDate.parse(tanggalString);
                            record.setLastCheckup(tanggal);
                        } catch (DateTimeParseException e) {
                            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
                            System.out.println("║        [ERROR] | Format tanggal salah. Gunakan format yyyy-mm-dd      ║");
                            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                        }
                        function.displayVaksin(); // 💉 Tampilkan opsi vaksin dan masukkan status vaksin
                        System.out.print("Masukkan status vaksin (Copy Statusnya): ");
                        String vaksinStatus = scanner.nextLine();
                        record.setVaccineStatus(vaksinStatus);
                        break;
                    case 0: // 🚪 Keluar dari mode edit
                        editing = false;
                        function.spasi();
                        function.retroSpinner("Menyimpan perubahan", 1500);// 🌀 Tampilkan animasi keluar
                        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                        System.out.println("║                   [SUCCESS] | BERHASIL MENGEDIT PET                   ║");
                        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                        break;
                    default: // ❌ Input pilihan tidak valid
                        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
                        System.out.println("║                      [ERROR] | PILIHAN TIDAK VALID                    ║");
                        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                }
            }
                System.out.println("\nSetelah mengubah data, pet menjadi:"); // 📋 Tampilkan data setelah diedit
                System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║ %-69s ║\n" , "  [SUCCESS] | Menampilkan Detail Kucing " + petEdit.getrasPet());
                System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
                System.out.printf("║ %-69s ║\n", " Ras Kucing           : " + petEdit.getrasPet());
                String hargaFormatted = String.format("%, .2f", petEdit.getHargaPet()).replace(",", ".");
                System.out.printf("║ %-69s ║\n", " Harga                : Rp" + hargaFormatted);
                System.out.printf("║ %-69s ║\n", " Stok                 : " + petEdit.getStokPet());
                System.out.printf("║ %-69s ║\n", " Jenis                : " + petEdit.getjenisPet());
                System.out.printf("║ %-69s ║\n", " Diskon               : " + (petEdit.getDiskonPet() * 100) + "%");
                if (petEdit instanceof kucing) {
                    String mantel = ((kucing) petEdit).getManteBulu();
                    System.out.printf("║ %-69s ║\n", " Mantel Bulu          : " + mantel);
                }
                if (petEdit instanceof kucing) {
                    String bulu = ((kucing) petEdit).getKeramahan();
                    System.out.printf("║ %-69s ║\n", " Keramahan            : " + bulu);
                }
                // 🧾 Tampilkan info kesehatan
                petList.HealthRecord health = petEdit.getHealthRecord();
                System.out.printf("║ %-69s ║\n", " Pemeriksaan Terakhir : " + health.getLastCheckup());
                System.out.printf("║ %-69s ║\n", " Status Vaksin        : " + health.getVaccineStatus());
                System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else { // ❌ Jika indeks tidak valid
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                   [ERROR] | NOMOR PET TIDAK VALID                     ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

}
