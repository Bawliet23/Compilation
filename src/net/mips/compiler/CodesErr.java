package net.mips.compiler;

public enum CodesErr {
    CAR_INC_ERR("CARACTERE INCONNU"), FIC_VID_ERR("FICHIER VIDE"), PROGRAM_ERR("Mot cle program attendu !"), ID_ERR("Identification attendu !"), PVIR_ERR("Symbole ; attendu !");

    private String message;

   private CodesErr(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

