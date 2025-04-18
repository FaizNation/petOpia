package models; // package model

import java.time.LocalDate; // Import LocalDate untuk menyimpan tanggal

// class anjing 🐶 yang merupakan turunan dari class petList
public class anjing extends petList{ 
    private String tinggi; // atribut tinggi untuk menyimpan tinggi anjing
    private String latihan; // atribut latihan untuk menyimpan tingkat pelatihan anjing
    // 🔹 Konstruktor
   public anjing (String rasPet, double hargaPet, int stokPet, String jenisPet, double diskonPet, String tinggi, String latihan, LocalDate lastCheckup, String vaccineStatus) {
        super(rasPet, hargaPet, stokPet, jenisPet, diskonPet, lastCheckup, vaccineStatus);
        setTinggi(tinggi);
        setLatihan(latihan);
    }
    // 🔹 Getter tinggi
    public String getTinggi() {
        return tinggi;
    }
    // 🔹 Setter tinggi
    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }
    // 🔹 Getter latihan
    public String getLatihan() {
        return latihan;
    }
    // 🔹 Setter latihan
    public void setLatihan(String latihan) {
        this.latihan = latihan;
    }

    //polimorfisme method displayInfo() untuk menampilkan informasi anjing
    @Override
    void displayInfo() {
        super.displayInfo(); // memanggil method displayInfo() dari class petList
        System.out.println("Tinggi : " + getTinggi());
        System.out.println("Mudah dilatih : " + getLatihan());
    }
}
