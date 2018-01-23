package com.edutech.javaee.s01.e01.resources;

import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nahum
 */
public class ExampleEndpointTest {
    
    public ExampleEndpointTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNames method, of class ExampleEndpoint.
     */
    @Test
    public void testGetNames() {
        System.out.println("getNames");
        ExampleEndpoint instance = new ExampleEndpoint();
        Response expResult = null;
        Response result = instance.getNamesAsString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
