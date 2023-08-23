package com.latam.alura.hotel.controlador;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import com.latam.alura.hotel.factory.*;
import com.latam.alura.hotel.dao.*;
import com.latam.alura.hotel.modelo.*;

public class HuespedControlador {

	private HuespedDao huespedDao;
	
	public HuespedControlador() {
		Connection con = new ConnectionFactory().recuperaConexion();
		this.huespedDao =  new HuespedDao(con);
	}
	
		
	public void guardar(Huesped huesped) {
		this.huespedDao.guardar(huesped);
	}
	
	public List<Huesped> mostrarHuesped() {
		return this.huespedDao.mostrar();
	}
	
	public List<Huesped> buscarHuesped(String id) {
		return this.huespedDao.buscarId(id);
	}
	
	public void actualizarHuesped(String nombre, String apellido, LocalDate fechaDeNacimiento, String nacionalidad, String telefono,
			Integer idReserva, Integer id ) {
		this.huespedDao.ActualizarHuesped( nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, idReserva, id);
	}
	
	public void Eliminar(Integer idReserva) {
		this.huespedDao.Eliminar(idReserva);
	}
}