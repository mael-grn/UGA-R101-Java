import java.util.ArrayList;
import java.util.Scanner;

public class polars_main {
    public static void main(String[] args) {

        Scanner lecteur = new Scanner(System.in);
        final ArrayList<Polar> lesPolars = InitBibPolars.lesPolars();


        int anMin = lesPolars.get(0).getAnnee();
        int anMax = lesPolars.get(lesPolars.size()-1).getAnnee();

        System.out.println("saisir an1,");
        int an1 = Utilitaire.saisirIntMinMax(anMin, anMax);

        System.out.println("saisir an2,");
        int an2 = Utilitaire.saisirIntMinMax(an1, anMax);

        System.out.println("saisir un auteur :");
        System.out.print(">>> ");
        String auteur = lecteur.nextLine();

        /*
        final ArrayList<Polar> lesPolars = InitBibPolars.lesPolars();

        for (int i=0; i<lesPolars.size(); i++){
            System.out.println(lesPolars.get(i));
        }
         */     // affichage de tous les romans de lesPolars

        /*
        QESTIONS :
        On remarque que cela affiche trop de resulat pour que ce soit traitable
         */     // reponse aux questions


        // recherche roman avec auteur et anne

        if (Utilitaire.existPolar(lesPolars, an1, an2, auteur)){
            System.out.println("\nRoman de " + auteur + " entre " + an1 + " et " + an2);
            Utilitaire.lesPolarsDe(lesPolars, an1, an2, auteur);
        } else {
            System.out.println("Aucun roman de " + auteur + " entre " + an1 + " et " + an2);
        }

        lecteur.close();

    }
}
