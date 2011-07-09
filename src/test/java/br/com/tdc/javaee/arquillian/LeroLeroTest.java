package br.com.tdc.javaee.arquillian;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LeroLeroTest {

    @Inject
    private LeroLero leroLero;

    @Deployment 
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class,"test.jar")
                .addClass(LeroLero.class)
                .addAsManifestResource(EmptyAsset.INSTANCE,ArchivePaths.create("beans.xml"));
    }

    @Test 
    public void testIsDeployed(){
        Assert.assertNotNull(leroLero);
    }

    @Test
    public void doSomethingThatFailTest() {
        Assert.assertFalse(leroLero.doSomethingComplicated()[1]);
    }

}
