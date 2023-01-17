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
	
	private static final int NUM_MAX_CLIENTES = 5;
	static Clientes clientes = new Clientes(NUM_MAX_CLIENTES);
	

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
			Cliente cliente = Consola.leerCliente();
			
			clientes.insertar(cliente);
			
			System.out.println("El cliente " + cliente + " ha sido insertado con éxito en el sitema. ");

			System.out.println("Disponibles " + (clientes.getCapacidad() - clientes.getTamano() + "clientes para insertar en el sistema "));
			
		}
		catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
	}
		
		
	private static void buscarCliente()
	{
		try 
		{
			Cliente cliente = new Cliente("Pedro Patricio Cárdenas Figueroa", "45933426B", "ppcf_11@outlook.com", "644495603", "11/02/1995");
			Clientes clientes = new Clientes(cliente);
			if ((clientes.buscar(cliente)) == null)
			{
				System.out.println("El cliente " + cliente + " no está registrado en el sistema");
			}
			else
			{
				System.out.println(cliente);
			}
		}
		catch (NullPointerException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private static void borrarCliente()
	{
		try
		{
			Cliente cliente = new Cliente("Pedro Patricio Cárdenas Figueroa", "45933426B", "ppcf_11@outlook.com", "644495603", "11/02/1995");
			Clientes clientes = new Clientes(cliente);
			
			clientes.buscar(cliente);
			
			System.out.println("El cliente " + cliente + " ha sido borrado del sistema");
			System.out.println("Disponibles " + (clientes.getCapacidad() - clientes.getTamano()) + " clientes para insertar en el sistema");
		}
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
			
			for (int i = 0; i <= clientes.get(fechaNacimiento).length -1; i++ )
			{
				if (clientes.get(fechaNacimiento)[i] != null)
				{
					clienteRegistrado = true;
					System.out.println(clientes.get(fechaNacimiento)[i]);
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