package com.latam.alura.hotel.modelo;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.latam.alura.hotel.factory.*;

public class Usuario {

	private String usuario;
	private String contraseña;

	public Usuario(String usuario, String contraseña) {
		this.usuario = usuario;
		this.contraseña = contraseña;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	
	public static boolean validarUsuario(String nombre , String contraseña){
		ConnectionFactory con = new ConnectionFactory();
		Connection connec = null;
		PreparedStatement state = null;
		ResultSet result = null;
		
		try {
			connec = con.recuperaConexion();
			state = connec.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? and contraseña = ?");
			state.setString(1, nombre);
			state.setString(2, contraseña);
			result = state.executeQuery();
			
			return result.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (result != null)
					result.close();
				if (state != null);
				state.close();
				if (connec != null);
				connec.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
