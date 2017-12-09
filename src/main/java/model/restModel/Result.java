package model.restModel;

public class Result {
    private String docType, taal, categorie;

    public Result(String docType, String taal, String categorie) {
        this.docType = docType;
        this.taal = taal;
        this.categorie = categorie;
    }

    public Result() {
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getTaal() {
        return taal;
    }

    public void setTaal(String taal) {
        this.taal = taal;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
