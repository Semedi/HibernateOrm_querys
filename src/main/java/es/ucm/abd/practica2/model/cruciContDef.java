package es.ucm.abd.practica2.model;

public class cruciContDef {
	private int id_crucigrama;
	private int id_definicion;
	private Orientation orientacion;
	private int X;
	private int Y;
	
	
	private String respuestaCrucigrama;
	
	public cruciContDef(int idC, int idD, Orientation o, int x, int y){
		this.id_crucigrama=idC;
		this.id_definicion=idD;
		this.orientacion=o;
		this.X=x;
		this.Y=y;
	}
	
	
	public cruciContDef(int idC, int idD, Orientation o, int x, int y, String respuestaCrucigrama){
		this.id_crucigrama=idC;
		this.id_definicion=idD;
		this.orientacion=o;
		this.X=x;
		this.Y=y;
		this.setRespuestaCrucigrama(respuestaCrucigrama);
	}
	
	
	
	public int getIdCruci(){
		return this.id_crucigrama;
	}
	
	
	
	public int getIdDef(){
		return this.id_definicion;
	}
	
	
	
	public Orientation getOrientacion(){
		return this.orientacion;
	}
	public int getX(){
		return this.X;
	}
	public int getY(){
		return this.Y;
	}


	public String getRespuestaCrucigrama() {
		return respuestaCrucigrama;
	}


	public void setRespuestaCrucigrama(String respuestaCrucigrama) {
		this.respuestaCrucigrama = respuestaCrucigrama;
	}
}
