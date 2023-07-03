package fr.eni.eniencheredr.bo;

public class Retraits {
    private Articles_Vendus no_article;
    private String rue;
    private Integer code_postal;
    private String ville;

    public Retraits() {
    }

    public Retraits(String rue, Integer code_postal, String ville) {
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    public Retraits(Articles_Vendus no_article, String rue, Integer code_postal, String ville) {
        this.no_article = no_article;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    public Articles_Vendus getNo_article() {
        return no_article;
    }

    public void setNo_article(Articles_Vendus no_article) {
        this.no_article = no_article;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Integer getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(Integer code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Retraits{" +
                "no_article=" + no_article +
                ", rue='" + rue + '\'' +
                ", code_postal=" + code_postal +
                ", ville='" + ville + '\'' +
                '}';
    }
}
