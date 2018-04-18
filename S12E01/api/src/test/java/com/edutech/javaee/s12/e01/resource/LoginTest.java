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
public class LoginTest {

    public LoginTest() {
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
    public void loginUser() {
        System.out.println("test");
        Client client = ClientBuilder.newClient();

        Form form = new Form();
        form.param("idUsuario", "mmutzus")
            .param("password", "helloworld1.");

        Response response = client
                .target("http://localhost:8081/edutec-javaee/api/usuarios/login")
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.form(form), Response.class);

        JsonObject json = response.readEntity(JsonObject.class);
        System.out.println(json.toString());
        String token = json.getString("token");
        assert (token.contains("Bearer"));
    }
}
