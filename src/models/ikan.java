package models; // package model

import java.time.LocalDate; // Import LocalDate untuk menyimpan tanggal

// class ikan 🐠 yang merupakan turunan dari class petList
public class ikan extends petList{
    private String ilmiah; // atribut ilmiah untuk menyimpan nama ilmiah ikan
    private String suhuAir; // atribut suhuAir untuk menyimpan suhu air ikan
    // 🔹 Konstruktor
    public ikan (String rasPet, double hargaPet, int stokPet, String jenisPet, double diskonPet, String ilmiah, String suhuAir, LocalDate lastCheckup, String vaccineStatus) {
        super(rasPet, hargaPet, stokPet, jenisPet, diskonPet, lastCheckup, vaccineStatus);
        setIlmiah(ilmiah);
        setSuhuAir(suhuAir);
    }
    // 🔹 Getter ilmiah
    public String getIlmiah() {
        return ilmiah;
    }
    // 🔹 Setter ilmiah
    public void setIlmiah(String ilmiah) {
        this.ilmiah = ilmiah;
    }
    // 🔹 Getter suhu air
    public String getSuhuAir() {
        return suhuAir;
    }
    // 🔹 Setter suhu air
    public void setSuhuAir(String suhuAir) {
        this.suhuAir = suhuAir;
    }
    //polimorfisme method displayInfo() untuk menampilkan informasi ikan
    @Override
    void displayInfo() {
        super.displayInfo(); // memanggil method displayInfo() dari class petList
        System.out.println("ilmiah : " + getIlmiah());
        System.out.println("suhuAir : " + getSuhuAir());
    }
}
