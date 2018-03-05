package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Vehiculos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricula;
	private LocalDate fechaMat; 
	private String marca;
	private String nombreDue�o;
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public LocalDate getFechaMat() {
		return fechaMat;
	}
	public void setFechaMat(LocalDate fechaMat) {
		this.fechaMat = fechaMat;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getNombreDue�o() {
		return nombreDue�o;
	}
	public void setNombreDue�o(String nombreDue�o) {
		this.nombreDue�o = nombreDue�o;
	}
	
}
