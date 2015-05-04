package es.ucm.abd.practica2.model;

//import java.sql.Date;
import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;
import java.util.List;







import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





@Entity
public class Crucigrama {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(nullable = false, length = 50)
	private String titulo;
	
	
	@Column
	private Date fechaCreacion;
	

	
	@OneToMany(mappedBy="crucigrama", cascade = CascadeType.ALL)
	private List<cruciContDef> contiene;


	
	

	
	/************CONSTRUCTORES*************/
	public Crucigrama(){}
	public Crucigrama(Integer id, String titulo, Date fecha){
		
		this.id=id;
		this.titulo=titulo;
		this.fechaCreacion=fecha;
		this.contiene = new ArrayList<cruciContDef>();
		
	}
	
	
	
	public Crucigrama(String titulo, Date date) {
		// TODO Auto-generated constructor stub
		
		this.titulo=titulo;
		this.fechaCreacion=date;
		this.contiene = new ArrayList<cruciContDef>();
	}


	/*****************************************************************/

	public String toString(){
		
		return "CRUCIGRAMA: "+this.getTitulo();
		
	}
	
	
	public Integer getIdCrucigrama(){
		return this.id;
	}
	public void setIdCrucigrama(Integer id){
		this.id=id;
	}
	public String getTitulo(){
		return this.titulo;
	}
	public void setTitulo(String titulo){
		this.titulo=titulo;
	}
	public Date getFecha(){
		return this.fechaCreacion;
	}
	public void setFecha(Date fecha){
		this.fechaCreacion=fecha;
	}




	public void add(cruciContDef relacion) {
		// TODO Auto-generated method stub
		
		
		
		this.contiene.add(relacion);
		
		
	}
	
	public int getPalabras(){
		
		return contiene.size();
	}
	
	
	
	
	public List<cruciContDef> getContiene(){
		
		return this.contiene;
	}


	



}
