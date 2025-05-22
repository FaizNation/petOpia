package FaizNation.petOpia_dev.models;

import java.time.LocalDate;

public class anjing extends petList {
    private String tinggi;
    private String latihan;

    public anjing(String rasPet, double hargaPet, int stokPet, String jenisPet, double diskonPet, String tinggi,
            String latihan, LocalDate lastCheckup, String vaccineStatus) {
        super(rasPet, hargaPet, stokPet, jenisPet, diskonPet, lastCheckup, vaccineStatus);
        setTinggi(tinggi);
        setLatihan(latihan);
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getLatihan() {
        return latihan;
    }

    public void setLatihan(String latihan) {
        this.latihan = latihan;
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Tinggi : " + getTinggi());
        System.out.println("Mudah dilatih : " + getLatihan());
    }
}
