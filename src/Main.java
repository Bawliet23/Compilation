import net.mips.compiler.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner("E:/projects/Compilateur_Like_Pascal/src/net/mips/compiler/sc.txt");
        sc.initMotsCles();
          sc.symSuivant();



    }
}