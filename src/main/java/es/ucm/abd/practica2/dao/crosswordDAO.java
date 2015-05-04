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
		

		 //Query query = s.createQuery("FROM Crucigrama where titulo LIKE '%str%'");
		  // Query query =s.createQuery("SELECT c.id, c.fechacreacion"+"FROM Crucigrama AS c"+"WHERE c.titulo LIKE '%"+str+"%'"+" GROUP BY c.id");
		
		Session s = f.openSession();
	
		Query query = s.createQuery("FROM Crucigrama WHERE titulo LIKE :cadena");
		query.setString("cadena", "%"+str+"%");
		
		 List<Crucigrama> crucigramas = (List<Crucigrama>)query.list();
		 
		 List<Object []> resultado = new ArrayList<Object []>();
		//query = s.createQuery("FROM CruciContDef WHERE crucigrama_id = :id ");
		
		 for (Crucigrama c: crucigramas) {
			 Object [] objeto= new Object[]{c.getIdCrucigrama(), c.getTitulo(), c.getFecha(),(long)c.getPalabras()};
			resultado.add(objeto);
			 
			 
			}
			
	
		s.close();
		
			//SELECT id, titulo, fechacreacion, count(*) FROM `crucigrama` WHERE titulo like "%engua%" GROUP BY id


		
	
			/*
		Session s = f.openSession();
		Query q = s.createQuery("SELECT c.id"
		+ "FROM Crucigrama c JOIN c.contiene e JOIN e.votos vs "
		+ "WHERE c.titulo LIKE %:cadena% "
		+ "GROUP BY e.numeroTemporada, e.numeroEpisodio");
		
		
		q.setString("id", str);
		
		
		for (Object[] o : (List<Object[]>)q.list()) {
		System.out.println(o[0] + ":" + o[1]);
		}
		s.close();
		
		
		*/
		
		return resultado;
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
		return null;
	}
	



}
