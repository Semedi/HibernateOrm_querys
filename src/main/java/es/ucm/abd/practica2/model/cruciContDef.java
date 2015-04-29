package es.ucm.abd.practica2.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cruciContDef")
public class cruciContDef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer _id;
	
	@ManyToOne
	private Crucigrama _crucigrama;
	
	@ManyToOne
	private Definicion _definicion;
	
	@Enumerated
	private Orientation _orientacion;
	
	@Column(nullable = false)
	private int _X;
		
	@Column(nullable = false)
	private int _Y;
	
	
	/************CONSTRUCTORES*************/

	public cruciContDef(){}
	
	public cruciContDef(Definicion definicion, Crucigrama crucigrama, Orientation o, int x, int y){
		
		
		this._definicion=definicion;
		this._crucigrama=crucigrama;
		this._orientacion=o;
		this._X=x;
		this._Y=y;
	}
	
	
	public cruciContDef(int id, Definicion definicion, Crucigrama crucigrama, Orientation o, int x, int y){
		
		this._id = id;
		this._definicion=definicion;
		this._crucigrama=crucigrama;
		this._orientacion=o;
		this._X=x;
		this._Y=y;
		
	}
	
	/******************************************/
	


	

	public Orientation getOrientacion(){
		return this._orientacion;
	}
	public int getX(){
		return this._X;
	}
	public int getY(){
		return this._Y;
	}

	public Crucigrama get_crucigrama() {
		return _crucigrama;
	}
	
	

	public void setCrucigrama(Crucigrama _crucigrama) {
		this._crucigrama = _crucigrama;
	}

	
	public Definicion getDefinicion() {
		return _definicion;
	}

	
	public void setDefinicion(Definicion _definicion) {
		this._definicion = _definicion;
	}





}
