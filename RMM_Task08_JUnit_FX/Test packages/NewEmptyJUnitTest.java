/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import static javafxapplication.FXMLDocumentController.myDiv;

/**
 *
 * @author ADMIN-PC
 */
public class NewEmptyJUnitTest {
    
    
    
    public NewEmptyJUnitTest() {
    }

    
    @Test
    public void test2MyDiv() throws Exception {
        try {
            assertTrue("Проверка деления 0 на 0", myDiv(0,0,0,0) == 0);
            fail("Нет ошибки при делении 0 на 0");
        } catch (Exception thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }
    @Test
    public void test1MyDiv() throws Exception {
        try {
            assertTrue("Проверка что при единицах решение равно либо 0 либо 1", myDiv(1,1,1,1) == 0 || myDiv(1,1,1,1) == 1);
            fail("Нет ошибки при проверке");
        } catch (Exception thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
