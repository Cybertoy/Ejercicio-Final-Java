package smm;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Ventas
{
	
	private int codCliente;
	private int codMusica;
	private int codVenta;
	
	public static int numVentas;
	
	public Ventas(int codigoCliente, int codigoMusica, int codigoVenta)
	{
		this.codCliente = codigoCliente;
		this.codMusica = codigoMusica;
		this.codVenta = codigoVenta;
	}
	
	public static void crearVenta(HashMap<Integer, Ventas> tablaVentas)
	{
		int idCliente;
		int idMusica;
		
		Scanner entrada = new Scanner(System.in);
			
		System.out.print("Codigo cliente al que vender: ");
		idCliente = entrada.nextInt();
		
		System.out.print("Codigo de disco a vender: ");
		idMusica = entrada.nextInt();
		
		Ventas venta = new Ventas (idCliente, idMusica, numVentas);
		numVentas = numVentas + 1;
		tablaVentas.put(numVentas, venta);
		
		System.out.println("Venta creada con exito");
	}
	
	public static void listarVenta(HashMap<Integer, Ventas> tablaVentas)
	{
		if (tablaVentas.isEmpty())
		{
			System.out.println("No hay ventas en el registro.");
		}
		else
		{
			Set<Entry<Integer,Ventas>> s = tablaVentas.entrySet();
			Iterator<Entry<Integer, Ventas>> it=s.iterator();
			
			while (it.hasNext())
			{
				Entry<Integer, Ventas> m =it.next();
	            int key=(Integer)m.getKey();
	            int value=(int)m.getValue().codCliente;
	            int value2=(int)m.getValue().codMusica;
	            int value3=(int)m.getValue().codVenta;
	            System.out.println("Key: "+key+" codigoCliente: "+value+" codigoMusica: "+value2+" codigoVenta: "+value3);	
			}
		}
	}
	
	/**
	 * Elimina un venta dado.
	 *
	 * @param tablaventas Almacena los ventas creados.
	 * @param indice Codigo de venta a eliminar
	 */
	public static void eliminarVenta(HashMap<Integer, Ventas> tablaVentas, int indice)
	{
		if (tablaVentas.isEmpty())
		{
			System.out.println("No existen ventas dados de alta para borrar");
		}
		else
		{
			tablaVentas.remove(indice);
			System.out.println("venta eliminado correctamente");
		}
	}
	
	/**
	 * Elimina un venta que sera pedido por pantalla.
	 *
	 * @param tablaventas Almacena los ventas creados.
	 */
	public static void eliminarVenta(HashMap<Integer, Ventas> tablaVentas)
	{
		if (tablaVentas.isEmpty())
		{
			System.out.println("No existen ventas dados de alta para borrar");
		}
		else
		{
			int indice;
			Scanner entrada = new Scanner(System.in);
			System.out.print("Indice de venta a borrar: ");
			indice = entrada.nextInt();
			tablaVentas.remove(indice);
			System.out.println("venta eliminada correctamente");
		}
	}
	
	public static void vuelcaVentasAFichero(HashMap<Integer, Ventas> tablaVentas, FileWriter fichero)
	{
		PrintWriter pw = new PrintWriter(fichero);
		
		Set<Entry<Integer,Ventas>> s = tablaVentas.entrySet();
		Iterator<Entry<Integer, Ventas>> it=s.iterator();
		
		while (it.hasNext())
		{
			Entry<Integer, Ventas> m =it.next();
            int key=(Integer)m.getKey();
            int value=(int)m.getValue().codCliente;
            int value2=(int)m.getValue().codMusica;
            int value3=(int)m.getValue().codVenta;
            pw.println(value+";"+value2+";"+value3);
		}
	}
}
