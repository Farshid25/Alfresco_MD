package content_reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class Apache_Poi_OfficeExcel {
   // public static void main(String... args) throws Exception {
    public String readFile(String filePath){
        //String path = "src\\main\\resources\\excel.xlsx";
        try {
            FileInputStream docx = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(docx);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            String text = "";

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        text += (currentCell.getStringCellValue() + " ");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        text +=  (currentCell.getNumericCellValue() + " ");
                    }

                }
            }
            return text;
        }catch (Exception e) {
            System.out.println(e);
        }

    return null;
    }

    public static void main(String[] args) {
        Apache_Poi_OfficeExcel t = new Apache_Poi_OfficeExcel();
        //String poep = t.Getxlsx("src\\main\\resources\\excel.xlsx");

        System.out.println(t.readFile("src\\main\\resources\\Excel.xlsx"));
        //System.out.println(poep);

    }
}

