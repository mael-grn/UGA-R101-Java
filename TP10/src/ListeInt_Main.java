import java.util.Scanner;

public class ListeInt_Main {
    public static void main(String[] args) {

        Scanner lecteur = new Scanner(System.in);
        int unEnt;

        /*
        ListeChainee<Integer> listeInt = new ListeChainee<>();

        for (int i = 1; i <= 15; i++){
            int tempInfo = (int) (Math.random()*100);
            Cellule<Integer> temp = new Cellule<>(tempInfo);
            listeInt.insereAtPosit(i, temp.getInfo());
        }
        System.out.println("Longueur de la liste : " + listeInt.getLongueur());
        System.out.print("Affichage de gauche à droite : ");
        listeInt.afficheGaucheDroiteRec();

        System.out.println("\n*** Somme des elements de la liste");
        System.out.println("    Iteratifs : " + Utilitaire.sommeListeInt(listeInt));
        System.out.println("    Recursif : " + Utilitaire.sommeListeIntRec(listeInt));

        System.out.println("\n*** Recherche d'un entier dans la liste : ");
        unEnt = Utilitaire.getIntV2(lecteur);

        if (Utilitaire.rechValListe(listeInt, unEnt)) {
            System.out.println("    L'entier est present dans la liste (recherche recursive)");
        } else {
            System.out.println("    L'entier n'est pas present dans la liste (recherche recursive)");
        }

        int posVal = Utilitaire.premPosVal(listeInt, unEnt);
        Cellule<Integer> cellVal = listeInt.getTete();

        while (cellVal.getCelluleSuivante() != null && unEnt != cellVal.getInfo()) {
            cellVal = cellVal.getCelluleSuivante();
        }
        if (cellVal.getCelluleSuivante() == null) {
            if (cellVal.getInfo() == unEnt) {
                System.out.println("    L'entier à bien été trouvé à l'indice " + posVal + ", cela correspond à " + cellVal.getInfo() + " (recherche iterative)");
            } else {
                System.out.println("    La recherche iterative de l'element à échoué, car celui ci n'est pas present dans la liste");
            }
        } else {
            System.out.println("    L'entier à bien été trouvé à l'indice " + posVal + ", cela correspond à " + cellVal.getInfo() + " (recherche iterative)");
        }

         */

        ListeChainee<Integer> listeTriee = new ListeChainee<>();

        System.out.println("Insertion de 5 elements : ");

        int val;

        for (int i = 0; i < 5; i++){
            val = Utilitaire.getIntV2(lecteur);
            Utilitaire.insereDansListeTriee(listeTriee, val);
        }
        System.out.println(Utilitaire.verifTri(listeTriee));

        listeTriee.afficheGaucheDroiteRec();









    }
}
