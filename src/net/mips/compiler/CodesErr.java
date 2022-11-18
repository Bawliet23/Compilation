package net.mips.compiler;

public enum CodesErr {
    CAR_INC_ERR("CARACTERE INCONNU"),
    FIC_VID_ERR("FICHIER VIDE"),
    PROGRAM_ERR("Mot cle program attendu !"),
    ID_ERR("Identification attendu !"),
    PVIR_ERR("Symbole ; attendu !"),
    VIR_ERR("Symbole , attendu !"),
    PNT_ERR("Symbole . attendu !"),
    CONST_ERR("Const ne peut pas changer"),
    AFFEC_ERR("mot cle = attendu !"),
    NUM_ERR("Numero attendu !"),
    BRR_ERR("mot cle } attendu !"),
    BRL_ERR("mot cle { attendu !"),
    VAR_ERR("mot cle var attendu"),
    BEGIN_ERR("mot cle BEGIN attendu !!"),
    END_ERR("mot cle END attendu !!"),
    IF_ERR("mot cle IF attendu !!"),
    DIFF_ERR("mot cle != attendu !!"),
    INF_ERR("mot cle < attendu !!"),
    INFEG_ERR("mot cle <= attendu !!"),
    SUP_ERR("mot cle > attendu !!"),
    SUPEG_ERR("mot cle >= attendu !!"),
    DEFAULT_ERR("Default error"),
    PLUS_ERR("mot cle + attendu !!"),
    MOINS_ERR("mot cle - attendu!!"),
    MUL_ERR("mot cle * attendu!!"),
    DIV_ERR("mot cle / attendu!!"),
    THEN_ERR("Mot cle Then attendu!! "),
    WHILE_ERR("Mot cle While attendu!! "),
    DO_ERR("Mot cle Do attendu!! "),
    WRITE_ERR("Mot cle Write attendu!! "),
    PARG_ERR("Mot cle ( attendu!! "),
    PARD_ERR("Mot cle ) attendu!! "),
    READ_ERR("Mot cle Read attendu!! "),
    PROGRAMMEID_ERR(" nom de programme non autorisé"),
    IDNOTFOUND_ERR(" identificateur  non déclaré"),
    DOUBLEDECLARATION_ERR("double déclaration")
    ;



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


