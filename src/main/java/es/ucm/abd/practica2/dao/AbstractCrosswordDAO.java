package es.ucm.abd.practica2.dao;

import java.util.List;
import org.hibernate.SessionFactory;

/**
 * Interfaz de acceso a la base de datos.
 *
 * @author Manuel Montenegro
 * @param <C> Clase de crucigramas.
 * @param <W> Clase de palabras.
 */
public interface AbstractCrosswordDAO<C, W> {
	/**
	 * Establece el SessionFactory que será utilizado en el resto
	 * de operaciones de acceso.
	 * 
	 * @param f Session factory
	 */
    public void setSessionFactory(SessionFactory f);
    
    /**
     * Añade un crucigrama a la base de datos. Devuelve el 
     * identificador del crucigrama añadido.
     * 
     * @param crossword Crucigrama a añadir.
     * @return Identificador del crucigrama añadido.
     */
    public int insertCrossword(C crossword);
    
    /**
     * Añade una definición a la base de datos.
     * 
     * @param word Definición a añadir.
     */
    public void insertWord(W word);
    
    /**
     * Devuelve un crucigrama a partir de su identificador.
     * 
     * @param id Identificador del crucigrama.
     * @return Crucigrama.
     */
    public C findCrosswordById(int id);

    /**
     * Devuelve el identificador, título, fecha de creación
     * y número de palabras de los crucigramas cuyo título contenga
     * la palabra pasada como parámetro.
     * 
     * Devuelve una lista de arrays. Cada uno de estos arrays tiene
     * cuatro componentes:
     * 
     * * Identificador del crucigrama (Integer).
     * * Título del crucigrama (String).
     * * Fecha de creación del crucigrama (Date).
     * * Número de palabras del crucigrama (Integer o Long).
     * 
     * 
     * @param str Cadena de búsqueda del crucigrama.
     * @return Una lista de arrays con la información descrita anteriormente.
     */
    public List<Object[]> getCrosswordData(String str);
    
    /**
     * Devuelve una lista con aquellas palabras que contengan 
     * TODAS las etiquetas pasadas como parámetro.
     * 
     * @param tags Etiquetas a buscar.
     * @return Lista de palabras. Cada palabra devuelta ha
     * de contener las etiquetas pasadas como parámetro (y posiblemente
     * algunas más)
     */
    public List<W> findWordsByTags(String[] tags);
    
    /**
     * Devuelve aquellas palabras que encajen con las restricciones
     * pasadas como parámetro.
     * 
     * @param constraints Restricciones.
     * @return Lista de palabras.
     */
    public List<W> getMatchingWords(CharConstraint[] constraints);
}
