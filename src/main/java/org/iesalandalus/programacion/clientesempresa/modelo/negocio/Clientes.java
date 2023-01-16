package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
	
	//Definición de variables
	
	// Capacidad de array
	private int capacidad;
	// Tamaño de array
	private int tamano;
	//definición de array de Clientes
	public Cliente[] coleccionClientes;
	
	
	//Constructor con parámetros. En este caso: capacidad.
	public Clientes(int capacidad)
	{
		if (capacidad <= 0)
		{
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionClientes = new Cliente[capacidad];
		
	}
	
	// Para borrar necesitamos acceder a la posicion de la cita en el array y desplazamos la posición
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException 
	{
		
		int indiceCliente = buscarIndice(cliente);
		
		if (cliente == null)
		{
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		if(!tamanoSuperado(indiceCliente))
		{
			desplazarUnaPosicionHaciaIzquierda(indiceCliente);
		}
		else 
		{
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");
			
		}
		//Luego de desplazar la posición, se reduce el tamaño
		tamano--;
		
	}
	
	public Cliente buscar(Cliente cliente)
	{
		int indice = buscarIndice(cliente);
		if (!tamanoSuperado(indice))
		{
			return new Cliente(coleccionClientes[indice]);
		} 
		else
		{
			return null;
		}
	}
	
	//método que devuelve posición donde se encuentra cliente
	private int buscarIndice(Cliente cliente)
	{
		int indice = 0;
		
		boolean encontrado = false;
		
		//booleano que ayuda a optimizar la búsqueda en el recorrido del array
		//siempre y cuando se encuentre el índice antes de llegar al final del tamaño
		
		while (!tamanoSuperado(indice) && !encontrado)
		{
			if (coleccionClientes[indice].equals(cliente))
			{
				encontrado = true;
			}
			else
			{
				indice++;
			}
		}
		return indice;
	}
	
	//Método que devuelve true si el indice es superior o igual a la capacidad
	private boolean capacidadSuperada(int indice)
	{
		boolean capacidadSuperada;
		capacidadSuperada = (indice>= capacidad);
		return capacidadSuperada;
	}
	
	private Cliente[] copiaProfundaClientes()
	{
		Cliente[] copiaProfundaClientes = new Cliente[capacidad];
		
		for (int i=0; !tamanoSuperado(i); i++)
		{
			copiaProfundaClientes[i] = new Cliente(coleccionClientes[i]);
		}
		
		return copiaProfundaClientes;
	}
	
	private void desplazarUnaPosicionHaciaIzquierda(int indice)
	{
		for (int i = indice; i< coleccionClientes.length -1; i++)
		{
			/*
			 * Desplaza a la izquierda para recorrer desde el primero hasta 
			 * el último en orden ascendente (Si se desea recorrer a la derecha
			 * el proceso es en orden descendente)
			 */
			coleccionClientes[i] = coleccionClientes[i+1];
		}
		
	}
	
	//devuelve array de clientes
	public Cliente[] get() 
	{
		return coleccionClientes;
	}
	
	public int getCapacidad()
	{
		return capacidad;
	}
	
	public int getTamano()
	{
		return tamano;
	}
	
	public void insertar (Cliente cliente) throws OperationNotSupportedException
	{
		if (cliente == null)
		{
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		
		//Comprobación de tamaño en cuanto a capacidad
		
		if (capacidadSuperada(tamano))
		{
			throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");
		}
		
		int indice = buscarIndice(cliente);
		
		if (tamanoSuperado(indice))
		{
			coleccionClientes[tamano] = new Cliente(cliente);
		}
		else
		{
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");
		}
		
		//Después de insertar cliente, se incrementa tamaño de array
		
		tamano++;
	}
	
	//Método que devuelve true cuando el índice es superior o igual al tamaño del array
	private boolean tamanoSuperado(int indice)
	{
		boolean tamanoSuperado;
		tamanoSuperado = (indice >= tamano);
		return tamanoSuperado;
	}
	

}
