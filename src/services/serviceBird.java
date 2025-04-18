package services; // package services

import java.time.LocalDate; // 📅 Import LocalDate untuk menangani tanggal pemeriksaan terakhir 📅
import java.time.format.DateTimeParseException; // 📅 Import DateTimeParseException untuk menangani kesalahan format tanggal 📅
import java.util.ArrayList; // 📦 Import ArrayList untuk menyimpan daftar pet yang tersedia 📦
import java.util.Scanner; // 📦 Import Scanner untuk membaca input dari pengguna 📦

import models.burung; // 🐦 Import burung untuk menyimpan data pet burung 🐦
import utils.function; // 📦 Import function untuk menampilkan menu dan judul aplikasi 📦
import models.petList; // 📦 Import petList untuk menyimpan data pet yang tersedia 📦

public class serviceBird {
    static ArrayList<burung> listburung = service.listBurung; // 📦 ArrayList untuk menyimpan daftar pet burung yang tersedia 📦
    //function display burung
    public static void displayAllbuwungs() { 
         // 📦 Cek apakah list burung kosong
        if (listburung == null || listburung.isEmpty()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                     Tidak ada data pet yang tersedia                  ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        } else { // 📋 Menampilkan daftar burung
            int i = 1;
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           Daftar Semua Pet                            ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-10s | %-14s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon");
            System.out.println("╠═════|═══════════════|═══════════════════|════════════|════════════════╣");
            // 🔄 Loop untuk menampilkan setiap pet dalam daftar 🔄
            for (petList buwung : listburung) {
                if (buwung instanceof burung) {
                    String hargaFormatted = String.format("%, .2f", buwung.getHargaPet()).replace(",", ".");
                    System.out.printf("║ %-3s | %-13s | Rp%-15s | %-10d | %-3.0f%%           ║\n", i,
                            buwung.getrasPet(), hargaFormatted, buwung.getStokPet(),
                            buwung.getDiskonPet() * 100);
                    i++; // 🔢 Menambah nomor urut
                }
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // function Menampilkan detail burung berdasarkan pilihan pengguna
    public static void displayDetailbuwung(Scanner scanner) {
        // 📋 Menampilkan seluruh daftar burung terlebih dahulu
        displayAllbuwungs();
        // ❗ Cek apakah daftar burung kosong
        if (listburung == null || listburung.isEmpty()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║              [ERROR] | Tidak ada data pet yang tersedia               ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }
        // ❓ Konfirmasi apakah user ingin melihat detail salah satu burung
        System.out.print("\nApakah ingin melihat detail salah satu pet? (y/n): ");
        String konfirmasi = scanner.nextLine().trim().toLowerCase();

        if (!konfirmasi.equals("y")) {
            return; // 🔙 Jika tidak, kembali ke menu sebelumnya
        }
        // 🔢 Meminta input nomor burung yang ingin dilihat detailnya
        System.out.print("Masukkan nomor pet burung yang ingin dilihat: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); 
        
        // ⚠️ apakah nomor yang dipilih valid
        if (pilihan < 1 || pilihan > listburung.size()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║        [ERROR] | Nomor tidak tersedia. Silakan coba lagi.             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }
        // 🎯 Mengambil objek burung yang dipilih berdasarkan nomor
        petList petDipilih = listburung.get(pilihan - 1);
        // ✅ Pastikan objek tersebut benar-benar instance dari class burung
        if (petDipilih instanceof burung) { // instaceof untuk mengecek apakah petDipilih adalah burung
            // 🦜 Mengkonversi petDipilih menjadi objek burung
            burung oiseau = (burung) petDipilih;
            // 🖼️ Menampilkan header detail
            function.spasi();
            function.spinnerLoading("MenCari data pet ", 2000);
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n", "  [SUCCESS] | Menampilkan Detail burung " + oiseau.getrasPet());
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-69s ║\n", " Ras burung           : " + oiseau.getrasPet());
            String hargaFormatted = String.format("%, .2f", oiseau.getHargaPet()).replace(",", ".");
            System.out.printf("║ %-69s ║\n", " Harga                : Rp" + hargaFormatted);
            System.out.printf("║ %-69s ║\n", " Stok                 : " + oiseau.getStokPet());
            System.out.printf("║ %-69s ║\n", " Jenis                : " + oiseau.getjenisPet());
            System.out.printf("║ %-69s ║\n", " Diskon               : " + (oiseau.getDiskonPet() * 100) + "%");
            System.out.printf("║ %-69s ║\n", " Berat                : " + oiseau.getBerat());
            System.out.printf("║ %-69s ║\n", " Kicauan              : " + oiseau.getKicauan());
            // 💉 Menampilkan informasi kesehatan burung
            petList.HealthRecord health = oiseau.getHealthRecord();
            System.out.printf("║ %-69s ║\n", " Pemeriksaan Terakhir : " + health.getLastCheckup());
            System.out.printf("║ %-69s ║\n", " Status Vaksin        : " + health.getVaccineStatus());
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else {  // ❌ Jika data bukan burung, tampilkan pesan error
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                [ERROR] | Data bukan tipe burung!!!.                   ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // function untuk menampilkan menu edit burung
    public static void editbuwung(Scanner scanner) {
        displayAllbuwungs();// 📋 Tampilkan semua burung yang tersedia terlebih dahulu
        System.out.print("\nMasukkan nomor pet yang ingin diedit: ");// 🔢 Input nomor burung yang ingin diedit
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        // ✅ Cek apakah indeks valid   
        if (index >= 0 && index < listburung.size()) {
            petList petEdit = listburung.get(index); // 🎯 Ambil objek burung berdasarkan indeks
            boolean editing = true; 

            while (editing) { // 🖊️ Tampilkan menu edit burung
                System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║ %-70s ║\n", "                         Mengedit pet: " + petEdit.getrasPet());
                function.displayMenuEditbird();// 📋 Tampilkan menu edit burung
                int subPilihan = scanner.nextInt();
                scanner.nextLine();
                // ✏️ Gunakan switch-case untuk memilih bagian yang ingin diedit
                switch (subPilihan) {
                    case 1: // 📝 Edit nama/ras burung
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
                    case 6: // ⚖️ Edit berat (khusus burung)
                        if (petEdit instanceof burung) {
                            System.out.print("Masukkan berat baru: ");
                            String beratBaru = scanner.nextLine();
                            ((burung) petEdit).setBerat(beratBaru);
                        }
                        break;
                    case 7:  // 🎶 Edit kicauan (khusus burung)
                        if (petEdit instanceof burung) {
                            System.out.print("Masukkan Kicauan baru: ");
                            String kicauanBaru = scanner.nextLine();
                            ((burung) petEdit).setKicauan(kicauanBaru);
                        }
                        break;
                    case 8:// 🩺 Edit data kesehatan
                        petList.HealthRecord record = petEdit.getHealthRecord();
                        System.out.print("Masukkan tanggal pemeriksaan terakhir (yyyy-mm-dd): ");
                        String tanggalString = scanner.nextLine();
                        try {
                            LocalDate tanggal = LocalDate.parse(tanggalString);
                            record.setLastCheckup(tanggal);
                        } catch (DateTimeParseException e) {// ⚠️ Format tanggal salah
                            System.out.println( "\n╔═══════════════════════════════════════════════════════════════════════╗");
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
            System.out.println("\nSetelah mengubah data, pet menjadi:");// 📋 Tampilkan data setelah diedit
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n", "  [SUCCESS] | Menampilkan Detail Burung " + petEdit.getrasPet());
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-69s ║\n", " Ras burung           : " + petEdit.getrasPet());
            String hargaFormatted = String.format("%, .2f", petEdit.getHargaPet()).replace(",", ".");
            System.out.printf("║ %-69s ║\n", " Harga                : Rp" + hargaFormatted);
            System.out.printf("║ %-69s ║\n", " Stok                 : " + petEdit.getStokPet());
            System.out.printf("║ %-69s ║\n", " Jenis                : " + petEdit.getjenisPet());
            System.out.printf("║ %-69s ║\n", " Diskon               : " + (petEdit.getDiskonPet() * 100) + "%");
            if (petEdit instanceof burung) {
                String berat = ((burung) petEdit).getBerat();
                System.out.printf("║ %-69s ║\n", " Berat                : " + berat);
            }
            if (petEdit instanceof burung) {
                String kicaua = ((burung) petEdit).getKicauan();
                System.out.printf("║ %-69s ║\n", " kicauan              : " + kicaua);
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
