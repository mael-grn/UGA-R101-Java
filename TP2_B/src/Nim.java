import java.sql.SQLOutput;
import java.util.Scanner;

public class Nim {

    //fonction------------------------------------------------------------------------------------------------fonction
    private static int saisieEntPosMin(int min) {

        // {min > 0} => {resultat = un entier superieur ou egal à min saisi par l'utilisateur}
        Scanner lecteur = new Scanner(System.in);
        int saisie = 0;
        do {
            //saisire un entier selon les regles
            System.out.print(" superieur ou egal à " + min + " : ");
            saisie = lecteur.nextInt();
            lecteur.nextLine();

            //afficher erreur si entier ne respecte pas les regles
            if (saisie < min | saisie < 0) {
                System.out.print(">>> *** attention, il faut que ce soit");
            }
        } while (saisie < min | saisie < 0);
        return saisie;
    }

    //fonction------------------------------------------------------------------------------------------------fonction
    private static int prise(int reste, int max) {

        // { reste > 0, max > 0} => {résultat = entier > 0 et inférieur ou égal à la plus petite valeur parmi reste et max
        // après saisie par l'utilisateur}
        Scanner lecteur = new Scanner(System.in);
        int saisie;

        // prendre le plus petit entre reste et max, reste sera la valeur la plus petite
        if (reste > max) {
            reste = max;
        }
        do {
            //saisire un entier selon les regles
            System.out.print(" compris entre 1 et " + reste + " : ");
            saisie = lecteur.nextInt();
            lecteur.nextLine();
            if (saisie <= 0 | saisie > reste) {
                System.out.print(">>> *** attention, il faut que ce soit");
            }
        } while (saisie <= 0 | saisie > reste);
        return saisie;
    }

    //main------------------------------------------------------------------------------------------------main
    public static void main(String[] args) {

        //initialisation procedure main
        Scanner lecteur = new Scanner(System.in);
        final int minjoueurs = 2;
        final int maxprise = 3;
        int nbJoueurs, nbAllumettes;
        int nbCoups = 0;
        int reste = 0;
        int numJoueur = 0;

        //saisir nb joueurs et nb allumettes avec fonction
        System.out.print("*** saisir le nombre de joueurs,");
        nbJoueurs = saisieEntPosMin(minjoueurs);
        System.out.print("*** saisir le nombre d'allumettes,");
        nbAllumettes = saisieEntPosMin(3 * nbJoueurs);
        reste = nbAllumettes;

        //affichage nombre de jouers et d'allumettes
        System.out.println("*********************************************");
        System.out.println("Nombre de joueurs : " + nbJoueurs);
        System.out.println("Nombre d'allumettes : " + nbAllumettes);
        System.out.println("*********************************************");

        //boucle pour deroulement du jeu
        do {
            //numero du joueur qui va jouer

            if (numJoueur < nbJoueurs) {
                numJoueur++;
            } else {
                numJoueur = 1;
            }

            //saisie du nombre d'allumettes
            System.out.print("Joueur n°" + numJoueur + ", combien prenez vous d'allumettes ? Entrez un entier");
            reste = reste - prise(reste, maxprise);
            System.out.println(">>>Il reste " + reste + " allumettes");

            //incrementer le nombre de coups
            nbCoups++;
        } while (reste != 0);

        System.out.println("Victoire du joueur n°" + numJoueur + " apres " + nbCoups + " coups !");


    }
}
