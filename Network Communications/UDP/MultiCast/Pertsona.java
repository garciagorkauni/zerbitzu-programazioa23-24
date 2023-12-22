package MultiCast;

import java.io.Serializable;

public class Pertsona implements Serializable{
    public int id;
    public String izena;
    public int adina;


    public Pertsona(int id, String izena, int adina) {
        this.id = id;
        this.izena = izena;
        this.adina = adina;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getIzena() {
        return izena;
    }


    public void setIzena(String izena) {
        this.izena = izena;
    }


    public int getAdina() {
        return adina;
    }


    public void setAdina(int adina) {
        this.adina = adina;
    }

    
}
