package tsn_java_poi_xlsx;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JavaApplication1 {



    // Модификация данных в XLSX-файле
    public static void modifyXLSFile(String xlsFileName , double sal , double income) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(xlsFileName));
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator(); 
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.getRow(0).getCell(1).setCellValue(String.valueOf(income));
        sheet.getRow(1).getCell(1).setCellValue(String.valueOf(sal));
        evaluator.evaluateFormulaCell(wb.getSheetAt(0).getRow(2).getCell(1));
        evaluator.evaluateFormulaCell(wb.getSheetAt(0).getRow(3).getCell(1));
        evaluator.evaluateFormulaCell(wb.getSheetAt(0).getRow(4).getCell(1));
        //wb.setForceFormulaRecalculation(true);
        FileOutputStream fos = new FileOutputStream(xlsFileName);
        wb.write(fos);
        fos.flush();
        fos.close();
    }

    // Чтение данных из XLSX-файла
   

    public static void main(String[] args) {
        try {
            String xlsFileName = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
                    + System.getProperty("file.separator") + "input.xls";
            Scanner scan = new Scanner(System.in, "Windows-1251");
            System.out.print("Введите ваш доход за месяц: ");
            double income = scan.nextDouble();
            System.out.print("Введите вашу зарплату за месяц: ");
            double salary = scan.nextDouble();
            modifyXLSFile(xlsFileName, salary, income);
            Desktop.getDesktop().open(new File(xlsFileName));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
