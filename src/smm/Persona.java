package smm;

public class Persona
{
	private String nombre = "";
	private String apellidos = "";
	
	public Persona(String nombre, String apellidos)
	{
		nombre = this.nombre;
		apellidos = this.apellidos;
	}

	/**
	 * Devuelve el nombre del cliente.
	 *
	 * @return EL nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Informa el nombre del cliente.
	 *
	 * @param nombreCliente El nombre del cliente
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el apellido del cliente.
	 *
	 * @return El apellido del cliente
	 */
	public String getApellido() {
		return apellidos;
	}

	/**
	 * Informa el apellido del cliente.
	 *
	 * @param apellidoCliente El apellido del cliente
	 */
	public void setApellido(String apellidos) {
		this.apellidos = apellidos;
	}

}
