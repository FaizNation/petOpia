package models; // package model

import java.time.LocalDate; // Import LocalDate untuk menyimpan tanggal

// class kucing 😺 yang merupakan turunan dari class petList
public class kucing extends petList { 
    private String mantelBulu; // atribut mantelBulu untuk menyimpan jenis bulu kucing
    private String keramahan; // atribut keramahan untuk menyimpan rating keramahan kucing
    // 🔹 Konstruktor
    public kucing (String rasPet, double hargaPet, int stokPet, String jenisPet, double diskonPet, String mantelBulu, String keramahan, LocalDate lastCheckup, String vaccineStatus) {
        super(rasPet, hargaPet, stokPet, jenisPet, diskonPet, lastCheckup, vaccineStatus);
        setMantelBulu(mantelBulu);
        setKeramahan(keramahan);
    }
    // 🔹 Getter mantel bulu
    public String getManteBulu() {
        return mantelBulu;
    }
    // 🔹 Setter mantel bulu
    public void setMantelBulu(String mantelBulu) {
        this.mantelBulu = mantelBulu;
    }
    // 🔹 Getter keramahan
    public String getKeramahan() {
        return keramahan;
    }   
    // 🔹 Setter keramahan
    public void setKeramahan (String keramahan) {
        this.keramahan = keramahan;
    }
    //polimorfisme method displayInfo() untuk menampilkan informasi kucing
    @Override
    void displayInfo() {
        super.displayInfo(); // memanggil method displayInfo() dari class petList
        System.out.println("Bulu kucing : " + getManteBulu());
        System.out.println("Rating keramahan : " + getKeramahan());
    }
}
