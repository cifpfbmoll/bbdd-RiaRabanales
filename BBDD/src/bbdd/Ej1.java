/*
 * Maria Rabanales González
 */
package bbdd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Ej1 {

    public static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {
        //TODO crear fichero if not exists
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
            System.out.println("  1- consulta");
            System.out.println("  2- actualización");
            System.out.println("  3- inserción");
            System.out.println("  0- salir");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("OPCIÓN ELEGIDA:");
            String opcionInicial = lector.nextLine();

            switch (opcionInicial) {
                case "1":
                    menuConsulta();
                    break;
                case "2":
                    //TODO
                    break;
                case "3":
                    //TODO
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

    public static void menuConsulta() {
        boolean salir2 = false;
        while (salir2 == false) {
            System.out.println("");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("MENU CONSULTA:");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("  1- bebedores en diferentes bares");
            System.out.println("  2- número de bebedores que prefieren determinada cerveza");
            //TODO más consultas
            System.out.println("  0- volver al menú principal");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("OPCIÓN ELEGIDA:");
            String opcionConsulta = lector.nextLine();

            switch (opcionConsulta) {
                case "1":
                    consultarBebedores();
                    break;
                case "2":
                    consultarCervezas();
                    break;
                case "0":
                    salir2 = true;
                    break;
                default:
                    System.out.println("  Opción imposible.");
            }
        }
    }

    //Primera consulta de prueba; esta con consulta normal y no PreparedStatement
    public static void consultarBebedores() {
        System.out.println("¿En qué bar?");
        System.out.println("  1- Todos los bares");
        System.out.println("  2- The Edge");
        System.out.println("  3- Satisfaction");
        System.out.println("  4- James Joyce Pub");
        System.out.println("  5- Talk of the Town");
        System.out.println("  6- Down Under Pub");
        System.out.println("Introduce número:");
        String opcionBar = lector.nextLine();

        try (Connection con = obtenerConexion()) {
            Statement stat = con.createStatement();
            switch (opcionBar) {
                case "1":
                    ResultSet rset1 = stat.executeQuery("SELECT DISTINCT drinker FROM frequents;");
                    imprimirConsulta("bebedores en todos los bares", rset1, "tipoString", "drinker");
                    if (rset1 != null) {
                        rset1.close();
                    }
                    break;
                case "2":
                    ResultSet rset2 = stat.executeQuery("SELECT DISTINCT drinker FROM frequents WHERE bar = 'The Edge';");
                    imprimirConsulta("bebedores en 'The Edge'", rset2, "tipoString", "drinker");
                    if (rset2 != null) {
                        rset2.close();
                    }
                    break;
                case "3":
                    ResultSet rset3 = stat.executeQuery("SELECT DISTINCT drinker FROM frequents WHERE bar = 'Satisfaction';");
                    imprimirConsulta("bebedores en 'Satisfaction'", rset3, "tipoString", "drinker");
                    if (rset3 != null) {
                        rset3.close();
                    }
                    break;
                case "4":
                    ResultSet rset4 = stat.executeQuery("SELECT DISTINCT drinker FROM frequents WHERE bar = 'James Joyce Pub';");
                    imprimirConsulta("bebedores en 'James Joyce Pub'", rset4, "tipoString", "drinker");
                    if (rset4 != null) {
                        rset4.close();
                    }
                    break;
                case "5":
                    ResultSet rset5 = stat.executeQuery("SELECT DISTINCT drinker FROM frequents WHERE bar = 'Talk of the Town';");
                    imprimirConsulta("bebedores en 'Talk of the Town'", rset5, "tipoString", "drinker");
                    if (rset5 != null) {
                        rset5.close();
                    }
                    break;
                case "6":
                    ResultSet rset6 = stat.executeQuery("SELECT DISTINCT drinker FROM frequents WHERE bar = 'Down Under Pub';");
                    imprimirConsulta("bebedores en 'Down Under Pub'", rset6, "tipoString", "drinker");
                    if (rset6 != null) {
                        rset6.close();
                    }
                    break;
                default:
                    System.out.println("  Opción imposible.");
            }
            if (stat != null) {
                stat.close();
            }
            //No me hace falta cerrar la conexión porque está el try
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            System.out.println("* * * * * * * * * * * *");
            System.out.println("FIN DE LA CONSULTA");
        }
    }

    //Segunda consulta de prueba; esta sí que preparedStatement
    public static void consultarCervezas() {
        System.out.println("¿Qué cerveza?");
        System.out.println("  1- Amstel");
        System.out.println("  2- Corona");
        System.out.println("  3- Budweiser");
        System.out.println("  4- Dixie");
        System.out.println("  5- Erdinger");
        System.out.println("Introduce número:");
        String opcionBeer = lector.nextLine();

        try (Connection con = obtenerConexion()) {
            PreparedStatement stat = con.prepareStatement("SELECT count(*) AS cuenta FROM likes where beer =?;");
            switch (opcionBeer) {
                case "1":
                    stat.setString(1, "Amstel");
                    ResultSet rset1 = stat.executeQuery();
                    imprimirConsulta("número de bebedores de Amstel", rset1, "tipoInt", "cuenta");
                    if (rset1 != null) {
                        rset1.close();
                    }
                    break;
                case "2":
                    stat.setString(1, "Corona");
                    ResultSet rset2 = stat.executeQuery();
                    imprimirConsulta("número de bebedores de Corona", rset2, "tipoInt", "cuenta");
                    if (rset2 != null) {
                        rset2.close();
                    }
                    break;
                case "3":
                    stat.setString(1, "Budweiser");
                    ResultSet rset3 = stat.executeQuery();
                    imprimirConsulta("número de bebedores de Budweiser", rset3, "tipoInt", "cuenta");
                    if (rset3 != null) {
                        rset3.close();
                    }
                    break;
                case "4":
                    stat.setString(1, "Dixie");
                    ResultSet rset4 = stat.executeQuery();
                    imprimirConsulta("número de bebedores de Dixie", rset4, "tipoInt", "cuenta");
                    if (rset4 != null) {
                        rset4.close();
                    }
                    break;
                case "5":
                    stat.setString(1, "Erdinger");
                    ResultSet rset5 = stat.executeQuery();
                    imprimirConsulta("número de bebedores de Erdinger", rset5, "tipoInt", "cuenta");
                    if (rset5 != null) {
                        rset5.close();
                    }
                    break;
                default:
                    System.out.println("  Opción imposible.");
            }
            if (stat != null) {
                stat.close();
            }
            //No me hace falta cerrar la conexión porque está el try
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            System.out.println("* * * * * * * * * * * *");
            System.out.println("FIN DE LA CONSULTA");
        }
    }

    //Con este método obtengo la conexión:
    public static Connection obtenerConexion() throws SQLException {
        //el puerto default para MySQL es 3306
        String url = "jdbc:mysql://localhost:3306/javabd";
        String password = "alualualu";
        return DriverManager.getConnection(url, "root", password);
    }

    //Quiero un método de impresión de consultas que me sirva para todas:
    public static void imprimirConsulta(String consulta, ResultSet rset, String tipo, String concepto) throws SQLException {
        try (BufferedWriter writerMejorado = new BufferedWriter(new FileWriter("consultas.txt", true))) {
            writerMejorado.write("- - - - - - - - - - - - - - - -\n");
            writerMejorado.write("Consulta " + consulta + ":\n");
            System.out.println("Consulta " + consulta + ":");
            while (rset.next()) {
                if (tipo.equals("tipoString")) {
                    String miString = rset.getString(concepto);
                    writerMejorado.write("  -" + miString + "\n");
                    System.out.println("  -" + miString);
                } else if (tipo.equals("tipoInt")) {
                    int miInt = rset.getInt(concepto);
                    writerMejorado.write("  -" + miInt + "\n");
                    System.out.println("  -" + miInt);
                }
            }
            writerMejorado.write("- - - - - - - - - - - - - - - -\n");
            //TODO
        } catch (IOException eio) {
            eio.printStackTrace();
        }
    }

    //Quiero crear un fichero sólo si no existe ya:
    public static void crearFichero() {
        //TODO que el usuario pueda introducir el nombre
        File fichero = new File("consultas.txt");
        try {
            fichero.createNewFile();
        } catch (IOException eio) {
            eio.printStackTrace();
        }
    }
}
