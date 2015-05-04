package es.ucm.abd.practica2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;





@Entity
public class Definicion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(length = 200)
	private String enunciado;
	
	
	@Column(length = 50)
	private String respuesta;
	
	@Lob
	private byte[] imagen;

	@ElementCollection
	@OrderColumn(name="etiqueta")
	private String[] etiquetas;


	
	@OneToMany(mappedBy="definicion", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<cruciContDef> contiene;

	
	
	
	
	/*******CONSTRUCTORES***********/
	
	public Definicion(){}
	
	public Definicion(Integer id, String enunciado, String respuesta, byte[]imagen, String[]etiquetas ){
		this.id=id;
		this.enunciado=enunciado;
		this.respuesta=respuesta;
		this.imagen=imagen;
		this.etiquetas=etiquetas;
		this.contiene = new ArrayList<cruciContDef>();
	}
	
	public Definicion(String respuesta, String enunciado, String[] etiquetas) {
		// TODO Auto-generated constructor stub
		this.respuesta=respuesta;
		this.enunciado=enunciado;
		this.etiquetas=etiquetas;
		this.contiene = new ArrayList<cruciContDef>();
	}
	
	
	/***********************************************************/
	
	
	
	
	public Integer getIdDef(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public String getEnunciado(){
		return this.enunciado;
	}
	
	public void setEnunciado(String enunciado){
		this.enunciado=enunciado;
	}
	
	public String getRespuesta(){
		return this.respuesta;
	}
	
	public void setRespuesta(String respuesta){
		this.respuesta=respuesta;
	}
	
	public byte[] getImagen(){
		return this.imagen;
	}
	
	public void setImagen(byte [] imagen){
		this.imagen=imagen;
	}
	
	public String[] getEtiquetas(){
		return this.etiquetas;
	}
	
	public void setEtiquetas(String[]etiquetas){
		this.etiquetas=etiquetas;
		
	}
	
		
	public void add(cruciContDef relacion) {
		// TODO Auto-generated method stub
		this.contiene.add(relacion);
	}
	
	
	public List<cruciContDef> getContiene(){
		
		return this.contiene;
	}

}


