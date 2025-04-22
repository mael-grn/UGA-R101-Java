import java.util.ArrayList;

public class Utilitaire_T {

    public static ArrayList<Pays> TriBulle(ArrayList<Pays> vPays) {
        // { vPays non vide }
        // => { résultat = vecteur de Pays trié selon l'ORDRE(continent, nom)
        // en utilisant la méthode du TRI À BULLES AMÉLIORÉ }
        int i = 0, j;
        boolean permut = true;
        while (permut) {
            j = vPays.size() - 1;
            permut = false;
            while (j > i) {
                if (vPays.get(j).compareTo(vPays.get(j - 1)) == -1) {
                    Pays temp = vPays.get(j);
                    vPays.set(j, vPays.get(j - 1));
                    vPays.set(j - 1, temp);
                    permut = true;
                }
                j--;
            }
            i++;
        }
        return vPays;
    }

    public static boolean verifTri(ArrayList<Pays> vPays) {
        // { } => {résultat = vrai si vPays trié selon l'ORDRE(continent, nom)}
        int i = 0;
        while (i < vPays.size() - 1 && vPays.get(i).compareTo(vPays.get(i + 1)) == -1) {
            i++;
        }
        if (i == vPays.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public static int rechDichoIter(ArrayList<Pays> vPays, String contP, String nomP) {
        // { vPays trié selon l'ORDRE(continent, nom} =>
        // { résultat = * indice du pays de continent contP et de nom nomP
        // s'il existe dans le vecteur vPays
        // * -1 si non trouvé
        // LA RECHERCHE EST DICHOTOMIQUE ITÉRATIVE }
        int pop = 0, surf = 0;
        Pays unPays = new Pays(nomP, contP, pop, surf);
        if (vPays.get(vPays.size() - 1).compareTo(unPays) == -1) {
            return -1;
        } else {
            int inf = 0, sup = vPays.size() - 1, m;
            while (inf < sup) {
                m = (inf + sup) / 2;
                if (vPays.get(m).compareTo(unPays) >= 0) {
                    sup = m;
                } else {
                    inf = m + 1;
                }
            }
            if (vPays.get(sup).compareTo(unPays) == 0) {
                return sup;
            } else {
                return -1;
            }
        }
    }


    public static int rechDichoRec(ArrayList<Pays> vPays, String contP, String nomP) {
        // { vPays trié selon l'ORDRE(continent, nom) } =>
        // { résultat = * indice du pays de continent contP et de nom nomP
        // s'il existe dans le vecteur vPays
        // * -1 si non trouvé }
        int pop = 0, surf = 0;
        Pays unPays = new Pays(contP, nomP, pop, surf);
        if (vPays.get(vPays.size() - 1).compareTo(unPays) == -1) {
            return -1;
        } else {
            return rechDichoWorker(vPays, contP, nomP, 0, vPays.size() - 1);
        }


    }


    public static int rechDichoWorker(ArrayList<Pays> vPays, String contP, String nomP,
                                      int inf, int sup) {
        // { vPays trié selon l'ORDRE(continent,nom) } =>
        // { résultat = indice du pays de continent contP et de nom nomP, s'il existe
        // dans le vecteur vPays[inf .. sup], ou -1 si non trouvé
        // LA RECHERCHE EST DICHOTOMIQUE RÉCURSIVE }
        int pop = 0, surf = 0;
        Pays unPays = new Pays(nomP, contP, pop, surf);
        if (inf == sup) {
            if (vPays.get(inf) == unPays) {
                return inf;
            } else {
                return -1;
            }
        } else {
            int m = (inf + sup) / 2;
            if (vPays.get(m).compareTo(unPays) >= 0) {
                return rechDichoWorker(vPays, contP, nomP, inf, m);
            } else {
                return rechDichoWorker(vPays, contP, nomP, m + 1, sup);
            }
        }
    }


    public static Pays plusGrandPaysIter(ArrayList<Pays> vPays) {
        // {vPays non vide} => {résultat = élément de vPays ayant
        // la plus grande superficie
        // RECHERCHE ITÉRATIVE}
        int i = 1;
        Pays plusGrandP = vPays.get(0);
        while (i < vPays.size()) {
            if (vPays.get(i).getSuperficie() > plusGrandP.getSuperficie()) {
                plusGrandP = vPays.get(i);
            }
            i++;
        }
        return plusGrandP;
    }

    public static Pays plusGrandPaysDPR(ArrayList<Pays> vPays) {
        // {vPays non vide} => {résultat = élément de vPays ayant
        // la plus grande superficie}
        return maxPaysDPRWorker(vPays, 0, vPays.size() - 1);

    }

    public static Pays maxPaysDPRWorker(ArrayList<Pays> vPays,
                                        int inf, int sup) {
        // {vPays non vide} => {résultat = élément de vPays[inf..sup] ayant
        // la plus grande superficie
        // RECHERCHE RÉCURSIVE }
        if (sup == inf) {
            return vPays.get(inf);
        } else {
            int m = (inf + sup) / 2;
            Pays maxg = maxPaysDPRWorker(vPays, inf, m), maxd = maxPaysDPRWorker(vPays, m + 1, sup);
            Math.max(maxg.getSuperficie(), maxd.getSuperficie());
            if (Math.max(maxg.getSuperficie(), maxd.getSuperficie()) == maxg.getSuperficie()) {
                return maxg;
            } else {
                return maxd;
            }
        }


    }


    public static int indMinPopSeq(ArrayList<Pays> vPays) {
        // { vPays non vide } =>
        // { résultat = indice dans vPays de l'élément
        //  dont le nombre d'habitants est le plus faible
        // RECHERCHE ITÉRATIVE }
        int i = 1, ind = 0;
        Pays plusPetitPop = vPays.get(0);
        while (i < vPays.size()) {
            if (vPays.get(i).getPopulation() < plusPetitPop.getPopulation()) {
                plusPetitPop = vPays.get(i);
                ind = i;
            }
            i++;
        }
        return ind;
    }

    public static int indMinPopDPR(ArrayList<Pays> vPays) {
        // { vPays non vide } =>
        // {résultat = indice de l'élément de vPays dont
        // le nombre d'habitants est le plus faible}
        return indMinPopDPRWorker(vPays, 0, vPays.size() - 1);
    }


    public static int indMinPopDPRWorker(ArrayList<Pays> vPays,
                                         int inf, int sup) {
        // { vPays non vide } =>
        // { résultat = indice dans vPays[inf..sup]
        // de l'élément dont le d'habitants est le plus faible
        // RECHERCHE RÉCURSIVE }
        if (sup == inf) {
            return inf;
        } else {
            int m = (inf + sup) / 2;
            int maxg = indMinPopDPRWorker(vPays, inf, m), maxd = indMinPopDPRWorker(vPays, m + 1, sup);
            if (Math.min(vPays.get(maxg).getPopulation(), vPays.get(maxd).getPopulation()) == vPays.get(maxg).getPopulation()) {
                return maxg;
            } else {
                return maxd;
            }
        }

    }




}