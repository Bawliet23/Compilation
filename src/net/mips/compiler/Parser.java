package net.mips.compiler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.security.cert.CertificateRevokedException;

public class Parser {

    protected     ScannerWS scanner ;
    public Parser(String filename) throws FileNotFoundException {
        this.scanner = new ScannerWS(filename);

    }

    public Parser() {
    }

    public static void main(String[] args) throws IOException, ErreurSyntaxique, ErreurSemantique {
        Parser sc = new Parser("E:/projects/Compilateur_Like_Pascal/src/net/mips/compiler/sc.txt");
        sc.scanner.initMotsCles();
        sc.scanner.lireCar();
//        while (sc.scanner.getCarCour()!=Scanner.EOF) {
            sc.scanner.symSuivant();
            sc.Program();
//        }

        }

    public ScannerWS getScanner() {
        return scanner;
    }

    public void setScanner(ScannerWS scanner) {
        this.scanner = scanner;
    }

    public void testAccept(Tokens t, CodesErr c) throws IOException, ErreurSyntaxique {
        if (this.scanner.getSymbCour().getToken().equals(t)){
            this.scanner.symSuivant();
        }else{
            Erreur(c);
        }
    }
    public void Erreur(CodesErr err) throws ErreurSyntaxique {
        throw new ErreurSyntaxique( err.getMessage());


    }
    public void  Program() throws IOException, ErreurSyntaxique, ErreurSemantique {
        testAccept(Tokens.PROGRAM_TOKEN,CodesErr.PROGRAM_ERR);
        testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
        testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
        Block();

        testAccept(Tokens.PNT_TOKEN,CodesErr.PNT_ERR);
    }

    public void Block() throws IOException, ErreurSyntaxique, ErreurSemantique {
        Consts();
       Vars();
       Insts();
    }
    public   void Consts() throws IOException, ErreurSyntaxique, ErreurSemantique {
        if ((this.scanner.getSymbCour().getToken().equals(Tokens.CONST_TOKEN))) {
            testAccept(Tokens.CONST_TOKEN, CodesErr.CONST_ERR);
            while (this.scanner.getSymbCour().getToken().equals(Tokens.ID_TOKEN)){
//               test_insere(Tokens.ID_TOKEN,ClasseIdf.CONSTANTE,code)
                testAccept(Tokens.ID_TOKEN, CodesErr.ID_ERR);
                testAccept(Tokens.AFFEC_TOKEN, CodesErr.AFFEC_ERR);
                testAccept(Tokens.NUM_TOKEN, CodesErr.NUM_ERR);
                testAccept(Tokens.PVIR_TOKEN, CodesErr.PVIR_ERR);
            }

        }
    }

    public void Vars() throws IOException, ErreurSyntaxique, ErreurSemantique {
        if ((this.scanner.getSymbCour().getToken().equals(Tokens.VAR_TOKEN))) {
            testAccept(Tokens.VAR_TOKEN, CodesErr.VAR_ERR);
            testAccept(Tokens.ID_TOKEN, CodesErr.ID_ERR);
            while (this.scanner.getSymbCour().getToken().equals(Tokens.VIR_TOKEN)){
                testAccept(Tokens.VIR_TOKEN, CodesErr.VIR_ERR);
                testAccept(Tokens.ID_TOKEN, CodesErr.ID_ERR);
            }
            testAccept(Tokens.PVIR_TOKEN, CodesErr.PVIR_ERR);
        }
    }

    public void Insts() throws IOException, ErreurSyntaxique, ErreurSemantique {
        testAccept(Tokens.BEGIN_TOKEN,CodesErr.BEGIN_ERR);
        Inst();
        while (this.scanner.getSymbCour().getToken().equals(Tokens.PVIR_TOKEN)){
            testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
            Inst();
        }
        testAccept(Tokens.END_TOKEN, CodesErr.END_ERR);
    }

    public void Inst() throws IOException, ErreurSyntaxique, ErreurSemantique {
        switch(this.scanner.getSymbCour().getToken()) {
            case ID_TOKEN:
                AFFECT();
                break;
            case WHILE_TOKEN:
                Tantque();
                break;
            case READ_TOKEN:
                Lire();
                break;
            case IF_TOKEN:
                SI();
                break;
            case WRITE_TOKEN:
                Ecrire();
                break;
            case BEGIN_TOKEN:
                Insts();
                break;
        }

    }

    public void AFFECT() throws IOException, ErreurSyntaxique, ErreurSemantique {
        testAccept(Tokens.ID_TOKEN, CodesErr.ID_ERR);
        testAccept(Tokens.AFFEC_TOKEN,CodesErr.AFFEC_ERR);
        EXPR();
    }
    public void SI() throws IOException, ErreurSyntaxique, ErreurSemantique {
        testAccept(Tokens.IF_TOKEN, CodesErr.IF_ERR);
        Cond();
        testAccept(Tokens.THEN_TOKEN, CodesErr.THEN_ERR);
        Inst();
    }
    public void Tantque() throws IOException, ErreurSyntaxique, ErreurSemantique {
        testAccept(Tokens.WHILE_TOKEN, CodesErr.WHILE_ERR);
        Cond();
        testAccept(Tokens.DO_TOKEN, CodesErr.DO_ERR);
        Inst();

    }
    public void Ecrire() throws IOException, ErreurSyntaxique, ErreurSemantique {
        testAccept(Tokens.WRITE_TOKEN, CodesErr.WRITE_ERR);
        testAccept(Tokens.PARG_TOKEN, CodesErr.PARG_ERR);
        EXPR();
        while (this.scanner.getSymbCour().getToken().equals(Tokens.VIR_TOKEN)){
            testAccept(Tokens.VIR_TOKEN, CodesErr.VIR_ERR);
            EXPR();
        }
        testAccept(Tokens.PARD_TOKEN, CodesErr.PARD_ERR);

    }
    public void Lire() throws IOException, ErreurSyntaxique, ErreurSemantique {
        testAccept(Tokens.READ_TOKEN, CodesErr.READ_ERR);
        testAccept(Tokens.PARG_TOKEN, CodesErr.PARG_ERR);
        testAccept(Tokens.ID_TOKEN, CodesErr.ID_ERR);
        while (this.scanner.getSymbCour().getToken().equals(Tokens.VIR_TOKEN)){
            testAccept(Tokens.VIR_TOKEN, CodesErr.VIR_ERR);
            testAccept(Tokens.ID_TOKEN, CodesErr.ID_ERR);
        }
        testAccept(Tokens.PARD_TOKEN, CodesErr.PARD_ERR);




    }
    public void Cond() throws IOException, ErreurSyntaxique, ErreurSemantique {
        EXPR();
        Relop();
        EXPR();
    }
    public void Relop() throws IOException, ErreurSyntaxique {
        switch (this.scanner.getSymbCour().getToken()) {
            case AFFEC_TOKEN:
                testAccept(Tokens.AFFEC_TOKEN, CodesErr.AFFEC_ERR);
                break;
            case DIFF_TOKEN:
                testAccept(Tokens.DIFF_TOKEN, CodesErr.DIFF_ERR);
                break;
            case INF_TOKEN:
                testAccept(Tokens.INF_TOKEN, CodesErr.INF_ERR);
                break;
            case SUP_TOKEN:
                testAccept(Tokens.SUP_TOKEN, CodesErr.SUP_ERR);
                break;
            case INFEG_TOKEN:
                testAccept(Tokens.INFEG_TOKEN, CodesErr.INFEG_ERR);
                break;
            case SUPEG_TOKEN:
                testAccept(Tokens.SUPEG_TOKEN, CodesErr.SUPEG_ERR);
                break;
            default:Erreur(CodesErr.DEFAULT_ERR);
        }
    }
    public void EXPR() throws IOException, ErreurSyntaxique, ErreurSemantique {
        Term();
        while (this.scanner.getSymbCour().getToken().equals(Tokens.PLUS_TOKEN) || this.scanner.getSymbCour().getToken().equals(Tokens.MOINS_TOKEN) ){
            Addop();
            Term();
        }

    }
    public void Addop() throws IOException, ErreurSyntaxique {
        switch  (this.scanner.getSymbCour().getToken()) {
            case PLUS_TOKEN :
                testAccept(Tokens.PLUS_TOKEN,CodesErr.PLUS_ERR);
                break;
            case MOINS_TOKEN:
                testAccept(Tokens.MOINS_TOKEN,CodesErr.MOINS_ERR);
                break;
            default:Erreur(CodesErr.DEFAULT_ERR);
        }
    }
    public void Term() throws IOException, ErreurSyntaxique, ErreurSemantique {
        Fact();
        while (this.scanner.getSymbCour().getToken().equals(Tokens.MUL_TOKEN) || this.scanner.getSymbCour().getToken().equals(Tokens.DIV_TOKEN) ){
            Mulop();
            Term();
        }

    }
    public void Mulop() throws IOException, ErreurSyntaxique {
        switch  (this.scanner.getSymbCour().getToken()) {
            case MUL_TOKEN:
                testAccept(Tokens.MUL_TOKEN,CodesErr.MUL_ERR);
                break;
            case DIV_TOKEN:
                testAccept(Tokens.DIV_TOKEN,CodesErr.DIV_ERR);
                break;
            default:Erreur(CodesErr.DEFAULT_ERR);
        }
    }
    public void Fact() throws IOException, ErreurSyntaxique, ErreurSemantique {
        switch  (this.scanner.getSymbCour().getToken()) {
            case ID_TOKEN:
                testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
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
