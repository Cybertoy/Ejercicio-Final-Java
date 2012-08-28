package smm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * The Class Ventas.
 */
public class Ventas
{
	
	/** Codigo de cliente */
	private int codCliente;
	
	/** Codigo de disco */
	private int codMusica;
	
	/** Codigo de venta */
	private int codVenta;
	
	/** Numero de ventas */
	public static int numVentas;
	
	/**
	 * Crea el objeto venta que sera guardado en un array.
	 *
	 * @param codigoCliente Codigo de cliente
	 * @param codigoMusica Codigo de disco
	 * @param codigoVenta Codigo de venta
	 */
	public Ventas(int codigoCliente, int codigoMusica, int codigoVenta)
	{
		this.codCliente = codigoCliente;
		this.codMusica = codigoMusica;
		this.codVenta = codigoVenta;
	}
	
	/**
	 * Metodo que pide los datos referentes a la venta
	 *
	 * @param tablaVentas Almacena las ventas
	 */
	public static void crearVenta(HashMap<Integer, Ventas> tablaVentas)
	{
		int idCliente = 0;
		int idMusica = 0;
		boolean entradaCorrecta = false;
		int error = 0;
		
		Scanner entrada = null;
		
		if (Clientes.getNumClientes() == 0)  // Miramos si hay clientes en el sistema
		{
			error = 1;
			System.out.println("No hay clientes dados de alta, no se puede realizar la venta.");
		}
		else
		{
			if (Musica.getNumMusica()==0) // Miramos si hay discos en el sistema
			{
				error = 1;
				System.out.println("No hay discos dados de alta, no se puede realizar la venta.");
			}
		}
		
		if (error == 0)
		{
			do
			{
				System.out.print("Codigo cliente al que vender: ");
				try
				{				
					entrada = new Scanner(System.in);
					idCliente = entrada.nextInt();
					if (!Clientes.buscaCliente(idCliente,Tienda.tablaClientes)) // Buscamos el cliente en el sistema
					{
						System.out.println("El indice del cliente no existe.");					
					}
					else
						entradaCorrecta=true;
				}
				catch (InputMismatchException ime)  // Se introduce un dato no numerico
				{
					entradaCorrecta = false;
					System.out.println("La entrada del codigo cliente ha sido erronea, introduzca una correcta.");
				}
			} while (!entradaCorrecta);			

			do
			{
				entradaCorrecta = false;
				System.out.print("Codigo de disco a vender: ");
				try
				{
					entrada = new Scanner(System.in);
					idMusica = entrada.nextInt();
					if (!Musica.buscaMusica(idMusica, Tienda.tablaMusica)) // Buscamos el disco en el sistema
					{
						System.out.println("El indice del disco no existe.");		
					}
					else
						entradaCorrecta = true;
				}
				catch (InputMismatchException ime) // Se introduce un dato no numerico
				{
					entradaCorrecta=false;
					System.out.println("La entrada del codigo de disco ha sido erronea, introduzca una correcta.");
				}				
			} while (!entradaCorrecta);
			
			Ventas venta = new Ventas (idCliente, idMusica, numVentas);
			numVentas = numVentas + 1;
			tablaVentas.put(numVentas, venta);  // damos de alta la venta en el sistema
			System.out.println("Venta creada con exito");
		}
	}
	
	/**
	 * Lista las ventas almacenadas.
	 *
	 * @param tablaVentas Almacena las ventas realizadas
	 */
	public static void listarVenta(HashMap<Integer, Ventas> tablaVentas)
	{
		int codigoCliente;
		int codigoMusica;
		int codigoVenta;
		
		if (tablaVentas.isEmpty())  // No hay ventas en el sistema
		{
			System.out.println("No hay ventas en el registro.");
		}
		else
		{
			Set<Entry<Integer,Ventas>> s = tablaVentas.entrySet();
			Iterator<Entry<Integer, Ventas>> it=s.iterator();
			Entry<Integer, Ventas> m = null;
			
			while (it.hasNext())  // Recorremos las ventas almacenadas
			{
				m =it.next();
	            codigoCliente=(int)m.getValue().codCliente;
	            codigoMusica=(int)m.getValue().codMusica;
	            codigoVenta=(int)m.getValue().codVenta;
	            System.out.println("Codigo de cliente: "+codigoCliente+" Codigo de disco: "+codigoMusica+" Codigo de venta: "+codigoVenta);	
			}
		}
	}
	
	/**
	 * Elimina una venta dada.
	 *
	 * @param tablaVentas Almacena las ventas creadas
	 * @param indice Codigo de venta a eliminar
	 */
	public static void eliminarVenta(HashMap<Integer, Ventas> tablaVentas, int indice)
	{
		if (tablaVentas.isEmpty()) // No hay ventas en el sistema
		{
			System.out.println("No existen ventas dados de alta para borrar");
		}
		else
		{
			if(Ventas.buscaVenta(indice, tablaVentas)) // Buscamos la venta en el sistema
			{
				tablaVentas.remove(indice);
				System.out.println("venta eliminado correctamente");				
			}
			else
				System.out.println("No existe venta con ese indice");
		}
	}
	
	/**
	 * Elimina un venta que sera pedido por pantalla.
	 *
	 * @param tablaVentas Almacena las ventas realizadas
	 */
	public static void eliminarVenta(HashMap<Integer, Ventas> tablaVentas)
	{
		if (tablaVentas.isEmpty()) // No hay ventas en el sistema
		{
			System.out.println("No existen ventas dados de alta para borrar");
		}
		else
		{
			int indice;
			Scanner entrada = null;
			boolean indiceCorrecto = false;
			
			do
			{
				System.out.print("Codigo de venta a borrar: ");
				try
				{
					entrada = new Scanner(System.in);
					indice = entrada.nextInt();
					if(buscaVenta(indice, tablaVentas)) // Buscamos la venta en el sistema
					{						
						tablaVentas.remove(indice);
						System.out.println("Venta eliminada correctamente");
						indiceCorrecto = true;
					}
					else
						System.out.println("No existe venta con ese indice.");
				}
				catch (InputMismatchException ime)  // Se introduce un dato no numerico
				{
					System.out.println("La entrada del indice ha sido erronea, introduzca una correcta.");		
				}
			} while (!indiceCorrecto);
		}
	}

	/**
	 * Busca venta.
	 *
	 * @param indice Codigo de venta
	 * @param tablaVentas Almacena las ventas realizadas
	 * @return true, si encuentra la venta en la tabla tablaVentas
	 */
	private static boolean buscaVenta(int indice, HashMap<Integer, Ventas> tablaVentas)
	{
		boolean encontrado = false;
		int key;
		
		Set<Entry<Integer,Ventas>> s = tablaVentas.entrySet();
		Iterator<Entry<Integer, Ventas>> it=s.iterator();
		Entry<Integer, Ventas> m = null;
		
		while (it.hasNext())  // Buscamos la venta en el sistema
		{
			m =it.next();
			key=(Integer)m.getKey();
			if (indice == key) // Encontrado
				encontrado = true;
		}
		return encontrado;
	}
	
	/**
	 * Vuelca ventas a fichero.
	 *
	 * @param tablaVentas Almacena las ventas realizadas
	 * @param fichero Fichero que almacena las ventas
	 */
	public static void vuelcaVentasAFichero(HashMap<Integer, Ventas> tablaVentas, FileWriter fichero)
	{
		int codigoCliente;
		int codigoMusica;
		int codigoVenta;
		
		PrintWriter pw = new PrintWriter(fichero);
		
		Set<Entry<Integer,Ventas>> s = tablaVentas.entrySet();
		Iterator<Entry<Integer, Ventas>> it=s.iterator();
		Entry<Integer, Ventas> m = null;
		
		while (it.hasNext())  // Recorremos la tabla con las ventas
		{
			m =it.next();
            codigoCliente=(int)m.getValue().codCliente;
            codigoMusica=(int)m.getValue().codMusica;
            codigoVenta=(int)m.getValue().codVenta;
            pw.println(codigoCliente+";"+codigoMusica+";"+codigoVenta); // Linea con venta al fichero
		}
	}
	
	/**
	 * Vuelca fichero a tabla ventas.
	 *
	 * @param tablaVentas Almacena las ventas realizadas
	 */
	public static void vuelcaFicheroAVentas(HashMap<Integer, Ventas> tablaVentas)
	{
		String linea;
		FileReader ficheroVentasRead = null;
		BufferedReader br = null;
		StringTokenizer orden = null;
		int[] arrayLinea = null;
		
		arrayLinea = new int[3]; // Guarda los 3 valores de la linea
		
		try
		{
			ficheroVentasRead = new FileReader("ventas.txt");
			br = new BufferedReader(ficheroVentasRead);
			while((linea=br.readLine())!=null)  // Recorremos todas las lineas
			{	
				orden = new StringTokenizer(linea,";"); //Recoge el token correspondiente, no tiene en cuenta el token ";"
				for (int i=0; orden.hasMoreTokens(); i++)
				{
					arrayLinea[i]=Integer.parseInt(orden.nextToken()); // Almacenamos el token correspondiente
				}
					
				Ventas venta = new Ventas (arrayLinea[0], arrayLinea[1], arrayLinea[2]);
				numVentas = numVentas + 1;
				tablaVentas.put(numVentas, venta); //  Damos de alta la venta en el sistema
			}
		}
		catch (IOException ioe) // Error en la lectura del fichero
		{
			System.out.println("Se ha producido un error al procesar el fichero de ventas");
		}
		finally // Cuando termine
		{
			try
			{                    
	            if( null != ficheroVentasRead )
	            {   
	            	ficheroVentasRead.close(); // Cerramos el fichero
	            }                  
	        }
			catch (IOException ioe) // Error al cerrar el fichero
			{ 
				System.out.println("Se ha producido un error al procesar el fichero de ventas");
	        }
		}
	}
}
