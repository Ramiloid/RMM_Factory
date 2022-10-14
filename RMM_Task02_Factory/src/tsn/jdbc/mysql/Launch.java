package tsn.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Launch {

    static String jdbcUrl = "jdbc:mysql://localhost:3306/telephone_book?serverTimezone=Asia/Almaty&useSSL=false";
    static String username = "root";
    static String password = "";

    public static void watchTable() {

        String sql = "SELECT * FROM telephons";

        Scanner in = new Scanner(System.in);

        try ( Connection conn = DriverManager.getConnection(jdbcUrl, username, password);  Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
            String name, surname, telephone;
            ResultSet resultSet = stmt.executeQuery(sql);
            int Switcher=0;
            System.out.println("Хотите фильтровать данные? 1-Да 2-Нет");
            try{
                 Switcher = Integer.parseInt(in.nextLine());
            }catch(Exception e){
                System.exit(0);
            }
            ////////////фильтруем данные
            if (Switcher == 1) {
                System.out.println("По имени или фамили? 1-Имя 2-Фамилия 3 - Неважно");
                int k = Integer.parseInt(in.nextLine());
                System.out.println("Введите строку");
                String filter = in.nextLine();
                String sort = "";
                System.out.println("Сортировать по 1-Убыванию 2-Возрастанию");
                if(Integer.parseInt(in.nextLine())==1){
                    sort = "DESC";
                }else{ sort = "ASC";}
                
                

                if(k==1){
                     resultSet = stmt.executeQuery("SELECT * FROM telephons WHERE name LIKE '%"+filter+"%' "+"ORDER BY name "+sort);
                }else if(k==2){
                    resultSet = stmt.executeQuery("SELECT * FROM telephons WHERE surname LIKE '%"+filter+"%'"+"ORDER BY name "+sort);
                }else{
                    resultSet = stmt.executeQuery("SELECT * FROM telephons WHERE name LIKE '%"+filter+"%'"+"ORDER BY name "+sort+" OR  surname LIKE '%"+filter+"%'"+"+ORDER BY name "+sort);
                }

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    name = resultSet.getString("name");
                    surname = resultSet.getString("surname");
                    telephone = resultSet.getString("telephone_number");
                    System.out.printf("%d. %s - %s - %s \n", id, name, surname, telephone);
                }

            } 
            //////////////////////////////////////////не фильтруем
            else {
                resultSet = stmt.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    name = resultSet.getString("name");
                    surname = resultSet.getString("surname");
                    telephone = resultSet.getString("telephone_number");
                    System.out.printf("%d. %s - %s - %s \n", id, name, surname, telephone);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//ready without Filter

    public static int selectString() {
        Scanner in = new Scanner(System.in);
        watchTable();
        System.out.println("Введите номер строки которую хотите выбрать");
        int id = Integer.parseInt(in.nextLine());
        return id;

    }//ready

    public static void deleteString() {
        
        
        int selectID = selectString();
        String sql = "DELETE FROM telephons WHERE id ='" + selectID + "'";
        try ( Connection conn = DriverManager.getConnection(jdbcUrl, username, password);  Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            int count=stmt.executeUpdate(sql);
            if(count>0){
			  System.out.println("Удалено...");
		   }else{
			 System.out.println("Нету такого столбца");
		  }		
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//Ready

    public static void addString() {
        Scanner in = new Scanner(System.in);
        String name, surname, telephone;
        System.out.println("Введите имя");
        name = in.nextLine();
        System.out.println("Введите фамилию");
        surname = in.nextLine();
        System.out.println("Введите номер");
        telephone = in.nextLine();

        String sql = "INSERT INTO telephons(`name`, `surname`, `telephone_number`) VALUES ('" + name + "','" + surname + "','" + telephone + "')";

        try ( Connection conn = DriverManager.getConnection(jdbcUrl, username, password);  Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM telephons");
            resultSet.moveToInsertRow();
            resultSet.updateString("name", name);
            resultSet.updateString("surname", surname);
            resultSet.updateString("telephone_number", telephone);
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//ready

    public static void modifyString() {
        Scanner in = new Scanner(System.in);
        int currentID = selectString();
        String name, surname, telephone;
        String sqlPrintCurrent = "SELECT * FROM telephons WHERE id ='" + currentID+" ' ";

        try ( Connection conn = DriverManager.getConnection(jdbcUrl, username, password);  Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM telephons WHERE id='"+currentID+"'");
            while (true) {
                System.out.println("Что хотите модифицировать 1-Имя, 2-Фамилия, 3-Номер,4-Отмена");
                int switcher = Integer.parseInt(in.nextLine());
                switch (switcher) {
                    case 1:
                        System.out.println("Введите имя");
                        name = in.nextLine();
                        int count=stmt.executeUpdate("UPDATE telephons SET name = '"+name+"' WHERE id = '"+currentID+"'");
                        if(count>0){
			  System.out.println("Имя изменено...");
		   }else{
			 System.out.println("Нету такого столбца");
		  }		
                        break;
                    case 2:
                        System.out.println("Введите фамилию");
                        surname = in.nextLine();
                        count=stmt.executeUpdate("UPDATE telephons SET surname = '"+surname+"' WHERE id = '"+currentID+"'");
                        if(count>0){
			  System.out.println("Фамилия изменено...");
		   }else{
			 System.out.println("Нету такого столбца");
		  }		
                        break;
                    case 3:
                        System.out.println("Введите телефон");
                        telephone = in.nextLine();
                        count=stmt.executeUpdate("UPDATE telephons SET telephone_number = '"+telephone+"' WHERE id = '"+currentID+"'");
                        if(count>0){
			  System.out.println("Телефон изменено...");
		   }else{
			 System.out.println("Нету такого столбца");
		  }		
                        break;
                    case 4:
                        System.out.println("****************Далее******************");
                        break;

                }
                break;
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int state=0;
        while (state != 5) {
            System.out.println("Что вы хотите сделать? 1 - Добавить запись//2-Редактировать записи//3-Вывести данные//4-Удалить строку//5-Выход");
            state = Integer.parseInt(in.nextLine());
            try {
                switch (state) {
                    case 1:
                        addString();
                        break;
                    case 2:
                        modifyString();
                        break;
                    case 3:
                        watchTable();
                        break;
                    case 4:
                        deleteString();
                        break;
                    case 5:
                        System.out.println("Закрываемся........");
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                break;
            }

        }
        System.out.println("Bye");

    }
}
