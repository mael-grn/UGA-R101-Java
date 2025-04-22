import java.util.ArrayList;

public class UtilitairePaireChaineEntier {


    public static int indicePourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i = 0;
        while (i < listePaires.size() && listePaires.get(i).getChaine().compareToIgnoreCase(chaine) != 0) {
            i++;
        }
        if (i < listePaires.size()) {
            return i;
        } else {
            return -1;
        }
    }
    public static int indicePourChaineDicho(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        if (listePaires.size() == 0) {
            return -1;
        } else {
            if (chaine.compareTo(listePaires.get(0).getChaine()) < 0) {
                return -1;
            } else if (chaine.compareTo(listePaires.get(listePaires.size() - 1).getChaine()) > 0) {
                return listePaires.size() - 1;
            } else {
                int inf = 0, sup = listePaires.size() - 1, m;
                while (inf < sup) {
                    m = (inf + sup) / 2;
                    if (listePaires.get(m).getChaine().compareTo(chaine) >= 0) {
                        sup = m;
                    } else {
                        inf = m + 1;
                    }
                }
                if (listePaires.get(sup).getChaine().compareTo(chaine) == 0) {
                    return sup;
                } else {
                    return -1;
                }
            }
        }
    }
    public static int indicePourChaineDichoNbComp(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int nbComp = 1;
        if (listePaires.size() == 0) {
            return nbComp;
        } else {
            if (chaine.compareTo(listePaires.get(0).getChaine()) < 0) {
                return nbComp;
            } else if (chaine.compareTo(listePaires.get(listePaires.size() - 1).getChaine()) > 0) {
                return nbComp;
            } else {
                int inf = 0, sup = listePaires.size() - 1, m;
                while (inf < sup) {
                    m = (inf + sup) / 2;
                    nbComp++;
                    if (listePaires.get(m).getChaine().compareTo(chaine) >= 0) {
                        sup = m;
                    } else {
                        inf = m + 1;
                    }
                }
                nbComp++;
                if (listePaires.get(sup).getChaine().compareTo(chaine) == 0) {
                    return nbComp;
                } else {
                    return nbComp;
                }
            }
        }
    }

    public static int entierPourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        // {} => {entier ou chaine apparait dans listepaire}
        int i = 0;
        while (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) != 0) {
            i++;
        }
        if (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) == 0) {
            return listePaires.get(i).getEntier();
        } else {
            return 0;
        }
    }
    public static int entierPourChaineTrie(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int ind=indicePourChaineDicho(listePaires,chaine);
        if (ind!=-1){
            return listePaires.get(ind).getEntier();
        } else {
            return 0;
        }
    }
    public static int entierPourChaineTrieNbComp(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int ind=indicePourChaineDicho(listePaires,chaine);
        int nbComp=indicePourChaineDichoNbComp(listePaires, chaine);
        if (ind!=-1){
            return nbComp++;
        } else {
            return nbComp++;
        }
    }

    public static String chaineMax(ArrayList<PaireChaineEntier> listePaires) {
        int nb = listePaires.get(0).getEntier();
        String mot = listePaires.get(0).getChaine();
        for(int i = 1; i<listePaires.size();i++){
            if(listePaires.get(i).getEntier()>nb){
                nb = listePaires.get(i).getEntier();
                mot = listePaires.get(i).getChaine();
            }
        }
        return mot;
    }


    public static float moyenne(ArrayList<PaireChaineEntier> listePaires) {
        float moy = 0;
        for (int i = 0; i < listePaires.size(); i++) {
            moy += listePaires.get(i).getEntier();
        }
        return (moy / listePaires.size());
    }

    public static void triFusion(ArrayList<PaireChaineEntier> vPaire, int inf, int sup) {
        //{vPaire[inf..sup] non vide} => {vPaire[inf..sup] trié}
        if (inf < sup) {
            int m = (inf + sup) / 2;
            triFusion(vPaire, inf, m);
            triFusion(vPaire, m + 1, sup);
            fusionTabGTabD(vPaire, inf, m, sup);
        }
    }

    public static void fusionTabGTabD(ArrayList<PaireChaineEntier> vPaire, int inf, int m, int sup) {
        //{ inf <= sup, m = (inf+sup)/2, vInt[inf..m] trié, vInt[m+1..sup] trié
        // => { VInt[inf..sup] trié }
        ArrayList<PaireChaineEntier> temp = new ArrayList<>();
        int ig = inf, id = m + 1, is = 0;
        while (ig <= m & id <= sup) {
            if (vPaire.get(ig).getChaine().compareToIgnoreCase(vPaire.get(id).getChaine()) <= 0) {
                temp.add(vPaire.get(ig));
                ig++;
            } else {
                temp.add(vPaire.get(id));
                id++;
            }
        }
        while (ig <= m) {
            temp.add(vPaire.get(ig));
            ig++;
        }
        while (id <= sup) {
            temp.add(vPaire.get(id));
            id++;
        }
        while (is < temp.size()) {
            vPaire.set(is + inf, temp.get(is));
            is++;
        }
    }

}
