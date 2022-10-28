package net.mips.compiler;

public class ErreurLexicale extends ErreurCompilation{

    public ErreurLexicale(CodesErr message) {
        super(CodesErr.CAR_INC_ERR.getMessage());
    }
}
