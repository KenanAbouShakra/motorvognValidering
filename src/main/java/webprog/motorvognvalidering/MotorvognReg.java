package webprog.motorvognvalidering;

public class MotorvognReg {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private long personnr;
    private String fornavn;
    private String etternavn;
    private String kjennetegn;
    private String bilmodell;
    private String bilmerke;

    public MotorvognReg(int id,long personnr, String fornavn, String etternavn, String kjennetegn, String bilmodell, String bilmerke) {
        this.id=id;
        this.personnr = personnr;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.kjennetegn = kjennetegn;
        this.bilmodell = bilmodell;
        this.bilmerke = bilmerke;
    }
    public MotorvognReg(){};
    public long getPersonnr(){
        return personnr;
    }
    public void setPersonnr(long personnr){
        this.personnr=personnr;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getKjennetegn() {
        return kjennetegn;
    }

    public void setKjennetegn(String kjennetegn) {
        this.kjennetegn = kjennetegn;
    }

    public String getBilmodell() {
        return bilmodell;
    }

    public void setBilmodell(String bilmodell) {
        this.bilmodell = bilmodell;
    }

    public String getBilmerke() {
        return bilmerke;
    }

    public void setBilmerke(String bilmerke) {
        this.bilmerke = bilmerke;
    }
}
