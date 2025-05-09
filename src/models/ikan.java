package models;

import java.time.LocalDate;

public class ikan extends petList {
    private String ilmiah;
    private String suhuAir;

    public ikan(String rasPet, double hargaPet, int stokPet, String jenisPet, double diskonPet, String ilmiah,
            String suhuAir, LocalDate lastCheckup, String vaccineStatus) {
        super(rasPet, hargaPet, stokPet, jenisPet, diskonPet, lastCheckup, vaccineStatus);
        setIlmiah(ilmiah);
        setSuhuAir(suhuAir);
    }

    public String getIlmiah() {
        return ilmiah;
    }

    public void setIlmiah(String ilmiah) {
        this.ilmiah = ilmiah;
    }

    public String getSuhuAir() {
        return suhuAir;
    }

    public void setSuhuAir(String suhuAir) {
        this.suhuAir = suhuAir;
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("ilmiah : " + getIlmiah());
        System.out.println("suhuAir : " + getSuhuAir());
    }
}
