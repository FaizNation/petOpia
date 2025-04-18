package services; // package services

import java.time.LocalDate; // 📅 Import LocalDate untuk menangani tanggal pemeriksaan terakhir 📅
import java.time.format.DateTimeParseException; // ❗ Import DateTimeParseException untuk menangani kesalahan format tanggal ❗
import java.util.ArrayList; // 📦 Import ArrayList untuk menyimpan daftar pet yang tersedia 📦
import java.util.Scanner; // 📦 Import Scanner untuk membaca input dari pengguna 📦

import models.ikan; // 🐟 Import model ikan untuk mengelola data ikan 🐟
import utils.function; // 📦 Import function untuk menampilkan menu dan judul aplikasi 📦
import models.petList; // 📦 Import petList untuk menyimpan data pet yang tersedia 📦

public class serviceFish {
    static ArrayList<ikan> listIkan = service.listIkan;// 📦 ArrayList untuk menyimpan daftar pet ikan yang tersedia 📦
    //function display ikan
    public static void displayiwak() {
        // 📦 Cek apakah list ikan kosong
        if (listIkan == null || listIkan.isEmpty()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                     Tidak ada data pet yang tersedia                  ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        } else { // 📋 Menampilkan daftar ikan
            int i = 1;
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           Daftar Semua Pet                            ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-10s | %-14s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon");
            System.out.println("╠═════|═══════════════|═══════════════════|════════════|════════════════╣");
            // 🔄 Loop untuk menampilkan setiap pet dalam daftar 🔄
            for (petList poisson : listIkan) {
                if (poisson instanceof ikan) {
                    String hargaFormatted = String.format("%, .2f",
                            poisson.getHargaPet()).replace(",", ".");
                    System.out.printf("║ %-3s | %-13s | Rp%-15s | %-10d | %-3.0f%%           ║\n", i,
                            poisson.getrasPet(), hargaFormatted,
                            poisson.getStokPet(),
                            poisson.getDiskonPet() * 100);
                    i++; // 🔢 Menambah nomor urut
                }
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // function Menampilkan detail ikan berdasarkan pilihan pengguna
    public static void displayDetailIwak(Scanner scanner) {
        // 📋 Menampilkan seluruh daftar ikan terlebih dahulu
        displayiwak();
        if (listIkan == null || listIkan.isEmpty()) { // ❗ Cek apakah daftar ikan kosong
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║              [ERROR] | Tidak ada data pet yang tersedia               ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }
        // ❓ Konfirmasi apakah user ingin melihat detail salah satu ikan
        System.out.print("\nApakah ingin melihat detail salah satu pet? (y/n): ");
        String konfirmasi = scanner.nextLine().trim().toLowerCase();

        if (!konfirmasi.equals("y")) {
            return; // 🔙 Jika tidak, kembali ke menu sebelumnya
        }
        System.out.print("Masukkan nomor pet ikan yang ingin dilihat: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); 
        // ⚠️ apakah nomor yang dipilih valid
        if (pilihan < 1 || pilihan > listIkan.size()) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║        [ERROR] | Nomor tidak tersedia. Silakan coba lagi.             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        }
        // 🎯 Mengambil objek ikan yang dipilih berdasarkan nomor
        petList petDipilih = listIkan.get(pilihan - 1);
        // ✅ Pastikan objek tersebut benar-benar instance dari class ikan
        if (petDipilih instanceof ikan) { // instaceof untuk mengecek apakah petDipilih adalah ikan
            ikan poisson = (ikan) petDipilih;// 🐠 Mengkonversi petDipilih menjadi objek ikan
            function.spasi();
            function.spinnerLoading("MenCari data pet ", 2000);
            // 🖼️ Menampilkan header detail
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
            // 💉 Menampilkan informasi kesehatan ikan
            petList.HealthRecord health = poisson.getHealthRecord();
            System.out.printf("║ %-69s ║\n", " Pemeriksaan Terakhir : " + health.getLastCheckup());
            System.out.printf("║ %-69s ║\n", " Status Vaksin        : " + health.getVaccineStatus());
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else { // ❌ Jika data bukan ikan, tampilkan pesan error
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                [ERROR] | Data bukan tipe ikan!!!.                     ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // function untuk menampilkan menu edit ikan
    public static void editIwak(Scanner scanner) {
        displayiwak(); // 📋 Tampilkan semua ikan yang tersedia terlebih dahulu
        System.out.print("\nMasukkan nomor pet yang ingin diedit: ");  // 🔢 Input nomor ikan yang ingin diedit
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        // ✅ Cek apakah indeks valid
        if (index >= 0 && index < listIkan.size()) { // 🎯 Ambil objek ikan berdasarkan indeks
            petList petEdit = listIkan.get(index);
            boolean editing = true;
            // ✏️ Gunakan switch-case untuk memilih bagian yang ingin diedit
            while (editing) { // 🖊️ Tampilkan menu edit ikan
                System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║ %-70s ║\n", "                         Mengedit pet: " + petEdit.getrasPet());
                function.displayMenuEditFish(); // 📜 Menampilkan menu edit ikan 📜
                int subPilihan = scanner.nextInt();
                scanner.nextLine();
                // ✏️ Mengubah nilai mahasiswa menggunakan setter & switch case ✏️
                switch (subPilihan) {
                    case 1: // 📝 Edit nama/ras ikan
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
                    case 6: // 🐠 Edit nama ilmiah
                        if (petEdit instanceof ikan) {
                            System.out.print("Masukkan nama ilmiah baru: ");
                            String ilmiahBaru = scanner.nextLine();
                            ((ikan) petEdit).setIlmiah(ilmiahBaru);
                        }
                        break;
                    case 7: // 🌡️ Edit suhu air
                        if (petEdit instanceof ikan) {
                            System.out.print("Masukkan Suhu air baru: ");
                            String suhuBaru = scanner.nextLine();
                            ((ikan) petEdit).setSuhuAir(suhuBaru);
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
