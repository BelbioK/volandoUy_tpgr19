package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import main.java.entidades.Aerolinea;
import main.java.entidades.Cliente;
import main.java.entidades.RutaFinalizada;

public class DBConnection {
	private static DBConnection instancia;
	private DBConnection() {
		
	}
	public static DBConnection getInstancia() {
		if(instancia == null)
			instancia = new DBConnection();
		
		return instancia;
	}
	
	public Aerolinea getAerolinea(String nombre) {
		try { //pruebita si qued[o la db
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("VolandoUyDB");
	        EntityManager em = emf.createEntityManager();
	        Query query = em.createQuery("SELECT a FROM Aerolinea a WHERE a.nickname=?1").setParameter(1, nombre);
			List<Aerolinea> res = query.getResultList();
			if(res != null && !res.isEmpty())
				return res.get(0);
			em.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public RutaFinalizada getRuta(String nombre) {
		try { //pruebita si qued[o la db
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("VolandoUyDB");
	        EntityManager em = emf.createEntityManager();
	    	Query query = em.createQuery("SELECT a FROM RutaFinalizada a WHERE a.nombre=?1").setParameter(1, nombre);
			List<RutaFinalizada> res = query.getResultList();
			if(res != null && !res.isEmpty())
				return res.get(0);
			em.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void persistRuta(RutaFinalizada rf) {
		try { //pruebita si qued[o la db
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("VolandoUyDB");
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();
	        transaction.begin();
	        em.persist(rf);
	        transaction.commit();
			em.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
