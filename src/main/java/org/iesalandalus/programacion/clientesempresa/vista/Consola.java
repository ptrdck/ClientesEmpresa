package org.iesalandalus.programacion.clientesempresa.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
	/*
	 * Al estar en una clase intermedia, las excepciones serán propagadas.
	 */
	public static Cliente leerCliente()
	{
		Cliente cliente = null;
		
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
		
		try {
			cliente = new Cliente(nombre, dni, correo, telefono, fechaNacimiento);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return cliente;
	}
	
	public static Cliente leerClienteDni()
	{
		System.out.print("Ingrese el DNI del cliente: ");
		String dni = Entrada.cadena();
		Cliente cliente = new Cliente("Carl Sagan", dni, "carlsagan@cosmos.com", "644493658", LocalDate.of(1990,  11, 2));
		
		return cliente;
		
		
	}
	
	public static LocalDate leerFechaNacimiento()
	{
		LocalDate fechaNacimiento = null;
		String fechaCadena = null;
		
		/*
		 * DateTimeFormatter basado en el atributo publico de la clase Cliente del patrón 
		 * FORMATO_FECHA
		 */
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(Cliente.FORMATO_FECHA);
		
		System.out.print("Ingrese la fecha de nacimiento del cliente (dd/MM/yyyy): ");
		fechaCadena = Entrada.cadena();
		
		try 
		{
			fechaNacimiento = LocalDate.parse(fechaCadena, formatoFecha);
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
		System.out.println("===================================");
		System.out.println("0.- Salir");
		System.out.println("1.- Insertar Cliente");
		System.out.println("2.- Buscar Cliente");
		System.out.println("3.- Borrar Cliente");
		System.out.println("4.- Mostrar Clientes según fecha");
		System.out.println("5.- Mostrar Clientes");
		System.out.println("===================================");
	}
	
	private Consola()
	{
		
	}

}
