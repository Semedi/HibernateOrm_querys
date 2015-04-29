package es.ucm.abd.practica2.model;

import java.util.ArrayList;
//import java.sql.Date;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Crucigrama")
public class Crucigrama {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer _id;
	
	@Column(nullable = false, length = 50)
	private String _titulo;
	
	@Temporal(TemporalType.DATE)
	private Date _fechaCreacion;
	
	
	@OneToMany
	private List<cruciContDef> _contiene;
	
	
	
	
	
	/************CONSTRUCTORES*************/
	public Crucigrama(){
		this._contiene = new ArrayList<cruciContDef>();
	}
	public Crucigrama(int id, String titulo, Date fecha){
		
		this._id=id;
		this._titulo=titulo;
		this._fechaCreacion=fecha;
		this._contiene = new ArrayList<cruciContDef>();
		
	}
	
	
	
	public Crucigrama(String titulo, Date fecha) {
		// TODO Auto-generated constructor stub
		
		this._titulo=titulo;
		this._fechaCreacion=fecha;
		this._contiene = new ArrayList<cruciContDef>();
	}


	/*****************************************************************/


	public int getIdCrucigrama(){
		return this._id;
	}
	public void setIdCrucigrama(int id){
		this._id=id;
	}
	public String getTitulo(){
		return this._titulo;
	}
	public void setTitulo(String titulo){
		this._titulo=titulo;
	}
	public Date getFecha(){
		return this._fechaCreacion;
	}
	public void setFecha(Date fecha){
		this._fechaCreacion=fecha;
	}




	public void add(cruciContDef relacion) {
		// TODO Auto-generated method stub
		this._contiene.add(relacion);
	}
	
	
	public List<cruciContDef> getContiene(){
		
		return this._contiene;
	}


	



}
