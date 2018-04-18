package com.edutech.javaee.s12.e01.resource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author nahum
 */
@RunWith(Arquillian.class)
public class ExampleTest {
    
    public ExampleTest() {
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

    @Test
    public void numbers() {
        assert( 2 > 1);
    }

    @Test
    public void hello() {
        
        Client client = ClientBuilder.newClient();
        String response = client
                .target("http://localhost:8081/edutec-javaee/api/")
                .request(MediaType.TEXT_HTML)
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class);

        assert (response.equals("Hello World!"));        
    }

    @Test
    public void suma() {
        Integer a = 5, b = 7;
        Client client = ClientBuilder.newClient();
        String response = client
                .target("http://localhost:8081/edutec-javaee/api/suma/" + a.toString() + "/" + b.toString() )
                .request(MediaType.TEXT_HTML)
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class);

        assert (response.equals( "12" ));        
    }

}
