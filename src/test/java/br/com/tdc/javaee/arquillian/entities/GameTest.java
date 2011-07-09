package br.com.tdc.javaee.arquillian.entities;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

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
public class GameTest {

    @Inject
    UserTransaction utx;

    @PersistenceContext
    EntityManager em;

    @Deployment public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class, "test2.jar")
                .addClass(Game.class)
                .addAsManifestResource(EmptyAsset.INSTANCE,ArchivePaths.create("beans.xml"))
                .addAsManifestResource("test-persistence.xml", ArchivePaths.create("persistence.xml"));

    }

    private static final String[] GAME_TITLES =
        {
        "Uncharted",
        "Metal Gear Solid",
        "Street Fighter",
        "Red dead redemption"
        };



    public void insertSampleRecords() throws Exception
    {

        utx.begin();

        em.createQuery("delete from Game").executeUpdate();

        // insert records
        System.out.println("Inserting records...");
        for (String title : GAME_TITLES)
        {
            Game game = new Game(title);
            em.persist(game);
        }

        utx.commit();
    }

    @Test
    public void should_be_able_to_select_games_using_jpql() throws Exception
    {
        this.insertSampleRecords();

        utx.begin();
        em.joinTransaction();

        System.out.println("Selecting (using JPQL)...");
        List<Game> games = em.createQuery("select g from Game g order by g.id",
                Game.class).getResultList();
        System.out.println("Found " + games.size() + " games (using JPQL)");
        Assert.assertEquals(GAME_TITLES.length, games.size());

        for (int i = 0; i < GAME_TITLES.length; i++) {
            Assert.assertEquals(GAME_TITLES[i], games.get(i).getName());
            System.out.println(games.get(i));
        }

        utx.commit();

    }

}
