package es.ucm.abd.practica2.dao;

import java.util.List;







import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import es.ucm.abd.practica2.model.Crucigrama;
import es.ucm.abd.practica2.model.Definicion;

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
		
	
		return crossword.getIdCrucigrama();
	}

	@Override
	public void insertWord(Definicion word) {
		// TODO Auto-generated method stub
		
	
		Session session = f.openSession();
		
		
		
		Transaction tr= session.beginTransaction();
		
	
		session.save("definicion", word);
		
		
		tr.commit();
	}

	@Override
	public Crucigrama findCrosswordById(int id) {
		// TODO Auto-generated method stub
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
