package tsn_java_poi_xlsx;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class JavaApplication1 {
    

    static {
        // Установка формата вывода для java.util.logging.SimpleFormatter
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT [%4$-7s] %3$s - %5$s %n");

    }
    static java.util.logging.Logger log = java.util.logging.Logger.getLogger(JavaApplication1.class.getName());

    // Объявление логировцика типа org.apache.log4j.Logger
    static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(JavaApplication1.class);

    
    
    // Модификация данных в XLSX-файле
    public static void modifyXLSXFile(String xlsxFileName , double sal , double income) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(xlsxFileName));
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator(); 
        XSSFSheet sheet = wb.getSheetAt(0);
        log4j.info("Запускаем обновление файла");
        sheet.getRow(0).getCell(1).setCellValue(String.valueOf(income));
        sheet.getRow(1).getCell(1).setCellValue(String.valueOf(sal));
        evaluator.evaluateFormulaCell(wb.getSheetAt(0).getRow(2).getCell(1));
        evaluator.evaluateFormulaCell(wb.getSheetAt(0).getRow(3).getCell(1));
        evaluator.evaluateFormulaCell(wb.getSheetAt(0).getRow(4).getCell(1));
        log4j.info("Мы обновили Excel файл");
        //wb.setForceFormulaRecalculation(true);
        FileOutputStream fos = new FileOutputStream(xlsxFileName);
        wb.write(fos);
        fos.flush();
        fos.close();
    }

    // Чтение данных из XLSX-файла
   

    public static void main(String[] args) throws IOException {
        
            Handler fileHandler = new FileHandler("logging.log", 100 *  1024, 3, true);
            fileHandler.setFormatter(new SimpleFormatter());
            log.addHandler(fileHandler);
            log.info("Start");
        log.log(Level.INFO, "Полезная информация");
        log.log(Level.WARNING, "Предупреждаю полезная информация");
        log.log(Level.SEVERE, "Ошибка информация бесполезна");
        log.info("На этом log все , дальше log4");

        try {
            throw new Exception("ERR!");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "My Exception: {0}", ex.getMessage());
        }
        log.info("Идем дальше");
        try {
            System.out.println("Начинаем работу программы: ");
             log4j.info("Начали работу программы");
      
        
            String xlsxFileName = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
                    + System.getProperty("file.separator") + "input.xlsx";
            Scanner scan = new Scanner(System.in, "Windows-1251");
            System.out.print("Введите ваш доход за месяц: ");
            double income = scan.nextDouble();
            if(income <= 0){
                log4j.warn("У вас убытки из-за дохода");
                
            }else{log4j.info("У вас положительный доход");}
            System.out.print("Введите вашу зарплату за месяц: ");
            double salary = scan.nextDouble();
            if(salary>income){
                log4j.error("Вы наврали про доходы");
            }else{log4j.info("С зарплатой все в норме");}
            modifyXLSXFile(xlsxFileName, salary, income);
            Desktop.getDesktop().open(new File(xlsxFileName));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            org.apache.log4j.LogManager.shutdown();
    }

}
