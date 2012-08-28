package smm;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.*;

/**
 * Clase para la gestion de clientes.
 * Los clientes se podran crear, listar y borrar
 * 
 * @version 1.0, 25/05/2012
 * @author Santiago Martinez Martinez
 */
public class Clientes extends Persona
{
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
		super(nombre,apellidos);
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
		boolean dniCorrecto = true;
		
		Scanner entrada = null;
		
		do // Validacion nombre
		{
			entrada = new Scanner(System.in);
			System.out.print("Nombre del cliente: ");
			nombre = entrada.nextLine();
		} while(nombre.equals(""));
		
		do // Validacion apellido
		{
			entrada = new Scanner(System.in);
			System.out.print("Apellido del cliente: ");
			apellidos = entrada.nextLine();
		} while(apellidos.equals(""));
		
		do // Validacion DNI
		{
			System.out.print("DNI del cliente (8 digitos, guion, letra): ");
			dni = entrada.nextLine();
			if (!validaDNI(dni))
			{
				System.out.println("El DNI introducido no es valido, reviselo.");
				dniCorrecto = false;
			}
			else
				dniCorrecto = true;
		} while (!dniCorrecto);
		
		do // Validacion edad
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
		
		System.out.println("Cliente creado con exito con codigo " + numClientes);
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
	            nombre=(String)m.getValue().getNombre();
	            apellidos=(String)m.getValue().getApellido();
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
				// Nos aseguramos que el usuario quiere borrar el cliente
				System.out.println("Se va a proceder al borrado del cliente: ");
	            System.out.println("Nombre: "+tablaClientes.get(indice).getNombre()+
     				   			   " Apellidos: "+tablaClientes.get(indice).getApellido()+
     				               " DNI: "+tablaClientes.get(indice).dniCliente+
     				               " Edad: "+tablaClientes.get(indice).edadCliente);	
	            entrada = new Scanner(System.in);
	            
	            do // Esperamos entrada si o no
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
						// Nos aseguramos que el usuario quiere borrar el cliente
						System.out.println("Se va a proceder al borrado del cliente: ");
			            System.out.println("Nombre: "+tablaClientes.get(indice).getNombre()+
		     				   			   " Apellidos: "+tablaClientes.get(indice).getApellido()+
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

	/**
	 * Valida el DNI introducido siguiendo un patron 8 digitos, guion , letra DNI
	 *
	 * @param DNI cliente
	 * @return true, si el dni es correcto en formato y su letra de control
	 */
	public static boolean validaDNI(String dni)
	{
		Pattern patron=Pattern.compile("\\d{8}\\-[a-zA-Z]{1}"); // Expresion regular que verifica 8 digitos, guion y letra del DNI
		Matcher m=patron.matcher(dni); 
		if( m.matches()) // El patron coincide con el patron introducido
		{
			if( Clientes.validarLetra(dni)) // Validamos la letra
				return true;
		}
		return false; // El patron no es valido.
	}
 
	/**
	 * Valida la letra de control del DNI
	 *
	 * @param letra del DNI
	 * @return true, si la letra de DNI es correcta
	 */
	private static boolean validarLetra(String dni)
	{
		String[] letra=dni.split("-"); // Nos quedamos con la letra introducida.
 
		int caracter=(Integer.valueOf(letra[0]).intValue())%23;
		String[] control={"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E","T"}; // Digito de control
 
		if(control[caracter].compareToIgnoreCase(letra[1])==0) // La letra del dni introducido coincide con el digitol control calculado
			return true;

		return false; // No coincide
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
}
