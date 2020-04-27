/*
 * Maria Rabanales González
 */
package bbdd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
                    actualizarCampos();
                    break;
                case "3":
                    insertarCampos();
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

    public static void actualizarCampos() {
        try (Connection con = obtenerConexion()) {
            int tabla = seleccionarTabla();
            String columna = "";
            ArrayList<String> columnas = new ArrayList<>();
            boolean otraColumna = true;
            //Porque como mucho puedo modificar 3 columnas, y eso en según qué tablas.
            int maxColumna = 0;
            while (otraColumna == true && maxColumna < 3) {
                System.out.println("¿Qué columna quieres modificar?");
                columna = lector.nextLine();
                columnas.add(columna);
                maxColumna++;
                System.out.println("¿Quieres modificar otra columna más? S/N");
                String otraColumnaOpcion = lector.nextLine().toUpperCase();
                if (!otraColumnaOpcion.equals("S")) {
                    otraColumna = false;
                }
            }
            String query = "UPDATE ";

            //Aquí empiezo a construir mi query:
            switch (tabla) {
                case 1:         //tabla bar
                    query += "bar SET ? = ?";
                    break;
                case 2:         // tabla cerveza
                    query += "beer SET ? = ?";
                    break;
                case 3:         //tabla drinker
                    query += "drinker SET ? = ?";
                    break;
                case 4:         // tabla frequents
                    query += "frequents SET ? = ?";
                    break;
                case 5:         //tabla likes
                    query += "likes SET ? = ?";
                    break;
                case 6:         //tabla serves
                    query += "serves SET ? = ?";
                    break;
            }
            if (columnas.size() > 1) {
                query += " AND ? = ? ";
                if (columnas.size() > 2) {
                    query += " AND ? = ? ";
                }
            }
            query += ";";
            //TODO: pedir valores, rellenar campos
            //prepStat.setString(3, inCampos.get(2));
            PreparedStatement prepStat = con.prepareStatement(query);
            prepStat.execute();
            prepStat.close();
            //TODO       
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            System.out.println("* * * * * * * * * * * *");
            System.out.println("FIN DE LA ACTUALIZACIÓN");
        }
    }

    public static void insertarCampos() {
        int tabla = seleccionarTabla();
        String textoQuery = "";
        ArrayList<String> textos = new ArrayList<>();
        switch (tabla) {
            case 1:         //tabla bar
                textos.add("nombre del bar");
                textos.add("dirección del bar");
                textoQuery = "INSERT INTO bar VALUES (?, ?)";
                break;
            case 2:         // tabla cerveza
                textos.add("nombre de la cerveza");
                textos.add("fabricante");
                textoQuery = "INSERT INTO beer VALUES (?, ?)";
                break;
            case 3:         //tabla drinker
                textos.add("nombre del bebedor");
                textos.add("dirección del bebedor");
                textoQuery = "INSERT INTO drinker VALUES (?, ?)";
                break;
            case 4:         // tabla frequents
                textos.add("nombre del bebedor");
                textos.add("bar que frecuenta");
                textos.add("veces a la semana que acude");
                textoQuery = "INSERT INTO frequents VALUES (?, ?, ?)";
                break;
            case 5:         //tabla likes
                textos.add("nombre del bebedor");
                textos.add("cerveza que le gusta");
                textoQuery = "INSERT INTO likes VALUES (?, ?)";
                break;
            case 6:         //tabla serves
                textos.add("nombre del bar");
                textos.add("cerveza que sirve");
                textos.add("precio");
                textoQuery = "INSERT INTO serves VALUES (?, ?, ?)";
                break;
            //TODO: revisar lo de arriba para PKs y FKs
            //TODO: imprimir en archivo las inserciones?        
        }
        insertarTabla(textos, textoQuery);
    }

    public static void insertarTabla(ArrayList<String> inTextos, String query) {
        try (Connection con = obtenerConexion()) {
            //TODO: esto quedaría más limpio como arraylist de arraylists
            ArrayList<String> inCampos = new ArrayList<>();
            //Innecesario: ArrayList<String> inColumnas = new ArrayList<>(Arrays.asList("name", "address"));
            System.out.println("Para insertar un nuevo elemento rellena la siguiente información:");
            for (int i = 0; i < inTextos.size(); i++) {
                String inCampo = "";
                do {
                    System.out.println("  -" + inTextos.get(i) + ":");
                    inCampo = lector.nextLine();
                } while (!validarCampo(inCampo));
                inCampos.add(inCampo);
            }
            PreparedStatement prepStat = con.prepareStatement(query);
            prepStat.setString(1, inCampos.get(0));
            prepStat.setString(2, inCampos.get(1));
            if (inTextos.size() == 3) {
                prepStat.setString(3, inCampos.get(2));
            }
            prepStat.execute();
            prepStat.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            System.out.println("* * * * * * * * * * * *");
            System.out.println("FIN DEL PROCESO");
        }
    }

    public static boolean validarCampo(String campo) {
        boolean campoValido = false;
        if (!campo.equals("")) {
            campoValido = true;
        }
        if (campoValido == false) {
            System.out.println("ERROR: campo vacío. Introduce:");
        }
        return campoValido;
    }

    public static int seleccionarTabla() {
        int tabla = 0;
        while (tabla < 1 || tabla > 6) {
            System.out.println("Selecciona en qué tabla quieres operar:");
            System.out.println("  1- bares");
            System.out.println("  2- cervezas");
            System.out.println("  3- bebedores");
            System.out.println("  4- frecuentan");
            System.out.println("  5- gustos");
            System.out.println("  6- sirven");
            tabla = Integer.parseInt(lector.nextLine());
        }
        return tabla;
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
