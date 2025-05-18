package FaizNation.petopia_dev.models;

import java.time.LocalDate;

public class kucing extends petList{
    private String mantelBulu;
    private String keramahan;

    public kucing(String rasPet, double hargaPet, int stokPet, String jenisPet, double diskonPet, String mantelBulu,
            String keramahan, LocalDate lastCheckup, String vaccineStatus) {
        super(rasPet, hargaPet, stokPet, jenisPet, diskonPet, lastCheckup, vaccineStatus);
        setMantelBulu(mantelBulu);
        setKeramahan(keramahan);
    }

    public String getManteBulu() {
        return mantelBulu;
    }

    public void setMantelBulu(String mantelBulu) {
        this.mantelBulu = mantelBulu;
    }

    public String getKeramahan() {
        return keramahan;
    }

    public void setKeramahan(String keramahan) {
        this.keramahan = keramahan;
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Bulu kucing : " + getManteBulu());
        System.out.println("Rating keramahan : " + getKeramahan());
    }
}
