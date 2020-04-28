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
import static bbdd.Ej1.obtenerConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ej2 {

    public static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {
        Ej1.crearFichero();
        menuInicial();
    }

    public static void menuInicial() {
        boolean salir = false;
        while (salir == false) {
            System.out.println("");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("MENU PRINCIPAL:");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("  1- consulta");
            System.out.println("  2- actualización");
            System.out.println("  3- inserción");
            System.out.println("  4- transacción");
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
            System.out.println("  1- actualización simple de los precios de cerveza");
            System.out.println("  2- transacción 1");
            System.out.println("  3- transacción 2");
            System.out.println("  0- vuelta al menú principal");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("OPCIÓN ELEGIDA:");
            String opcionTransaccion = lector.nextLine();

            switch (opcionTransaccion) {
                case "1":
                    actualizarSimple();
                    break;
                case "2":
                    realizarTransaccion1();
                    break;
                case "3":
                    realizarTransaccion2();
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

    public static void actualizarSimple() {
        //He complicado las opciones demasiado.
        try (Connection con = obtenerConexion()) {
            System.out.println("ACTUALIZACIÓN DE PRECIOS:");
            String query = "UPDATE serves SET price = ? WHERE bar = ? AND beer = ?;";
            ArrayList<String> valores = seleccionarBarCerveza();
            System.out.println("¿Qué nuevo precio quieres poner?");
            double precio = Double.parseDouble(lector.nextLine());
            //A continuación: rellenar campos
            PreparedStatement prepStat = con.prepareStatement(query);
            prepStat.setString(1, Double.toString(precio));
            prepStat.setString(2, valores.get(0));
            prepStat.setString(3, valores.get(1));
            prepStat.execute();
            prepStat.close();
            //TODO CONTINUAR  
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            System.out.println("* * * * * * * * * * * *");
            System.out.println("FIN DE LA ACTUALIZACIÓN");
        }
    }

    public static void realizarTransaccion1() {
        try (Connection con = obtenerConexion()) {
            System.out.println("TRANSACCIÓN 1:");
            //TODO CONTINUAR  
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            System.out.println("* * * * * * * * * * * *");
            System.out.println("FIN DE LA TRANSACCIÓN 1");
        }
    }

    public static void realizarTransaccion2() {
        try (Connection con = obtenerConexion()) {
            System.out.println("TRANSACCIÓN 2:");
            //TODO CONTINUAR  
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            System.out.println("* * * * * * * * * * * *");
            System.out.println("FIN DE LA TRANSACCIÓN 2");
        }
    }

    public static ArrayList<String> seleccionarBarCerveza() {
        //TODO
        ArrayList<String> valores = new ArrayList<>();
        ArrayList<String> bares = consultarValores("serves", "bar");
        System.out.println("¿En qué bar quieres modificar?");
        for (int i = 0; i < bares.size(); i++) {
            System.out.println("  -" + (i + 1) + ": " + bares.get(i));
        }
        int opcionBar = Integer.parseInt(lector.nextLine());
        opcionBar--;
        System.out.println("Selección: " + bares.get(opcionBar));
        valores.add(bares.get(opcionBar));
        ArrayList<String> cervezas = consultarValores("serves", "beer", bares.get(opcionBar));
        System.out.println("¿El precio de qué cerveza quieres modificar?");
        for (int i = 0; i < cervezas.size(); i++) {
            System.out.println("  -" + (i + 1) + ": " + cervezas.get(i));
        }
        int opcionBeer = Integer.parseInt(lector.nextLine());
        opcionBeer--;
        System.out.println("Selección: " + cervezas.get(opcionBeer));
        valores.add(cervezas.get(opcionBeer));
        //traza: System.out.println(valores.get(0) + valores.get(1));
        return valores;
    }

    //Me voy a crear métodos de consulta nuevos que valgan para todas las tablas, con overloading. Me acabará facilitando el trabajo.
    public static ArrayList<String> consultarValores(String tabla, String columna) {
        ArrayList<String> lista = new ArrayList<>();
        try (Connection con = Ej1.obtenerConexion()) {
            String query = "SELECT DISTINCT " + columna + " FROM " + tabla + ";";
            PreparedStatement stat = con.prepareStatement(query);
            ResultSet rset = stat.executeQuery();
            while (rset.next()) {
                String valor = rset.getString(1);
                lista.add(valor);
            }
            rset.close();
            stat.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return lista;
    }

    public static ArrayList<String> consultarValores(String tabla, String columna, String condicion) {
        ArrayList<String> lista = new ArrayList<>();
        try (Connection con = Ej1.obtenerConexion()) {
            String query = "SELECT DISTINCT " + columna + " FROM " + tabla + " WHERE bar = ?;";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, condicion);
            ResultSet rset = stat.executeQuery();
            while (rset.next()) {
                String valor = rset.getString(1);
                lista.add(valor);
            }
            rset.close();
            stat.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return lista;
    }

}
