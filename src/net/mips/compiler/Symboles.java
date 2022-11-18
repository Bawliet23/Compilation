package net.mips.compiler;

public class Symboles {
    private Tokens token;
    private String nom="";

    private ClasseIdf classeIdf;

    public Symboles(Tokens token, String nom, ClasseIdf classeIdf) {
        this.token = token;
        this.nom = nom;
        this.classeIdf = classeIdf;
    }

    public Symboles() {
    }

    public Symboles(Tokens token, String nom) {
        this.token = token;
        this.nom = nom;
    }

    public Tokens getToken() {
        return token;
    }

    public void setToken(Tokens token) {
        this.token = token;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ClasseIdf getClasseIdf() {
        return classeIdf;
    }

    public void setClasseIdf(ClasseIdf classeIdf) {
        this.classeIdf = classeIdf;
    }

    @Override
    public String toString() {
        return "Symboles{" +
                "token=" + token +
                ", nom='" + nom + '\'' +
                ", classeIdf=" + classeIdf +
                '}';
    }
}
