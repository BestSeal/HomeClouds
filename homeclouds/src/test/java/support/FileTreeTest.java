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
public class FileTreeTest {
    
    public FileTreeTest() {
    }
    
    /*
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }*/

    /**
     * Test of GetFiles method, of class FileTree.
     */
    @Test
    public void testGetFiles() {
        System.out.println("GetFiles");
        String login = "123";
        String expResult = "<ul><li>NewFolder<ul><li>venus.html</li><li>"
                + "venus_files<ul><li>codemirror.js</li><li>venus.css</li><li>"
                + "venus.js</li></ul></li></ul></li><li>riscv1.s</li></ul>";
        String result = FileTree.GetFiles(login);
        assertEquals(expResult, result);
    }

    /**
     * Test of ExploreDirectory method, of class FileTree.
     */
    @Test
    public void testExploreDirectory() {
        System.out.println("ExploreDirectory");
        String login = "123";
        String expResult = "<div class = \"explorer\"><form method=\"post\""
                + " action=\"\"> <div class = \"file\"><button type = \"submit"
                + "\" value=\"NewFolder\" name=\"path\"><img src=\"..\\..\\imgs"
                + "\\folder.png\"><p>NewFolder</p></button></div><div class = "
                + "\"file\"><button type = \"submit\" value=\"riscv1.s\" name="
                + "\"path\"><img src=\"..\\..\\imgs\\s.png\"><p>riscv1.s</p>"
                + "</button></div></form></div>";
        String result = FileTree.ExploreDirectory(login);
        assertEquals(expResult, result);
    }

    /**
     * Test of CreateFolder method, of class FileTree.
     */
    @Test
    public void testCreateFolder() {
        System.out.println("CreateFolder");
        String path = "123";
        String expResult = "Такая папка уже существует";
        String result = FileTree.CreateFolder(path);
        assertEquals(expResult, result);
    }
    
}
