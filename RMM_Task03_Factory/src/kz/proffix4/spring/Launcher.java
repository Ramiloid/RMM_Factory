package kz.proffix4.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.List;

class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с бинами

            ContactDAO contactDAO = (ContactDAO) context.getBean("telephons"); // Загрузка бина доступа к таблице клиентов 

            contactDAO.deleteAll(); // Удаление всех записей
            System.out.println("Создаем новый объект");
            Contact person = new Contact("Sergey", "Talipov", "+78888888"); // Создание нового объекта таблицы клиентов 
            
            contactDAO.insert(person); // Вставить новый объект (запись) в таблицу клиентов
            System.out.println("Вставили его в таблицу");

            contactDAO.insert(new Contact("Sergey", "Talin", "+78888888")); // Вставить новый объект (запись) в таблицу клиентов
            contactDAO.insert(new Contact("Pavel", "Borovoi", "+78888888")); // Вставить новый объект (запись) в таблицу клиентов
            
            contactDAO.append("Lars", "Vogel", "+111"); // Добавлние записей
            contactDAO.append("Jim", "Knopf", "+113");
            contactDAO.append("Lars", "Man", "+112");
            contactDAO.append("Spider", "Man", "+114");

            List<Contact> person1 = contactDAO.findByTelephone("+113"); // Поиск записи по телефону
            System.out.println("Ищем объект по телефону");
            for (Contact myPerson : person1) {
                System.out.println(myPerson.getFirstName() + " " + myPerson.getLastName());
            }
            
            List<Contact> person2 = contactDAO.findByLastName("Talipov"); // Поиск записи по фамилии
            System.out.println("Ищем объект по фамилии");
            for (Contact myPerson : person2) {
                System.out.println(myPerson.getFirstName() + " " + myPerson.getLastName());
            }
            
            contactDAO.deleteByLastName("bor"); // Удаление записей по фрагменту фамилии
            System.out.println("Удаляем запись с фамилией где есть слово bor");
            contactDAO.delete("Sergey", "Talin"); // Удалениезаписи пл имени и фамилии
            System.out.println("Удаляем запись с именем Serget и фамилией Talin");

            List<Contact> persons = contactDAO.findByFirstName("Sergey"); // Поиск записей по фрагменту имени
            System.out.println("Ищем объект по имени Sergey");
            for (Contact myPerson : persons) {
                System.out.println(myPerson.getFirstName() + " " + myPerson.getLastName());
            }
            System.out.println(persons != null ? persons : "Нет данных");

            
            
            contactDAO.deleteByName("Sergey");//Удаление записи по имени
            System.out.println("Удаляем запись с именем Sergey");
            contactDAO.deleteByTelephone("+112");//Удаление записи по номеру
            System.out.println("Удаляем запись с номер +112");
            
            contactDAO.updateByFirstName("Jim","Ramil");//Обновление записи по имени
            System.out.println("Обновляем имя Jim на Ramil");
            contactDAO.updateByLastName("Knopf", "Talipov"); // Изменение записей в таблице
            System.out.println("Обновляем запись с фамилией Knopf на Talipov");
            contactDAO.updateByTelephone("+114", "+758");//Обновление записи по номеру в таблице
            System.out.println("Обновляем запись где телефон +114 на +758");

            System.out.println("Данные в таблице БД:");

            List<Contact> list = contactDAO.selectAll();
            for (Contact myPerson : list) {
                System.out.println(myPerson.getFirstName() + " " + myPerson.getLastName() + " " + myPerson.getTelephone());
            }

            System.out.println("Вывод записей с имением Lars и фамилией Vogel:");

            list = contactDAO.select("Lars", "Vogel");
            for (Contact myPerson : list) {
                System.out.println(myPerson.getFirstName() + " " + myPerson.getLastName());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
}
