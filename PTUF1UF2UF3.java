package practiques;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PTUF1UF2UF3 {

    public static void main(String[] args) throws IOException, SQLException {
        Scanner teclat = new Scanner(System.in);
        boolean sortir = false;
        FileWriter fw = null;
        BufferedWriter bf = null;
        PrintWriter escritor = null;
        fw = new FileWriter("equips.txt", false);
        bf = new BufferedWriter(fw);
        escritor = new PrintWriter(bf);
        escritor.println("Madrid 5 2 1 2 8");
        escritor.println("Barca 5 3 1 1 10");
        escritor.println("Tàrrega 5 1 3 1 7");
        escritor.close();

        do {
            System.out.println("^^^^MENU GESTOR D'EQUIPS^^^^");
            System.out.println("1.Visualitzar tots els equips de la lliga amb les seves puntuacions");
            System.out.println("2.Afegir un nou equip amb les seves puntuacions");
            System.out.println("3.Modificar les dades d’un equip");
            System.out.println("4.Visualitzar les dades del líder i del cuer de la lliga");
            System.out.println("5.Sortir");
            System.out.println("\nTria una de les opcions");

            int opcio = teclat.nextInt();

            switch (opcio) {
                case 1:
                    visualitzarequips();
                    break;
                case 2:
                    afegirequips();
                    break;
                case 3:
                    modificarequips();
                    break;
                case 4:
                    visualitzarlider();
                    break;
                case 5:
                    sortir = true;
                    break;
                default:
                    System.out.println("L'Opció no és vàlida");
            }

            System.out.println(("opció: ") + opcio);

        } while (!sortir);
    }

    static void visualitzarequips() throws IOException {
         try {
            Scanner input = new Scanner(new File("equips.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       

    }

    static void afegirequips() throws SQLException, FileNotFoundException {
        try(FileWriter fw = new FileWriter("equips.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
        //aqui se escriu el nou equip amb les noves puntuacions
        out.println("");
        out.println("");
        out.println("");
    } catch (IOException e) {
        
    }

    }

    static void modificarequips() throws IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        Scanner teclat = new Scanner(System.in);
        System.out.println("MODIFICAR EQUIP");
        System.out.println("Nom del equip a modificar:");
        String equip = teclat.nextLine();
        teclat.nextLine();
        System.out.println("Nou nom:");
        String nom = teclat.nextLine();
        System.out.println("Noves puntuacions:");
        int puntuacions = teclat.nextInt();
        teclat.nextLine();
        PreparedStatement sentencia = null;

        try {
            Scanner input = new Scanner(new File("equips.txt"));
            archivo = new File("equips.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(br);
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            sentencia.setString(4, equip);
            sentencia.setString(1, nom);
            sentencia.setInt(2, puntuacions);
            sentencia.executeUpdate();
            System.out.println("Equip modificat: " + nom);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    static void visualitzarlider() throws FileNotFoundException {
        File archivo = new File("equips.txt");
        Scanner sc = new Scanner(System.in);
        String respuesta;

        try {
            do {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                System.out.println("Escriu el nom del equip que sigui el lider: ");
                respuesta = "Equip: " + sc.nextLine();

                String linea = "";
                boolean encontrado = false;
                while ((linea = br.readLine()) != null) {

                    if (linea.equalsIgnoreCase(respuesta)) {
                        System.out.println(linea);

                        for (int i = 0; i < 2; i++) {
                            System.out.println(br.readLine());
                        }
                        encontrado = true;
                        break;

                    }

                }

                if (!encontrado) {
                    System.out.println("El Equip no existeix");
                }
                respuesta = sc.nextLine();

            } while (respuesta.equalsIgnoreCase("si"));
        } catch (IOException e) {

            System.out.println("Error");
        }

    }
}
