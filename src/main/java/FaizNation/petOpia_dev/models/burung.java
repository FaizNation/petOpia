package FaizNation.petOpia_dev.models;

import java.time.LocalDate;

public class burung extends petList{
    private String berat;
    private String kicauan;

    public burung(String rasPet, double hargaPet, int stokPet, String jenisPet, double diskonPet, String berat,
            String kicauan, LocalDate lastCheckup, String vaccineStatus) {
        super(rasPet, hargaPet, stokPet, jenisPet, diskonPet, lastCheckup, vaccineStatus);
        setBerat(berat);
        setKicauan(kicauan);
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getKicauan() {
        return kicauan;
    }

    public void setKicauan(String kicauan) {
        this.kicauan = kicauan;
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Berat : " + getBerat());
        System.out.println("Kicauan : " + getKicauan());
    }
}
