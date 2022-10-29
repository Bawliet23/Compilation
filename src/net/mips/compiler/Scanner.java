package net.mips.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Scanner {
    private List<Symboles> motsCles;
    private Symboles symbCour;
    private char carCour;
    private FileReader fluxSour;

    public final static int EOF ='\uffff';

    public Scanner(String filename) throws FileNotFoundException {
        File f = new File(filename);
        if (!f.exists()){
            System.out.println(CodesErr.FIC_VID_ERR.getMessage());
        }
        this.fluxSour = new FileReader(f);
        this.motsCles = new ArrayList<>();
        this.symbCour= new Symboles();
    }
    public void initMotsCles(){
//        this.motsCles.add(new Symboles(Tokens.AFFEC_TOKEN,"="));
//        this.motsCles.add(new Symboles(Tokens.PLUS_TOKEN,"+"));
//        this.motsCles.add(new Symboles(Tokens.MOINS_TOKEN,"-"));
//        this.motsCles.add(new Symboles(Tokens.MUL_TOKEN,"*"));
//        this.motsCles.add(new Symboles(Tokens.DIV_TOKEN,"/"));
//        this.motsCles.add(new Symboles(Tokens.EG_TOKEN,"=="));
//        this.motsCles.add(new Symboles(Tokens.DIFF_TOKEN,"!="));
//        this.motsCles.add(new Symboles(Tokens.INF_TOKEN,"<"));
//        this.motsCles.add(new Symboles(Tokens.SUP_TOKEN,">"));
//        this.motsCles.add(new Symboles(Tokens.INFEG_TOKEN,"<="));
//        this.motsCles.add(new Symboles(Tokens.SUPEG_TOKEN,">="));
//        this.motsCles.add(new Symboles(Tokens.VIR_TOKEN,","));
//        this.motsCles.add(new Symboles(Tokens.PVIR_TOKEN,";"));
//        this.motsCles.add(new Symboles(Tokens.PNT_TOKEN,"."));
//        this.motsCles.add(new Symboles(Tokens.PARD_TOKEN,")"));
//        this.motsCles.add(new Symboles(Tokens.PARG_TOKEN,"("));
        this.motsCles.add(new Symboles(Tokens.BEGIN_TOKEN,"begin"));
        this.motsCles.add(new Symboles(Tokens.END_TOKEN,"end"));
        this.motsCles.add(new Symboles(Tokens.IF_TOKEN,"if"));
        this.motsCles.add(new Symboles(Tokens.WHILE_TOKEN,"while"));
        this.motsCles.add(new Symboles(Tokens.DO_TOKEN,"do"));
        this.motsCles.add(new Symboles(Tokens.THEN_TOKEN,"then"));
        this.motsCles.add(new Symboles(Tokens.WRITE_TOKEN,"write"));
        this.motsCles.add(new Symboles(Tokens.READ_TOKEN,"read"));
        this.motsCles.add(new Symboles(Tokens.CONST_TOKEN,"const"));
        this.motsCles.add(new Symboles(Tokens.VAR_TOKEN,"var"));
        this.motsCles.add(new Symboles(Tokens.PROGRAM_TOKEN,"program"));
        this.motsCles.add(new Symboles(Tokens.ERR_TOKEN,"err"));
    }

    public void codeLex(String mot){
        for (Symboles sym : motsCles){
            if(sym.getNom().toLowerCase().equals(mot)){
                this.symbCour.setToken(sym.getToken());
                return;
            }
        }
        this.symbCour.setToken(Tokens.ID_TOKEN);
    }
    public void lireCar() throws IOException {
       this.carCour = (char)this.fluxSour.read();
    }
    public void lireMot() throws IOException {
        do{
            this.symbCour.setNom(this.symbCour.getNom()+this.carCour);
            lireCar();
        }while(Character.isLetterOrDigit(this.carCour) );
        codeLex(this.symbCour.getNom());
    }

    public void lireNumber() throws IOException {
        while(Character.isLetterOrDigit(this.carCour)){
            this.symbCour.setNom(this.symbCour.getNom()+this.carCour);
            lireCar();
            if(Character.isLetter(this.carCour)){
                lireMot();
                return;
            }

        }
        this.symbCour.setToken(Tokens.NUM_TOKEN);
    }
public void  symSuivant() throws IOException {
            this.symbCour=new Symboles();
            lireCar();
            while (Character.isWhitespace(this.carCour)) {
                lireCar();
            }
            if (Character.isDigit(this.carCour)) {
                lireNumber();
            }else if (Character.isLetterOrDigit(this.carCour)) {
                lireMot();
            }
            switch (this.carCour){
                case '+':this.symbCour.setNom("+");
                this.symbCour.setToken(Tokens.PLUS_TOKEN);
                break;
                case '=':this.symbCour.setNom("=");
                    this.symbCour.setToken(Tokens.AFFEC_TOKEN);
                    break;
                case ';':this.symbCour.setNom(";");
                    this.symbCour.setToken(Tokens.PVIR_TOKEN);
                    break;
                case '{':this.symbCour.setNom("{");
                    this.symbCour.setToken(Tokens.BRL_TOKEN);
                    break;
                case '}':this.symbCour.setNom("}");
                    this.symbCour.setToken(Tokens.BRR_TOKEN);
                    break;

                case '-':this.symbCour.setNom("-");
                    this.symbCour.setToken(Tokens.MOINS_TOKEN);
                    break;
                case '*':this.symbCour.setNom("*");
                    this.symbCour.setToken(Tokens.MUL_TOKEN);
                    break;
                case '/':this.symbCour.setNom("/");
                    this.symbCour.setToken(Tokens.DIV_TOKEN);
                    break;
//                case (char) EOF:this.symbCour.setNom("EOF");
//                        this.symbCour.setToken(Tokens.EOF_TOKEN);
//                    System.out.println(this.getSymbCour().toString());
//                    break;
//                case "==":this.symbCour.setNom(";");
//                    this.symbCour.setToken(Tokens.EG_TOKEN);
//                    System.out.println(this.getSymbCour().toString());
//                    break;
//                case "!=":this.symbCour.setNom(";");
//                    this.symbCour.setToken(Tokens.DIFF_TOKEN);
//                    System.out.println(this.getSymbCour().toString());
//                    break;
                case '<':this.symbCour.setNom("<");
                    this.symbCour.setToken(Tokens.INF_TOKEN);
                    break;
                case '>':this.symbCour.setNom(">");
                    this.symbCour.setToken(Tokens.SUP_TOKEN);
                    break;
//                case "<=":this.symbCour.setNom(";");
//                    this.symbCour.setToken(Tokens.INFEG_TOKEN);
//                    System.out.println(this.getSymbCour().toString());
//                    break;
//                case ">=":this.symbCour.setNom(";");
//                    this.symbCour.setToken(Tokens.SUPEG_TOKEN);
//                    System.out.println(this.getSymbCour().toString());
//                    break;
                case ',':this.symbCour.setNom(",");
                    this.symbCour.setToken(Tokens.VIR_TOKEN);
                    break;
                case '.' :this.symbCour.setNom(".");
                    this.symbCour.setToken(Tokens.PNT_TOKEN);
                    break;
                case '(':this.symbCour.setNom("(");
                    this.symbCour.setToken(Tokens.PARG_TOKEN);
                    break;

                    case ')':this.symbCour.setNom(")");
                    this.symbCour.setToken(Tokens.PARD_TOKEN);
                    break;
                case (char) EOF :
                    this.symbCour.setNom("EOF");
                    this.symbCour.setToken(Tokens.EOF_TOKEN);
                    break;

            }
        }


    public List<Symboles> getMotsCles() {
        return motsCles;
    }

    public void setMotsCles(List<Symboles> motsCles) {
        this.motsCles = motsCles;
    }

    public Symboles getSymbCour() {
        return symbCour;
    }

    public void setSymbCour(Symboles symbCour) {
        this.symbCour = symbCour;
    }

    public char getCarCour() {
        return carCour;
    }

    public void setCarCour(char carCour) {
        this.carCour = carCour;
    }

    public FileReader getFluxSour() {
        return fluxSour;
    }

    public void setFluxSour(FileReader fluxSour) {
        this.fluxSour = fluxSour;
    }
}
