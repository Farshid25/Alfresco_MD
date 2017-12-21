package services.NaturalLangugageApi;

import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.apache.regexp.RE;
import services.content_reader.*;

import java.util.*;
import java.util.stream.Collectors;

public class Analyze_Entities {
    ArrayList<model.Entity> entities = new ArrayList<>();

    public ArrayList<model.Entity> analyzeEntities(String text ) throws Exception {
//        model.Entity entityobject = new model.Entity();

        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Type.PLAIN_TEXT)
                    .build();
            AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder()
                    .setDocument(doc)
                    .setEncodingType(EncodingType.UTF16)
                    .build();

            AnalyzeEntitiesResponse response = language.analyzeEntities(request);

            for (Entity entity : response.getEntitiesList()) {
                //entities.add(new model.Entity(entity.getName(), entity.getSalience()));
                //entityobject.setName(entity.getName());

            for (EntityMention mention : entity.getMentionsList()) {

//                System.out.printf("Content: %s\n", mention.getText().getContent());           // alleen zinnin i.c.m. Sentences, anders geen input.
                entities.add(new model.Entity(entity.getName(),entity.getSalience(), mention.getType()));

                //System.out.printf("Type: %s\n\n", mention.getType()); //common/proper
            }
            }
        }
        return entities;
    }

    public static void main(String[] args) throws Exception {
        Analyze_Entities analyze_entitiesText = new Analyze_Entities();
//        Apache_Tika_PDF pdfReader = new Apache_Tika_PDF();
//        analyze_entitiesText.analyzeEntities(pdfReader.readFile("src\\main\\Aanvullende_Files\\poem.pdf"));

        Apacke_Tika_Docx docxReader = new Apacke_Tika_Docx();
        model.Entity object = new model.Entity();

        //ArrayList<model.Entity> entit;// = new ArrayList<>();
     //   List<model.Entity> entit = analyze_entitiesText.analyzeEntities(docxReader.readFile("src\\main\\Aanvullende_Files\\test1.docx\\"));

        }
        }
