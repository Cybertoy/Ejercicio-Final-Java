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
public class Tienda
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
		String[] arrayComando = null;
		boolean flag; 
		
		flag = false;
		
		if(!compruebaFichero())
		{
			// El fichero no existe lo creamos			
			try 
			{
				System.out.println("Se crea el fichero de ventas");
				ficheroVentas = new FileWriter("ventas.txt");
				System.out.println("El fichero ventas.txt se ha creado con exito");
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
			System.out.println("Se carga el fichero de ventas");
			Ventas.vuelcaFicheroAVentas(tablaVentas);
			try
			{
				ficheroVentas = new FileWriter("ventas.txt");
				ficheroVentas.flush();
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
				arrayComando = new String[3];
				System.out.print("Esperando instrucción: ");
				instruccion = entrada.nextLine();
				
				StringTokenizer orden = new StringTokenizer(instruccion);
				if ((orden.countTokens() > 1 && orden.countTokens() <= 3) || (instruccion.equals("cerrar")))
				{
					for (int i=0; orden.hasMoreTokens(); i++) // Dividimos la instruccion en partes
						arrayComando[i]=orden.nextToken().toLowerCase();
					
					flag = true;
				}
				else  // Mostramos los comandos correctos de ejecucion
				{
					instrucciones();
					flag = false;
				}
			}
			while (!flag);

			interpretaComando(arrayComando); // Verificamos si el comando es correcto
		}
		while (!(arrayComando[0].equals("cerrar"))); // cerranos el proceso
		
		try
		{
			Ventas.vuelcaVentasAFichero(tablaVentas, ficheroVentas); // Volcamos al fichero todas las ventas del sistema
			ficheroVentas.close();
		}
		catch (IOException ioe) // Error al cerrar el fichero
		{
			System.out.println("Se ha producido un error al procesar el fichero de ventas");
		}
		catch (NullPointerException npe) // Prevencionde errores
		{
			System.out.println("Se ha producido un error al procesar el fichero de ventas");
		}
		System.out.println("Proceso finalizado");
	}
	

	/**
	 * Muestra el set de instrucciones entendibles por el sistema 
	 * 
	 */
	private static void instrucciones()
	{
		System.out.println("Las instrucciones posibles del proceso son:");
		System.out.println("\tcrear cliente | musica | venta\t\t\tCrea una entidad del concepto elegido.");
		System.out.println("\tlistar cliente | musica | venta\t\t\tLista las entidades del concepto elegido.");
		System.out.println("\teliminar cliente | musica | venta [codigo]\tBorra una entidad del concepto elegido");
		System.out.println("\tcerrar\t\t\t\t\t\tCierra el proceso.");
	}
	
	/**
	 * Verifica la existencia del fichero de ventas. 
	 * Si no existe se crea.
	 * 
	 * @return true, si el fichero existe
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
		int comandoEncontrado = 0;
		
		if (comando[0].equals("crear"))
		{
			if (comando[1].equals("cliente"))
			{
				comandoEncontrado=1;
				Clientes.crearCliente(tablaClientes);
			}
			if (comando[1].equals("musica"))
			{
				comandoEncontrado=1;
				Musica.crearMusica(tablaMusica);
			}
			if (comando[1].equals("venta"))
			{
				comandoEncontrado=1;
				Ventas.crearVenta(tablaVentas);
			}
		}
		if (comando[0].equals("listar"))
		{
			if (comando[1].equals("cliente"))
			{
				comandoEncontrado=1;
				Clientes.listarClientes(tablaClientes);
			}
			if (comando[1].equals("musica"))
			{
				comandoEncontrado=1;
				Musica.listarMusica(tablaMusica);
			}
			if (comando[1].equals("venta"))
			{
				comandoEncontrado=1;
				Ventas.listarVenta(tablaVentas);
			}
		}
		if (comando[0].equals("eliminar"))
		{
			if (comando[1].equals("cliente"))
			{
				if (comando[2] != null)
				{
					comandoEncontrado=1;
					try
					{
						Clientes.eliminarCliente(tablaClientes, Integer.parseInt(comando[2]));
					}
					catch (NumberFormatException nfe)  // El codigo cliente no era numerico
					{
						instrucciones();
					}
				}
				else
				{
					comandoEncontrado=1;
					Clientes.eliminarCliente(tablaClientes);
				}
			}
			if (comando[1].equals("musica"))
			{
				if (comando[2] != null)
				{
					comandoEncontrado=1;
					try
					{
						Musica.eliminarMusica(tablaMusica, Integer.parseInt(comando[2]));
					}
					catch (NumberFormatException nfe) // El codigo del disco no era numerico
					{
						instrucciones();
					}
				}
				else
				{
					comandoEncontrado=1;
					Musica.eliminarMusica(tablaMusica);
				}
			}
			if (comando[1].equals("venta"))
			{
				if (comando[2] != null)
				{
					comandoEncontrado=1;
					try
					{
						Ventas.eliminarVenta(tablaVentas, Integer.parseInt(comando[2]));
					}
					catch (NumberFormatException nfe) // El codigo de venta no era numerico
					{
						instrucciones();
					}
				}
				else
				{
					comandoEncontrado=1;
					Ventas.eliminarVenta(tablaVentas);
				}
			}
		}
		if (comando[0].equals("cerrar"))
			comandoEncontrado = 1;
		
		if (comandoEncontrado == 0)  // El comando no ha sido reconocido, mostramos el set de instrucciones
			instrucciones();
	}
}
