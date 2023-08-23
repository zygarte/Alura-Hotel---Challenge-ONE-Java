package com.latam.alura.hotel.modelo;



import java.time.LocalDate;

public class Reserva {

	private Integer id;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private String valor;
	private String formaDePago;
	
	
	
	public Reserva( LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaDePago) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}
	
	
	

	public Reserva(Integer id, LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaDePago) {
		super();
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}




	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}




	public LocalDate getFechaSalida() {
		return fechaSalida;
	}




	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}




	public String getValor() {
		return valor;
	}




	public void setValor(String valor) {
		this.valor = valor;
	}




	public String getFormaDePago() {
		return formaDePago;
	}




	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}





	
	
	
	
}