package es.ucm.abd.practica2.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;



@Entity
public class Definicion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer _id;
	
	
	@Column(nullable = false, length = 200)
	private String _enunciado;
	
	@Column(nullable = false, length = 50)
	private String _respuesta;
	
	@Lob
	private byte[] _imagen;
	
	@Column(nullable = false)
	private String[] _etiquetas;
	
	@OneToMany
	private ArrayList<cruciContDef> _contiene;
	
	
	
	
	/*******CONSTRUCTORES***********/
	
	public Definicion(){	
		_contiene = new ArrayList<cruciContDef>();
	}
	
	public Definicion(int id, String enunciado, String respuesta, byte[]imagen, String[]etiquetas ){
		this._id=id;
		this._enunciado=enunciado;
		this._respuesta=respuesta;
		this._imagen=imagen;
		this._etiquetas=etiquetas;
		this._contiene = new ArrayList<cruciContDef>();
	}
	
	public Definicion(String respuesta, String enunciado, String[] etiquetas) {
		// TODO Auto-generated constructor stub
		this._respuesta=respuesta;
		this._enunciado=enunciado;
		this._etiquetas=etiquetas;
		this._contiene = new ArrayList<cruciContDef>();
	}
	
	
	/***********************************************************/
	
	
	
	
	public int getIdDef(){
		return this._id;
	}
	
	public void setId(int id){
		this._id=id;
	}
	
	public String getEnunciado(){
		return this._enunciado;
	}
	
	public void setEnunciado(String enunciado){
		this._enunciado=enunciado;
	}
	
	public String getRespuesta(){
		return this._respuesta;
	}
	
	public void setRespuesta(String respuesta){
		this._respuesta=respuesta;
	}
	
	public byte[] getImagen(){
		return this._imagen;
	}
	
	public void setImagen(byte [] imagen){
		this._imagen=imagen;
	}
	
	public String[] getEtiquetas(){
		return this._etiquetas;
	}
	
	public void setEtiquetas(String[]etiquetas){
		this._etiquetas=etiquetas;
		
	}
	
		
	public void add(cruciContDef relacion) {
		// TODO Auto-generated method stub
		this._contiene.add(relacion);
	}
	
	
	public ArrayList<cruciContDef> getContiene(){
		
		return this._contiene;
	}

}


