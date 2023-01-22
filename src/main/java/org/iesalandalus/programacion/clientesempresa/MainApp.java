package org.iesalandalus.programacion.clientesempresa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

/**
 * 
 * @author Pedro Patricio Cárdenas Figueroa
 * IES Al-Andalús
 * Programación
 * Tarea Online 4
 *
 */

public class MainApp {
	
	//Declaramos un número bajo para optimizar el tiempo de pruebas del código
	private static final int NUM_MAX_CLIENTES = 3;
	public static Clientes clientes = new Clientes(NUM_MAX_CLIENTES);
	

	public static void main(String[] args) {
		
		
		System.out.println("_______________________");
		System.out.println("**  Tarea Online 4  **");
		System.out.println("**  Programación  **");
		System.out.println("** Pedro Cárdenas F. **");
		System.out.println("_______________________");
		
		Opcion opcion;
		
		do
		{
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			ejecutarOpcion(opcion);
		}while (opcion != Opcion.SALIR);
		
		System.out.println("¡¡¡Hasta luego Lucas!!!");
	}
	private static void ejecutarOpcion(Opcion opcion)
	{
		switch (opcion)
		{
		case SALIR:
			break;
		case INSERTAR_CLIENTE:
			insertarCliente();
			break;
		case BUSCAR_CLIENTE:
			buscarCliente();
			break;
		case BORRAR_CLIENTE:
			borrarCliente();
			break;
		case MOSTRAR_CLIENTES_FECHA:
			mostrarClientesFecha();
			break;
		case MOSTRAR_CLIENTES:
			mostrarClientes();
			break;
		default:
				
			}
		}
	
	private static void insertarCliente() {
		
		try
		{
			Cliente cliente = new Cliente(Consola.leerCliente());
			
			clientes.insertar(cliente);
			
			System.out.println("El cliente " + cliente + " ha sido insertado con éxito en el sitema. ");

			System.out.println("Disponibles " + (clientes.getCapacidad() - clientes.getTamano() + " clientes para insertar en el sistema "));
			
		}
		/*
		 * Captura de posibles excepciones al insertar una cliente.
		 * Clases: Cliente, CLientes y método insertar
		 */
		catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
	}
		
		
	private static void buscarCliente()
	{
		try 
		{
		
			Cliente cliente = new Cliente(Consola.leerClienteDni());
			if ((clientes.buscar(cliente)) == null)
			{
				System.out.println("El cliente " + cliente + " no está registrado en el sistema");
			}
			else
			{
				System.out.println(cliente);
			}
			
			/*
			 * Basta con calcular un posible nulo al introducir la fecha para verificar el incumplimiento
			 * del formato y se captura desde COnsola.
			 */
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private static void borrarCliente()
	{
		try
		{
			Cliente cliente = new Cliente(Consola.leerClienteDni());
			clientes.borrar(cliente);
			
			System.out.println("El cliente " + cliente + " ha sido borrado del sistema");
			System.out.println("Disponibles " + (clientes.getCapacidad() - clientes.getTamano()) + " clientes para insertar en el sistema");
		}
		//Caotura de posibles excepciones
		catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private static void mostrarClientes()
	{
		if (clientes.getTamano() == 0)
		{
			System.out.println("El sistema no tiene clientes en su registro ");
		}
		
		for (int i = 0; i < clientes.getTamano(); i++)
		{
			System.out.println(clientes.get()[i]);
		}
	}
	
	private static void mostrarClientesFecha()
	{
		try
		{
			LocalDate fechaNacimiento = Consola.leerFechaNacimiento();
			
			boolean clienteRegistrado = false;
			
			for (int i = 0; i <= clientes.coleccionClientes.length; i++ )
			{
				if (clientes.get()[i].getFechaNacimiento().equals(fechaNacimiento))
				{
					clienteRegistrado = true;
					System.out.println(clientes.get()[i].toString());
				}
			}
			
			if (clientes.getTamano() == 0)
			{
				System.out.println("El sistema no tiene clientes registrados. Ingrese un cliente ");
			}
			else if (!clienteRegistrado) 
			{
				System.out.println("No hay clientes registrados en el sistema con la fecha ingresada: " + 
						fechaNacimiento.format(DateTimeFormatter.ofPattern("dd 'de' MM 'del' yyyy")));
				
			}
			
		}
		catch (NullPointerException e)
		{
			System.out.println(e.getMessage());
		}
	}

}