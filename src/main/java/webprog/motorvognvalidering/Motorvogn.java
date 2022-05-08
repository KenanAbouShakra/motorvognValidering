package webprog.motorvognvalidering;

public class Motorvogn {

    private String modell;
    private String merke;

    public Motorvogn(String merke,String modell ) {

        this.modell = modell;
        this.merke = merke;
    }
    public Motorvogn(){};

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getMerke() {
        return merke;
    }

    public void setMerke(String merke) {
        this.merke = merke;
    }



}
