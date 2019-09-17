package controller;

import dto.LoginDTO;

public class UsuariosPermissao {
	
	public static boolean usuarioValido(LoginDTO login) {
		if (login.getUsuario().equals("Usuario") && login.getSenha().equals("Usuario")) {
			return true;
		}
		if (login.getUsuario().equals("Admin") && login.getSenha().equals("Admin")) {
			return true;
		}
		return false;
	}
	
	public static String getPapel(String usuario) {
		switch (usuario) {
		case "Admin":
			return "Administrador";			
		default:
			return "Usuario";
		}
	}

}
