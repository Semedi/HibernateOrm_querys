package es.ucm.abd.practica2.model;

import java.util.ArrayList;
//import java.sql.Date;
import java.util.Date;
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
	private int id;
	
	
	@Column(nullable = false, length = 50)
	private String titulo;
	
	
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	

	
	@OneToMany(mappedBy="crucigrama", cascade = CascadeType.ALL)
	private List<cruciContDef> contiene;


	
	

	
	/************CONSTRUCTORES*************/
	public Crucigrama(){
		this.contiene = new ArrayList<cruciContDef>();
	}
	public Crucigrama(int id, String titulo, Date fecha){
		
		this.id=id;
		this.titulo=titulo;
		this.fechaCreacion=fecha;
		this.contiene = new ArrayList<cruciContDef>();
		
	}
	
	
	
	public Crucigrama(String titulo, Date fecha) {
		// TODO Auto-generated constructor stub
		
		this.titulo=titulo;
		this.fechaCreacion=fecha;
		this.contiene = new ArrayList<cruciContDef>();
	}


	/*****************************************************************/


	
	
	public int getIdCrucigrama(){
		return this.id;
	}
	public void setIdCrucigrama(int id){
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
	
	
	
	
	
	public List<cruciContDef> getContiene(){
		
		return this.contiene;
	}


	



}
