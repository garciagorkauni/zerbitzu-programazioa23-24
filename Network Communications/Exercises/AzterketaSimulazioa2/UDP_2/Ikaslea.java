package Exercises.AzterketaSimulazioa2.UDP_2;

import java.io.Serializable;

public class Ikaslea implements Serializable{
    String izena;
    String abizena;
    int adina;
    String gradoa;
    boolean lehenengoMaila;

    public Ikaslea() {
    }

    public Ikaslea(String izena, String abizena, int adina, String gradoa, boolean lehenengoMaila) {
        this.izena = izena;
        this.abizena = abizena;
        this.adina = adina;
        this.gradoa = gradoa;
        this.lehenengoMaila = lehenengoMaila;
    }

    
    public String getIzena() {
        return izena;
    }

    public String getAbizena() {
        return abizena;
    }

    public int getAdina() {
        return adina;
    }

    public String getGradoa() {
        return gradoa;
    }

    public boolean isLehenengoMaila() {
        return lehenengoMaila;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    public void setAdina(int adina) {
        this.adina = adina;
    }

    public void setGradoa(String gradoa) {
        this.gradoa = gradoa;
    }

    public void setLehenengoMaila(boolean lehenengoMaila) {
        this.lehenengoMaila = lehenengoMaila;
    }

    @Override
    public String toString() {
        return "Ikaslea{" + "izena=" + izena + ", abizena=" + abizena + ", adina=" + adina + ", gradoa=" + gradoa + ", lehenengoMaila=" + lehenengoMaila + '}';
    }
    
}
