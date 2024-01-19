package Exercises.AzterketaSimulazioa2.TCP_3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerService extends Thread{
    
    Socket client;

    public ServerService(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        //Erantzun zuzenak arraylist baten gorde
        ArrayList<String> erantzunZuzenak = new ArrayList<>();
        erantzunZuzenak.add("a");
        erantzunZuzenak.add("b");
        erantzunZuzenak.add("a");

        //Erabiltzailearen erantzunak gordetzeko arraylista sortu
        ArrayList<String> erabiltzailearenErantzunak = new ArrayList<>();

        //Galderekin arraylist bat sortu
        ArrayList<String> galderak = new ArrayList<>();
        galderak.add("xx1. galdera: hauetako zein da konexiora bideratutako garraio-protokoloa?xx" + //
                "a) TCPxx" + //
                "b) UDPxx" + //
                "c) IPxx");
        galderak.add("xx2. galdera: Zer-nolako aplikazioek jasotzen dute onura gehien UDP protokolotik?xx" + //
                "a) Artxiboen transferentziaxx" + //
                "b) Bideokonferentzia eta ahots-transmisioaxx" + //
                "c) Web nabigazioaxx");
        galderak.add("xx3. galdera: Zein da \"Acknowledgment\" (ACK) eremuaren funtzioa TCPren goiburuan?xx" + //
                "a) Datuak entregatu direla berresteaxx" + //
                "b) Hasierako konexioa ezartzeaxx" + //
                "c) Bideratzea kudeatzeaxx");
            
        try {
            //TCPrako idazketa- eta irakurketa-kanalak sortu
            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);

            //Ariketaren kodigoa
            for(String galdera:galderak) {
                //Bezereoari galdera bidali
                writer.println(galdera);

                //Bezeroaren mezua jaso eta gorde
                String erantzuna = reader.readLine();
                erabiltzailearenErantzunak.add(erantzuna);
            }

            String emaitzaTestua;
            int erantzunZuzenKopurua = 0;
            for (int i = 0; i < 3; i++) {
                if (erantzunZuzenak.get(i).equals(erabiltzailearenErantzunak.get(i))){
                    erantzunZuzenKopurua++;
                }
            }
            emaitzaTestua = "Asmatutako erantzunen kopurua: " + erantzunZuzenKopurua + "xx";
            if(erantzunZuzenKopurua > erantzunZuzenak.size()/2){
                emaitzaTestua += "GAINDITUTA!";
            } else {
                emaitzaTestua += "EZ GAINDITUTA!";
            }
            writer.println(emaitzaTestua);

        } catch (Exception e) {
        }
    }
}
