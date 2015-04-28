package es.ucm.abd.practica2.model;

public class Definicion {
	private int id;
	private String enunciado;
	private String respuesta;
	private byte[] imagen;
	private String[] etiquetas;
	
	
	
	public Definicion(){
		
	}
	public Definicion(int id, String enunciado, String respuesta, byte[]imagen, String[]etiquetas ){
		this.id=id;
		this.enunciado=enunciado;
		this.respuesta=respuesta;
		this.imagen=imagen;
		this.etiquetas=etiquetas;
	}
	public Definicion(String respuesta, String enunciado, String[] etiquetas) {
		// TODO Auto-generated constructor stub
		this.respuesta=respuesta;
		this.enunciado=enunciado;
		this.etiquetas=etiquetas;
	}
	public int getIdDef(){
		return this.id;
	}
	public void setId(int id){
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
}

