package burgers;

/**
 * Clase Paso, destinada a almacenar la información de los pasos de una Receta.
 * @author Daniel Campos Tello
 */
public class Paso {

  private Accion accion;
  private Ingrediente ingrediente;
  private double tiempo;
  private String descripcion;

  /**
   * Constructor vacio de un Paso.
   */
  public Paso() {
  
  }
  
  /**
   * Constructor completo de la clase Paso.
   * @param accion
   * @param ingrediente
   * @param tiempo
   * @param descripcion para describir mas extensamente el Paso a realizar.
   */
  public Paso(Accion accion, Ingrediente ingrediente, double tiempo, String descripcion) {
    this.accion = accion;
    this.ingrediente = ingrediente;
    this.tiempo = tiempo;
    this.descripcion = descripcion;
  }

  /**
   * metodo que devuelve una Accion
   * @return
   */
  public Accion getAccion() {
    return accion;
  }

  /**
   * metodo para cambiar una accion
   * @param accion
   */
  public void setAccion(Accion accion) {
    this.accion = accion;
  }

  /**
   * Metodo que devuelve el Ingrediente de un Paso.
   * @return Ingrediente.
   */
  public Ingrediente getIngrediente() {
    return ingrediente;
  }

  /**
   * Metodo que permite cambiar el Ingrediente de un Paso.
   * @param ingrediente
   */
  public void setIngrediente(Ingrediente ingrediente) {
    this.ingrediente = ingrediente;
  }

  /**
   * metodo que deevuelve una Descripcion de un Paso.
   * @return
   */
  public String getDescripcion() {
    return descripcion;
  }

  /** 
   * metodo para cambiar una Descripcion de un Paso.
   * @param descripcion
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  /**
   * Metodo que devuelve el tiempo de un Paso.
   * @return tiempo
   */
  public double getTiempo() {
    return tiempo;
  }

  /**
   * Metodo que permite cambiar el tiempo de un Paso.
   * @param tiempo
   */
  public void setTiempo(double tiempo) {
    this.tiempo = tiempo;
  }
  
  /**
   * Metodo para imprimir un paso.
   * @return
   */
  public String imprimirPaso() {
    return getAccion() + " " + getIngrediente().getNombre() + ": " + getDescripcion();
  }
  
  /**
   * Metodo toString
   * @return
   */
  @Override
  public String toString() {
    return "Paso{" + "accion=" + accion + ", ingrediente=" + ingrediente + ", tiempo=" + tiempo + ", descripcion=" + descripcion + '}';
  }
  
 

}
