package burgers;

/**
 * Clase Accion, destinada a almacenar las acciones de cada Paso.
 * @author Daniel Campos Tello
 */
public class Accion {

  private String nombre;

  /**
   * Constructor completo de una Accion.
   * @param nombre
   */
  public Accion(String nombre) {
    setNombre(nombre);
  }

  /**
   * Metodo que devuelve el nombre de una Accion.
   * @return nombre
   */
  public String getNombre() {
    String paramNombre = new String(nombre);
    return paramNombre;
  }

  /**
   * Metodo que permite cambiar el nombre de una Accion.
   * @param nombre
   */
  public void setNombre(String nombre) {
    String paramNombre = new String(nombre);
    this.nombre = paramNombre;
  }

  /**
   * Metodo que devuelve los parametros de una Accion.
   * @return
   */
  @Override
  public String toString() {
    return nombre;
  }


  
}
