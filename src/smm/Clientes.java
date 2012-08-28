package smm;

import java.util.HashMap;
import java.util.InputMismatchException;
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
		int edad = 0;
		boolean edadCorrecta = false;
		
		Scanner entrada = null;
		
		do
		{
			entrada = new Scanner(System.in);
			System.out.print("Nombre del cliente: ");
			nombre = entrada.nextLine();
		} while(nombre.equals(""));
		
		do
		{
			entrada = new Scanner(System.in);
			System.out.print("Apellido del cliente: ");
			apellidos = entrada.nextLine();
		} while(apellidos.equals(""));
		System.out.print("DNI del cliente: ");
		dni = entrada.nextLine();
		
		do
		{
			System.out.print("Edad del cliente: ");
			try
			{
				entrada = new Scanner(System.in);
				edad = entrada.nextInt();
				if (edad > 0) // La edad no es negativa
					edadCorrecta=true;
				else
					System.out.println("La entrada de la edad ha sido erronea, introduzca una correcta.");
			}
			catch (InputMismatchException ime)  // Se ha introducido un elemento no numerico
			{
				edadCorrecta=false;
				System.out.println("La entrada de la edad ha sido erronea, introduzca una correcta.");
			}
		} while(!edadCorrecta);
		
		Clientes cliente = new Clientes (nombre, apellidos, dni, edad); 
		numClientes = numClientes + 1;
		tablaClientes.put(numClientes, cliente); // Damos de alta al cliente
		
		System.out.println("Cliente creado con exito");
	}
	
	/**
	 * Lista los clientes almacenados.
	 *
	 * @param tablaClientes Almacena los clientes creados.
	 */
	public static void listarClientes(HashMap<Integer, Clientes> tablaClientes)
	{
		if (tablaClientes.isEmpty())  // No hay clientes en el sistema
		{
			System.out.println("No existen clientes dados de alta");
		}
		else
		{
			String nombre = null;
			String apellidos = null;
			String dni = null;
			int edad;
			
			Set<Entry<Integer,Clientes>> s = tablaClientes.entrySet();
			Iterator<Entry<Integer, Clientes>> it=s.iterator();
			Entry<Integer, Clientes> m = null;
            
            while (it.hasNext())
			{
				m =it.next();
	            nombre=(String)m.getValue().nombreCliente;
	            apellidos=(String)m.getValue().apellidoCliente;
	            dni=(String)m.getValue().dniCliente;
	            edad=m.getValue().edadCliente;
	            System.out.println("Nombre: "+nombre+
	            				   " Apellidos: "+apellidos+
	            				   " DNI: "+dni+
	            				   " Edad: "+edad);	
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
		if (tablaClientes.isEmpty()) // No hay clientes en el sistema
		{
			System.out.println("No existen clientes dados de alta para borrar.");
		}
		else
		{
			String respuesta;
			Scanner entrada = null;
			
			if(Clientes.buscaCliente(indice, tablaClientes)) // Buscamos el cliente en el sistema
			{
				System.out.println("Se va a proceder al borrado del cliente: ");
	            System.out.println("Nombre: "+tablaClientes.get(indice).nombreCliente+
     				   			   " Apellidos: "+tablaClientes.get(indice).apellidoCliente+
     				               " DNI: "+tablaClientes.get(indice).dniCliente+
     				               " Edad: "+tablaClientes.get(indice).edadCliente);	
	            entrada = new Scanner(System.in);
	            
	            do
	            {
	            	System.out.print("¿Esta usted seguro? (si/no): ");
		            respuesta = entrada.nextLine().toLowerCase();
		            if ((!respuesta.equals("si")) && (!respuesta.equals("no")))
		            	System.out.println("Por favor escriba si o no.");
	            } while ((!respuesta.equals("si")) && (!respuesta.equals("no")));
	            
	            if (respuesta.equals("si"))
	            {
	            	tablaClientes.remove(indice);
	            	numClientes = numClientes - 1;
	            	System.out.println("Cliente eliminado correctamente.");
	            }
	            if (respuesta.equals("no"))
	            {
	            	System.out.println("El cliente no fue borrado del sistema.");
	            }
			}
			else
				System.out.println("No existe cliente con ese indice.");
		}
	}
	
	/**
	 * Elimina un cliente que sera pedido por pantalla.
	 *
	 * @param tablaClientes Almacena los clientes creados.
	 */
	public static void eliminarCliente(HashMap<Integer, Clientes> tablaClientes)
	{	
		if (tablaClientes.isEmpty()) // No hay clientes en el sistema
		{
			System.out.println("No existen clientes dados de alta para borrar.");
		}
		else
		{
			int indice;
			Scanner entrada = null;
			boolean indiceCorrecto = false;
			String respuesta;
			
			do
			{
				System.out.print("Indice de cliente a borrar: ");
				try
				{
					entrada = new Scanner(System.in);
					indice = entrada.nextInt();
					if(Clientes.buscaCliente(indice, tablaClientes)) // Buscamos el cliente en el sistema
					{
						System.out.println("Se va a proceder al borrado del cliente: ");
			            System.out.println("Nombre: "+tablaClientes.get(indice).nombreCliente+
		     				   			   " Apellidos: "+tablaClientes.get(indice).apellidoCliente+
		     				               " DNI: "+tablaClientes.get(indice).dniCliente+
		     				               " Edad: "+tablaClientes.get(indice).edadCliente);	
			            entrada = new Scanner(System.in);
			            
			            do // Esperamos una entrada si o no.
			            {
			            	System.out.print("¿Esta usted seguro? (si/no): ");
				            respuesta = entrada.nextLine().toLowerCase();
				            if ((!respuesta.equals("si")) && (!respuesta.equals("no")))
				            	System.out.println("Por favor escriba si o no.");
			            } while ((!respuesta.equals("si")) && (!respuesta.equals("no")));
			            
			            if (respuesta.equals("si")) // Borramos cliente
			            {
							tablaClientes.remove(indice);
							numClientes = numClientes - 1;
							System.out.println("Cliente eliminado correctamente.");
							indiceCorrecto = true;
			            }
			            if (respuesta.equals("no"))
			            {
			            	System.out.println("El cliente no fue borrado del sistema.");
			            	indiceCorrecto = true;
			            }
					}
					else
						System.out.println("No existe cliente con ese indice.");
				}
				catch (InputMismatchException ime) // Se introduce un dato no numerico
				{
					System.out.println("La entrada del indice ha sido erronea, introduzca una correcta.");
				}
			} while (!indiceCorrecto);
		}
	}

	/**
	 * Busca clientes.
	 *
	 * @param Codigo de cliente
	 * @param tablaVentas Almacena los clientes
	 * @return true, si encuentra el cliente en la tabla tablaClientes
	 */
	public static boolean buscaCliente(int indice, HashMap<Integer, Clientes> tablaClientes)
	{
		boolean encontrado = false;
		int key;
		
		Set<Entry<Integer,Clientes>> s = tablaClientes.entrySet();
		Iterator<Entry<Integer, Clientes>> it=s.iterator();
		Entry<Integer, Clientes> m = null;
		
		while (it.hasNext())  // Buscamos al cliente en el sistema
		{
			m =it.next();
			key=(Integer)m.getKey();
			if (indice == key)  // Encontrado
				encontrado = true;
		}
		return encontrado;
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
