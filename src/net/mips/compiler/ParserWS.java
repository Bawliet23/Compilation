package net.mips.compiler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ParserWS extends Parser{
//    private ScannerWS scanner ;
    public ParserWS(String filename) throws FileNotFoundException {
        super(filename);
    }

    public static void main(String[] args) throws IOException, ErreurSyntaxique, ErreurSemantique {
        ParserWS sc = new ParserWS("E:/projects/Compilateur_Like_Pascal/src/net/mips/compiler/sc.txt");
        sc.scanner.initMotsCles();
        sc.scanner.lireCar();
//        while (sc.scanner.getCarCour()!=Scanner.EOF) {
        sc.scanner.symSuivant();
        sc.Program();

//        }

    }

    public void test_Insere(Tokens t , ClasseIdf cdi, CodesErr err) throws IOException, ErreurSemantique {
        if (this.scanner.getSymbCour().getToken().equals(t)){
                this.scanner.cherche_Symb();
                if (this.scanner.getPlaceSymb() != -1 )
                    throw new ErreurSemantique(CodesErr.DOUBLEDECLARATION_ERR.getMessage());
            this.scanner.entrerSymb(cdi);
            this.getScanner().symSuivant();
        }else{
            throw new ErreurSemantique(err.getMessage());
        }
    }

    public void test_Cherche(Tokens t,CodesErr err) throws IOException, ErreurSemantique {
        if (this.scanner.getSymbCour().getToken().equals(t)) {
            this.scanner.cherche_Symb();
            if (this.scanner.getPlaceSymb() == -1 ){
                throw new ErreurSemantique(CodesErr.IDNOTFOUND_ERR.getMessage());
            }else if(this.scanner.getTableSymb().get(this.scanner.getPlaceSymb()).getClasseIdf() == ClasseIdf.PROGRAMME){
                throw new ErreurSemantique(CodesErr.PROGRAMMEID_ERR.getMessage());
            }
            this.getScanner().symSuivant();
        }else{
            throw new ErreurSemantique(err.getMessage());
        }
    }

    @Override
    public void Program() throws IOException, ErreurSyntaxique, ErreurSemantique {
        testAccept(Tokens.PROGRAM_TOKEN,CodesErr.PROGRAM_ERR);
        test_Insere(Tokens.ID_TOKEN,ClasseIdf.PROGRAMME,CodesErr.ID_ERR);
        testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
        Block();

        testAccept(Tokens.PNT_TOKEN,CodesErr.PNT_ERR);
    }

    @Override
    public void Consts() throws IOException, ErreurSyntaxique, ErreurSemantique {
        if ((this.scanner.getSymbCour().getToken().equals(Tokens.CONST_TOKEN))) {
            testAccept(Tokens.CONST_TOKEN, CodesErr.CONST_ERR);
            while (this.scanner.getSymbCour().getToken().equals(Tokens.ID_TOKEN)){
                test_Insere(Tokens.ID_TOKEN,ClasseIdf.CONSTANTE,CodesErr.CONST_ERR);
                testAccept(Tokens.AFFEC_TOKEN, CodesErr.AFFEC_ERR);
                testAccept(Tokens.NUM_TOKEN, CodesErr.NUM_ERR);
                testAccept(Tokens.PVIR_TOKEN, CodesErr.PVIR_ERR);
            }

        }
    }

    @Override
    public void Vars() throws IOException, ErreurSyntaxique, ErreurSemantique {
        if ((this.scanner.getSymbCour().getToken().equals(Tokens.VAR_TOKEN))) {
            testAccept(Tokens.VAR_TOKEN, CodesErr.VAR_ERR);
            this.test_Insere(Tokens.ID_TOKEN,ClasseIdf.VARIALE,CodesErr.VAR_ERR);
            while (this.scanner.getSymbCour().getToken().equals(Tokens.VIR_TOKEN)){
                testAccept(Tokens.VIR_TOKEN, CodesErr.VIR_ERR);
                this.test_Insere(Tokens.ID_TOKEN,ClasseIdf.VARIALE,CodesErr.VAR_ERR);
            }
            testAccept(Tokens.PVIR_TOKEN, CodesErr.PVIR_ERR);
        }
    }

    @Override
    public void AFFECT() throws IOException, ErreurSyntaxique, ErreurSemantique {
        this.scanner.cherche_Symb();
        if(this.scanner.getPlaceSymb()!=-1 && this.scanner.getTableSymb().get(this.scanner.getPlaceSymb()).getClasseIdf() == ClasseIdf.CONSTANTE){
            throw new ErreurSemantique(CodesErr.CONST_ERR.getMessage());
        }
        test_Cherche(Tokens.ID_TOKEN, CodesErr.ID_ERR);
        testAccept(Tokens.AFFEC_TOKEN,CodesErr.AFFEC_ERR);
        EXPR();
    }

    @Override
    public void Lire() throws IOException, ErreurSyntaxique, ErreurSemantique {
        testAccept(Tokens.READ_TOKEN, CodesErr.READ_ERR);
        testAccept(Tokens.PARG_TOKEN, CodesErr.PARG_ERR);
        this.scanner.cherche_Symb();
         if(this.scanner.getPlaceSymb()!=-1 && this.scanner.getTableSymb().get(this.scanner.getPlaceSymb()).getClasseIdf() == ClasseIdf.CONSTANTE){
            throw new ErreurSemantique(CodesErr.CONST_ERR.getMessage());
        }
        test_Cherche(Tokens.ID_TOKEN, CodesErr.ID_ERR);
        while (this.scanner.getSymbCour().getToken().equals(Tokens.VIR_TOKEN)){
            testAccept(Tokens.VIR_TOKEN, CodesErr.VIR_ERR);
            test_Cherche(Tokens.ID_TOKEN, CodesErr.ID_ERR);
        }
        testAccept(Tokens.PARD_TOKEN, CodesErr.PARD_ERR);
    }

    @Override
    public void Fact() throws IOException, ErreurSyntaxique, ErreurSemantique {
        switch  (this.scanner.getSymbCour().getToken()) {
            case ID_TOKEN:
                test_Cherche(Tokens.ID_TOKEN,CodesErr.ID_ERR);
                break;
            case NUM_TOKEN:
                testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
                break;
            case PARG_TOKEN:
                testAccept(Tokens.PARG_TOKEN,CodesErr.PARG_ERR);
                EXPR();
                testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
                break;
            default:Erreur(CodesErr.DEFAULT_ERR);
        }


    }
}
