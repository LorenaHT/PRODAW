package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Vehiculo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String matricula;
	private int marcaModelo;
	private LocalDate fechaMatricula;
	private float precio;
	public Vehiculo() {
		
	}
	public Vehiculo(int id, String matricula, int marcaModelo, LocalDate fechaMatricula, float precio) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marcaModelo = marcaModelo;
		this.fechaMatricula = fechaMatricula;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getMarcaModelo() {
		return marcaModelo;
	}
	public void setMarcaModelo(int marcaModelo) {
		this.marcaModelo = marcaModelo;
	}
	
	public LocalDate getFechaMatricula() {
		return fechaMatricula;
	}
	public void setFechaMatricula(LocalDate fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
