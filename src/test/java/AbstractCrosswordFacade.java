
import es.ucm.abd.practica2.dao.AbstractCrosswordDAO;
import es.ucm.abd.practica2.model.Orientation;
import java.util.Date;
import java.util.List;


/**
 * Interfaz que representa una fachada para el acceso a las funciones
 * proporcionadas del modelo orientado a objetos.
 *
 * @author Manuel Montenegro
 * @param <C> Clase de los crucigramas.
 * @param <W> Clase de las definiciones.
 */
public interface AbstractCrosswordFacade<C, W> {
	
	/**
	 * Crea un nuevo crucigrama.
	 * 
	 * @param title Título del crucigrama.
	 * @param date Fecha de creación del crucigrama.
	 * @return Instancia de la clase de los crucigramas con la información indicada.
	 */
    public C newCrossword(String title, Date date);
    
    /**
     * Crea una nueva definición.
     * 
     * @param sequence Respuesta correspondiente a dicha definición
     * @param hint Enunciado de la definición.
     * @param tags Lista de etiquetas asociadas a la definición.
     * @return Instancia de la clase de definiciones con la información indicada.
     */
    public W newDefinition(String sequence, String hint, String[] tags);
    
    /**
     * Añade una definición a un crucigrama
     * 
     * @param crossword Crucigrama
     * @param word Definición a añadir
     * @param row Fila de la definición dentro del crucigrama
     * @param column Columna de la definición dentro del crucigrama
     * @param orientation Orientación de la definición dentro del crucigrama
     */
    public void addWordToCrossword(C crossword, W word, int row, int column, Orientation orientation);

    /**
     * Obtiene la respuesta asociada a la definición dada
     * 
     * @param word Definición.
     * @return Respuesta asociada a la definición dada.
     */
    public String getAnswerOfWord(W word);
    
    /**
     * Obtiene un array con las etiquetas asociadas a una definición dada.
     * 
     * @param word Definición.
     * @return Array con las etiquetas de la definición. Si la definición
     * no contiene etiquetas, se devolverá un array con cero elementos.
     */
    public String[] getTagsOfWord(W word);
    
    /**
     * Devuelve el enunciado de una definición.
     *  
     * @param word Definición.
     * @return Enunciado de la definición pasada como parámetro.
     */
    public String getHintOfWord(W word);
    
    /**
     * Devuelve el título de un crucigrama.
     * 
     * @param crossword Crucigrama,
     * @return Título del crucigrama
     */
    public String getTitleOfCrossword(C crossword);
    
    /**
     * Devuelve la fecha de creación de un crucigrama.
     * 
     * @param crossword Crucigrama.
     * @return Fecha de creación del crucigrama.
     */
    public Date getDateOfCrossword(C crossword);
    
    /**
     * Devuelve la información de las palabras contenidas en un crucigrama.
     * 
     * Cada elemento de la lista devuelta por esta función es un array de
     * cuatro componentes:
     * 
     * * Respuesta de la palabra (String).
     * * Fila donde está contenida la definición dentro del crucigrama (Integer).
     * * Columna donde está contenida la definición dentro del crucigrama (Integer).
     * * Orientación de la palabra dentro del crucigrama (Orientacion.HORIZONTAL o Orientacion.VERTICAL).
     * 
     * @param crossword Crucigrama.
     * @return Lista de arrays con la información expuesta anteriormente.
     */
    public List<Object[]> getWordsOfCrossword(C crossword);
    

    /**
     * Devuelve un objeto DAO para realizar las operaciones de acceso a datos.
     * 
     * @return Objeto DAO.
     */
    public AbstractCrosswordDAO<C, W> createDAO();
    
}
