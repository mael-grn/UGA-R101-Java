import java.util.*;
public class AlgorechIntger {
    public static PaireResultatCompteur<Integer> indRechSeqO(ArrayList<Integer> vInt,
                                                             int unInt) {
//{ vInt non vide, trié }
// => { à la fin du traitement, ind est l'indice de la 1ère occurrence
// de unInt dans vInt, ou est égal à -1 si unInt n'est pas dans vInt
//
// résultat = variable de type PaireResCompteur avec :
// - res = ind
// - compteur = nombre de comparaisons effectuées
// entre inInt et un élément de vInt
// RECHERCHE SÉQUENTIELLE }
        int index = 0;
        int nb_comp = 1;
        while (index < vInt.size() && unInt != vInt.get(index)) {
            nb_comp++;
            index++;
        }
        return new PaireResultatCompteur<>(index, nb_comp);
    }
    public static PaireResultatCompteur<Integer> indRechDichoItO(ArrayList<Integer> vInt,
                                                            int unInt) {
//{ vInt non vide, trié }
// => { à la fin du traitement, ind est l'indice de la 1ère occurrence
// de unInt dans vInt,ou est égal à -1 si unInt n'est pas dans vInt
//
// résultat = variable de type PaireResCompteur avec :
// - res = ind
// - compteur = nombre de comparaisons effectuées
// entre inInt et un élément de vInt
// RECHERCHE DICHOTOMIQUE ITÉRATIVE }
        int nb_comp=2;
        if (vInt.get(vInt.size()-1)>unInt) {
            return new PaireResultatCompteur<>(-1,nb_comp);

        } else {
            int inf = 01;
            int sup = vInt.size() - 1;
            int m;
            while (inf < sup) {
                m = (inf + sup) / 2;
                nb_comp=nb_comp+2;
                if (vInt.get(m) >= unInt) {
                    sup = m;
                } else {
                    inf = m + 1;
                }
            }
            nb_comp++;
            if (vInt.get(sup)==unInt){
                return new PaireResultatCompteur<>(sup,nb_comp);} else {
                return new PaireResultatCompteur<>(-1,nb_comp);
            }
        }
    }
    public static PaireResultatCompteur<Integer> indRDichoRecO(ArrayList<Integer> vInt,
                                                          int unInt) {
//{ vInt non vide, trié }
// => { à la fin du traitement, ind est l'indice de la 1ère occurrence
// de unInt dans vInt, ou est égal à -1 si non trouvé
//
// résultat = variable de type PaireResCompteur avec :
// - res = ind
// - compteur = nombre de comparaisons effectuées
// entre inInt et un élément de vInt[inf..sup] }
        if (vInt.get(vInt.size() - 1)< unInt) {
            return new PaireResultatCompteur(-1,1);
        } else {
            return indRDichoRecWorkerO(vInt,unInt,0,vInt.size()-1);
        }}
    public static PaireResultatCompteur<Integer> indRDichoRecWorkerO(ArrayList<Integer> vInt,
                                                                int unInt, int inf,
                                                                int sup) {
//{ vInt[inf..sup] non vide, trié }
// => { à la fin du traitement, ind est l'indice de la 1ère occurrence
// de unInt dans vInt[inf..sup], ou est égal à -1 si non trouvé
//
// résultat = variable de type PaireResCompteur avec :
// - res = ind
// - compteur = nombre de comparaisons effectuées
// entre inInt et un élément de vInt[inf..sup]
// RECHERCHE DICHOTOMIQUE RÉCURSIVE }
        if (inf == sup) {
            if (vInt.get(sup) == unInt) {
                return new PaireResultatCompteur<>(sup,1);
            } else {
                return new PaireResultatCompteur<>(-1,1);
            }
        } else {
            int m = (inf + sup) / 2;
            PaireResultatCompteur<Integer> paireRC;
            if (vInt.get(m) < unInt) {
                paireRC = indRDichoRecWorkerO(vInt, unInt, m+1, sup);
            } else {
                paireRC = indRDichoRecWorkerO(vInt, unInt, inf, m);
            }
            return new PaireResultatCompteur<>(paireRC.getRes(),paireRC.getCompteur() + 1);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(-45, -45, -10, 9, 20, 30, 75));
        System.out.println(test+ ""+(test.size()-1));
        Scanner lecteur = new Scanner(System.in);
        int unInt=lecteur.nextInt();
        lecteur.nextLine();
        System.out.println("rech seq ite "+ indRechDichoItO(test,unInt).getRes() + ""+  indRechDichoItO(test,unInt).getCompteur());
        System.out.println("rech dico ite "+ indRechDichoItO(test,unInt).getRes()+" "+ indRechDichoItO(test,unInt).getCompteur());
        System.out.println("rech dico rec "+ indRDichoRecO(test,unInt).getRes()+" "+indRDichoRecO(test,unInt).getCompteur() );
    }
}

