package burgers;

import java.util.*;

/**
 * Clase Receta, compuesta de dos
 * colecciones, una lista para los
 * Ingredientes y un LinkedHashSet para
 * almacenar los Pasos.
 *
 * @author Daniel Campos Tello
 */
public class Receta {

  private String nombre;
  private LinkedList<Paso> pasos;
  private double precioTotal;

  /**
   * Constructor completo de la clase
   * Receta.
   *
   * @param nombre
   * @param pasos
   * @param precioTotal
   */
  public Receta(String nombre, LinkedList pasos, double precioTotal) {
    setNombre(nombre);
    this.pasos = pasos;
    this.precioTotal = precioTotal;
  }

  /**
   * Constructor con un parametro
   *
   * @param nombre
   */
  public Receta(String nombre) {
    setNombre(nombre);
    this.pasos = new LinkedList<>();
    this.precioTotal = precioTotal();
  }

  /**
   * constructor vacio de la clase
   * Receta.
   */
  public Receta() {
    setNombre("");
    this.pasos = new LinkedList<>();
    this.precioTotal = precioTotal();

  }

  /**
   * Metodo que devuelve el nombre de
   * una Receta.
   *
   * @return nombre
   */
  public String getNombre() {
    String paramNombre = new String(nombre);
    return paramNombre;
  }

  /**
   * Metodo para cambiar el nombre a una
   * Receta.
   *
   * @param nombre
   */
  public void setNombre(String nombre) {
    String paramNombre = new String(nombre);
    this.nombre = paramNombre;
  }

  /**
   * Metodo encargado de calcular el
   * precio total de una Receta.
   *
   * @return precioTotal
   */
  public double precioTotal() {
    precioTotal = 0;
    HashSet<Ingrediente> ingredientes = new HashSet<>();
    for (Paso p : pasos) {
      ingredientes.add(p.getIngrediente());
    }
    for (Ingrediente i : ingredientes) {
      precioTotal += i.getPrecio();
    }
    return redondeaPrecio(precioTotal);

  }

  /**
   * funcion que redondea el precio a dos decimales
   * @param precioTotal
   * @return
   */
  public double redondeaPrecio(double precioTotal) {
    return Math.round(this.precioTotal * 100.0) / 100.0;
  }

  /**
   * Metodo encargado de devolver los
   * Ingredientes de una Receta.
   *
   * @return cadena de ingredientes
   */
  public String imprimirIngredientes() {
    //creamos un set de strings para evitar ingredientes repetidos
    LinkedHashSet<String> nombreIngredientes = new LinkedHashSet<>();
    for (Paso p : pasos) {
      nombreIngredientes.add(p.getIngrediente().getNombre());
    }
    String s = "";
    String punto = ".";
    String coma = ",";
    String fin = "";
    //uso un iterador para a;adir coma o punto despues de cada ingrediente.
    Iterator<String> it = nombreIngredientes.iterator();
    while (it.hasNext()) {
      String i = it.next();
      if (it.hasNext()) {
        fin = coma;
      } else {
        fin = punto;
      }
      s = s.concat(i + fin);
    }
    return s;
  }

  /**
   * Este metodo es usado a la hora de
   * exportar las recetas al txt.
   *
   * @return cadena de pasos
   */
  public String imprimirPasosExportar() {
    String s = "";
    Iterator<Paso> it = pasos.iterator();
    while (it.hasNext()) {
      Paso p = it.next();
      s = s.concat(p.getAccion().getNombre() + "-" + p.getIngrediente().getNombre() + "-" + p.getIngrediente().getPrecio() + "-" + p.getTiempo() + "-" + p.getDescripcion() + "|");
    }
    return s;
  }

  /**
   * Metodo encargado de añadir un Paso
   * a una Receta, se creara un Paso
   * desde 0.
   *
   * @param a Accion
   * @param i Ingrediente
   * @param t tiempo
   * @param descripcion
   */
  public void addPaso(Accion a, Ingrediente i, double t, String descripcion) {
    Paso p = new Paso(a, i, t, descripcion);
    pasos.add(p);
  }

  /**
   * Metodo destinado a calcular el
   * tiempo necesario para cocinar una
   * Receta.
   *
   * @return tiempo total estimado de la
   * receta.
   */
  public double calcularTiempo() {
    double total = 0;
    for (Paso p : pasos) {
      total += p.getTiempo();
    }
    return total;
  }

  /**
   * Metodo encargado de mostrar los
   * pasos a seguir para cocinar una
   * Receta.
   *
   * @param r es la receta a cocinar
   *
   */
  public void elaboracion(Receta r) {
    for (int i = 0; i < pasos.size(); i++) {
      System.out.println("Paso " + (i + 1) + " -> " + pasos.get(i).imprimirPaso());
    }
  }

  /**
   * Metodo que sirve para devolver los
   * parametros de una Receta.
   *
   * @return
   */
  @Override
  public String toString() {
    return "Receta{" + "nombre=" + getNombre() + ", ingredientes=" + imprimirIngredientes() + ", precio=" + precioTotal() + '}';
  }

}
