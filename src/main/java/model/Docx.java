package model;

import javax.print.Doc;

public class Docx  {
   // private String docx;
    private double confidence;
    private String category;
//    public Docx(String nm, String tit){
//       super(nm, tit);
       ///this.docx = doc;

public Docx(){}


    public double getConfidence() {
        return confidence;
    }

    public void setCategory(String  category) {
        this.category = category;
    }

    public String getCategory(){
    return this.category;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return
                "Category = " + getCategory() +
                "\nAND Confidence = " + getConfidence();
    }
}
