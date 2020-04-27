/*
Continuando con el ejercicio anterior, añadiremos una nueva opción en el menú que será “transacciones”, y que nos llevará a un nuevo menú con tres acciones:
Actualización simple. Incluye en esta opción dos sentencias update de una de las tablas, en las que se le pida al usuario qué campo quiere actualizar de dicha tabla y el valor del mismo. ¿Se actualiza la tabla si falla la primera sentencia? ¿Y si falla la segunda se actualiza la primera?
Transacción_1. Incluye una transacción que se compone de tres sentencias de actualización sobre una tabla, aunque habrá una cuarta sentencia de actualización que no forma parte de la transacción. Realiza el control adecuado y contesta a las siguientes preguntas (incluye las preguntas y respuestas al inicio del programa como comentario). 
¿Se actualiza la tabla si falla la primera, segunda o tercera sentencia?
¿Y si se ejecuta correctamente las tres primeras sentencias que forman parte de la transacción y falla la última qué ocurre?
¿Qué ocurre si dejas el autocommit a false y ejecutas el apartado b y luego el a?

Transacción_2. Replica el apartado anterior en un nuevo método, pero incluyendo un savepoint a partir de la segunda sentencia. ¿Qué ocurre si falla la segunda sentencia? ¿Y si falla la tercera?

 * 
 */
package bbdd;

//Para no compiacr el código, llamaré a los métodos del Ej1 
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ej2 {

    public static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {
        crearFichero();
        menuInicial();
    }

    public static void menuInicial() {
        boolean salir = false;
        while (salir == false) {
            System.out.println("");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("MENU PRINCIPAL:");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("  1- actualización simple");
            System.out.println("  2- transacción 1");
            System.out.println("  3- transacción 2");
            System.out.println("  0- salir");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("OPCIÓN ELEGIDA:");
            String opcionInicial = lector.nextLine();

            switch (opcionInicial) {
                case "1":
                    Ej1.menuConsulta();
                    break;
                case "2":
                    Ej1.actualizarCampos();
                    break;
                case "3":
                    Ej1.insertarCampos();
                    break;
                case "4":
                    menuTransaccion();
                    break;
                case "0":
                    salir = true;
                    System.out.println("ADIÓS.");
                    System.out.println("* * * * * * * * * * * *");
                    break;
                default:
                    System.out.println("  Opción imposible.");
            }
        }
    }

    public static void menuTransaccion() {
        boolean salir2 = false;
        while (salir2 == false) {
            System.out.println("");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("MENU TRANSACCIONES:");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("  1- consulta");
            System.out.println("  2- actualización");
            System.out.println("  3- inserción");
            System.out.println("  0- vuelta al menú principal");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("OPCIÓN ELEGIDA:");
            String opcionTransaccion = lector.nextLine();

            switch (opcionTransaccion) {
                case "1":
                    //TODO
                    break;
                case "2":
                    //TODO
                    break;
                case "3":
                    //TODO
                    break;
                case "0":
                    salir2 = true;
                    break;
                default:
                    System.out.println("  Opción imposible.");
            }
        }
//TODO
    }

    public static void crearFichero() {
        //TODO que el usuario pueda introducir el nombre
        File fichero = new File("consultasEj2.txt");
        try {
            fichero.createNewFile();
        } catch (IOException eio) {
            eio.printStackTrace();
        }
    }

}
