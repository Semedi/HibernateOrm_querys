import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.ucm.abd.practica2.dao.AbstractCrosswordDAO;
import es.ucm.abd.practica2.dao.crosswordDAO;
import es.ucm.abd.practica2.model.Crucigrama;
import es.ucm.abd.practica2.model.Definicion;
import es.ucm.abd.practica2.model.Orientation;
import es.ucm.abd.practica2.model.cruciContDef;


public class CrosswordFacade implements AbstractCrosswordFacade<Crucigrama, Definicion> {
	


	@Override
	public Crucigrama newCrossword(String title, Date date) {
		// TODO Auto-generated method stub
		return new Crucigrama(title, date);
	}

	@Override
	public Definicion newDefinition(String sequence, String hint, String[] tags) {
		// TODO Auto-generated method stub
		return new Definicion(sequence,hint,tags);
	}

	@Override
	public void addWordToCrossword(Crucigrama crossword, Definicion word,
			int row, int column, Orientation orientation) {
		// TODO Auto-generated method stub
	
			
		/*Creamos el objeto de relacion */
		cruciContDef relacion = new cruciContDef(word, crossword, orientation , row, column);
	
		//añadimos las correspondientes relaciones (uno a muchos)
	    crossword.add(relacion);
		word.add(relacion);
		
	}

	@Override
	public String getAnswerOfWord(Definicion word) {
		// TODO Auto-generated method stub
		return word.getRespuesta();
	}

	@Override
	public String[] getTagsOfWord(Definicion word) {
		// TODO Auto-generated method stub
		return word.getEtiquetas();
	}

	@Override
	public String getHintOfWord(Definicion word) {
		// TODO Auto-generated method stub
		return word.getEnunciado();
	}

	@Override
	public String getTitleOfCrossword(Crucigrama crossword) {
		// TODO Auto-generated method stub
		return crossword.getTitulo();
	}

	@Override
	public Date getDateOfCrossword(Crucigrama crossword) {
		// TODO Auto-generated method stub
		return crossword.getFecha();
	}

	@Override
	public List<Object[]> getWordsOfCrossword(Crucigrama crossword) {
		// TODO Auto-generated method stub
		
		List <Object[]> lista = new ArrayList<Object[]>();
		
		
		
		for (cruciContDef r: crossword.getContiene()){	
			
			
			Object[] o = new Object[]{  r.getDefinicion().getRespuesta(), 
										(Integer)r.getX(), 
										(Integer)r.getY(),
										r.getOrientacion()};
		
			
			lista.add(o);		
		}
			
		
		
		
		return lista;
	}

	@Override
	public AbstractCrosswordDAO<Crucigrama, Definicion> createDAO() {
		// TODO Auto-generated method stub
		return new crosswordDAO();
	}

}
