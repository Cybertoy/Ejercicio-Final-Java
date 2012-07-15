package smm;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main
{
	static HashMap<Integer, Clientes> tablaClientes = new HashMap<Integer, Clientes>();
	static HashMap<Integer, Musica> tablaMusica = new HashMap<Integer, Musica>();
	
	public static void main(String[] args)
	{
		Scanner entrada = new Scanner(System.in);
		String instruccion = "";
		String[] arrayComando = new String[3];
		boolean flag; 
		
		flag = false;
		
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
		System.out.println("Adios!!!!!!!");
	}
	
	static void interpretaComando(String[] comando)
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
