package burgers;

import java.util.Objects;

/**
 * clase Ingrediente, almacena la información general de un Ingrediente.
 * @author Daniel Campos Tello
 */
public class Ingrediente {

  private String nombre;
  private double precio;

  /**
   * Constructor completo de Ingrediente.
   * @param nombre nombre del ingrediente.
   * @param precio precio unitario del ingrediente.
   */
  public Ingrediente(String nombre, double precio) {
    setNombre(nombre);
    setPrecio(precio);
  }

  /**
   *
   */
  public Ingrediente() {
  }
  
  /**
   * metodo para devolver el nombre del ingrediente.
   * @return nombre
   */
  public String getNombre() {
    String paramNombre = new String(nombre);
    return paramNombre;
  }

  /**
   * metodo para cambiar el nombre de un ingrediente.
   * @param nombre
   */
  public void setNombre(String nombre) {
    String paramNombre = new String(nombre);
    this.nombre = paramNombre;
  }

  /**
   * metodo para devolver el precio de un ingrediente.
   * @return precio
   */
  public double getPrecio() {
    return precio;
  }

  /**
   * metodo para cambiar el precio de un ingrediente.
   * @param precio
   */
  public void setPrecio(double precio) {
    this.precio = precio;
  }

  
  /**
   * metodo que devuelve los atributos de un ingrediente.
   * @return
   */
  
  @Override
  public String toString() {
    return "Ingrediente{" + "nombre=" + nombre + ", precio=" + precio + '}';
  }

  /**
   * Metodo hashCode
   * @return
   */
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 71 * hash + Objects.hashCode(this.nombre);
    hash = 71 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
    return hash;
  }

  /**
   * metodo equals
   * @param obj
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Ingrediente other = (Ingrediente) obj;
    if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
      return false;
    }
    if (!Objects.equals(this.nombre, other.nombre)) {
      return false;
    }
    return true;
  }

  

  
}
