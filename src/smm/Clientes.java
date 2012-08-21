package smm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase para la gestion de clientes.
 * Los clientes se podran crear, listar y borrar
 * 
 * @version 1.0, 25/05/2012
 * @author Santiago Martinez Martinez
 */
public class Clientes
{
	
	/** Nombre del cliente. */
	private String nombreCliente;
	
	/** Apellido del cliente. */
	private String apellidoCliente;
	
	/** DNI del cliente. */
	private String dniCliente;
	
	/** Edad del cliente. */
	private int	edadCliente;
	
	/** Numero total de clientes. */
	private static int numClientes = 0;
	
	/**
	 * Crea el objeto cliente que sera guardado en un array.
	 *
	 * @param nombre Nombre del cliente
	 * @param apellidos Apellidos del cliente
	 * @param dni DNI del cliente
	 * @param edad Edad del cliente
	 */
	private Clientes(String nombre, String apellidos, String dni, int edad)
	{
		this.nombreCliente = nombre;
		this.apellidoCliente = apellidos;
		this.dniCliente = dni;
		this.edadCliente = edad;
	}
	
	/**
	 * Metodo que pide los datos del cliente y los almacena.
	 *
	 * @param tablaClientes Almacena los clientes creados.
	 */
	public static void crearCliente(HashMap<Integer, Clientes> tablaClientes)
	{
		String nombre;
		String apellidos;
		String dni;
		int edad;
		
		Scanner entrada = new Scanner(System.in);
			
		System.out.print("Nombre del cliente: ");
		nombre = entrada.nextLine();
		System.out.print("Apellido del cliente: ");
		apellidos = entrada.nextLine();
		System.out.print("DNI del cliente: ");
		dni = entrada.nextLine();
		System.out.print("Edad del cliente: ");
		edad = entrada.nextInt();
		
		Clientes cliente = new Clientes (nombre, apellidos, dni, edad);
		numClientes = numClientes + 1;
		tablaClientes.put(numClientes, cliente);
		
		System.out.println("Cliente creado con exito");
	}
	
	/**
	 * Lista los clientes almacenados.
	 *
	 * @param tablaClientes Almacena los clientes creados.
	 */
	public static void listarClientes(HashMap<Integer, Clientes> tablaClientes)
	{
		if (tablaClientes.isEmpty())
		{
			System.out.println("No existen clientes dados de alta");
		}
		else
		{
			Set<Entry<Integer,Clientes>> s = tablaClientes.entrySet();
			Iterator<Entry<Integer, Clientes>> it=s.iterator();
			
			Entry<Integer, Clientes> m = null;
            
            while (it.hasNext())
			{
				m=it.next();
	            int key=(Integer)m.getKey();
	            String value=(String)m.getValue().nombreCliente;
	            System.out.println("Key :"+key+" value :"+value);	
			}
		}
	}
	
	/**
	 * Elimina un cliente dado.
	 *
	 * @param tablaClientes Almacena los clientes creados.
	 * @param indice Codigo de cliente a eliminar
	 */
	public static void eliminarCliente(HashMap<Integer, Clientes> tablaClientes, int indice)
	{
		if (tablaClientes.isEmpty())
		{
			System.out.println("No existen clientes dados de alta para borrar");
		}
		else
		{
			tablaClientes.remove(indice);
			System.out.println("Cliente eliminado correctamente");
		}
	}
	
	/**
	 * Elimina un cliente que sera pedido por pantalla.
	 *
	 * @param tablaClientes Almacena los clientes creados.
	 */
	public static void eliminarCliente(HashMap<Integer, Clientes> tablaClientes)
	{
		if (tablaClientes.isEmpty())
		{
			System.out.println("No existen clientes dados de alta para borrar");
		}
		else
		{
			int indice;
			Scanner entrada = new Scanner(System.in);
			System.out.print("Indice de cliente a borrar: ");
			indice = entrada.nextInt();
			tablaClientes.remove(indice);
			System.out.println("Cliente eliminado correctamente");
		}
	}
	
	// GETTERS Y SETTERS
	/**
	 * Devuelve el numero de clientes.
	 *
	 * @return El numero de clientes almacenados
	 */
	public static int getNumClientes()
	{
		return numClientes;
	}

	/**
	 * Informa el numero de clientes.
	 *
	 * @param numClientes Numero de clientes almacenados
	 */
	public static void setNumClientes(int numClientes)
	{
		Clientes.numClientes = numClientes;
	}

	/**
	 * Devuelve la edad del cliente.
	 *
	 * @return La edad del cliente
	 */
	public int getEdadCliente()
	{
		return edadCliente;
	}

	/**
	 * Informa la edad del cliente.
	 *
	 * @param edadCliente La edad del cliente
	 */
	public void setEdadCliente(int edadCliente)
	{
		this.edadCliente = edadCliente;
	}

	/**
	 * Devuelve el DNI del cliente.
	 *
	 * @return El DNI del cliente
	 */
	public String getDniCliente()
	{
		return dniCliente;
	}

	/**
	 * Informa el DNI del cliente.
	 *
	 * @param dniCliente El DNI del cliente
	 */
	public void setDniCliente(String dniCliente)
	{
		this.dniCliente = dniCliente;
	}

	/**
	 * Devuelve el apellido del cliente.
	 *
	 * @return El apellido del cliente
	 */
	public String getApellidoCliente()
	{
		return apellidoCliente;
	}

	/**
	 * Informa el apellido del cliente.
	 *
	 * @param apellidoCliente El apellido del cliente
	 */
	public void setApellidoCliente(String apellidoCliente)
	{
		this.apellidoCliente = apellidoCliente;
	}

	/**
	 * Devuelve el nombre del cliente.
	 *
	 * @return EL nombre del cliente
	 */
	public String getNombreCliente()
	{
		return nombreCliente;
	}

	/**
	 * Informa el nombre del cliente.
	 *
	 * @param nombreCliente El nombre del cliente
	 */
	public void setNombreCliente(String nombreCliente)
	{
		this.nombreCliente = nombreCliente;
	}
	
}
