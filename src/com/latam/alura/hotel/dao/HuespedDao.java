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

public class HuespedDao {
	
	private Connection con;

	
	
	public HuespedDao(Connection con) {
		super();
		this.con = con;
	}
	
	
	public void guardar(Huesped huespedes) {
		
		try {
			
			String sql = "INSERT INTO HUESPEDES(NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO,"
					+ "ID_RESERVA) VALUES (?,?,?,?,?,?)";
			try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1, huespedes.getNombre());
				pstm.setString(2, huespedes.getApellido());
				pstm.setObject(3, huespedes.getFechaDeNacimiento());
				pstm.setString(4, huespedes.getNacionalidad());
				pstm.setString(5, huespedes.getTelefono());
				pstm.setInt(6, huespedes.getIdReserva());
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while(rst.next()) {
						huespedes.setId(rst.getInt(1));
					}
				}
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> mostrar(){
		List<Huesped> huespedes = new ArrayList<Huesped>();
		try {
			String sql = "SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, "
					+ "TELEFONO,ID_RESERVA FROM HUESPEDES";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.execute();
				transformarResultado(huespedes,pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Huesped> buscarId(String id){
		List<Huesped> huespedes = new ArrayList<Huesped>();
		try {
			String sql = "SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, "
					+ "TELEFONO,ID_RESERVA FROM HUESPEDES WHERE ID=?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.setString(1, id);
				pstm.execute();
				transformarResultado(huespedes,pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}  
	
	
	public void ActualizarHuesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono,
			Integer idReserva, Integer id) {
		try(PreparedStatement stm = con.prepareStatement(""
				+ "UPDATE HUESPEDES SET NOMBRE=?, APELLIDO=?, FECHA_NACIMIENTO=?, NACIONALIDAD=?, "
				+ "TELEFONO=?, ID_RESERVA=? WHERE ID=?")){
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setObject(3, fechaNacimiento);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			stm.setInt(6, idReserva);
			stm.setInt(7, id);
			stm.execute();
			
		}catch(SQLException e) {
			 throw new RuntimeException(e);
		}
	}
	
	
	public void Eliminar(Integer id) {
		try (PreparedStatement stm = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID=?")){
			stm.setInt(1, id);
			stm.execute();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
private void transformarResultado(List<Huesped> huespedes, PreparedStatement pstm) throws SQLException {
		
		try(ResultSet rst = pstm.executeQuery()){
			while(rst.next()) {
				int id = rst.getInt("id");
				String nombre = rst.getString("nombre");
				String apellido = rst.getString("apellido");				
				LocalDate fechaNacimiento = rst.getDate("fecha_nacimiento").toLocalDate().plusDays(1);
				String nacionalidad = rst.getString("nacionalidad");	
				String telefono = rst.getString("telefono");
				int idReserva = rst.getInt("id_reserva");
				
				Huesped huesped = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
				huespedes.add(huesped);
		
			}
		}
		
	}


}