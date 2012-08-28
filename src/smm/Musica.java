package smm;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase para la gestion de musica.
 * Los discos se podran crear, listar y borrar
 * 
 * @version 1.0, 25/05/2012
 * @author Santiago Martinez Martinez
 */
public class Musica
{
	
	/** Titulo del disco. */
	private String tituloMusica;
	
	/** Formato del disco. */
	private String formatoMusica;
	
	/** Numero de discos almacenados. */
	private static int numMusica = 0;
	
	/**
	 * Crea el objeto musica que sera guardado en un array.
	 *
	 * @param titulo Titulo del disco
	 * @param tipo Tipo de musica
	 */
	private Musica(String titulo, String tipo)
	{
		this.tituloMusica = titulo;
		this.setFormatoMusica(tipo);
	}
	
	/**
	 * Metodo que pide los datos del disco y los almacena.
	 *
	 * @param tablaMusica Almacena los discos creados.
	 */
	public static void crearMusica(HashMap<Integer, Musica> tablaMusica)
	{
		String titulo;
		String formato;
		
		Scanner entrada = new Scanner(System.in);

		System.out.print("Titulo del disco: ");
		titulo = entrada.nextLine();
		do
		{
			System.out.print("Formato del disco (CD/vinilo/casete): ");
			formato = entrada.nextLine().toLowerCase();
			if (!verificaFormato(formato))
				System.out.println("No se ha introducido un formato valido.");
		} while (!verificaFormato(formato));
		Musica musica = new Musica (titulo, formato);
		numMusica = numMusica + 1;
		tablaMusica.put(numMusica, musica);  // Damos de alta el disco		
		System.out.println("Musica creada con exito");
	}
	
	/**
	 * Lista la musica almacenada.
	 *
	 * @param tablaMusica Almacena los discos creados.
	 */
	public static void listarMusica(HashMap<Integer, Musica> tablaMusica)
	{
		String titulo = null;
		String formato = null;
		
		if (tablaMusica.isEmpty())  // No hay discos en el sistema
		{
			System.out.println("No existen discos dados de alta");
		}
		else
		{
			Set<Entry<Integer,Musica>> s = tablaMusica.entrySet();
			Iterator<Entry<Integer, Musica>> it=s.iterator();
			Entry<Integer, Musica> m = null;
			
			while (it.hasNext()) // Recorremos los discos en el sistema
			{
				m =it.next();
	            titulo=(String)m.getValue().tituloMusica;
	            formato=(String)m.getValue().formatoMusica;
	            System.out.println("Titulo: "+titulo+
	            				   " Formato: "+formato);	
			}
		}
	}
	
	/**
	 * Elimina un disco dado.
	 *
	 * @param tablaMusica Almacena los discos creados.
	 * @param indice Codigo del disco a eliminar
	 */
	public static void eliminarMusica(HashMap<Integer, Musica> tablaMusica, int indice)
	{
		if (tablaMusica.isEmpty()) // No hay discos en el sistema
		{
			System.out.println("No existen discos dados de alta para borrar");
		}
		else
		{
			String respuesta;
			Scanner entrada = null;
			
			if(Musica.buscaMusica(indice, Tienda.tablaMusica)) // Buscamos el disco en el sistema
			{
				System.out.println("Se va a proceder al borrado del disco: ");
	            System.out.println("Titulo: "+tablaMusica.get(indice).tituloMusica+
     				   			   " Formato: "+tablaMusica.get(indice).formatoMusica);	
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
					tablaMusica.remove(indice);
					System.out.println("Disco eliminado correctamente");
					numMusica = numMusica - 1;
	            }
	            if (respuesta.equals("no"))
	            {
	            	System.out.println("El disco no fue borrado del sistema.");
	            }				
			}
			else
				System.out.println("No existe disco con ese indice.");
		}
	}
	
	/**
	 * Elimina un disco que sera pedido por pantalla.
	 *
	 * @param tablaMusica Almacena los discos creados.
	 */
	public static void eliminarMusica(HashMap<Integer, Musica> tablaMusica)
	{
		if (tablaMusica.isEmpty()) // No hay discos en el sistema
		{
			System.out.println("No existen discos dados de alta para borrar");
		}
		else
		{
			int indice;
			Scanner entrada = null;
			boolean indiceCorrecto = false;
			String respuesta;
			
			do
			{
				System.out.print("Indice de disco a borrar: ");
				try
				{
					entrada = new Scanner(System.in);
					indice = entrada.nextInt();
					if(Musica.buscaMusica(indice, Tienda.tablaMusica))  // Buscamos si el disco a borrar existe en el sistema
					{
						System.out.println("Se va a proceder al borrado del disco: ");
			            System.out.println("Titulo: "+tablaMusica.get(indice).tituloMusica+
		     				   			   " Formato: "+tablaMusica.get(indice).formatoMusica);	
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
							tablaMusica.remove(indice);
							System.out.println("Disco eliminado correctamente");
							numMusica = numMusica - 1;
							indiceCorrecto = true;	
			            }
			            if (respuesta.equals("no"))
			            {
			            	System.out.println("El disco no fue borrado del sistema.");
			            	indiceCorrecto = true;
			            }		
					}
					else
						System.out.println("No existe disco con ese indice.");
				}
				catch (InputMismatchException ime)  // Se introduce un dato no numerico
				{
					System.out.println("La entrada del indice ha sido erronea, introduzca una correcta.");
				}
			}
			while (!indiceCorrecto);
		}
	}

	/**
	 * Busca musica.
	 *
	 * @param Codigo de disco
	 * @param tablaVentas Almacena los discos
	 * @return true, si encuentra el disco en la tabla tablaMusica
	 */
	public static boolean buscaMusica(int indice, HashMap<Integer, Musica> tablaMusica)
	{
		boolean encontrado = false;
		int key;
		
		Set<Entry<Integer,Musica>> s = tablaMusica.entrySet();
		Iterator<Entry<Integer, Musica>> it=s.iterator();
		Entry<Integer, Musica> m = null;
		
		while (it.hasNext()) // Buscamos disco en el sistema
		{
			m =it.next();
			key=(Integer)m.getKey();
			if (indice == key)  // Encontrado
				encontrado = true;
		}
		return encontrado;
	}
	
	private static boolean verificaFormato(String formato)
	{
		if ((formato.equals("cd")) || (formato.equals("vinilo")) || (formato.equals("casete")))
		{
			return true;
		}
		else
			return false;
	}
	
	// GETTERS Y SETTERS
	/**
	 * Devuelve el numero de discos.
	 *
	 * @return El numero de discos almacenados
	 */
	public static int getNumMusica()
	{
		return numMusica;
	}

	/**
	 * Informa el numero de discos.
	 *
	 * @param numClientes Numero de discos almacenados
	 */
	public static void setNumMusica(int numClientes)
	{
		Musica.numMusica = numClientes;
	}
	
	/**
	 * GDevuelve el titulo del disco.
	 *
	 * @return El titulo del disco.
	 */
	public String getTituloMusica()
	{
		return tituloMusica;
	}

	/**
	 * Informa el titulo del disco.
	 *
	 * @param tituloMusica Titulo del disco.
	 */
	public void setTituloMusica(String tituloMusica)
	{
		this.tituloMusica = tituloMusica;
	}

	/**
	 * Devuelve el tipo de musica del disco.
	 *
	 * @return El tipo de musica del disco.
	 */
	public String getTipoMusica()
	{
		return formatoMusica;
	}

	/**
	 * Informa del tipo de musica del disco
	 *
	 * @param tipoMusica El tipo de musica del disco.
	 */
	public void setFormatoMusica(String tipoMusica)
	{
		this.formatoMusica = tipoMusica;
	}
}
