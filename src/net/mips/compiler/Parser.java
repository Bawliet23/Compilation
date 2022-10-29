package net.mips.compiler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateRevokedException;

public class Parser {

    private  Scanner scanner ;
    public Parser(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(filename);
    }



    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void testAccept(Tokens t, CodesErr c) throws IOException {
        if (this.scanner.getSymbCour().getToken().equals(t)){
            this.scanner.symSuivant();
        }else{
            Erreur(c);
        }
    }
    private void Erreur(CodesErr err){
        err.getMessage();
    }
    private void  Program() throws IOException {
        testAccept(Tokens.PROGRAM_TOKEN,CodesErr.PROGRAM_ERR);
        testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
        testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
        //Block();
        testAccept(Tokens.PNT_TOKEN,CodesErr.PNT_ERR);


    }
    private  void Consts() throws IOException {
        testAccept(Tokens.CONST_TOKEN,CodesErr.CONST_ERR);
        testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
        testAccept(Tokens.AFFEC_TOKEN,CodesErr.AFFEC_ERR);
        testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
        testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
        testAccept(Tokens.BRL_TOKEN,CodesErr.BRL_ERR);
        testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
        testAccept(Tokens.AFFEC_TOKEN,CodesErr.AFFEC_ERR);
        testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
        testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
        testAccept(Tokens.BRR_TOKEN,CodesErr.BRR_ERR);



    }

}
