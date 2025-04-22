import java.util.ArrayList;
import java.util.Scanner;

public class Monde {
    public static void main(String[] args) {
        ArrayList<Pays> leMonde = InitMonde.creerMonde();
        Utilitaire.TriBulle(leMonde);
        Scanner lecteur = new Scanner(System.in);


        System.out.print("continent : ");
        String cont = lecteur.nextLine();

        System.out.print("nom : ");
        String nom = lecteur.nextLine();

        System.out.println("recherche iterative : " + Utilitaire.rechDichoIter(leMonde, cont, nom));

        System.out.println("recherche recursive : " + Utilitaire.rechDichoRec(leMonde, cont, nom));

        System.out.println("plus grande superficie iteratif : " + Utilitaire.plusGrandPaysIter(leMonde));

        System.out.println("plus grande superficie recursif : " + Utilitaire.plusGrandPaysDPR(leMonde));

        System.out.println("plus petite population iteratif : " + leMonde.get(Utilitaire.indMinPopSeq(leMonde)));

        System.out.println("plus petite population recursif : " + leMonde.get(Utilitaire.indMinPopDPR(leMonde)));


    }
}
