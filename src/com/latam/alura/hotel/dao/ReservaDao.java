package com.latam.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.latam.alura.hotel.modelo.*;

public class ReservaDao {
	
	private Connection con;

	public ReservaDao(Connection con) {
		super();
		this.con = con;
	}
	
	public void guardar(Reserva reserva) {
		try {
			
			String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago)"
					+ "VALUES (?,?,?,?)";
			try(PreparedStatement pstm = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)){
				
				pstm.setObject(1, reserva.getFechaEntrada());
				pstm.setObject(2, reserva.getFechaSalida());
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaDePago());
				
				pstm.executeUpdate();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					
					while(rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> mostrar(){
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_DE_PAGO FROM RESERVAS";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.execute();
				
				transformarResultado(reservas,pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	

	public List<Reserva> buscarId(String id){
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_DE_PAGO FROM RESERVAS WHERE ID = ?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.setString(1, id);
				pstm.execute();
				
				transformarResultado(reservas,pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	
	
	public void Actualizar( LocalDate dateEntrada, LocalDate dateSalida, String valor, String formaPago, Integer id){
		
		try(PreparedStatement pstm = con.prepareStatement("UPDATE RESERVAS SET "
				+ "FECHA_ENTRADA=?, FECHA_SALIDA=?, VALOR=?, FORMA_DE_PAGO=? WHERE ID=?")){
			pstm.setObject(1, java.sql.Date.valueOf(dateEntrada));
			pstm.setObject(2,java.sql.Date.valueOf(dateSalida));
			pstm.setString(3, valor);
			pstm.setString(4, formaPago);
			pstm.setInt(5, id);
			pstm.execute();
		}catch(SQLException e){
			throw new RuntimeException();
		}
	}
	
	
	
	public void Eliminar(Integer id) {
		try {
			Statement state = con.createStatement();
			state.execute("SET FOREIGN_KEY_CHECKS=0");
			PreparedStatement pstm = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");
			pstm.setInt(1, id);
			pstm.execute();
			state.execute("SET FOREIGN_KEY_CHECKS=1");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	
	private void transformarResultado(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		
		try(ResultSet rst = pstm.getResultSet()){
			
			while(rst.next()) {
				int id = rst.getInt("id");
				LocalDate fechaEntrada = rst.getDate("fecha_entrada").toLocalDate().plusDays(1);
				LocalDate fechaSalida = rst.getDate("fecha_salida").toLocalDate().plusDays(2);
				String valor = rst.getString("valor");
				String formaPago = rst.getString("forma_de_pago");
				
				
				Reserva producto = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);
				reservas.add(producto);
			}
			
		}
		
	}
	
	
	
}