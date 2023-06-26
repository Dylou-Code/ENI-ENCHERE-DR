package fr.eni.eniencheredr.bo;

public class Categories {
    private Integer no_categorie;
    private String libelle;

    public Categories() {
    }
    public Categories(Integer no_categorie, String libelle) {
        this.no_categorie = no_categorie;
        this.libelle = libelle;
    }
    public Integer getNo_categorie() {
        return no_categorie;
    }

    public void setNo_categorie(Integer no_categorie) {
        this.no_categorie = no_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "no_categorie=" + no_categorie +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
