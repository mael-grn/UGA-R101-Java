import java.util.ArrayList;

public class Utilitaire {

    public static ArrayList<Pays> TriBulle(ArrayList<Pays> vPays) {
        // { vPays non vide }
        // => { résultat = vecteur de Pays trié selon l'ORDRE(continent, nom)
        // en utilisant la méthode du TRI À BULLES AMÉLIORÉ }

        int j;
        int i = 0;
        boolean onAPermute = true;

        while (onAPermute) {
            j = vPays.size()-1;
            onAPermute = false;

            while (j > i) {
                if ((vPays.get(j).compareTo(vPays.get(j-1)) < 0)) {
                    Pays temp = vPays.get(j);
                    vPays.set(j, vPays.get(j-1));
                    vPays.set(j-1, temp);
                    onAPermute = true;
                }
                j = j-1;
            }
            i = i+1;
        }
        return vPays;
    }

    public static boolean verifTri(ArrayList<Pays> vPays) {
        // { } => {résultat = vrai si vPays trié selon l'ORDRE(continent, nom)}
        int i = 1;
        while (i < vPays.size() && vPays.get(i).compareTo(vPays.get(i-1)) >= 0){
            i++;
        }
        return i == vPays.size();
    }

    public static int rechDichoIter(ArrayList<Pays> vPays, String contP, String nomP) {
        // { vPays trié selon l'ORDRE(continent, nom} =>
        // { résultat = * indice du pays de continent contP et de nom nomP
        // s'il existe dans le vecteur vPays
        // * -1 si non trouvé
        // LA RECHERCHE EST DICHOTOMIQUE ITÉRATIVE }
        Pays Temp = new Pays(nomP, contP, 0, 0);

        if (vPays.size() == 0 || vPays.get(vPays.size()-1).compareTo(Temp) < 0){
            System.out.println("a");
            return -1;
        }else {
            int sup = vPays.size() - 1;
            int inf = 0;
            int m;

            while (inf < sup) {
                m = (inf + sup) / 2;
                if (vPays.get(m).compareTo(Temp) >= 0) {
                    sup = m;
                } else {
                    inf = m + 1;
                }
            }
            if (vPays.get(sup).compareTo(Temp) == 0) {
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
        Pays Temp = new Pays(nomP, contP, 0, 0);

        if (vPays.size() == 0 || vPays.get(vPays.size()-1).compareTo(Temp) < 0){
            return -1;
        }else {
            return rechDichoWorker(vPays, contP, nomP, 0, vPays.size()-1);
        }
    }

    public static int rechDichoWorker(ArrayList<Pays> vPays, String contP, String nomP, int inf, int sup) {
        // { vPays trié selon l'ORDRE(continent,nom) } =>
        // { résultat = indice du pays de continent contP et de nom nomP, s'il existe
        // dans le vecteur vPays[inf .. sup], ou -1 si non trouvé
        // LA RECHERCHE EST DICHOTOMIQUE RÉCURSIVE }
        Pays Temp = new Pays(nomP, contP, 0, 0);
        int m = (inf + sup) / 2;

        if (vPays.get(sup).compareTo(Temp) == 0){
            return sup;
        } else if (inf == sup){
            return -1;
        }
        if (vPays.get(m).compareTo(Temp) >= 0){
            return rechDichoWorker(vPays, contP, nomP, inf, m);
        } else {
            return rechDichoWorker(vPays, contP, nomP, m + 1, sup);
        }
    }

    public static Pays plusGrandPaysIter(ArrayList<Pays> vPays) {
        // {vPays non vide} => {résultat = élément de vPays ayant
        // la plus grande superficie
        // RECHERCHE ITÉRATIVE}
        int max = vPays.get(0).getSuperficie();
        int ind = 0;

        for (int i = 0; i < vPays.size(); i++){
            if (vPays.get(i).getSuperficie() > max) {
                max = vPays.get(i).getSuperficie();
                ind = i;
            }
        }
        return vPays.get(ind);
    }

    public static Pays plusGrandPaysDPR(ArrayList<Pays> vPays) {
        // {vPays non vide} => {résultat = élément de vPays ayant
        // la plus grande superficie}
        return maxPaysDPRWorker(vPays, 0, vPays.size()-1);
    }
    public static Pays maxPaysDPRWorker(ArrayList<Pays> vPays, int inf, int sup) {
        // {vPays non vide} => {résultat = élément de vPays[inf..sup] ayant
        // la plus grande superficie
        // RECHERCHE RÉCURSIVE }

        if (inf == sup){
            return vPays.get(inf);
        } else {
            int m = (inf + sup) / 2;

            Pays maxG = maxPaysDPRWorker(vPays, inf, m);

            Pays maxD = maxPaysDPRWorker(vPays, m+1, sup);

            if (Math.max(maxG.getSuperficie(), maxD.getSuperficie()) == maxG.getSuperficie()){
                return maxG;
            } else {
                return maxD;
            }
        }

    }

    public static int indMinPopSeq(ArrayList<Pays> vPays) {
        // { vPays non vide } =>
        // { résultat = indice dans vPays de l'élément
        // dont le nombre d'habitants est le plus faible
        // RECHERCHE ITÉRATIVE }

        int min = vPays.get(0).getPopulation();
        int ind = 0;

        for (int i = 0; i < vPays.size(); i++){
            if (vPays.get(i).getPopulation() < min){
                min = vPays.get(i).getPopulation();
                ind = i;
            }
        }
        return ind;
    }

    public static int indMinPopDPR(ArrayList<Pays> vPays) {
        // { vPays non vide } =>
        // {résultat = indice de l'élément de vPays dont
        // le nombre d'habitants est le plus faible}
        return indMinPopDPRWorker(vPays, 0, vPays.size()-1);
    }
    public static int indMinPopDPRWorker(ArrayList<Pays> vPays, int inf, int sup) {
        // { vPays non vide } =>
        // { résultat = indice dans vPays[inf..sup]
        // de l'élément dont le d'habitants est le plus faible
        // RECHERCHE RÉCURSIVE }

        if (inf == sup) {
            return inf;
        } else {
            int m = (inf + sup) / 2;

            int maxG = indMinPopDPRWorker(vPays, inf, m);

            int maxD = indMinPopDPRWorker(vPays, m+1, sup);

            if (Math.min(vPays.get(maxG).getPopulation(), vPays.get(maxD).getPopulation()) == vPays.get(maxG).getPopulation()){
                return maxG;
            } else {
                return maxD;
            }
        }
    }

}
