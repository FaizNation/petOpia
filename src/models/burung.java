package models; // package model

import java.time.LocalDate; // Import LocalDate untuk menyimpan tanggal

// class burung 🐦 yang merupakan turunan dari class petList
public class burung extends petList{
    private String berat; // atribut berat untuk menyimpan berat burung
    private String kicauan; // atribut kicauan untuk menyimpan suara burung
    // 🔹 Konstruktor
    public burung (String rasPet, double hargaPet, int stokPet, String jenisPet, double diskonPet, String berat, String kicauan, LocalDate lastCheckup, String vaccineStatus) {
        super(rasPet, hargaPet, stokPet, jenisPet, diskonPet, lastCheckup, vaccineStatus);
        setBerat(berat);
        setKicauan(kicauan);
    }
    // 🔹 Getter berat
    public String getBerat() {
        return berat;
    }
    // 🔹 Setter berat
    public void setBerat(String berat) {
        this.berat = berat;
    }
    // 🔹 Getter kicauan
    public String getKicauan() {
        return kicauan;
    }
    // 🔹 Setter kicauan
    public void setKicauan(String kicauan) {
        this.kicauan = kicauan;
    }
    //polimorfisme method displayInfo() untuk menampilkan informasi burung
    @Override
    void displayInfo() {
        super.displayInfo();// memanggil method displayInfo() dari class petList
        System.out.println("Berat : " + getBerat());
        System.out.println("Kicauan : " + getKicauan());
    }
}
