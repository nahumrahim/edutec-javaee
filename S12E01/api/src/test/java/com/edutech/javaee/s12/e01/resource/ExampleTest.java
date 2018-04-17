package com.edutech.javaee.s12.e01.resource;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    
    /*@Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(ExampleEndpoint.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }*/

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
}
