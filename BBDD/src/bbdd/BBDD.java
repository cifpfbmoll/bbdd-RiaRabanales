/*
 * Maria Rabanales González
 */
package bbdd;

import java.util.Scanner;

public class BBDD {

    public static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {
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
            System.out.println("  1- blah blah");
            System.out.println("  0- salir");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("OPCIÓN ELEGIDA:");
            String opcionConsulta = lector.nextLine();

            switch (opcionConsulta) {
                case "1":
                    //TODO
                    break;
                case "0":
                    salir2 = true;
                    break;
                default:
                    System.out.println("  Opción imposible.");
            }
        }
    }
}
