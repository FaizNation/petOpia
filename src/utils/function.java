package utils;

public class function {

    // 📌 Function display Judul (Header)
    public static void displayJudul() {
        // 🎨 Kode warna ANSI
        String white = "\u001B[37m"; // Warna putih untuk teks biasa
        String yellow = "\u001B[33m"; // Warna kuning khusus untuk kata "PETOPIA"
        String reset = "\u001B[0m"; // Reset warna ke default terminal

        String[] lines = {
                white + "╔═══════════════════════════════════════════════════════════════════════╗",
                "║>>>                        SELAMAT DATANG DI                        <<<║",
                // ✨ Bagian ini akan menampilkan kata PETOPIA dengan warna kuning
                "║>>                          <<< " + yellow + "PETOPIA" + white + " >>>                          <<║",
                "║>>>                    Temukan Sahabat Terbaikmu                    <<<║",
                "╚═══════════════════════════════════════════════════════════════════════╝" + reset
        };
        // 🔁 Loop untuk mencetak setiap baris dengan efek ketik
        for (String line : lines) {
            for (char c : line.toCharArray()) {
                System.out.print(c); // Cetak karakter satu per satu
                // ⏱️ Delay 2ms antar karakter untuk efek ketik
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                }
            }
            System.out.println(); // ❌ Jika thread terganggu, abaikan saja
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    // 📌Function display Menu
    public static void displayMenu() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                MENU                                   ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ║ Tampilkan ras pet                                               ║");
        System.out.println("║  2. ║ Cari pet                                                        ║");
        System.out.println("║  3. ║ edit pet                                                        ║");
        System.out.println("║  4. ║ Beli pet                                                        ║");
        System.out.println("║  5. ║ Keluar                                                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.print("Pilihan: ");
    }

    // 📌Function display menu cari (menu 2)
    public static void displayMenuCari() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                MENU                                    ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ║ Cari berdasar harga                                              ║");
        System.out.println("║  2. ║ Cari pet berdasarkan jenis                                       ║");
        System.out.println("║  0. ║ Kembali                                                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.print("Pilihan: ");
    }

    // 📌Function display menu tampilkan (menu 1)
    public static void displayMenuTampilkan() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                MENU                                    ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ║ Tampilkan semua pet                                              ║");
        System.out.println("║  2. ║ Tampilkan pet berdasarkan harga                                  ║");
        System.out.println("║  3. ║ Tampilkan pet detail pet                                         ║");
        System.out.println("║  0. ║ Kembali                                                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.print("Pilihan: ");
    }

    // 📌Function display menu edit kucing
    public static void displayMenuEditCats() {
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ║ Ubah Nama                                                        ║");
        System.out.println("║  2. ║ Ubah Harga                                                       ║");
        System.out.println("║  3. ║ Ubah Stok                                                        ║");
        System.out.println("║  4. ║ Ubah Jenis                                                       ║");
        System.out.println("║  5. ║ Ubah Diskon                                                      ║");
        System.out.println("║  6. ║ Ubah Mantel Bulu                                                 ║");
        System.out.println("║  7. ║ Ubah Rating Keramahan                                            ║");
        System.out.println("║  8. ║ Ubah Data Vaksinasi                                              ║");
        System.out.println("║  0. ║ CONFRIM                                                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.print("Pilih bagian yang ingin diedit: ");
    }

    // 📌Function display menu edit anjing
    public static void displayMenuEditDogs() {
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ║ Ubah Nama                                                        ║");
        System.out.println("║  2. ║ Ubah Harga                                                       ║");
        System.out.println("║  3. ║ Ubah Stok                                                        ║");
        System.out.println("║  4. ║ Ubah Jenis                                                       ║");
        System.out.println("║  5. ║ Ubah Diskon                                                      ║");
        System.out.println("║  6. ║ Ubah Tinggi                                                      ║");
        System.out.println("║  7. ║ Ubah Rating Kesulitan Melatih                                    ║");
        System.out.println("║  8. ║ Ubah Data Vaksinasi                                              ║");
        System.out.println("║  0. ║ CONFRIM                                                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.print("Pilih bagian yang ingin diedit: ");
    }

    // 📌Function display menu edit ikan
    public static void displayMenuEditFish() {
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ║ Ubah Nama                                                        ║");
        System.out.println("║  2. ║ Ubah Harga                                                       ║");
        System.out.println("║  3. ║ Ubah Stok                                                        ║");
        System.out.println("║  4. ║ Ubah Jenis                                                       ║");
        System.out.println("║  5. ║ Ubah Diskon                                                      ║");
        System.out.println("║  6. ║ Ubah Nama Ilmiah                                                 ║");
        System.out.println("║  7. ║ Ubah Suhu Air                                                    ║");
        System.out.println("║  8. ║ Ubah Data Vaksinasi                                              ║");
        System.out.println("║  0. ║ CONFRIM                                                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.print("Pilih bagian yang ingin diedit: ");
    }

    // 📌Function display menu edit burung
    public static void displayMenuEditbird() {
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ║ Ubah Nama                                                        ║");
        System.out.println("║  2. ║ Ubah Harga                                                       ║");
        System.out.println("║  3. ║ Ubah Stok                                                        ║");
        System.out.println("║  4. ║ Ubah Jenis                                                       ║");
        System.out.println("║  5. ║ Ubah Diskon                                                      ║");
        System.out.println("║  6. ║ Ubah Berat                                                       ║");
        System.out.println("║  7. ║ Ubah Kiacuan                                                     ║");
        System.out.println("║  8. ║ Ubah Data Vaksinasi                                              ║");
        System.out.println("║  0. ║ CONFRIM                                                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.print("Pilih bagian yang ingin diedit: ");
    }

    // 📌Function display rating
    public static void displayRating() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           Penjelasan Rating                            ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ║ ■■■■■   = Sangat Mudah/Bagus                                     ║");
        System.out.println("║  2. ║ ■■■■-   = Mudah/Bagus                                            ║");
        System.out.println("║  3. ║ ■■■-    = Cukup Mudah/Bagus                                      ║");
        System.out.println("║  4. ║ ■■-     = Kurang Mudah/Bagus                                     ║");
        System.out.println("║  5. ║ ■-      = Sulit/Buruk                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
    }

    // 📌Function display vaksin
    public static void displayVaksin() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           Penjelasan Vaksin                            ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ║ Lengkap  = Sudah semua vaksin sesuai jadwal dokter.              ║");
        System.out.println("║  2. ║ Sebagian = Baru sebagian, belum lengkap.                         ║");
        System.out.println("║  3. ║ Tertunda = Belum divaksin, atau menunggu jadwal.                 ║");
        System.out.println("║  4. ║ Ulang    = Perlu vaksin booster (misal: rabies).                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
    }

    // 📌Function display by faiz nation (Footer)
    public static void displayFaizNation() {
        // 🎨 Kode warna ANSI
        String white = "\u001B[37m"; // Warna putih untuk teks biasa
        String yellow = "\u001B[33m"; // Warna kuning khusus untuk kata "PETOPIA"
        String reset = "\u001B[0m"; // Reset warna ke default terminal
        // 📝 Daftar baris yang akan ditampilkan dengan kombinasi warna
        String[] lines = {
                white + "╔════════════════════════════════════════════════════════════════════════╗",
                "║>>>           TERIMAKASIH TELAH MENGGUNAKAN APLIKASI SAYA            <<<║",
                "║>>                  <<< " + yellow + "FADLY FAIS FAJARRUDDIN" + white + " >>>                    <<║",
                "║>>>                           24111814015                            <<<║",
                "╠════════════════════════════════════════════════════════════════════════╣",
                "║>>>                                                                  <<<║",
                "║>>                    " + yellow + "ehh udah lebaran aja nih wkwk" + white + "                   <<║",
                "║>>                 " + yellow + "Minal Aidzin Wal Faidzin yagess!!!" + white + "                 <<║",
                "║>>>                                                                  <<<║",
                "╚════════════════════════════════════════════════════════════════════════╝" + reset
        };
        // 🖋️ Efek ketik baris demi baris
        for (String line : lines) {
            for (char c : line.toCharArray()) {
                System.out.print(c); // Cetak karakter satu per satu
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                } // ⏳ Delay antar karakter (2ms untuk efek ketik cepat)
            }
            System.out.println(); // Baris baru setelah selesai satu baris
            try {
                Thread.sleep(80); // ⏳ Delay antar baris
            } catch (InterruptedException e) {
            }
        }
    }

    // 📌Function display daftar jenis pet
    public static void displayDaftarJenis() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           Jenis Pet Yang Tersedia                      ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║   1. ║ Kucing                                                          ║");
        System.out.println("║   2. ║ Anjing                                                          ║");
        System.out.println("║   3. ║ Ikan                                                            ║");
        System.out.println("║   4. ║ Burung                                                          ║");
        System.out.println("║   0. ║ Kembali                                                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
    }

    public static void retroSpinner(String pesan, int durasiMillis) {
        String[] frames = { ".  ", ".. ", "...", " ..", "  .", "   " };
        long endTime = System.currentTimeMillis() + durasiMillis;

        while (System.currentTimeMillis() < endTime) {
            for (String frame : frames) {
                if (System.currentTimeMillis() >= endTime)
                    break;
                System.out.print("\r" + pesan + frame);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.print("\r" + pesan + " DONE!!!\n");
    }

    public static void progressBarWithPercentage(String pesan, int durasiMillis) {
        int totalBars = 30;
        long startTime = System.currentTimeMillis();
        long endTime = startTime + durasiMillis;

        while (System.currentTimeMillis() < endTime) {
            long elapsed = System.currentTimeMillis() - startTime;
            int progress = (int) ((elapsed * totalBars) / durasiMillis);
            int percentage = (int) ((elapsed * 100) / durasiMillis);

            StringBuilder bar = new StringBuilder();
            for (int i = 0; i < totalBars; i++) {
                bar.append(i < progress ? "■" : " ");
            }

            System.out.printf("\r%s [%s] %3d%%", pesan, bar.toString(), Math.min(percentage, 100));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.printf("\r%s [%s] 100%% \n", pesan, "■".repeat(totalBars));
    }

    public static void spinnerLoading(String pesan, int durasiMillis) {
        String[] spinner = { "|", "/", "-", "\\" };
        long endTime = System.currentTimeMillis() + durasiMillis;

        System.out.print(pesan + " ");
        int i = 0;
        while (System.currentTimeMillis() < endTime) {
            System.out.print("\b" + spinner[i % spinner.length]);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            i++;
        }
        System.out.print("\bDONE!!!");
        System.out.println(" [SUCCESS]");
    }

    public static void spasi () {
        System.out.println("");
    }
}
