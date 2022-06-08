package burgers;

import java.util.Scanner;

/**
 * Main de la aplicacion.
 * @author daniel campos tello
 */
public class Burgers {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Restaurante restaurante = new Restaurante();
    
    restaurante.importarRecetas();
    
    int opcionMenu = 0;
    int seleccion = 0;
    boolean salir = false;
    do {
      salir = true;
      try {
        System.out.println("Elige una opcion: ");
        System.out.println("1 - Listar recetas | 2 - Cocinar receta | 3 - Crear receta | 4 - Borrar receta | 5 - Exportar recetas a otro fichero | 6 - Guardar y Salir del programa");
        Scanner sc = new Scanner(System.in);
        opcionMenu = sc.nextInt();
        switch (opcionMenu) {
          case 1:
            restaurante.imprimirRecetas();
            break;
          case 2:
            System.out.println("Dime el nº de receta que quieres cocinar: ");
            seleccion = sc.nextInt();
            restaurante.cocinarReceta(seleccion);
            break;
          case 3:
            restaurante.crearReceta();
            break;
          case 4:
            System.out.println("Dime el nº de receta que quieres borrar: ");
            seleccion = sc.nextInt();
            restaurante.delReceta(seleccion);
            System.out.println("receta borrada.");
            break;
          case 5:
              restaurante.exportarRecetas(true);
              break;
          case 6:
            System.out.println("Guardando cambios...");
            restaurante.exportarRecetas(false);
            System.out.println("Saliendo del programa...");
            sc.close();
            //para cerrar el programa definitivamente, dado que joption pane lo matiene funcionando.
            System.exit(0);
            break;
          default:
            System.out.println("Eso no es una opcion.");
            break;
        }

      } catch (Exception e) {
        System.out.println("Opcion no valida.");
        salir = false;
      }
    } while (opcionMenu != 6 || salir == false);
  }
  
}
