package net.mips.compiler;

public class ErreurSyntaxique extends ErreurCompilation{

    public ErreurSyntaxique(String message) {
        super(message);
    }
    public ErreurSyntaxique(CodesErr err) {
        super(err.getMessage());
    }

}
