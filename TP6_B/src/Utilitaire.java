import java.util.ArrayList;
import java.util.Scanner;

public class Utilitaire {

    public static int saisirIntMinMax(int min, int max) {

        //{ min <= max } => { résultat = entier compris entre min et max

        Scanner lecteur = new Scanner(System.in);
        int saisie;

        System.out.println("entier compris entre " + min + " et " + max + " : ");

        do {                                    // boucle saisie controle
            System.out.print(">>> ");
            saisie = lecteur.nextInt();
            lecteur.nextLine();

            if (saisie < min | saisie > max){
                System.out.println("veuillez respecter les consignes !!");
            }
        } while (saisie < min | saisie > max);
        
        lecteur.close();
        return saisie;
    }       // saisie entre min et max

    public static int rechPremIndSeq(ArrayList<Polar> vPolar, int an, String aut) {
        // {vPolar trié dans l'ordre (annee, auteur)} =>
        // {* s'il y a dans vPolar au moins un élément d'année an et d'auteur aut,
        // résultat = indice du premier de ces éléments
        // * sinon, résultat = -1}
        // LA RECHERCHE EST SÉQUENTIELLE !!!

        int i = 0;

        // recherche indice de l'element
        while (i<vPolar.size() && vPolar.get(i).getAnnee() != an | vPolar.get(i).getAuteur().compareToIgnoreCase(aut) != 0){i++;}

        // retourne -1 si pas trouvé
        if (i == vPolar.size()){
            i = -1;
        }

        return i;

    }   // recherche seq element de polars

    public static int rechPremIndDicho(ArrayList<Polar> vPolar, int an, String aut) {
        // {vPolar trié dans l'ordre (annee, auteur)} =>
        // {* s'il y a dans vPolar au moins un élément d'année an et d'auteur aut,
        // résultat = indice du premier de ces éléments
        // * sinon, résultat = -1}
        // LA RECHERCHE EST DICHOTOMIQUE !!!

        int inf = 0;
        int sup = vPolar.size()-1;
        int m;

        while (inf<sup){        //recherche en meme temps anne et auteur, avec une seul condition
            m = (inf+sup)/2;
            if (vPolar.get(m).getAnnee() >= an && vPolar.get(m).getAuteur().compareToIgnoreCase(aut) >= 0){
                sup = m;
            } else {
                inf = m+1;
            }
        }       //retourne ce que l'ont attends
        if (vPolar.get(sup).getAnnee() == an && vPolar.get(sup).getAuteur().compareToIgnoreCase(aut) == 0){
            return sup;
        } else {
            return -1;
        }
    }   //recherche dich elements polars

    public static boolean existPolar(ArrayList<Polar> vPolar,
                                     int an1, int an2, String unAuteur) {
        // { * vPolar non vide et trié dans l'ordre (annee, auteur)
        // * an1 <= an2
        // * an1 est >= à la plus petite valeur de l'attribut annee dans vPolar
        // * an2 est <= à la plus grande valeur de l'attribut année dans vPolar }
        // => { résultat = vrai s'il existe dans vPolar, au moins un roman
        // dont l'année est comprise entre an1 et an2 et d'auteur unAuteur'}

        int i = 0;

        while (i < (an2-an1) && rechPremIndDicho(vPolar, (an1+i), unAuteur) == -1) { i++;}

        return i != (an2-an1);

    }       // si il existe un polar

    public static void lesPolarsDe(ArrayList<Polar> vPolar,
                                   int an1, int an2, String unAuteur) {
        // { * vPolar non vide et trié dans l'ordre (annee, auteur)
        // * an1 <= an2
        // * an1 est >= à la plus petite valeur de l'attribut annee dans vPolar
        // * an2 est <= à la plus grande valeur de l'attribut année dans vPolar }
        // * vPolar contient au moins un roman dont l'auteur est unAuteur
        // et dont l'année est dans l'intervalle [an1, an2} }
        // => { chaque année A comprise entre an1 et an2 est affichée, suivie :
        // * d'un tiret, si aucun roman n'a été écrit l'année A par unAuteur
        // * de l'affichage ligne à ligne des titres des romans écrits par
        // unAuteur, l'année A (chaque titre précédé d'un tiret) }

        int k;

        for (int i = 0; i <= (an2-an1); i++){

            System.out.println("* année " + (an1+i));

            if (rechPremIndDicho(vPolar,(an1+i), unAuteur) >= 0){

                k = rechPremIndDicho(vPolar,(an1+i), unAuteur);

                while (k < vPolar.size() && vPolar.get(k).getAnnee() == (an1+i) & vPolar.get(k).getAuteur().compareToIgnoreCase(unAuteur) == 0) {
                    System.out.println(" - " + vPolar.get(k).getTitre());
                    k = k + 1;
                }

            } else {
                System.out.println(" -");
            }

        }
    }       //affichage de polars
}
