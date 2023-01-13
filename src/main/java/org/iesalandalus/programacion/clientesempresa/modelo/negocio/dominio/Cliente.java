package org.iesalandalus.programacion.clientesempresa.modelo.negocio.dominio;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente 
{
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_TELEFONO = "[679][0-9]{8}";
	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	
	
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private LocalDate fechaNacimiento;
	
	
	//Constructor copia
	public Cliente (Cliente cliente)
	{
		if (cliente == null) 
		{
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		setNombre(cliente.getNombre());
		setDni(cliente.getDni());
		setCorreo(cliente.getCorreo());
		setTelefono(cliente.getTelefono());
		setFechaNacimiento(cliente.getFechaNacimiento());
	}
	
	//Constructor con parámetros
	public Cliente (String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento)
	{
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
		setTelefono(telefono);
		setFechaNacimiento(fechaNacimiento);
	}
	
	private boolean comprobarLetraDni (String dni)
	{
		char[] LETRAS_DNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
		
		int dniNumero;
		char dniLetra;
		
		dniNumero = Integer.parseInt(dni.substring(0, 7));
		dniLetra = dni.charAt(8);
		
		if (dniLetra == LETRAS_DNI[dniNumero % 23])
		{
			return true;
		}else {
			return false;
		}
		
	}

	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}
	
	private String formateaNombre (String nombre) 
	{
		
		String formateaNombre = "";
		
		StringTokenizer st = new StringTokenizer(nombre);
		
		int palabras = st.countTokens();
		
		for (int i = 0; i < palabras ; i++ )
		{
			
			String palabraSingular = st.nextToken();
			formateaNombre += palabraSingular.substring(0, 1).toUpperCase()+palabraSingular.substring(1).toLowerCase();
									
		}
		
		return formateaNombre;
		
	}
	
	
	public String getCorreo() {
		return correo;
	}
	
	public String getDni() {
		return dni;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	private String getIniciales() {
		
		String iniciales = "";
		
		StringTokenizer stIniciales = new StringTokenizer(nombre);
		
		while (stIniciales.hasMoreTokens()) {
			String inicial = stIniciales.nextToken();
			iniciales += inicial.substring(0,1); 
		}
		
		return iniciales;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	
	public void setCorreo(String correo) 
	{
		Pattern patron = Pattern.compile(ER_CORREO);
		Matcher mPatron = patron.matcher(correo);
		
		if (correo == null)
		{
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
		}
		
		if (!mPatron.matches()) 
		{
			throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
		}

		else
		{
		this.correo = correo;
		}
	}

	private void setDni(String dni) 
	{
		Pattern patron = Pattern.compile(ER_DNI);
		Matcher mPatron = patron.matcher(dni);
		
		if (dni == null)
		{
			throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
		}
		
		if (!mPatron.matches()) 
		{
			throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");
		}
		else if (!comprobarLetraDni(dni))
		{
			throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
		}
		
		else 
		{
		this.dni = dni;
		}
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) 
	{
		
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public void setNombre (String nombre) 
	{
		
		if (nombre == null)
		{
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		}
		if (nombre.isEmpty())
		{
			throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
		}
		else 
		{
		this.nombre = nombre;
		}
	}
	
	public void setTelefono (String telefono)
	{
		Pattern patron = Pattern.compile(ER_TELEFONO);
		Matcher mPatron = patron.matcher(telefono);
		
		if (telefono == null)
		{
			throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		}
		
		if (!mPatron.matches()) 
		{
			throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");
			
		}
		
		else
		{
		this.telefono = telefono;
		}
	}

	@Override
	public String toString() {
		return "Cliente " + "(" + getIniciales() +") [ + nombre=" + nombre + ", dni=" + dni + ", correo=" + correo + ", telefono=" + telefono
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
	
	
	


}
