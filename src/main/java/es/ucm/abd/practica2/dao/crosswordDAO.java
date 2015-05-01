package es.ucm.abd.practica2.dao;

import java.util.List;










import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import es.ucm.abd.practica2.model.Crucigrama;
import es.ucm.abd.practica2.model.Definicion;
import es.ucm.abd.practica2.model.cruciContDef;

public class crosswordDAO implements AbstractCrosswordDAO<Crucigrama, Definicion> {
	
	private SessionFactory f;

	@Override
	public void setSessionFactory(SessionFactory f) {
		// TODO Auto-generated method stub
		this.f=f;
	}

	@Override
	public int insertCrossword(Crucigrama crossword) {
		// TODO Auto-generated method stub
		
		Session session = f.openSession();			
		Transaction tr= session.beginTransaction();	
		session.save(crossword);		
		tr.commit();
		
			
		return crossword.getIdCrucigrama();
		
	}

	@Override
	public void insertWord(Definicion word) {
		// TODO Auto-generated method stub
		
		 
		
		Session session = f.openSession();			
		Transaction tr= session.beginTransaction();	
		session.save(word);		
		tr.commit();
	
	
	
	}

	@Override
	public Crucigrama findCrosswordById(int id) {
		// TODO Auto-generated method stub
		
		Session s = f.openSession();
		Query q = s.createQuery("FROM Crucigrama AS WHERE ID = id");

		
		 List<Object[]> resultados = q.list();
		
		s.close();

		for (Object[] p : resultados) {
			 System.out.println("POLLA");
			}

		
		
		
		return null;
	}

	@Override
	public List<Object[]> getCrosswordData(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Definicion> findWordsByTags(String[] tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Definicion> getMatchingWords(CharConstraint[] constraints) {
		// TODO Auto-generated method stub
		return null;
	}
	



}
