package services; // package services
// 📦 Import yang diperlukan untuk aplikasi 📦
import java.time.LocalDate; // 📦 Import LocalDate untuk menyimpan tanggal 📦
import java.util.ArrayList; // 📦 Import ArrayList untuk menyimpan daftar pet yang tersedia 📦
import java.util.Comparator; // 📦 Import Comparator untuk membandingkan objek 📦
import java.util.List; // 📦 Import List untuk menyimpan daftar pet yang tersedia 📦
import java.util.Scanner; // 📦 Import Scanner untuk membaca input dari pengguna 📦

import models.*; // 📦 Import model-model pet yang digunakan dalam aplikasi 📦
import utils.function; // 📦 Import function untuk menampilkan function yang diperlukan 📦

public class service { 
    static ArrayList<petList> listPet = new ArrayList<>();  // 📦 ArrayList untuk menyimpan daftar pet yang tersedia 📦

    public static ArrayList<kucing> listKucing = new ArrayList<>(); // 📦 ArrayList untuk menyimpan daftar kucing 📦
    public static ArrayList<anjing> listAnjing = new ArrayList<>(); // 📦 ArrayList untuk menyimpan daftar anjing 📦
    public static ArrayList<burung> listBurung = new ArrayList<>(); // 📦 ArrayList untuk menyimpan daftar burung 📦
    public static ArrayList<ikan> listIkan = new ArrayList<>();     // 📦 ArrayList untuk menyimpan daftar ikan 📦
    static {
        listPet(); // 📦 Memanggil method listPet untuk mengisi daftar pet awal 📦
    }

    public static void listPet() {
        // 📌 Menambahkan daftar pet awal ke dalam list 📌
        // 😺 LIST KUCING 😺
        listKucing.add(new kucing("Persia", 6500000, 10, "Kucing", 0.1,
                "panjang dan mewah", "■■■■■", LocalDate.of(2024, 12, 25), "Lengkap"));
        listKucing.add(new kucing("Anggora", 1400000, 8, "Kucing", 0.08,
                "panjang dan padat", "■■■■■", LocalDate.of(2024, 2, 29), "Ulang"));
        listKucing.add(new kucing("Maine coon", 1800000, 5, "Kucing", 0.12,
                "Padat dan halus", "■■■■■", LocalDate.of(2024, 9, 25), "Sebagian"));
        // 🐶 LIST ANJING 🐶
        listAnjing.add(new anjing("Golden", 4750000, 5, "Anjing", 0.12,
                "22-24 inchi", "■■■■-", LocalDate.of(2023, 11, 25), "Lengkap"));
        listAnjing.add(new anjing("Husky", 5500000, 4, "Anjing", 0.4,
                "21-23 inci", "■■■■-", LocalDate.of(2021, 8, 25), "Lengkap"));
        listAnjing.add(new anjing("Bulldog", 23000000, 6, "Anjing", 0.7,
                "14–16 inci", "■■■■■", LocalDate.of(2021, 7, 25), "Lengkap"));
        // 🐟 LIST IKAN 🐟
        listIkan.add(new ikan("Koi", 500000, 20, "Ikan", 0.3,
                "Cyprinus rubrofuscus", "18-28°C", LocalDate.of(2021, 5, 8), "Sebagian"));
        listIkan.add(new ikan("Arwana", 200000, 10, "Ikan", 0.10,
                "Scleropages formosus", "26-30°C", LocalDate.of(2019, 4, 6), "Lengkap"));
        listIkan.add(new ikan("Cupang", 25000, 50, "Ikan", 0,
                "Betta", "24-28°C", LocalDate.of(2024, 5, 3), "-"));
        // 🐦 LIST BURUNG 🐦
        listBurung.add(new burung("Kakaktua", 2500000, 8, "Burung", 1,
                "300-900 gram)", "keras dan melengking", LocalDate.of(2024, 2, 23), "-"));
        listBurung.add(new burung("Beo", 1500000, 15, "Burung", 1.1,
                "418-526 gram", "Dapat Bicara", LocalDate.of(2025, 4, 15), "-"));
        listBurung.add(new burung("Kenari", 500000, 28, "Burung", 2.0,
                "15-30 gram", " Gacor dan Panjang", LocalDate.of(2023, 5, 24), "-"));
    }

    // 📌Function display Menu pets
    public static void displayPets(Scanner scanner) {
        while (true) {
            function.displayMenuTampilkan();// 🐾 Menampilkan menu untuk menampilkan daftar pet 🐾
            int PilihDisplayPets = scanner.nextInt();
            scanner.nextLine();
            switch (PilihDisplayPets) {
                case 1:
                    displayAllPets();// 📋 Menampilkan semua pet 📋
                    break;
                case 2:
                    displayPetsHarga();// 💰 Menampilkan pet berdasarkan harga 💰
                    break;
                case 3:
                    displayJenis(scanner);// 🐾 Menampilkan pet berdasarkan jenis 🐾
                case 0:
                    return;// 🔙 Kembali ke menu utama 🔙
            }

        }
    }

    // 📌Function display jenis pets
    public static void displayJenis(Scanner scanner) {
        function.displayDaftarJenis(); // 📝 Menampilkan daftar jenis pet yang tersedia
        System.out.print("Pilih jenis pet yang ingin ditampilkan: "); // 🎯 Meminta input pilihan dari user
        int jenis = scanner.nextInt();
        scanner.nextLine();

        switch (jenis) {
            case 1:
                serviceCat.displayDetailCat(scanner);  // 🐱 Menampilkan detail semua kucing
                break;
            case 2:
                serviceDog.displayDetailDoggy(scanner); // 🐶 Menampilkan detail semua anjing
                break;
            case 3:
                serviceFish.displayDetailIwak(scanner); //🐟 Menampilkan detail semua ikan
                break;
            case 4:
                serviceBird.displayDetailbuwung(scanner); // 🐦 Menampilkan detail semua burung
                break;
            case 0:
                return;// 🔙 Kembali ke menu utama 🔙
        }
    }

    // 📌 Function untuk menampilkan seluruh data hewan peliharaan
    public static void displayAllPets() {
        List<petList> listPet = new ArrayList<>(); // 🧺 Membuat list kosong untuk menyimpan semua pet
        // ➕ Menambahkan seluruh daftar hewan dari masing-masing jenis ke listPet
        listPet.addAll(listKucing); // 🐱
        listPet.addAll(listAnjing); // 🐶
        listPet.addAll(listBurung); // 🐦
        listPet.addAll(listIkan);   // 🐟
        // ❗ Cek apakah daftar pet kosong
        if ((listPet == null || listPet.isEmpty())) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                     Tidak ada data pet yang tersedia                  |");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        } else {
            function.spasi();
            function.spinnerLoading("MenCari data pet ", 2000);
            int i = 1; // 🔢 Nomor urut
             // 📋 Header tampilan daftar pet
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           Daftar Semua Pet                            ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-4s | %-9s | %-8s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon", "Jenis");
            System.out.println("╠═════|═══════════════|═══════════════════|══════|═══════════|══════════╣");
            // 🔄 Loop untuk menampilkan setiap pet dalam daftar 🔄
            for (petList pet : listPet) {
                // 💰 Format harga agar tampil lebih rapi (gunakan titik sebagai pemisah ribuan)
                String hargaFormatted = String.format("%, .2f", pet.getHargaPet()).replace(",", ".");
                System.out.printf("║ %-3s | %-13s | Rp%-15s | %-4d | %-3.0f%%      | %-8s ║\n", i,
                        pet.getrasPet(), hargaFormatted, pet.getStokPet(),
                        pet.getDiskonPet() * 100, pet.getjenisPet());
                i++; // ⬆️ Naikkan nomor urut
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // 📌 Function untuk menampilkan menu pencarian hewan peliharaan
    public static void displayCari(Scanner scanner) {

        while (true) {
            function.displayMenuCari(); // 📋 Menampilkan menu pencarian (berdasarkan harga / jenis)
            int Cari = scanner.nextInt();
            scanner.nextLine();
            switch (Cari) {
                case 1:
                    displayCariPetHarga(scanner, listPet); // 🔍 cari berdasar harga 🔍
                    break;
                case 2:
                    displayCariPetJenis(scanner, listPet);// 🔍 cari berdasar jenis 🔍
                case 0:
                    return;  // 🔙 Kembali ke menu sebelumnya
            }
        }
    }

    // 📌 Function untuk mencari pet berdasarkan harga yang diinput user
    public static void displayCariPetHarga(Scanner scanner, ArrayList<petList> listpet) {
        List<petList> listPet = new ArrayList<>(); // 🧺 Tempat gabungan semua list pet

        listPet.addAll(listKucing); // 🐱
        listPet.addAll(listAnjing); // 🐶
        listPet.addAll(listBurung); // 🐦
        listPet.addAll(listIkan);   // 🐟

        System.out.print("Masukkan harga pet yang dicari: "); // 🧾 Input harga dari user
        double hargaDicari = scanner.nextDouble();
        scanner.nextLine();
        function.spasi();
        function.spinnerLoading("MenCari data pet ", 2000);

        // 🔍 Panggil method dari class petList untuk cari pet dengan harga tertentu
        List<petList> hasil = petList.cariSemuaPetByHarga(listPet, hargaDicari);

        // ✅ Jika hasil ditemukan
        if (hasil != null) {
            int i = 1;// 📌 Untuk penomoran daftar 📌
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n", "             Data pet dengan harga Rp" + hargaDicari + " ditemukan");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-4s | %-9s | %-8s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon", "Jenis");
            System.out.println("╠═════|═══════════════|═══════════════════|══════|═══════════|══════════╣");
            // 🔁 Tampilkan semua pet yang cocok dengan harga
            for (petList pet : hasil) {
                // 💸 Format harga dengan titik sebagai pemisah ribuan
                String hargaFormatted = String.format("%, .2f", pet.getHargaPet()).replace(",", ".");
                System.out.printf("║ %-3s | %-13s | Rp%-15s | %-4d | %-3.0f%%      | %-8s ║\n", i,
                        pet.getrasPet(), hargaFormatted, pet.getStokPet(),
                        pet.getDiskonPet() * 100, pet.getjenisPet());
                i++; // 🔢 Menambah nomor urut
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else {
            // ❌ Jika tidak ditemukan data pet dengan harga tersebut
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n"," [ERROR] | Data pet dengan harga Rp" + hargaDicari + " tidak ditemukan.  |");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // 📌 Function untuk mencari pet berdasarkan jenis dan ras
    public static void displayCariPetJenis(Scanner scanner, ArrayList<petList> listPet) {
        List<petList> listFusion = new ArrayList<>(); // 🧺 List gabungan semua jenis pet
        // ➕ Tambahkan semua data dari setiap jenis ke dalam listFusion
        listFusion.addAll(listKucing); // 🐱
        listFusion.addAll(listAnjing); // 🐶
        listFusion.addAll(listBurung); // 🐦
        listFusion.addAll(listIkan);   // 🐟

        System.out.print("\nMasukkan jenis pet yang dicari: ");// 📝 Input jenis pet dari user
        String jenisDicari = scanner.nextLine().toLowerCase();//Manipulasi string untuk mengkorversi huruf jadi kecil

        System.out.print("Masukkan ras pet yang dicari: ");// 📝 Input ras pet dari user
        String rasDicari = scanner.nextLine().toLowerCase();//Manipulasi string untuk mengkorversi huruf jadi kecil

        function.spasi();
        function.spinnerLoading("MenCari data pet ", 2000);
        // 🔄 Searching menggunakan Comparator 🔄
        List<petList> hasil = petList.cariPetListJenis(listFusion, jenisDicari, rasDicari);
        // ✅ Jika hasil ditemukan
        if (hasil != null) {
            int i = 1;
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n",
                    "    Data pet ditemukan berdasarkan jenis '" + jenisDicari + "' dan ras '" + rasDicari);
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-4s | %-9s | %-8s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon", "jenis");
            System.out.println("╠═════|═══════════════|═══════════════════|══════|═══════════|══════════╣");
            // 🔁 Tampilkan setiap data pet yang sesuai
            for (petList pet : hasil) {
                String hargaFormatted = String.format("%, .2f", pet.getHargaPet()).replace(",", ".");
                System.out.printf("║ %-3s | %-13s | Rp%-15s | %-4d | %-3.0f%%      | %-8s ║\n", i,
                        pet.getrasPet(), hargaFormatted, pet.getStokPet(),
                        pet.getDiskonPet() * 100, pet.getjenisPet());
                i++;
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else {
            // ❌ Jika tidak ditemukan data pet yang cocok
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n" , "Pet dengan jenis '" + jenisDicari + "' dan ras '" + rasDicari + "' tidak ditemukan.");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // 📌 Function untuk membeli pet
    public static void beliPet(Scanner scanner) {
         // 🎉 Header
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  Silahkan pilih Pet yang Anda Suka!                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        displayAllPets(); // 🐾 Tampilkan daftar pet sebelum membeli 🐾
        // 📋 Kumpulan semua data pet dari berbagai jenis
        List<petList> listPet = new ArrayList<>();
        listPet.addAll(listKucing); // 🐱
        listPet.addAll(listAnjing); // 🐶
        listPet.addAll(listBurung); // 🐦
        listPet.addAll(listIkan);   // 🐠
        // 🛒 Inisialisasi keranjang belanja & jumlah beli
        ArrayList<petList> keranjang = new ArrayList<>();
        ArrayList<Integer> jumlahBeliList = new ArrayList<>();
        boolean beliLagi = true;
        // 🔁 Loop pembelian
        while (beliLagi) {
            System.out.print("\nMasukkan nama ras pet yang ingin dibeli: ");
            String rasPet = scanner.nextLine();

            // 🐶 Cari pet berdasarkan ras 🐶
            petList petDitemukan = null;
            for (petList pet : listPet) {
                if (pet.getrasPet().equalsIgnoreCase(rasPet)) {
                    petDitemukan = pet;
                    break;
                }
            }

            if (petDitemukan != null) {
                System.out.print("Masukkan jumlah yang ingin dibeli: ");// 📝 Input jumlah beli dari user
                int jumlahBeli = scanner.nextInt();
                scanner.nextLine();
                // 📦 Validasi jumlah beli
                if (jumlahBeli > 0 && jumlahBeli <= petDitemukan.getStokPet()) {
                    keranjang.add(petDitemukan);
                    jumlahBeliList.add(jumlahBeli);
                    petDitemukan.setStokPet(petDitemukan.getStokPet() - jumlahBeli);
                    System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                    System.out.printf("║ %-69s ║\n", " [SUCCESS] | " + jumlahBeli + " ekor " + petDitemukan.getrasPet()
                            + " telah ditambahkan ke keranjang.");
                    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                } else { // ❌ Jika stok tidak cukup atau jumlah tidak valid
                    System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                    System.out.printf("║ %-69s ║\n", " [ERROR] | Maaf, stok tidak mencukupi atau jumlah tidak valid.");
                    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                }
            } else { // ❌ Jika pet tidak ditemukan
                System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║ %-69s ║\n", " [ERROR] | Pet dengan ras '" + rasPet + "' tidak ditemukan.");
                System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            }
            System.out.print("Ingin beli pet lain? (true/false): ");// 📝 Input pilihan beli lagi dari user
            beliLagi = scanner.nextBoolean();
            scanner.nextLine();
        }
        // 📜 Tampilkan detail pembelian 📜
        if (!keranjang.isEmpty()) {
            double totalHarga = 0; // 💰 Total harga
            double totalDiskon = 0; // 💸 Total diskon
            function.spasi();
            function.progressBarWithPercentage("bentar bang loading", 3000);
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                         Detail Pembelian Pet                          ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-15s | %-8s | %-15s | %-22s ║\n", "Ras Pet", "Jumlah", "Harga Satuan",
                    "       Subtotal");
            System.out.println("╠═════════════════|══════════|═════════════════|════════════════════════╣");
            // 🧮 Loop semua item di keranjang
            for (int i = 0; i < keranjang.size(); i++) {
                petList pet = keranjang.get(i); // Ambil pet dari keranjang
                int jumlah = jumlahBeliList.get(i); // Ambil jumlah beli dari list
                double hargaAsli = pet.getHargaPet() * jumlah; // Hitung harga asli
                double diskon = hargaAsli * pet.getDiskonPet(); // Hitung diskon
                double hargaTotal = hargaAsli - diskon; // Hitung total harga setelah diskon
                // 💵 Format harga agar tampil lebih rapi (gunakan titik sebagai pemisah ribuan)
                String subtotalFormatted = String.format("%,.2f", hargaTotal).replace(",", ".");
                String hargaFormatted = String.format("%,.2f", pet.getHargaPet()).replace(",", ".");
                System.out.printf("║ %-15s | %-8d | Rp%-13s | Rp%-20s ║\n",
                        pet.getrasPet(), jumlah, hargaFormatted, subtotalFormatted);
                totalHarga += hargaTotal; // Tambah total harga
                totalDiskon += diskon; // Tambah total diskon
            } // 💸 Total akhir
            String totalDiskonFormatted = String.format("%,.2f", totalDiskon).replace(",", ".");
            String totalHargaFormatted = String.format("%,.2f", totalHarga).replace(",", ".");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-44s : Rp%-20s ║\n", "Total Diskon", totalDiskonFormatted);
            System.out.printf("║ %-44s : Rp%-20s ║\n", "Total Bayar", totalHargaFormatted);
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            // 🎉 Penutup sukses
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║             Pembelian berhasil! Stok pet telah diperbarui             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                       ║");
            System.out.println("║                Terimakasih Telah Membeli Pet Di Petopia               ║");
            System.out.println("║                                                                       ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else { // ❌ Jika tidak ada pembelian
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                         Pembelian dibatalkan.                         ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    // 📌 Function untuk menampilkan daftar pet berdasarkan harga
    public static void displayPetsHarga() {
        function.spasi();
        function.spinnerLoading("MenCari data pet ", 2000);
        // 📦 Gabungkan semua pet ke dalam satu list
        List<petList> listPet = new ArrayList<>();
        listPet.addAll(listKucing); // 🐱
        listPet.addAll(listAnjing); // 🐶
        listPet.addAll(listBurung); // 🐦
        listPet.addAll(listIkan);   // 🐠
        // 🔄 Sorting menggunakan Comparator 🔄
        listPet.sort(Comparator.comparingDouble(petList::getHargaPet));
        int i = 1;
        // 🎯 Header tampilan
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("|                Daftar Semua Pet Berdasar Harga Termurah               |");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
        System.out.printf("| %-3s | %-13s | %-17s | %-4s | %-9s | %-8s |\n",
                "No", "Ras", "Harga", "Stok", "Diskon", "Jenis");
        System.out.println("╠═════|═══════════════|═══════════════════|══════|═══════════|══════════╣");
        // 📜 Menampilkan daftar pet setelah sorting 📜
        for (petList pet : listPet) {
            String hargaFormatted = String.format("%, .2f", pet.getHargaPet()).replace(",", ".");
            System.out.printf("| %-3s | %-13s | Rp%-15s | %-4d | %-3.0f%%      | %-8s |\n", i,
                    pet.getrasPet(), hargaFormatted, pet.getStokPet(),
                    pet.getDiskonPet() * 100, pet.getjenisPet());
            i++;
        }   
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }

    // 📌 Function untuk mengedit pet
    public static void editPpet(Scanner scanner) {
        function.displayDaftarJenis(); // 📝 Menampilkan daftar jenis pet yang tersedia
        System.out.print("\nMasukkan nomor pet yang ingin diedit: "); // 📝 Input nomor pet dari user
        int noEdit = scanner.nextInt();
        scanner.nextLine();
        switch (noEdit) {
            case 1:
                serviceCat.editCats(scanner); // 🐱 Edit kucing
                break;
            case 2:
                serviceDog.editDoggy(scanner);// 🐶 Edit anjing
                break;
            case 3:
                serviceFish.editIwak(scanner); // 🐟 Edit ikan
                break;
            case 4:
                serviceBird.editbuwung(scanner); // 🐦 Edit burung
                break;
            case 0:
                return;// 🔙 Kembali ke menu utama 🔙
        }
    }

}
