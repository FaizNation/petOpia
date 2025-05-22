package FaizNation.petOpia_dev.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import FaizNation.petOpia_dev.models.anjing;
import FaizNation.petOpia_dev.models.burung;
import FaizNation.petOpia_dev.models.ikan;
import FaizNation.petOpia_dev.models.kucing;
import FaizNation.petOpia_dev.models.petList;
import FaizNation.petOpia_dev.utils.function;

public class services {
    static ArrayList<petList> listPet = new ArrayList<>();

    public static ArrayList<kucing> listKucing = new ArrayList<>();
    public static ArrayList<anjing> listAnjing = new ArrayList<>();
    public static ArrayList<burung> listBurung = new ArrayList<>();
    public static ArrayList<ikan> listIkan = new ArrayList<>();
    static {
        listPet();
    }

    public static void listPet() {
        listKucing.clear();
        listAnjing.clear();
        listBurung.clear();
        listIkan.clear();

        if (listKucing.isEmpty()) {
            listKucing.add(new kucing("Persian", 2000000, 20, "Kucing", 0.1, "Long Hair", "Yellow", LocalDate.now(), "Active"));
            listKucing.add(new kucing("Angora", 600000, 15, "Kucing", 0.15, "Medium Hair", "White", LocalDate.now(), "Active"));
            listKucing.add(new kucing("Munchkin", 4000000, 10, "Kucing", 0.2, "Short Hair", "Brown", LocalDate.now(), "Active"));
        }
        if (listAnjing.isEmpty()) {
            listAnjing.add(new anjing("Husky", 9000000, 7, "Anjing", 0.05, "Large", "Active", LocalDate.now(), "Active"));
            listAnjing.add(new anjing("Poodle", 3000000, 10, "Anjing", 0.1, "Small", "Friendly", LocalDate.now(), "Active"));
            listAnjing.add(new anjing("Corgi", 4000000, 11, "Anjing", 0.1, "Small", "Friendly", LocalDate.now(), "Active"));
        }
        if (listBurung.isEmpty()) {
            listBurung.add(new burung("Lovebird", 300000, 10, "Burung", 0.0, "Colorful", "Sweet", LocalDate.now(), "Active"));
            listBurung.add(new burung("Cockatiel", 800000, 12, "Burung", 0.15, "Yellow", "Cheerful", LocalDate.now(), "Active"));
            listBurung.add(new burung("Parrot", 3500000, 10, "Burung", 0.15, "Yellow", "Cheerful", LocalDate.now(), "Active"));
        }
        if (listIkan.isEmpty()) {
            listIkan.add(new ikan("Koi", 1300000, 10, "Ikan", 0.1, "Large", "Orange", LocalDate.now(), "Active"));
            listIkan.add(new ikan("Guppy", 50000, 25, "Ikan", 0.25, "Small", "Colorful", LocalDate.now(), "Active"));
            listIkan.add(new ikan("Molly",50000, 30, "Ikan", 0.0, "Small", "Colorful", LocalDate.now(), "Active"));
        }
    }

    public static void displayPets(Scanner scanner) {
        while (true) {
            function.displayMenuTampilkan();
            int PilihDisplayPets = scanner.nextInt();
            scanner.nextLine();
            switch (PilihDisplayPets) {
                case 1:
                    displayAllPets();
                    break;
                case 2:
                    displayPetsHarga();
                    break;
                case 3:
                    displayJenis(scanner);
                case 0:
                    return;
            }

        }
    }

    public static void displayJenis(Scanner scanner) {
        function.displayDaftarJenis();
        System.out.print("Pilih jenis pet yang ingin ditampilkan: ");
        int jenis = scanner.nextInt();
        scanner.nextLine();

        switch (jenis) {
            case 1:
                serviceCat.displayDetailCat(scanner);
                break;
            case 2:
                serviceDog.displayDetailDoggy(scanner);
                break;
            case 3:
                serviceFish.displayDetailIwak(scanner);
                break;
            case 4:
                serviceBird.displayDetailbuwung(scanner);
                break;
            case 0:
                return;
        }
    }

    public static void displayAllPets() {
        List<petList> listPet = new ArrayList<>();

        listPet.addAll(listKucing);
        listPet.addAll(listAnjing);
        listPet.addAll(listBurung);
        listPet.addAll(listIkan);

        if ((listPet == null || listPet.isEmpty())) {
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                     Tidak ada data pet yang tersedia                  |");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            return;
        } else {
            function.spasi();
            function.spinnerLoading("MenCari data pet ", 2000);
            int i = 1;

            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           Daftar Semua Pet                            ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-4s | %-9s | %-8s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon", "Jenis");
            System.out.println("╠═════|═══════════════|═══════════════════|══════|═══════════|══════════╣");

            for (petList pet : listPet) {

                String hargaFormatted = String.format("%, .2f", pet.getHargaPet()).replace(",", ".");
                System.out.printf("║ %-3s | %-13s | Rp%-15s | %-4d | %-3.0f%%      | %-8s ║\n", i,
                        pet.getrasPet(), hargaFormatted, pet.getStokPet(),
                        pet.getDiskonPet() * 100, pet.getjenisPet());
                i++;
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    public static void displayCari(Scanner scanner) {

        while (true) {
            function.displayMenuCari();
            int Cari = scanner.nextInt();
            scanner.nextLine();
            switch (Cari) {
                case 1:
                    displayCariPetHarga(scanner, listPet);
                    break;
                case 2:
                    displayCariPetJenis(scanner, listPet);
                case 0:
                    return;
            }
        }
    }

    public static void displayCariPetHarga(Scanner scanner, ArrayList<petList> listpet) {
        List<petList> listPet = new ArrayList<>();

        listPet.addAll(listKucing);
        listPet.addAll(listAnjing);
        listPet.addAll(listBurung);
        listPet.addAll(listIkan);

        System.out.print("Masukkan harga pet yang dicari: ");
        double hargaDicari = scanner.nextDouble();
        scanner.nextLine();
        function.spasi();
        function.spinnerLoading("MenCari data pet ", 2000);

        List<petList> hasil = petList.cariSemuaPetByHarga(listPet, hargaDicari);

        if (hasil != null) {
            int i = 1;
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n", "             Data pet dengan harga Rp" + hargaDicari + " ditemukan");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-4s | %-9s | %-8s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon", "Jenis");
            System.out.println("╠═════|═══════════════|═══════════════════|══════|═══════════|══════════╣");

            for (petList pet : hasil) {

                String hargaFormatted = String.format("%, .2f", pet.getHargaPet()).replace(",", ".");
                System.out.printf("║ %-3s | %-13s | Rp%-15s | %-4d | %-3.0f%%      | %-8s ║\n", i,
                        pet.getrasPet(), hargaFormatted, pet.getStokPet(),
                        pet.getDiskonPet() * 100, pet.getjenisPet());
                i++;
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else {

            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n",
                    " [ERROR] | Data pet dengan harga Rp" + hargaDicari + " tidak ditemukan.  |");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    public static void displayCariPetJenis(Scanner scanner, ArrayList<petList> listPet) {
        List<petList> listFusion = new ArrayList<>();

        listFusion.addAll(listKucing);
        listFusion.addAll(listAnjing);
        listFusion.addAll(listBurung);
        listFusion.addAll(listIkan);

        System.out.print("\nMasukkan jenis pet yang dicari: ");
        String jenisDicari = scanner.nextLine().toLowerCase();

        System.out.print("Masukkan ras pet yang dicari: ");
        String rasDicari = scanner.nextLine().toLowerCase();

        function.spasi();
        function.spinnerLoading("MenCari data pet ", 2000);

        List<petList> hasil = petList.cariPetListJenis(listFusion, jenisDicari, rasDicari);

        if (hasil != null) {
            int i = 1;
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n",
                    "    Data pet ditemukan berdasarkan jenis '" + jenisDicari + "' dan ras '" + rasDicari);
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-3s | %-13s | %-17s | %-4s | %-9s | %-8s ║\n",
                    "No", "Ras", "Harga", "Stok", "Diskon", "jenis");
            System.out.println("╠═════|═══════════════|═══════════════════|══════|═══════════|══════════╣");

            for (petList pet : hasil) {
                String hargaFormatted = String.format("%, .2f", pet.getHargaPet()).replace(",", ".");
                System.out.printf("║ %-3s | %-13s | Rp%-15s | %-4d | %-3.0f%%      | %-8s ║\n", i,
                        pet.getrasPet(), hargaFormatted, pet.getStokPet(),
                        pet.getDiskonPet() * 100, pet.getjenisPet());
                i++;
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else {

            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-69s ║\n",
                    "Pet dengan jenis '" + jenisDicari + "' dan ras '" + rasDicari + "' tidak ditemukan.");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    public static void beliPet(Scanner scanner) {

        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  Silahkan pilih Pet yang Anda Suka!                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        displayAllPets();

        List<petList> listPet = new ArrayList<>();
        listPet.addAll(listKucing);
        listPet.addAll(listAnjing);
        listPet.addAll(listBurung);
        listPet.addAll(listIkan);

        ArrayList<petList> keranjang = new ArrayList<>();
        ArrayList<Integer> jumlahBeliList = new ArrayList<>();
        boolean beliLagi = true;

        while (beliLagi) {
            System.out.print("\nMasukkan nama ras pet yang ingin dibeli: ");
            String rasPet = scanner.nextLine();

            petList petDitemukan = null;
            for (petList pet : listPet) {
                if (pet.getrasPet().equalsIgnoreCase(rasPet)) {
                    petDitemukan = pet;
                    break;
                }
            }

            if (petDitemukan != null) {
                System.out.print("Masukkan jumlah yang ingin dibeli: ");
                int jumlahBeli = scanner.nextInt();
                scanner.nextLine();

                if (jumlahBeli > 0 && jumlahBeli <= petDitemukan.getStokPet()) {
                    keranjang.add(petDitemukan);
                    jumlahBeliList.add(jumlahBeli);
                    petDitemukan.setStokPet(petDitemukan.getStokPet() - jumlahBeli);
                    System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                    System.out.printf("║ %-69s ║\n", " [SUCCESS] | " + jumlahBeli + " ekor " + petDitemukan.getrasPet()
                            + " telah ditambahkan ke keranjang.");
                    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                } else {
                    System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                    System.out.printf("║ %-69s ║\n", " [ERROR] | Maaf, stok tidak mencukupi atau jumlah tidak valid.");
                    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
                }
            } else {
                System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
                System.out.printf("║ %-69s ║\n", " [ERROR] | Pet dengan ras '" + rasPet + "' tidak ditemukan.");
                System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            }
            System.out.print("Ingin beli pet lain? (true/false): ");
            beliLagi = scanner.nextBoolean();
            scanner.nextLine();
        }

        if (!keranjang.isEmpty()) {
            double totalHarga = 0;
            double totalDiskon = 0;
            function.spasi();
            function.progressBarWithPercentage("bentar bang loading", 3000);
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                         Detail Pembelian Pet                          ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-15s | %-8s | %-15s | %-22s ║\n", "Ras Pet", "Jumlah", "Harga Satuan",
                    "       Subtotal");
            System.out.println("╠═════════════════|══════════|═════════════════|════════════════════════╣");

            for (int i = 0; i < keranjang.size(); i++) {
                petList pet = keranjang.get(i);
                int jumlah = jumlahBeliList.get(i);
                double hargaAsli = pet.getHargaPet() * jumlah;
                double diskon = hargaAsli * pet.getDiskonPet();
                double hargaTotal = hargaAsli - diskon;

                String subtotalFormatted = String.format("%,.2f", hargaTotal).replace(",", ".");
                String hargaFormatted = String.format("%,.2f", pet.getHargaPet()).replace(",", ".");
                System.out.printf("║ %-15s | %-8d | Rp%-13s | Rp%-20s ║\n",
                        pet.getrasPet(), jumlah, hargaFormatted, subtotalFormatted);
                totalHarga += hargaTotal;
                totalDiskon += diskon;
            }
            String totalDiskonFormatted = String.format("%,.2f", totalDiskon).replace(",", ".");
            String totalHargaFormatted = String.format("%,.2f", totalHarga).replace(",", ".");
            System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-44s : Rp%-20s ║\n", "Total Diskon", totalDiskonFormatted);
            System.out.printf("║ %-44s : Rp%-20s ║\n", "Total Bayar", totalHargaFormatted);
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");

            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║             Pembelian berhasil! Stok pet telah diperbarui             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                       ║");
            System.out.println("║                Terimakasih Telah Membeli Pet Di Petopia               ║");
            System.out.println("║                                                                       ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } else {
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                         Pembelian dibatalkan.                         ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        }
    }

    public static void displayPetsHarga() {
        function.spasi();
        function.spinnerLoading("MenCari data pet ", 2000);

        List<petList> listPet = new ArrayList<>();
        listPet.addAll(listKucing);
        listPet.addAll(listAnjing);
        listPet.addAll(listBurung);
        listPet.addAll(listIkan);

        listPet.sort(Comparator.comparingDouble(petList::getHargaPet));
        int i = 1;

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("|                Daftar Semua Pet Berdasar Harga Termurah               |");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
        System.out.printf("| %-3s | %-13s | %-17s | %-4s | %-9s | %-8s |\n",
                "No", "Ras", "Harga", "Stok", "Diskon", "Jenis");
        System.out.println("╠═════|═══════════════|═══════════════════|══════|═══════════|══════════╣");

        for (petList pet : listPet) {
            String hargaFormatted = String.format("%, .2f", pet.getHargaPet()).replace(",", ".");
            System.out.printf("| %-3s | %-13s | Rp%-15s | %-4d | %-3.0f%%      | %-8s |\n", i,
                    pet.getrasPet(), hargaFormatted, pet.getStokPet(),
                    pet.getDiskonPet() * 100, pet.getjenisPet());
            i++;
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }

    public static void editPpet(Scanner scanner) {
        function.displayDaftarJenis();
        System.out.print("\nMasukkan nomor pet yang ingin diedit: ");
        int noEdit = scanner.nextInt();
        scanner.nextLine();
        switch (noEdit) {
            case 1:
                serviceCat.editCats(scanner);
                break;
            case 2:
                serviceDog.editDoggy(scanner);
                break;
            case 3:
                serviceFish.editIwak(scanner);
                break;
            case 4:
                serviceBird.editbuwung(scanner);
                break;
            case 0:
                return;
        }
    }

    public static List<petList> getAllPets() {
        List<petList> allPets = new ArrayList<>();
        allPets.addAll(listKucing);
        allPets.addAll(listAnjing);
        allPets.addAll(listBurung);
        allPets.addAll(listIkan);
        return allPets;
    }

    public static petList findPetByName(String name) {
        return getAllPets().stream()
                .filter(pet -> pet.getrasPet().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static void deletePetByName(String name) {
        listKucing.removeIf(pet -> pet.getrasPet().equals(name));
        listAnjing.removeIf(pet -> pet.getrasPet().equals(name));
        listBurung.removeIf(pet -> pet.getrasPet().equals(name));
        listIkan.removeIf(pet -> pet.getrasPet().equals(name));
    }

}
