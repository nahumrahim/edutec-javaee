package com.edutech.javaee.s12.e01.resource;

import com.edutech.javaee.s12.e01.dao.RolDao;
import com.edutech.javaee.s12.e01.dao.UsuarioDao;
import com.edutech.javaee.s12.e01.model.Usuario;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
//@RunWith(Arquillian.class)
public class AutomaticTest {

    @Deployment
    public static JavaArchive  createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
        //return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(
                        UsuarioEndpoint.class,
                        UsuarioDao.class,
                        RolDao.class,
                        Usuario.class
               );
                //.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
                //.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private UsuarioEndpoint usuarioEndpoint;

    public AutomaticTest() {
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

    //@Test
    public void hello() {
        List<Usuario> usuarios = this.usuarioEndpoint.findAll();
        assert (usuarios.size() == 5);
    }
}
