package no.hvl.dat109.EAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import no.hvl.dat109.Stemme;


@Stateless
public class StemmeEAO {
    
	@PersistenceContext(name = "StemmePU")
    private EntityManager em;

    public void leggTilStemme(Stemme s) {
        em.persist(s);
    }
    
    public void oppdaterStemme(Stemme s) {
    	em.createQuery("UPDATE Stemme p SET p.score = :score WHERE p.deltaker = :deltaker AND p.stand = :stand")
    			.setParameter("score", s.getScore())
    			.setParameter("deltaker", s.getDeltaker())
    			.setParameter("stand", s.getStand())
    	        .executeUpdate();
    }

 public List<Stemme> hentStemmerPaaStand(String stand) {
        
        return (List<Stemme>) em.createQuery("SELECT s FROM Stemme s WHERE s.stand LIKE :stand")
        		.setParameter("stand", stand)
        		.getResultList();
    }

    public List<Stemme> hentStemmer(String deltaker) {
        
        return (List<Stemme>) em.createQuery("SELECT s FROM Stemme s WHERE s.deltaker LIKE :deltaker")
        		.setParameter("deltaker", deltaker)
        		.getResultList();
    }
}
