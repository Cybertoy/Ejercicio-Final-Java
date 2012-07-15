package smm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Clientes
{
	private String nombreCliente;
	private String apellidoCliente;
	private String dniCliente;
	private int	edadCliente;
	
	private static int numClientes = 0;
	
	private Clientes(String nombre, String apellidos, String dni, int edad)
	{
		this.nombreCliente = nombre;
		this.apellidoCliente = apellidos;
		this.dniCliente = dni;
		this.edadCliente = edad;
	}
	
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
			
			while (it.hasNext())
			{
				Entry<Integer, Clientes> m =it.next();
	            int key=(Integer)m.getKey();
	            String value=(String)m.getValue().nombreCliente;
	            System.out.println("Key :"+key+" value :"+value);	
			}
		}
	}
	
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
	public static int getNumClientes()
	{
		return numClientes;
	}

	public static void setNumClientes(int numClientes)
	{
		Clientes.numClientes = numClientes;
	}

	public int getEdadCliente()
	{
		return edadCliente;
	}

	public void setEdadCliente(int edadCliente)
	{
		this.edadCliente = edadCliente;
	}

	public String getDniCliente()
	{
		return dniCliente;
	}

	public void setDniCliente(String dniCliente)
	{
		this.dniCliente = dniCliente;
	}

	public String getApellidoCliente()
	{
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente)
	{
		this.apellidoCliente = apellidoCliente;
	}

	public String getNombreCliente()
	{
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente)
	{
		this.nombreCliente = nombreCliente;
	}
	
}
