package es.ucm.abd.practica2.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class cruciContDef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Crucigrama crucigrama;
	
	@ManyToOne
	private Definicion definicion;
	
	
	
	
	@Enumerated(EnumType.STRING)
	private Orientation orientacion;
	
	@Column
	private Integer X;
		
	@Column
	private Integer Y;
	
	
	/************CONSTRUCTORES*************/

	public cruciContDef(){}
	
	public cruciContDef(Definicion definicion, Crucigrama crucigrama, Orientation o, Integer x, Integer y){
		
		
		this.definicion=definicion;
		this.crucigrama=crucigrama;
		this.orientacion=o;
		this.X=x;
		this.Y=y;
	}
	
	
	
	/******************************************/
	


	

	public Orientation getOrientacion(){
		return this.orientacion;
	}
	public Integer getX(){
		return this.X;
	}
	public Integer getY(){
		return this.Y;
	}

	public Crucigrama get_crucigrama() {
		return this.crucigrama;
	}
	
	

	public void setCrucigrama(Crucigrama _crucigrama) {
		this.crucigrama = _crucigrama;
	}

	
	public Definicion getDefinicion() {
		return definicion;
	}

	
	public void setDefinicion(Definicion _definicion) {
		this.definicion = _definicion;
	}





}
