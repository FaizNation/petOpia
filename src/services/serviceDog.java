package services; // package services

import java.time.LocalDate; // 📅 Import LocalDate untuk menangani tanggal pemeriksaan terakhir 📅
import java.time.format.DateTimeParseException; // 📅 Import DateTimeParseException untuk menangani kesalahan format tanggal 📅
import java.util.ArrayList; // 📦 Import ArrayList untuk menyimpan daftar pet yang tersedia 📦
import java.util.Scanner; // 📦 Import Scanner untuk membaca input dari pengguna 📦

import models.anjing; // 🐶 Import model anjing untuk mengelola data anjing 🐶
import utils.function; // 📦 Import function untuk menampilkan menu dan judul aplikasi 📦
import models.petList; // 📦 Import petList untuk menyimpan data pet yang tersedia 📦

public class serviceDog {
    static ArrayList<anjing> listAnjing = service.listAnjing; // 📦 ArrayList untuk menyimpan daftar pet anjing yang tersedia 📦
    //function display anjing
    public static void displayAlldoggys() {
        // 📦 Cek apakah list anjing kosong
        if (listAnjing == null || listAnjing.isEmpty()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                     Tidak ada data pet yang tersedia                  ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        } else { // 📋 Menampilkan daftar anjing
            int i = 1;
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           Daftar Semua Pet                            ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-10s | %-14s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon");
            System.out.println("╠═════|═══════════════|═══════════════════|════════════|════════════════╣");
            // 🔄 Loop untuk menampilkan setiap pet dalam daftar 🔄
            for (petList doggy : listAnjing) {
                if (doggy instanceof anjing) {
                    String hargaFormatted = String.format("%, .2f", doggy.getHargaPet()).replace(",", ".");
                    System.out.printf("║ %-3s | %-13s | Rp%-15s | %-10d | %-3.0f%%           ║\n", i,
                            doggy.getrasPet(), hargaFormatted, doggy.getStokPet(),
                            doggy.getDiskonPet() * 100);
                    i++; // 🔢 Menambah nomor urut
                }
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }

    }

    // function Menampilkan detail anjing berdasarkan pilihan pengguna
    public static void displayDetailDoggy(Scanner scanner) {
        // 📋 Menampilkan seluruh daftar anjing terlebih dahulu
        displayAlldoggys();
        if (listAnjing == null || listAnjing.isEmpty()) { // ❗ Cek apakah daftar anjing kosong
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║              [ERROR] | Tidak ada data pet yang tersedia               ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }
        // ❓ Konfirmasi apakah user ingin melihat detail salah satu anjing
        System.out.print("\nApakah ingin melihat detail salah satu pet? (y/n): ");
        String konfirmasi = scanner.nextLine().trim().toLowerCase();

        if (!konfirmasi.equals("y")) {
            return; // 🔙 Jika tidak, kembali ke menu sebelumnya
        }
        System.out.print("Masukkan nomor pet anjing yang ingin dilihat: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        // ⚠️ apakah nomor yang dipilih valid
        if (pilihan < 1 || pilihan > listAnjing.size()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║        [ERROR] | Nomor tidak tersedia. Silakan coba lagi.             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }
        // 🎯 Mengambil objek anjing yang dipilih berdasarkan nomor
        petList petDipilih = listAnjing.get(pilihan - 1);
        // ✅ Pastikan objek tersebut benar-benar instance dari class anjing
        if (petDipilih instanceof anjing) { // instaceof untuk mengecek apakah petDipilih adalah anjing
            anjing chien = (anjing) petDipilih; // 🐶 Mengkonversi petDipilih menjadi objek anjing
            function.spasi();
            function.spinnerLoading("MenCari data pet ", 2000);
            // 🖼️ Menampilkan header detail
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n", "  [SUCCESS] | Menampilkan Detail anjing " + chien.getrasPet());
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-69s ║\n", " Ras anjing           : " + chien.getrasPet());
            String hargaFormatted = String.format("%, .2f", chien.getHargaPet()).replace(",", ".");
            System.out.printf("║ %-69s ║\n", " Harga                : Rp" + hargaFormatted);
            System.out.printf("║ %-69s ║\n", " Stok                 : " + chien.getStokPet());
            System.out.printf("║ %-69s ║\n", " Jenis                : " + chien.getjenisPet());
            System.out.printf("║ %-69s ║\n", " Diskon               : " + (chien.getDiskonPet() * 100) + "%");
            System.out.printf("║ %-69s ║\n", " Tinggi               : " + chien.getTinggi());
            System.out.printf("║ %-69s ║\n", " Rating Latihan       : " + chien.getLatihan());
            // 💉 Menampilkan informasi kesehatan anjing
            petList.HealthRecord health = chien.getHealthRecord();
            System.out.printf("║ %-69s ║\n", " Pemeriksaan Terakhir : " + health.getLastCheckup());
            System.out.printf("║ %-69s ║\n", " Status Vaksin        : " + health.getVaccineStatus());
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else { // ❌ Jika data bukan anjing, tampilkan pesan error
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                [ERROR] | Data bukan tipe Anjing!!!.                   ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // function untuk menampilkan menu edit anjing
    public static void editDoggy(Scanner scanner) {
        displayAlldoggys(); // 📋 Tampilkan semua anjing yang tersedia terlebih dahulu
        System.out.print("\nMasukkan nomor pet yang ingin diedit: "); // 🔢 Input nomor anjing yang ingin diedit
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        // ✅ Cek apakah indeks valid 
        if (index >= 0 && index < listAnjing.size()) { // 🎯 Ambil objek anjing berdasarkan indeks
            petList petEdit = listAnjing.get(index);
            boolean editing = true;

            while (editing) { // 🖊️ Tampilkan menu edit anjing
                System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║ %-70s ║\n", "                         Mengedit pet: " + petEdit.getrasPet());
                function.displayMenuEditDogs(); // 📜 Menampilkan menu edit anjing 📜
                int subPilihan = scanner.nextInt();
                scanner.nextLine();
                // ✏️ Gunakan switch-case untuk memilih bagian yang ingin diedit
                switch (subPilihan) {
                    case 1: // 📝 Edit nama/ras anjing
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
                    case 6: // 📏 Edit tinggi
                        if (petEdit instanceof anjing) {
                            System.out.print("Masukkan tinggi baru: ");
                            String tinggiBaru = scanner.nextLine();
                            ((anjing) petEdit).setTinggi(tinggiBaru);
                        }
                        break;
                    case 7: // ⭐ Edit rating latihan
                        function.displayRating(); // 📊 Menampilkan rating 📊
                        if (petEdit instanceof anjing) {
                            System.out.print("Masukkan Rating Latihan (Copy Ratingnya): ");
                            String latihanBaru = scanner.nextLine();
                            ((anjing) petEdit).setLatihan(latihanBaru);
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
                            System.out.println( "\n╔═══════════════════════════════════════════════════════════════════════╗");
                            System.out.println("║        [ERROR] | Format tanggal salah. Gunakan format yyyy-mm-dd      ║");
                            System.out.println( "╚═══════════════════════════════════════════════════════════════════════╝");
                        }
                        function.displayVaksin(); // 💉 Tampilkan opsi vaksin dan masukkan status vaksin
                        System.out.print("Masukkan status vaksin (Copy Statusnya): ");
                        String vaksinStatus = scanner.nextLine();
                        record.setVaccineStatus(vaksinStatus);
                        break;
                    case 0:  // 🚪 Keluar dari mode edit
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

            System.out.println("\nSetelah mengubah data, pet menjadi:");
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n", "  [SUCCESS] | Menampilkan Detail anjing " + petEdit.getrasPet());
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-69s ║\n", " Ras anjing           : " + petEdit.getrasPet());
            String hargaFormatted = String.format("%, .2f", petEdit.getHargaPet()).replace(",", ".");
            System.out.printf("║ %-69s ║\n", " Harga                : Rp" + hargaFormatted);
            System.out.printf("║ %-69s ║\n", " Stok                 : " + petEdit.getStokPet());
            System.out.printf("║ %-69s ║\n", " Jenis                : " + petEdit.getjenisPet());
            System.out.printf("║ %-69s ║\n", " Diskon               : " + (petEdit.getDiskonPet() * 100) + "%");
            if (petEdit instanceof anjing) {
                String tinggi = ((anjing) petEdit).getTinggi();
                System.out.printf("║ %-69s ║\n", " tinggi               : " + tinggi);
            }
            if (petEdit instanceof anjing) {
                String latihan = ((anjing) petEdit).getLatihan();
                System.out.printf("║ %-69s ║\n", " Latihan              : " + latihan);
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
