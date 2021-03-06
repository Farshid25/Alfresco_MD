package services.TextAnalyticsApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import services.content_reader.Apacke_Tika_Docx;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AnalyzeSentiment_MS {

    static class Document {
        public String id, text,language;

        public Document(String id, String language, String text) {
            this.id = id;
            this.language = language;
            this.text = text;
        }
    }

    static class Documents {
        public List<Document> documents;

        public Documents() {
            this.documents = new ArrayList<Document>();
        }

        public void add(String id, String language, String text) {
            this.documents.add(new Document(id, language, text));
        }
    }

    static class GetSentiment  {
        static String accessKey = "0bfb865c26a0405f99fdd806b24bea2d";
        static String host = "https://westcentralus.api.cognitive.microsoft.com";

        static String path = "/text/analytics/v2.0/sentiment"; // of text/analytics/v2.0

        public static String GetSentiment  (Documents documents) throws Exception {
            String text = new Gson().toJson(documents);
            byte[] encoded_text = text.getBytes("UTF-8");

            URL url = new URL(host + path);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/json");
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", accessKey);
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(encoded_text, 0, encoded_text.length);
            wr.flush();
            wr.close();

            StringBuilder response = new StringBuilder();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            return response.toString();
        }

        public static String prettify(String json_text) {
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(json_text).getAsJsonObject();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(json);
        }

//        public static void main(String[] args) {
//            Apacke_Tika_Docx docxReader = new Apacke_Tika_Docx();
//            try {
//                Documents documents = new Documents();
//                documents.add ("1", "en", docxReader.readFile("src\\main\\Aanvullende_Files\\Klacht.docx\\")); //"I really enjoy the new XBox One S. It has a clean look, it has 4K/HDR resolution and it is affordable.");
////                documents.add ("2", "es", "Si usted quiere comunicarse con Carlos, usted debe de llamarlo a su telefono movil. Carlos es muy responsable, pero necesita recibir una notificacion si hay algun problema.");
////                documents.add ("3", "en", "The Grand Hotel is a new hotel in the center of Seattle. It earned 5 stars in my review, and has the classiest decor I've ever seen.");
//
//                String response = GetSentiment  (documents);
//                System.out.println(prettify(response));
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
    }
}



