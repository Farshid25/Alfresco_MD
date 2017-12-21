//package model;
//
//import org.apache.tika.exception.TikaException;
//import org.xml.sax.SAXException;
//import services.NaturalLangugageApi.Analyze_Entities;
//import services.NaturalLangugageApi.Classifying_Content;
//import services.Type_Read_Switch.TypeDetection;
//import services.content_reader.Apacke_Tika_Docx;
//
//import java.io.IOException;
//import java.util.*;
//import java.util.stream.Collectors;
//
// public class DistinctTest {
//     public static void main(String[] args) throws Exception {
//
//         TypeDetection typeDetection = new TypeDetection();
//         Apacke_Tika_Docx apacheDoc = new Apacke_Tika_Docx();
//         Classifying_Content cc = new Classifying_Content();
//         Analyze_Entities ae = new Analyze_Entities();
//         model.Entity entityobject = new model.Entity();
//
//         List<Entity> valueList = ae.analyzeEntities(apacheDoc.readFile("src\\main\\Aanvullende_Files\\test1.docx\\"));
//
//         Map.Entry<String, Float> entry = null;
//         Map<String, Float> map = new HashMap<>();
//         ArrayList<Float> list = new ArrayList<>();
//
//         Float max  = Collections.max(list);
//
//         for (Map.Entry<String, Float> hash : valueList ) {
//            // list.add(hash)
//             map.put(hash.getKey(), hash.getValue());
//         }
//
//
////         try {
////
//
//               for (Map.Entry<String, Float> sal : map.entrySet()) {
//                   arr.add(sal.getKey());  arr.add(sal.getValue());
//
//                   //System.out.println(sal.getValue());
//               }
//                  //arr.add(sal.getValue());}
////
////                    String max  = Collections.max(arr).toString();
//////                     System.out.println("dit");
//////                     System.out.println("kir " + max);
//////                     System.out.println("dat");
////         } catch
////                 (Exception e) {
////             e.printStackTrace();
////         }
//     }
// }
//
//
////________________________________________________________________________________________________________________________________________
//       // public class SplitTest {
//            //public static void main(String[] args) throws Exception {
////                Apacke_Tika_Docx docx = new Apacke_Tika_Docx();
////
////                String goh = docx.readFile("src\\main\\Aanvullende_Files\\onzin.docx\\");
////                List<String> list = new ArrayList<>();
////                List<String> newList = new ArrayList<>();
////
////                String[] array = goh.split(" ");
////                int animalIndex = 0;
////                for (String animal : array) {
////                    list.add(animal);
////                }
////
////                for (String dd : list) {
////                    if (!newList.contains(dd)) {
////                        newList.add(dd);
////                    } else if (newList.contains(dd)) {
////                        continue;
////                    } else {
////                        //System.out.println("klaar");
////                    }
////
////                }
////                for (String pp : newList) {
////                    animalIndex++;
////                    //System.out.println(animalIndex + " " + pp);
////                }
////            }
////        }
//
//
////_____________________________Alle pogingen voor Distinct in Analyze_Entities.class
///*
// List<String> oudList = new ArrayList<>();
//        List<String> newList = new ArrayList<>();
//        String enti = String.valueOf(entit);
//        String[] array = enti.split(" ");
//
//        int animalIndex = 0;
//        for (String animal : array) {
//            oudList.add(animal);
//        }
//
//        for (String dd : oudList) {
//            if (!newList.contains(dd)) {
//                newList.add(dd);
//            } else if (newList.contains(dd)) {
//                continue;
//            } else {
//                System.out.println("klaar");
//            }
//        }for (String pp :newList){
//            animalIndex++;
//          System.out.println(animalIndex + " " + pp);
//        }
//        __________________________________________________________
//               //
//       // List<model.Entity> dd = new ArrayList<>();
//        //for (model.Entity currentWord : entit) {
//
//
//
////            if (dd.contains(currentWord)) {
////                //currentWord = previousWord;
////                System.out.println("duplicate");
////
////                //previousWord = currentWord;
////                //System.out.println(dd);
////            }else if(!dd.contains(currentWord)){
////            //    dd.add(currentWord);
////                System.out.println("added");
////            } else {
////                System.out.println("klaar");;
////            }
////        }
//
//        //    for (model.Entity d : dd) {
//               // String convertedToString = String.valueOf(d.getName());
//               // System.out.println(convertedToString);
//
//
////
////                for (model.Entity s : result)
////            System.out.println(s.getName());
//            // The result.
//            // System.out.println(result());
//
//
////        for (model.Entity e : entit) {
////            System.out.println(e.getName());
////
////            if (entit.contains(e.getName()))
////                System.out.println(e.getName());
//
//
////            if (e.getSalience() > 0.7)
////                System.out.println("test " + e.getNameBySalience(e.getSalience()));
////            else {
////                System.out.println("error");
////            }
//            // if ()
//            //}
//
//
////        for (model.Entity ent : analyze_entitiesText.analyzeEntities(docxReader.readFile("src\\main\\Aanvullende_Files\\test1.docx\\"), object)) {
////
////            System.out.println("Entitiy: " + ent.getName());
////            System.out.println("Salience: "+ent.getSalience()+"\n");
////            System.out.println("Type: " + ent.getType());
////        }
// */