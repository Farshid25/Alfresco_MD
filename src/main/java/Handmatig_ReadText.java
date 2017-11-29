import java.io.*;

public class Handmatig_ReadText {
    //Path path2 = Paths.get("C:\\Users\\Farshid\\Desktop\\Alfresco\\Test-Docs Alfresco\\Aanvullende docs\\New Fafa(Docx).docx");
    public String Test(String filepad) {
        // try{ //(LanguageServiceClient language = LanguageServiceClient.create()) {
        //String ppath = "C:\\Users\\Farshid\\Desktop\\Alfresco\\Test-Docs Alfresco\\Aanvullende docs\\New Fafa(TXT).txt";
//            byte[] encoded = Files.readAllBytes(Paths.get(path));
//
//            String [] files = Directory
        FileReader in = null;
        //FileWriter out = null;
        String output = "";

        try {
            in = new FileReader(filepad);
            int c;
            do {
                c = in.read();
                output += (char) c;
            } while (c != -1);

        } catch (Exception e) {
            System.out.println(e);
        }

        return output;
    }

    public static void main(String[] args) {
        Handmatig_ReadText handmatigReadText = new Handmatig_ReadText();
        System.out.println(handmatigReadText.Test("src\\main\\resources\\Txt.txt"));

    }
}

