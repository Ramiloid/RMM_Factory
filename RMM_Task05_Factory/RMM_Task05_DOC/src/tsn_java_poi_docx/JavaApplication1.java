package tsn_java_poi_docx;

import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class JavaApplication1 {
    
    static HWPFDocument doc = null;
    
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
            try (FileInputStream fis = new FileInputStream(dir + "input.doc")) {
                doc = new HWPFDocument(fis);
                fis.close();
            } catch (Exception ex) {
                System.err.println("Error template!");
            }

        editWordFile("username",username);
        editWordFile("rank",rank);
        editWordFile("name",name);
        editWordFile("date",date);
        editWordFile("cause",cause);
        editWordFile("crow",crow);
        
        try (FileOutputStream fos = new FileOutputStream(dir + "output.doc")) {
                doc.write(fos);
                fos.close();
                Desktop.getDesktop().open(new File(dir + "output.doc"));
        }
                catch (Exception ex) {
                System.err.println("Error getDesktop!");
            }
    }
        

      public static void editWordFile(String varius , String value){
       doc.getRange().replaceText(varius, value);
    }
  
}

