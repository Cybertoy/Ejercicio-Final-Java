package smm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Clase de la ejecucion principal del proceso.
 *
 * @version 1.0, 25/05/2012
 * @author Santiago Martinez Martinez
 */
public class Main
{
	
	/** Tabla que almacena los clientes. */
	static HashMap<Integer, Clientes> tablaClientes = new HashMap<Integer, Clientes>();
	
	/** Tabla que almacena los discos. */
	static HashMap<Integer, Musica> tablaMusica = new HashMap<Integer, Musica>();
	
	/** Tabla que almacena las ventas. */
	static HashMap<Integer, Ventas> tablaVentas = new HashMap<Integer, Ventas>();
	
	static FileWriter ficheroVentas;
	
	/**
	 * Metodo principal del proceso.
	 * 
	 * En nuestro proceso no precisameremos de la introduccion de ningun argumento.
	 *
	 * @param args Argumentos del proceso.
	 */
	public static void main(String[] args)
	{
		Scanner entrada = new Scanner(System.in);
		String instruccion = "";
		String[] arrayComando = new String[3];
		boolean flag; 
		
		flag = false;
		
		if(!compruebaFichero())
		{
			// El fichero no existe lo creamos			
			try 
			{
				System.out.println("Se crea fichero de ventas");
				ficheroVentas = new FileWriter("ventas.txt");
				System.out.println("El fichero ventas.txt se ha creado con éxito");
				ficheroVentas.flush();
			} 
			catch (IOException ioe) 
			{
				System.out.println("Se ha producido un error al procesar el fichero de ventas");
			}
		}
		else
		{
			// El fichero existe, lo cargamos
			try
			{
				ficheroVentas = new FileWriter("ventas.txt");
			}
			catch (IOException ioe)
			{
				System.out.println("Se ha producido un error al procesar el fichero de ventas");
			}
		}
		
		do
		{
			do
			{
				System.out.print("Esperando instrucción: ");
				instruccion = entrada.nextLine();
				
				StringTokenizer orden = new StringTokenizer(instruccion);
				if (orden.countTokens() > 0 && orden.countTokens() <= 3)
				{
					for (int i=0; orden.hasMoreTokens(); i++)
						arrayComando[i]=orden.nextToken().toLowerCase();
					
					flag = true;
				}
				else
				{
					// Implementar que acciones se pueden hacer con el programa.
					System.out.println("Explicar programa");
				}
			}
			while (!flag);

			interpretaComando(arrayComando);
		}
		while (!(arrayComando[0].equals("cerrar")));
		
		try
		{
			ficheroVentas.close();
		}
		catch (IOException ioe)
		{
			System.out.println("Se ha producido un error al procesar el fichero de ventas");
		}
		catch (NullPointerException npe)
		{
			System.out.println("Se ha producido un error al procesar el fichero de ventas");
		}
		System.out.println("Adios!!!!!!!");
	}
	

	/**
	 * Verifica la existencia del fichero de ventas. 
	 * Si no existe se crea.
	 * 
	 */
	private static boolean compruebaFichero()
	{
		File ficheroVentas = new File("ventas.txt");
		if (ficheroVentas.exists())
		{
			System.out.println("El fichero de ventas existe");
			return true;
		} 
		else 
		{
			System.out.println("El fichero de ventas no existe");
			return false;
		}
	}
	
	/**
	 * Interpreta el comando escrito por el usuario.
	 *
	 * @param comando Array con el comando a ejecutar por el proceso.
	 */
	private static void interpretaComando(String[] comando)
	{
		if (comando[0].equals("crear"))
		{
			if (comando[1].equals("cliente"))
			{
				Clientes.crearCliente(tablaClientes);
			}
			if (comando[1].equals("musica"))
			{
				Musica.crearMusica(tablaMusica);
			}

		}
		if (comando[0].equals("listar"))
		{
			if (comando[1].equals("cliente"))
			{
				Clientes.listarClientes(tablaClientes);
			}
			if (comando[1].equals("musica"))
			{
				Musica.listarMusica(tablaMusica);
			}

		}
		if (comando[0].equals("eliminar"))
		{
			if (comando[1].equals("cliente"))
			{
				if (comando[2] != null)
					Clientes.eliminarCliente(tablaClientes, Integer.parseInt(comando[2]));
				else
					Clientes.eliminarCliente(tablaClientes);
			}
			if (comando[1].equals("musica"))
			{
				if (comando[2] != null)
					Musica.eliminarMusica(tablaMusica, Integer.parseInt(comando[2]));
				else
					Musica.eliminarMusica(tablaMusica);
			}
		}
	}
}
