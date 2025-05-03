package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class petList {
    private String rasPet;
    private double hargaPet;
    protected int stokPet;
    private String jenisPet;
    protected int petDibeli;
    protected double diskonPet;
    protected HealthRecord healthRecord;

    public petList(
            String rasPet,
            double hargaPet,
            int stokPet,
            String jenisPet,
            double diskonPet, LocalDate lastCheckup, String vaccineStatus) {

        this.rasPet = rasPet;
        this.hargaPet = hargaPet;
        this.stokPet = stokPet;
        this.jenisPet = jenisPet;
        this.diskonPet = diskonPet;
        this.healthRecord = this.new HealthRecord(lastCheckup, vaccineStatus);
    }

    public String getrasPet() {
        return rasPet;
    }

    public void setrasPet(String rasPet) {
        this.rasPet = rasPet;
    }

    public double getHargaPet() {
        return hargaPet;
    }

    public void setHargaPet(double hargaPet) {
        if (hargaPet >= 0) {
            this.hargaPet = hargaPet;
        } else {
            System.out.println("Harga tidak boleh negatif");
        }
    }

    public int getStokPet() {
        return stokPet;
    }

    public void setStokPet(int stokPet) {
        this.stokPet = stokPet;
    }

    public String getjenisPet() {
        return jenisPet;
    }

    public void setjenisPet(String jenisPet) {
        this.jenisPet = jenisPet;
    }

    public int getPetDibeli() {
        if (petDibeli > stokPet) {
            System.out.println("Stok tidak mencukupi");
            return 0;
        } else {
            return petDibeli;
        }
    }

    public double getDiskonPet() {
        return diskonPet;
    }

    public void setDiskonPet(double diskonPet) {
        if (diskonPet >= 0 && diskonPet <= 1) {
            this.diskonPet = diskonPet;
        } else {
            System.out.println("Diskon harus diantara 0 dan 1");
        }
    }

    protected double totalHarga() {
        double total = this.hargaPet * this.stokPet;
        double diskon = total * this.diskonPet;
        return total - diskon;
    }

    public void kurangiStok(int jumlah) {
        if (jumlah >= 0 && jumlah <= this.stokPet) {
            stokPet -= jumlah;
            System.out.println(
                    "\n╔=======================================================================╗");
            System.out.println("|                         PEMBELIAN BERHASIL                            |");
            System.out.println("|=======================================================================|");
            System.out.printf("| %-69s |\n",
                    "                   Selamat " + jumlah + " Pet berhasil dibeli");
            System.out.printf("| %-69s |\n", "          stok pet " + rasPet + " tersisa saat ini adalah: "
                    + stokPet + " stok");
            System.out.println("|=======================================================================|");
        } else if (jumlah > this.stokPet) {
            System.out.println("Stok tidak mencukupi, hanya tersedia" + stokPet + "pet");
        } else {
            System.out.println("Jumlah tidak valid");
        }
    }

    public static List<petList> cariSemuaPetByHarga(List<petList> list, double harga) {
        List<petList> hasil = new ArrayList<>();
        for (petList pet : list) {
            if (pet.getHargaPet() == harga) {
                hasil.add(pet);
            }
        }
        return null;
    }

    public static List<petList> cariPetListJenis(List<petList> list, String jenis, String ras) {
        List<petList> hasil = new ArrayList<>();
        for (petList pet : list) {
            if (pet.getjenisPet().equalsIgnoreCase(jenis) &&
                    pet.getrasPet().equalsIgnoreCase(ras)) {
                hasil.add(pet);
            }
        }
        return null;
    }

    public static ArrayList<petList> quickSort(ArrayList<petList> lsst) {
        quickSortLoop(lsst, 0, lsst.size() - 1);
        return lsst;
    }

    private static void quickSortLoop(ArrayList<petList> List, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(List, low, high);
            quickSortLoop(List, low, pivotIndex - 1);
            quickSortLoop(List, pivotIndex + 1, high);
        }
    }

    private static int partition(ArrayList<petList> List, int low, int high) {
        String pivot = List.get(high).getjenisPet();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (List.get(j).getjenisPet().compareTo(pivot) <= 0) {
                i++;
                petList temp = List.get(i);
                List.set(i, List.get(j));
                List.set(j, temp);
            }
        }
        petList temp = List.get(i + 1);
        List.set(i + 1, List.get(high));
        List.set(high, temp);
        return i + 1;
    }

    public class HealthRecord {
        private LocalDate lastCheckup;
        private String vaccineStatus;

        public HealthRecord(LocalDate lastCheckup, String vaccineStatus) {
            this.lastCheckup = lastCheckup;
            this.vaccineStatus = vaccineStatus;
        }

        public LocalDate getLastCheckup() {
            return lastCheckup;
        }

        public void setLastCheckup(LocalDate lastCheckup) {
            this.lastCheckup = lastCheckup;
        }

        public String getVaccineStatus() {
            return vaccineStatus;
        }

        public void setVaccineStatus(String vaccineStatus) {
            this.vaccineStatus = vaccineStatus;
        }

        public void displayHealth() {
            System.out.println("Terakhir Cek: " + getLastCheckup());
            System.out.println("Status Vaksin: " + getVaccineStatus());
        }
    }

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(LocalDate lastCheckup, String vaccineStatus) {
        this.healthRecord = new HealthRecord(lastCheckup, vaccineStatus);
    }

    void displayInfo() {
        System.out.println("ras Pet : " + getrasPet());
        System.out.println("harga pet : " + getHargaPet());
        System.out.println("stok pet : " + getStokPet());
        System.out.println("jenis pet : " + getjenisPet());
        System.out.println("diskon pet : " + getDiskonPet());
        if (healthRecord != null) {
            System.out.println("KESEHATAN:");
            healthRecord.displayHealth();
        } else {
            System.out.println("KESEHATAN: Belum tersedia");
        }
    }
}
