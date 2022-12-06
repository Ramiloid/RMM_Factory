package tsn_java_poi_docx;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class JavaApplication1 {

    public static void main(String[] args) throws Exception {        
        System.out.print("Начинаем формирование отчета введите ваше ФИО: ");
        Scanner scan = new Scanner(System.in, "Windows-1251");
        String username = scan.nextLine();
        System.out.print("Введите должность кому направляете заявление: ");
        String rank = scan.nextLine();
        System.out.print("Введите ФИО директора: ");
        String name = scan.nextLine();
        
        String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        System.out.print("Введите причину отпуска: ");
        String cause = scan.nextLine();
        System.out.print("Введите подпись: ");
        String crow = scan.nextLine();
        String dir = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
                + System.getProperty("file.separator");
        XWPFDocument doc = new XWPFDocument(OPCPackage.open(dir + "input.docx"));
        editWordFile("username",username,doc);
        editWordFile("rank",rank,doc);
        editWordFile("name",name,doc);
        editWordFile("date",date,doc);
        editWordFile("cause",cause,doc);
        editWordFile("crow",crow,doc);

        FileOutputStream fos = new FileOutputStream(dir + "output.docx");
        doc.write(fos);
        fos.close(); 
        Desktop.getDesktop().open(new File(dir + "output.docx"));
    }
      public static void editWordFile(String varius , String value, XWPFDocument doc){
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains(varius)) {
                        text = text.replace(varius, value);
                        r.setText(text, 0);
                    }
                }
            }
        }
    }
  
}
