package smm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

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
	
	/** Tipo de musica. */
	private String tipoMusica;
	
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
		this.setTipoMusica(tipo);
	}
	
	/**
	 * Metodo que pide los datos del disco y los almacena.
	 *
	 * @param tablaMusica Almacena los discos creados.
	 */
	public static void crearMusica(HashMap<Integer, Musica> tablaMusica)
	{
		String titulo;
		String tipo;
		
		Scanner entrada = new Scanner(System.in);

		System.out.print("Titulo del disco: ");
		titulo = entrada.nextLine();
		System.out.print("Formato del disco: ");
		tipo = entrada.nextLine();
		
		Musica musica = new Musica (titulo, tipo);
		numMusica = numMusica + 1;
		tablaMusica.put(numMusica, musica);
		
		System.out.println("Musica creada con exito");
	}
	
	/**
	 * Lista la musica almacenada.
	 *
	 * @param tablaMusica Almacena los discos creados.
	 */
	public static void listarMusica(HashMap<Integer, Musica> tablaMusica)
	{
		if (tablaMusica.isEmpty())
		{
			System.out.println("No existen clientes dados de alta");
		}
		else
		{
			Set<Entry<Integer,Musica>> s = tablaMusica.entrySet();
			Entry<Integer, Musica> m = null;
			Iterator<Entry<Integer, Musica>> it=s.iterator();
			
			while (it.hasNext())
			{
				m =it.next();
	            int key=(Integer)m.getKey();
	            String value=(String)m.getValue().tituloMusica;
	            System.out.println("Key :"+key+" value :"+value);	
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
		if (tablaMusica.isEmpty())
		{
			System.out.println("No existen musica dados de alta para borrar");
		}
		else
		{
			tablaMusica.remove(indice);
			System.out.println("Musica eliminado correctamente");
		}
	}
	
	/**
	 * Elimina un disco que sera pedido por pantalla.
	 *
	 * @param tablaMusica Almacena los discos creados.
	 */
	public static void eliminarMusica(HashMap<Integer, Musica> tablaMusica)
	{
		if (tablaMusica.isEmpty())
		{
			System.out.println("No existen musica dados de alta para borrar");
		}
		else
		{
			int indice;
			Scanner entrada = new Scanner(System.in);
			System.out.print("Indice de musica a borrar: ");
			indice = entrada.nextInt();
			tablaMusica.remove(indice);
			System.out.println("Musica eliminado correctamente");
		}
	}
	
	// GETTERS Y SETTERS
	
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
		return tipoMusica;
	}

	/**
	 * Informa del tipo de musica del disco
	 *
	 * @param tipoMusica El tipo de musica del disco.
	 */
	public void setTipoMusica(String tipoMusica)
	{
		this.tipoMusica = tipoMusica;
	}

}
