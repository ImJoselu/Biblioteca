package com.example.demo.configuracion;

public class MainParaGenerarPassword {

	public static void main(String[] arg) {
		String password = "password103";
		System.out.println("Contrasenya original:" + password);

		String pass2 = EncriptaPassword.encriptarPassword(password);
		System.out.println("Contrasenya encriptada:" + pass2);
	}
}
