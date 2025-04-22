import java.util.Scanner;

public class Calculs {

    //fonction------------------------------------------------------------------------------------------------fonction
    private static int saisieEntPos() {

        //{} => {résultat = un entier positif ou nul saisi par l'utilisateur}
        int saisie;
        Scanner lecteur = new Scanner(System.in);
        do {
            //saisie utilisateur
            System.out.print("Saisir un entier positif ou nul : ");
            saisie = lecteur.nextInt();
            lecteur.nextLine();

            //message erreur
            if (saisie < 0) {
                System.out.println("erreur, recommencez");
            }
        } while (saisie < 0);
        return saisie;
    }

    //fonction------------------------------------------------------------------------------------------------fonction
    private static int saisieEntPosInfEgal(int x) {

        //{x > 0} => {résultat = entier compris entre 0 et x,
        // saisi par l'utilisateur}
        int saisie;
        Scanner lecteur = new Scanner(System.in);
        do {
            //saisie utilisateur
            System.out.print("saisir un entier compris entre 0 et " + x + " : ");
            saisie = lecteur.nextInt();
            lecteur.nextLine();

            // message erreur
            if (saisie < 0 | saisie >x) {
                System.out.println("erreur, recommencez");
            }
        } while (saisie < 0 | saisie > x);
        return saisie;
    }

    //fonction------------------------------------------------------------------------------------------------fonction
    private static int factorielle(int val) {
        // {val >= 0} => {résultat = factorielle de n (n!) }
        int calc = 1;
        int incremfact = 1;

         // calcul factorielle (boucle)
        for (int i = 1; i <= val; i++) {
            calc = calc * incremfact;
            incremfact++;
        }
        return calc;
    }

    //fonction------------------------------------------------------------------------------------------------fonction
    private static int combinaison(int n, int p) {
        // {n >= 0, 0 <= p <= n}
        // => {résultat = nombre de sous-ensembles de p éléments que
        // l'on peut obtenir à partir d'un ensemble de n éléments
        // formule : n!/(p! * (n-p)!)

        int calc;
        calc = factorielle(n)/(factorielle(p) * factorielle(n-p));
        return calc;
    }
    //fonction------------------------------------------------------------------------------------------------fonction
    private static int arrangement(int n, int p) {
        // {n >= 0, 0 <= p <= n}
        // => {résultat = nombre de n-uplets ordonnés de p éléments que
        // l'on peut obtenir à partir d'un ensemble de n éléments
        // formule : n!/(n-p!)}

        int calc;
        calc = factorielle(n)/factorielle(n-p);
        return calc;
    }
    //fonction------------------------------------------------------------------------------------------------fonction
    private static int trianglePascal(int n){
        // {n >= 0} => {les lignes 0 à n du triangle de Pascal ont été affichées
        // de façon à que sur chaque ligne, les coefficients binomiaux
        // soient séparés par un espace}
        int nbr_colonnes = 0;
        int calc;

        //ecris la gauche du tableau : le numero de ligne (=n)
        if (n<10){
            System.out.print("-" + n + "--");
        } else {
            System.out.print("-" + n + "-");
        }

        //ecris coefficient binomiaux
        for (int i = 0; i <= n; i++){
            calc = factorielle(n)/(factorielle(nbr_colonnes)*(factorielle(n-nbr_colonnes)));

            //gerer les espacements entre les nombres
            if (calc < 10){
                System.out.print("  ");
            } else if (calc < 100){
                System.out.print(" ");
            }
            System.out.print(" " + calc);
            nbr_colonnes++;
        }
        System.out.println("");
        return n;


    }
    //main--------------------------------------------------------------------------------------------------------main
    public static void main(String[] args) {
        int n, p;
        Scanner lecteur = new Scanner(System.in);

        //initialisation saisie + partie 1 prog
        n = saisieEntPos();
        p = saisieEntPosInfEgal(n);
        System.out.println("-------------------");
        System.out.print("factorielle de " + n + " = ");
        System.out.println(factorielle(n));
        System.out.println("-------------------");
        System.out.print("nombre de sous ensembles de " + p + " elements parmis " + n + " elements : ");
        System.out.println(combinaison(n, p));
        System.out.println("-------------------");
        System.out.print("nombre de n-uplets ordonnés " + p + " elements parmis " + n + " elements : ");
        System.out.println(arrangement(n, p));

            //affichage triangle de pascal
        System.out.println("-------------------");
        System.out.println("triangle de pascal pour " + n + " n-uplets : ");
        System.out.println("-------------------");


        //affichage premiere ligne
        int decompte_n = 0;
        System.out.print("     ");
        for (int e = 0; e<=n; e++){
            System.out.print(" -" + decompte_n + "-");
            decompte_n++;
        }
        decompte_n = 0;
        //affichage reste
        System.out.println();
        for (int i = 0; i<=n; i++){
            trianglePascal(decompte_n);
            decompte_n++;
        }
    }
}
