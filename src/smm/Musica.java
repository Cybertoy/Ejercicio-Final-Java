package smm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Musica
{
	private String tituloMusica;
	private String tipoMusica;
	
	private static int numMusica = 0;
	
	private Musica(String titulo, String tipo)
	{
		this.tituloMusica = titulo;
		this.setTipoMusica(tipo);
	}
	
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
	
	public static void listarMusica(HashMap<Integer, Musica> tablaMusica)
	{
		if (tablaMusica.isEmpty())
		{
			System.out.println("No existen clientes dados de alta");
		}
		else
		{
			Set<Entry<Integer,Musica>> s = tablaMusica.entrySet();
			Iterator<Entry<Integer, Musica>> it=s.iterator();
			
			while (it.hasNext())
			{
				Entry<Integer, Musica> m =it.next();
	            int key=(Integer)m.getKey();
	            String value=(String)m.getValue().tituloMusica;
	            System.out.println("Key :"+key+" value :"+value);	
			}
		}
	}
	
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
	
	public String getTituloMusica()
	{
		return tituloMusica;
	}

	public void setTituloMusica(String tituloMusica)
	{
		this.tituloMusica = tituloMusica;
	}

	public String getTipoMusica()
	{
		return tipoMusica;
	}

	public void setTipoMusica(String tipoMusica)
	{
		this.tipoMusica = tipoMusica;
	}

}
