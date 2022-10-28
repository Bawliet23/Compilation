package net.mips.compiler;

import java.io.FileNotFoundException;

public class Parser {

    public Parser(Scanner scanner) throws FileNotFoundException {
        this.scanner = scanner;
    }

    Scanner scanner = new Scanner("E:/projects/Compilateur_Like_Pascal/src/net/mips/compiler/sc.txt");

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void testAccept(Tokens t, CodesErr c){

    }


}
