package es.ucm.abd.practica2.model;

//import java.sql.Date;
import java.util.Date;
public class Crucigrama {
	private int id;
	private String titulo;
	private Date fechaCreacion;
	
	public Crucigrama(int id, String titulo, Date fecha){
		this.id=id;
		this.titulo=titulo;
		this.fechaCreacion=fecha;
	}
	
	
	public Crucigrama(String titulo, Date fecha) {
		// TODO Auto-generated constructor stub
		this.titulo=titulo;
		this.fechaCreacion=fecha;
	}


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

}
