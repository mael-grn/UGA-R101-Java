import java.util.Scanner;

public class ControleSaisie {
    public static void main(String[] args) {

        Scanner lecteur = new Scanner(System.in);

        System.out.println("\nTest de controle de saisie :");

        /*
        System.out.println("Vous avez saisie " + Utilitaire.getInt(lecteur) + " sans erreur");
        System.out.println("Vous avez saisie " + Utilitaire.getFloat(lecteur) + " sans erreur");

         */

        System.out.println("Vous avez saisie " + Utilitaire.getIntV2(lecteur) + " sans erreur");
        System.out.println("Vous avez saisie " + Utilitaire.getFloatV2(lecteur) + " sans erreur");



    }
}
