package com.example.gestion;



public class Article {
    private int id;
    private String codeArticle;
    private String libelle;
    private int qte;
    private Double prixHT;

    // Getter and Setter for PrixHT
    public Double getPrixHT() {
        return prixHT;
    }

    public void setPrixHt(Double prixHT) {
        this.prixHT = prixHT;
    }

    // Other getters and setters for the remaining properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}

