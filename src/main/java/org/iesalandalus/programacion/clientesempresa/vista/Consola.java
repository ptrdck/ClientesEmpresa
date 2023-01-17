package org.iesalandalus.programacion.clientesempresa.vista;

import java.time.LocalDate;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	//Método para ingresar una opción por pantalla
	public static Opcion elegirOpcion() 
	{
		
		int opcion =0;
		
		// Queremos que value nos devuelva un array con la lista de las constantes 
		//de la clase ENUM Opcion.
		
		Opcion[] opciones = Opcion.values();
		
		//Abrimos un ciclo do-while para recibir una entrada por teclado
		// evaluaremos la validez de la entrada
		
		do
		{
			System.out.print("Por favor, elija una opción: ");
			opcion = Entrada.entero();
		} while (opcion < 0 || opcion > opciones.length -1);
		
		return opciones[opcion];
	}
	
	public static Cliente leerCliente()
	{
		System.out.print("Ingrese el nombre del cliente: ");
		String nombre = Entrada.cadena();
		System.out.print("Ingrese el DNI del cliente: ");
		String dni = Entrada.cadena();
		System.out.print("Ingrese el correo del cliente: ");
		String correo = Entrada.cadena();
		System.out.print("Ingrese el teléfono del cliente: ");
		String telefono = Entrada.cadena();
		System.out.print("Ingrese la fecha de nacimiento del cliente: ");
		LocalDate fechaNacimiento = leerFechaNacimiento();
		
		return new Cliente(nombre, dni, correo, telefono, fechaNacimiento);
	}
	
	public static Cliente leerClienteDni()
	{
		System.out.print("Ingrese el DNI del cliente: ");
		String dni = Entrada.cadena();
		LocalDate fechaNacimiento = null;
		Cliente cliente = new Cliente("Carl", dni, "carlsagan@cosmos.com", "644493658", fechaNacimiento);
		
		return cliente;
		
		
	}
	
	public static LocalDate leerFechaNacimiento()
	{
		LocalDate fechaNacimiento = null;
		
		System.out.print("Ingrese la fecha de nacimiento del cliente (dd/MM/yyyy): ");
		String fechaCadena = Entrada.cadena();
		
		try 
		{
			fechaNacimiento = LocalDate.parse(fechaCadena);
		}
		catch (IllegalArgumentException e)
		{
			System.out.print(e.getMessage());
		}
		
		return fechaNacimiento;
	}
	
	//método para mostrar por pantalla información relacionada con el menú de opciones
	public static void mostrarMenu()
	{
		System.out.println("0.- Salir");
		System.out.println("1.- Insertar Cliente");
		System.out.println("2.- Buscar Cliente");
		System.out.println("3.- Borrar Cliente");
		System.out.println("4.- Mostrar Clientes según fecha");
		System.out.println("5.- Mostrar Clientes");
	}
	
	private Consola()
	{
		
	}

}
