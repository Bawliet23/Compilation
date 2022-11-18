package net.mips.compiler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScannerWS extends Scanner {
    private List<Symboles> tableSymb = new ArrayList<>();
    private int placeSymb;

    private int val;


    public ScannerWS(String filename) throws FileNotFoundException {
        super(filename);
    }

    @Override
    public void initMotsCles() {
        super.initMotsCles();
        this.tableSymb.addAll(this.getMotsCles());
    }

    @Override
    public void codeLex(String mot) {
        for (Symboles sym : tableSymb){
            if(sym.getNom().toLowerCase().equals(mot)){
                this.getSymbCour().setToken(sym.getToken());
                return;
            }
        }
        this.getSymbCour().setToken(Tokens.ID_TOKEN);
    }

   public void entrerSymb(ClasseIdf idf){
        this.getSymbCour().setClasseIdf(idf);
        this.tableSymb.add(this.getSymbCour());
   }

    public void cherche_Symb(){
        for (int i = 0; i < this.tableSymb.size(); i++) {
            if (this.tableSymb.get(i).getNom().equals(this.getSymbCour().getNom())){
                this.placeSymb = i;
                return;
            }
        }
        this.placeSymb=-1;
    }


    @Override
    public void lireNumber() throws IOException {
        while(Character.isLetterOrDigit(this.getCarCour())){
            this.getSymbCour().setNom(this.getSymbCour().getNom()+this.getCarCour());
            lireCar();
            if(Character.isLetter(this.getCarCour())){
                lireMot();
                return;
            }

        }
        this.getSymbCour().setToken(Tokens.NUM_TOKEN);
        this.val =  Integer.parseInt(this.getSymbCour().getNom());
    }

    public int getPlaceSymb() {
        return placeSymb;
    }

    public List<Symboles> getTableSymb() {
        return tableSymb;
    }

    public void setTableSymb(List<Symboles> tableSymb) {
        this.tableSymb = tableSymb;
    }

    public void setPlaceSymb(int placeSymb) {
        this.placeSymb = placeSymb;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
