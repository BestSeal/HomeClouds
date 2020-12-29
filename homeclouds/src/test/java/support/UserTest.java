/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sineo
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of validadeUser method, of class User.
     */
    @Test
    public void testValidadeExistingUser() {
        System.out.println("validadeExistingUser");
        String userLogin = "123";
        String userPass = "123";
        boolean expResult = true;
        boolean result = User.validadeUser(userLogin, userPass);
        assertEquals(expResult, result);
    }
    @Test
    public void testValidadeWrongUser() {
        System.out.println("validadeWrongUser");
        String userLogin = "123";
        String userPass = "1234";
        boolean expResult = false;
        boolean result = User.validadeUser(userLogin, userPass);
        assertEquals(expResult, result);
    }
    
}
