package burgers;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * Clase Restaurante, encargada de
 * almacenar las Recetas en un Mapa.
 * Ademas se han desarrollado una serie
 * de metodos para trabajar desde
 * ficheros.
 *
 * @author Daniel Campos Tello
 */
public class Restaurante {
  
  private TreeMap<Integer, Receta> recetas;
  private File fichero;
  private HashMap<String,Ingrediente> ingredientesExistentes;

  /**
   * Constructor de la clase
   * Restaurante.
   */
  public Restaurante() {
    recetas = new TreeMap<>();
    fichero = new File("recetas.txt");
    ingredientesExistentes = new HashMap<>();
    
  }

  /**
   * Metodo destinado a crear Recetas.
   * Cuenta con entrada construida con
   * JOptionPane.
   */
  public void crearReceta() {
    //declaramos variables
    String precioIngrediente = "";
    String nroIngredientes = "";
    int limite = 0;
    //creamos un jframe
    JFrame jf = new JFrame();
    //lo dejamos siempre encima, para evitar hacer alt+tab
    jf.setAlwaysOnTop(true);
    //preguntamos por nombre receta
    String nombreReceta = JOptionPane.showInputDialog(jf, "Introduce el nombre de la receta: ");
    //creamos una receta nueva
    Receta r = new Receta(nombreReceta);
    //preguntamos por el numero de ingredientes
    boolean confirmaNroIngredientes = false;
    while (confirmaNroIngredientes != true) {
      try {
        nroIngredientes = JOptionPane.showInputDialog(jf, "Introduce el numero de ingrediente: ");
        limite = Integer.parseInt(nroIngredientes);
        confirmaNroIngredientes = true;
      } catch (NumberFormatException e) {
        confirmaNroIngredientes = false;
        System.out.println("Nro Ingredientes no valido.");
      }
    }
    //array con ingredientes para pasarselo al siguiente paso, el menu desplegable
    Ingrediente[] listaIngredientes = new Ingrediente[limite];
    //bucle para preguntar por nro total ingredientes
    for (int i = 0; i < limite; i++) {
      //creamos ingredientes y añadimos al array
      Ingrediente ing = new Ingrediente();
      String nombreIngrediente = JOptionPane.showInputDialog(jf, "Introduce el nombre del ingrediente Nº"+ (i+1) +": ");
      //comprobamos si existe el ingrediente anteriormente.
      if (comprobarExistenciaIngrediente(nombreIngrediente) == false) {
        //si no existe en el mapa lo creamos.
        boolean confirmaPrecioIngrediente = false;
        while (confirmaPrecioIngrediente != true) {
          try {
            precioIngrediente = JOptionPane.showInputDialog(jf, "Introduce el precio de " + nombreIngrediente + ": ");
            precioIngrediente = precioIngrediente.replaceAll(",", ".");
            confirmaPrecioIngrediente = true;
          } catch (NumberFormatException e) {
            confirmaPrecioIngrediente = false;
            System.out.println("El precio no es valido");
          }
        }
        ing = new Ingrediente(nombreIngrediente, Double.parseDouble(precioIngrediente));
        //añadimos el ingrediente nuevo a la coleccion de ingredientes
        ingredientesExistentes.put(nombreIngrediente, ing);
      } else {
        //si el ingrediente existe lo usaremos para la creacion de la receta.
        ing = ingredientesExistentes.get(nombreIngrediente);
        JOptionPane.showMessageDialog(null, "Recopilando informacion del ingrediente: " + nombreIngrediente, nombreIngrediente + " ya existente", JOptionPane.WARNING_MESSAGE);
      }
      
      listaIngredientes[i] = ing;
    }
    //bucle para preguntar por los pasos
    boolean confirmacion = true;
    do {
      int opcion = JOptionPane.showConfirmDialog(jf, "Quieres añadir mas pasos?");
      System.out.println(opcion);
      if (opcion == 1) {
        //para salir del bucle
        confirmacion = false;
      } else {
        //creamos el siguiente paso del menu con la lista de ingredientes
        Object seleccionIngrediente = JOptionPane.showInputDialog(
                jf,
                "Lista ingredientes",
                "Creacion de pasos",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listaIngredientes,
                null
        );
        String p = JOptionPane.showInputDialog(jf, "Accion a realizar: ");
        String t = "";
        boolean confirmaTiempoPaso = false;
        while (confirmaTiempoPaso != true) {
          try {
            t = JOptionPane.showInputDialog(jf, "Introduce un tiempo estimado: ");
            //reemplazamos "," por "."
            t = t.replaceAll(",", ".");
            //En caso de que el tiempo este vacio lo sustituimos por 0
            if (t.isBlank() || t.isEmpty()) {
              t = "0";
            }
            confirmaTiempoPaso = true;
          } catch (NumberFormatException e) {
            System.out.println("Eso no es un numero valido.");
            confirmaTiempoPaso = false;
          }
        }
        String descripcion = JOptionPane.showInputDialog(jf, "Describe el paso con detalle: ");
        if (descripcion.isBlank() || descripcion.isEmpty()) {
          descripcion = "Sin descripcion";
        }
        Accion accion = new Accion(p);
        r.addPaso(accion, (Ingrediente) seleccionIngrediente, Double.parseDouble(t), descripcion);
      }
    } while (confirmacion != false);
    //se añade la receta a la lista | esto ayuda a la hora de editar una receta.
    addReceta(r);
    System.out.println("receta creada.");
  }

  /**
   * Metodo destinado para añadir una
   * Receta al Mapa de Recetas.
   *
   * @param r se le debe pasar como
   * parametro una Receta.
   */
  public void addReceta(Receta r) {
    recetas.put(recetas.size() + 1, r);
  }

  /**
   * Metodo para eliminar una Receta
   * almacenada en el mapa.
   *
   * @param n se le debe pasar la
   * posicion en el mapa de la Receta a
   * borrar.
   */
  public void delReceta(int n) {
    recetas.remove(n);
    
  }

  /**
   * Metodo encargado de recorrer el
   * mapa e imprimir su contenido.
   */
  public void imprimirRecetas() {
    System.out.println("Listado de recetas: ");
    System.out.println("_______________________________________________________");
    System.out.printf("%s %20s %20s\n", "Nº", "Nombre", "Precio");
    System.out.println("_______________________________________________________");
    for (Integer num : recetas.keySet()) {
      Receta r = recetas.get(num);
      System.out.printf("\n%s %21s %20s ", num, r.getNombre(), r.precioTotal() + "€");
      System.out.printf("\n%s %10s", "Ingredientes:", r.imprimirIngredientes());
      System.out.println("\n_______________________________________________________");
    }
  }

  /**
   * Metodo encargado de cocinar la
   * Receta.
   *
   * @param n se le debe pasar la
   * posicion en la mapa de la Receta a
   * cocinar.
   */
  public void cocinarReceta(int n) {
    System.out.println("Preparando receta de: " + recetas.get(n).getNombre() + ".");
    Receta r = recetas.get(n);
    r.elaboracion(r);
    System.out.println("Tiempo estimado: " + r.calcularTiempo() + " min");
  }

  /**
   * Metodo creado para exportar
   * recetas, se le pasa un parametro
   * bool para crear nuevo fichero.
   *
   * @param guardar true para crear
   * nuevo fichero
   */
  public void exportarRecetas(boolean guardar) {
    String nombreFichero = "recetas.txt";
    /*metodo para exportar recetas a .txt*/
    if (guardar == true) {
      //creamos un jframe
      JFrame jf = new JFrame();
      //lo dejamos siempre encima, para evitar tener que hacer alt+tab
      jf.setAlwaysOnTop(true);
      nombreFichero = JOptionPane.showInputDialog(jf, "Introduce el nombre del fichero.");
    }
    try {
      //declaramos un objeto que a su vez crea un fichero
      FileWriter escritura = new FileWriter(new File(nombreFichero));
      //recorremos nueestro mapa de recetas
      for (Integer num : recetas.keySet()) {
        Receta r = recetas.get(num);
        //preparamos la cadena a almacenar
        String receta = num + ";" + r.getNombre() + ";" + r.precioTotal() + ";" + r.imprimirPasosExportar();
        //declaramos un objeto de dicha clase y el anterior
        PrintWriter pw = new PrintWriter(escritura);
        //imprimimos en el fichero la cadena creada
        pw.println(receta);
      }
      escritura.close();
    } catch (IOException e) {
      System.out.println("No se encuentra el fichero");
    } finally {
      
    }
    System.out.println("Fichero exportado, nombre del fichero: " + nombreFichero);
  }
  
  /**
   * Metodo creado para importar
   * recetas, nos permite tener una bbdd
   * en texto plano.
   */
  public void importarRecetas() {
    try {
      //creamos un objeto de dicho tipo y le pasamos el fichero
      FileReader entrada = new FileReader(fichero);
      //declaramos un buffer
      BufferedReader mibuffer = new BufferedReader(entrada);
      String linea = "";
      while (linea != null) {
        try {
          //vamos recogiendo cada linea del fichero
          linea = mibuffer.readLine();
          //se separan los elementos
          String[] datosReceta = linea.split(";");
          String nombreReceta = datosReceta[1];
          String precioReceta = datosReceta[2];
          String pasosReceta = datosReceta[3];
          //se tratan los pasos
          String[] arrPasos = pasosReceta.split("\\|");
          //se crea un conjunto de pasos
          LinkedList<Paso> conjuntoPasos = new LinkedList<>();
          for (String arrP : arrPasos) {
            //se separan los pasos de los ingredientes,...
            String[] splitPasos = arrP.split("-");
            Accion accion = new Accion(splitPasos[0]);
            String descripcion = splitPasos[4];
            String nombreIngrediente = splitPasos[1];
            double precioIngrediente = Double.parseDouble(splitPasos[2]);
            Ingrediente ingrediente = new Ingrediente(nombreIngrediente, precioIngrediente);
            //añadimos los ingredientes a la coleccion de ingredientesExistentes.
            ingredientesExistentes.put(nombreIngrediente, ingrediente);
            double tiempoPorPaso = Double.parseDouble(splitPasos[3]);
            Paso p = new Paso(accion, ingrediente, tiempoPorPaso, descripcion);
            conjuntoPasos.add(p);
          }
          //se crea la receta y se añade, estamos tratando strings asique debemos castear el doble
          Receta r = new Receta(nombreReceta, conjuntoPasos, Double.parseDouble(precioReceta));
          addReceta(r);
        } catch (Exception e) {
        }
      }
      entrada.close();
    } catch (IOException e) {
      System.out.println("Fallo al leer el fichero");
    }
    System.out.println("Importacion de recetas completada satisfactoriamente.");
  }
  
  /**
   * Metodo para comprobar si ya existe un ingrediente en alguna de las recetas
   * de ser asi, usara ese ingrediente a la hora de crear una nueva.
   * @param nombreIngrediente
   * @return
   */
  public boolean comprobarExistenciaIngrediente(String nombreIngrediente) {
    boolean confirmacion = false;
      if (ingredientesExistentes.containsKey(nombreIngrediente)) {
        confirmacion = true;
      }
    return confirmacion;
  }
 
}
