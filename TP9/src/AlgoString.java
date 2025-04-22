import java.util.*;
public class AlgoString {
    public static void affichVect(ArrayList<String> vString) {
// { vString non vide } =>
// { le contenu de vString est affiché de façon "lisible" sur un petit écran }
        System.out.println();
        System.out.println("-- VECTEUR D'INSTRUMENTS DE CUISINE --");
        System.out.print("[");
        int i = 0;
        int nb = 0;
        while (i < vString.size() - 1) {
            System.out.print(vString.get(i) + ", ");
            i++;
            nb++;
            if (nb % 7 == 0 && nb < vString.size() - 1) {
                System.out.print("\n ");
            }
        }
        System.out.println(vString.get(vString.size() - 1) + "]");
        System.out.println();
        System.out.println("Nombre d'éléments : " + vString.size());
        System.out.println("--------------------------------------");
        System.out.println();
    }
    public static PaireResultatCompteur<Integer> nbCarMinOutille(ArrayList<String> vString) {
// { vInstruments non vide }
// => { à la fin du traitement, min est le nombre de caractères
// minimum d'un élément du vecteur
//
// résultat = variable de type PaireResCompteur avec
// - res = min
// - compteur = nombre de comparaisons effectuées pour
// produire min
// RECHERCHE SÉQUENTIELLE }
        int index=1;
        int nb_comp=0;
        int long_min=vString.get(0).length();
        while (index<vString.size()){
            nb_comp++;
            if (long_min>vString.get(index).length()){
                long_min=vString.get(index).length();
            }
            index++;
        }
    return new PaireResultatCompteur(long_min,nb_comp);
    }
    public static PaireResultatCompteur<Integer> nbCarMinDPRO(ArrayList<String> vString) {
//{ vString non vide }
// => { à la fin du traitement, min est le nombre de caractères
// minimum d'un élément de vString
//
// résultat = variable de type PaireResCompteur avec
// - res = min
// - compteur = nombre de comparaisons effectuées pour
// produire min
        if (vString.size()==1){
            return new PaireResultatCompteur(vString.get(0),0);
        } else {
            return nbCarMinDPRWorkerO(vString,0,vString.size()-1);
        }
    }

        public static PaireResultatCompteur<Integer> nbCarMinDPRWorkerO(ArrayList<String> vString,
        int inf, int sup) {
// { vString non vide }
// => { à la fin du traitement, min est le nombre de caractères
// minimum d'un élément de vString[inf..sup]
//
// résultat = variable de type PaireResCompteur avec
// - res = min
// - compteur = nombre de comparaisons effectuées pour
// produire min
// RECHERCHE RÉCURSIVE DIVISER POUR RÉGNER }
            if (inf == sup) {
                return new PaireResultatCompteur<Integer>(vString.get(inf).length(), 1);
            } else {
                int m = (inf + sup) / 2;
                int mingauche = nbCarMinDPRWorkerO(vString, inf, m).getRes();
                int compt_gau= nbCarMinDPRWorkerO(vString, inf, m).getCompteur();
                int mindroit = nbCarMinDPRWorkerO(vString, m+1, sup).getRes();
                int comp_droit=nbCarMinDPRWorkerO(vString, m+1, sup).getCompteur();
                if (mindroit<mingauche) {
                    return new PaireResultatCompteur<Integer>(mindroit,compt_gau+comp_droit);
                } else {
                    return new PaireResultatCompteur<Integer>(mingauche, compt_gau+comp_droit);
                }
            }
        }

    public static void main(String[] args) {
        ArrayList<String> vInstruments = new ArrayList<>(Arrays.asList("casserole", "fourchette",
                "cuillère", "couteau","passoire", "tamis", "spatule", "fouet", "éplucheur", "bain-marie",
                "râpe", "presse-ail", "presse-agrumes","ouvre-boîte", "thermomètre", "pince", "balance",
                "presse-purée", "louche", "minuteur", "ciseaux", "bol","mandoline", "doseur", "shaker"));
    }
}
