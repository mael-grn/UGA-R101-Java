import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Utilitaire {
    public static int getInt(Scanner lecteur) {
        // {} => {résultat = un entier saisi par l'utilisateur, SAISIE CONTRÔLÉE }

        int val;

        try {
            System.out.print("Saisir un entier : ");
            val = lecteur.nextInt();
            return val;
        } catch (InputMismatchException e) {
            lecteur.nextLine();
            return getInt(lecteur);
        }
    }
    public static float getFloat(Scanner lecteur) {
        // {} => {résultat = un réel saisi par l'utilisateur, SAISIE CONTRÔLÉE }

        float val;

        try {
            System.out.print("Saisir un float : ");
            val = lecteur.nextFloat();
            return val;
        } catch (InputMismatchException e){
            lecteur.nextLine();
            return getFloat(lecteur);
        }
    }
    public static int getIntV2(Scanner lecteur) {
    // {} => {résultat = un entier saisi par l'utilisateur, SAISIE CONTRÔLÉE }

        String saisie;

        try {
            System.out.print("Saisir un entier : ");
            saisie = lecteur.nextLine();
            return Integer.parseInt(saisie);
        } catch (NumberFormatException nfe){
            System.out.println("Une erreur s'est produite");
            return getIntV2(lecteur);
        }
    }
    public static float getFloatV2(Scanner lecteur) {
        // {} => {résultat = un réel saisi par l'utilisateur, SAISIE CONTRÔLÉE }
        String saisie;
        float val;

        try {
            System.out.print("Saisir un reel : ");
            saisie = lecteur.nextLine();
            return Float.parseFloat(saisie);
        } catch (NumberFormatException nfe){
            System.out.println("Une erreur s'est produite");
            return getFloatV2(lecteur);
        }
    }

    public static int sommeListeInt(ListeChainee<Integer> liste) {
        //{liste non vide} => {résultat = somme des éléments de liste
        // ALGORITHME ITÉRATIF}

        int val = 0;
        Cellule<Integer> uneCellule = liste.getTete();

        for (int i = 1; i <= liste.getLongueur(); i++) {
            val = val + uneCellule.getInfo();
            uneCellule = uneCellule.getCelluleSuivante();
        }
        return val;
    }

    public static int sommeListeIntRec(ListeChainee<Integer> liste) {
        //{liste non vide} => {résultat = somme des éléments de liste }

        int val = 0;

        val = val + sommeListeIntRecWorker(liste.getTete());

        return val;
    }
    private static int sommeListeIntRecWorker(Cellule<Integer> cellCour) {
        //{ } => {résultat = somme des éléments de la liste de tête cellCour
        // ALGORITHME RÉCURSIF}
        if (cellCour.getCelluleSuivante() == null) {
            return cellCour.getInfo();
        } else {
            return cellCour.getInfo() + sommeListeIntRecWorker(cellCour.getCelluleSuivante());
        }
    }

    public static boolean rechValListe(ListeChainee<Integer> liste, int val) {
        //{} => {résultat = vrai si au moins un élément de liste porte l'info val}
        return rechValListeWorker(liste.getTete(), val);
    }
    private static boolean rechValListeWorker(Cellule<Integer> cellCour, int val) {
        //{} => {résultat = vrai si au moins une valeur de la liste de tête cellCour
        // porte l'info val
        // ALGORITHME RÉCURSIF}
        if (cellCour.getCelluleSuivante() == null) {
            if (cellCour.getInfo() == val) {
                return true;
            } else {
                return false;
            }
        } else if (cellCour.getInfo() == val){
            return true;
        } else {
            return rechValListeWorker(cellCour.getCelluleSuivante(), val);
        }
    }

    public static int premPosVal(ListeChainee<Integer> liste, int val) {
        //{liste non vide} => {résultat = position de la première cellule de liste
        // portant l'info val, 0 si non trouvée
        // ALGORITHME ITÉRATIF}

        Cellule<Integer> uneCellule = liste.getTete();
        int ind = 1;

        while (uneCellule.getCelluleSuivante() != null && uneCellule.getInfo() != val) {
            uneCellule = uneCellule.getCelluleSuivante();
            ind++;
        }

        if (uneCellule.getCelluleSuivante() == null) {
            return 0;
        } else {
            return ind;
        }
    }

    public static void insereDansListeTriee(ListeChainee<Integer> liste, int val) {         //SEULE ET UNIQUE METHODE QUI NE MARCHE PAS
        //{liste triée} => {une cellule d'info = val a été insérée dans liste,
        // liste reste triée après insertion - FORME ITÉRATIVE}




        if (liste.getLongueur() == 0) {
            liste.insereTete(val);
        } else {
            Cellule<Integer> uneCellule = liste.getTete();

            while (uneCellule.getCelluleSuivante() != null && uneCellule.getInfo() < val) {
                uneCellule = uneCellule.getCelluleSuivante();
            }
            if (uneCellule.getCelluleSuivante() == null) {

                liste.afficheGaucheDroiteIter();

                uneCellule.setCelluleSuivante(new Cellule<>(val));
            } else {

                liste.afficheGaucheDroiteRec();

                uneCellule.setCelluleSuivante(new Cellule<>(val, uneCellule.getCelluleSuivante()));
            }

        }
    }
    public static boolean verifTri(ListeChainee<Integer> liste) {
        //{} => {résultat = vrai si liste est triée
        // ALGORITHME ITÉRATIF}
        Cellule<Integer> uneCellule = liste.getTete();

        while (uneCellule.getCelluleSuivante() != null && uneCellule.getInfo() <= uneCellule.getCelluleSuivante().getInfo()) {
            uneCellule = uneCellule.getCelluleSuivante();
        }
        if (uneCellule.getCelluleSuivante() == null) {
            return true;
        } else {
            return false;
        }
    }
}
