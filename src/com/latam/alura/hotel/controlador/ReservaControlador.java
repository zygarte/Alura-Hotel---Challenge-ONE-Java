package com.latam.alura.hotel.controlador;


import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import com.latam.alura.hotel.factory.*;
import com.latam.alura.hotel.dao.*;
import com.latam.alura.hotel.modelo.*;

public class ReservaControlador {


	private ReservaDao reservaDao;
	

	public ReservaControlador() {
		Connection con = new ConnectionFactory().recuperaConexion();
		this.reservaDao = new ReservaDao(con);
	}
	
	public void guardar(Reserva reserva) {
		this.reservaDao.guardar(reserva);
	}
	
	public List<Reserva> mostrar(){
		return this.reservaDao.mostrar();
	}
	
	
	public List<Reserva> buscar(String id){
		return this.reservaDao.buscarId(id);
	}
	
	public void actualizarReserva(LocalDate dateE, LocalDate dateS, String valor, String formaPago, Integer id) {
		this.reservaDao.Actualizar(dateE, dateS, valor, formaPago, id);
	}
	
	public void Eliminar (Integer id) {
		this.reservaDao.Eliminar(id);
	}
	
	
}