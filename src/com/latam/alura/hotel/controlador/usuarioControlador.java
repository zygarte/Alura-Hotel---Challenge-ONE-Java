package com.latam.alura.hotel.controlador;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.latam.alura.hotel.modelo.*;
import views.Login;
import views.MenuUsuario;

public class usuarioControlador implements ActionListener{


	private Login vista;
	
	public usuarioControlador (Login vista) {
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nombre = vista.getNombre();
		String contraseña = vista.getContraseña();
		
		if (Usuario.validarUsuario(nombre, contraseña)) {
			MenuUsuario menu = new MenuUsuario();
			menu.setVisible(true);
			vista.dispose();
		}else {
			JOptionPane.showMessageDialog(vista, "Usuario o Contraseña no validos");
		}
	}
	
}
