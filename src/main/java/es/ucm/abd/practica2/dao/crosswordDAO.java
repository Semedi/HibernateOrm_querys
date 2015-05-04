package es.ucm.abd.practica2.dao;

import java.util.ArrayList;
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
		
		
		System.out.println("******************HACEMOS LA BUSQUEDA**************************************");

		Session s = f.openSession();
		Crucigrama cw = (Crucigrama) s.get(Crucigrama.class, id);
		
		
		
		
		return cw;	
		}

	@Override
	public List<Object[]> getCrosswordData(String str) {
		// TODO Auto-generated method stub
		
/*
		
		Session s = f.openSession();
	
		Query query = s.createQuery("FROM Crucigrama WHERE titulo LIKE :cadena");
		query.setString("cadena", "%"+str+"%");
		
		 List<Crucigrama> crucigramas = (List<Crucigrama>)query.list();
		 
		 List<Object []> resultado = new ArrayList<Object []>();
	
		
		 for (Crucigrama c: crucigramas) {
			 Object [] objeto= new Object[]{c.getIdCrucigrama(), c.getTitulo(), c.getFecha(),(long)c.getPalabras()};
			resultado.add(objeto);
			 
			 
			}
			
	
		s.close();
		
	
		return resultado;
		
		*/
		
		Session s = f.openSession();
		Query query = s.createQuery("SELECT cross.id, cross.titulo, cross.fechaCreacion, sum(case when cross.id=cont.crucigrama.id then 1 else 0 END) "
				+ "FROM Crucigrama as cross, cruciContDef as cont "
				+ "WHERE cross.titulo LIKE :cadena "
				+ "GROUP BY cross.id");
		
		
		query.setString("cadena", "%"+str+"%");
		
		return query.list();
		
	}

	@Override
	public List<Definicion> findWordsByTags(String[] tags) {
		// TODO Auto-generated method stub
		
		Session s = f.openSession();
		Query query;
		
		if  (tags.length == 0){
			
			query = s.createQuery("FROM Definicion");
			return (List<Definicion>)query.list();
						
		}
		
		//FROM  Definion Where ? Member of d.tags 
		
		else {
			
			String aux = "FROM Definicion a WHERE";
			String aux2 =" ? MEMBER OF a.etiquetas";
			
			
			for ( int i = 0; i < tags.length; i++){
				if (i ==  0) aux +=aux2;
				else
					aux+= " AND"+aux2;				
			}
			
			query = s.createQuery(aux);
			
			for ( int i = 0; i < tags.length; i++)
				query.setString(i, tags[i]);
			
			
			return (List<Definicion>)query.list();
			
			
		}
		

	}

	@Override
	public List<Definicion> getMatchingWords(CharConstraint[] constraints) {
		// TODO Auto-generated method stub
		
		Session s = f.openSession();
		Query query;
		
			if  (constraints.length == 0){
			
			query = s.createQuery("FROM Definicion");
			return (List<Definicion>)query.list();
						
		}
			
		
			
			else {
				/*
				List<Definicion> lista = new ArrayList<Definicion>();
				query = s.createQuery("FROM Definicion");
				List<Definicion> lista2 = (List<Definicion>)query.list();
				
				for (Definicion d : lista2){
					
					
					*/
				}
				
				
				
			return null;
			}
			
	
	}
	




