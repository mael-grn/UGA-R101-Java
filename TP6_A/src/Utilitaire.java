
import java.util.ArrayList;
import java.util.Scanner;

public class Utilitaire {

    public static boolean saisieYN() {
        // {} => {saisie controlée de y ou n}

        Scanner lecteur = new Scanner(System.in);
        char saisie = 'o';

        System.out.print(">>> ");

        while ((int)saisie != 121 & (int)saisie != 110){
            saisie = lecteur.next().charAt(0);
            if ((int)saisie != 121 & (int)saisie != 110){
                System.out.print("saisir uniquement y ou n : ");
            }
        }
        return (int)saisie == 121;
    }   // retourne bool si oui ou non

    public static int indString(ArrayList<String> vString, String uneString) {
        // {vString trié} => { résultat = -1 si uneString est déjà dans vString,
        // sinon, résultat = indice où il faudrait
        // placer uneString dans vString (en respect du tri) }

        if (existString(vString, uneString)){return -1;}     // si uneString trouvé dans vString

        else {      // si uneString pas trouvé dans vString, recherche par dichotomie son placement

            int inf = 0;        // pour la recherche de l'indice ou placer unestring
            int sup = vString.size();
            int m;

            while (inf < sup){      // recherche dichotomie
                m = (inf + sup)/2;
                if (vString.get(m).compareTo(uneString) >=0) {
                    sup = m;
                }
                else {
                    inf = m+1;
                }
            }
            return sup;
        }
    }   //si uneString dans vString

    public static boolean existString(ArrayList<String> vString, String uneString) {
        // { vString trié } => { résultat = vrai si uneString appartient à vString }

        int i = 0;

        if (vString.size() == 0){return false;}     //car vString peut etre vide au debut

        while (i < vString.size() && vString.get(i).compareToIgnoreCase(uneString) != 0){i++;}

        return i != vString.size();
    }   //si unesString dans vString bool

    public static String saisieCont(ArrayList<String> vCont) {
        // { vCont trié, non vide } =>
        // { résultat = nom d'un élément de vCont
        // saisi par l'utilisateur }

        String saisie;

        System.out.println("saisir un continent ");
        Scanner lecteur = new Scanner(System.in);

        do {
            System.out.print(">>> ");
            saisie = lecteur.nextLine();

            if (!(existString(vCont, saisie))){
                System.out.println("veuillez saisir un continent qui existe : ");
            }
        } while (!(existString(vCont, saisie)));
        return saisie;
    }   //saisie control continents

    public static ArrayList<Pays> paysDeCont(ArrayList<Pays> vPays, String cont) {
        // { cont est le continent d'au moins un pays de vPays } =>
        // { résultat = vecteur contenant les pays de vPays
        // dont le continent est cont }

        ArrayList<Pays> vect = new ArrayList<>();
        int i = 0;

        while (vPays.size() > i){
            if (vPays.get(i).getContinent().compareToIgnoreCase(cont) == 0){
                vect.add(vPays.get(i));
            }
            i++;
        }

        return vect;
    }   // pays dont le continent est cont

    public static int rechPaysT(ArrayList<Pays> vPays, String nomP) {
        // { vPays trié sur le nom } =>
        // { résultat = indice dans vPays du pays de nom nomP s'il existe,
        // vPays.size() si pas de pays de nom nomP dans vPays }

        int i = 0;

        while (i < vPays.size() && vPays.get(i).getNom().compareToIgnoreCase(nomP) != 0){
            i++;
        }
        return i;
    }   // meme que exist string, mais avec arraylist pays

    public static void paysMoinsPeuples(ArrayList<Pays> vMonde, boolean YN) {
        //{ vMonde non vide } =>
        // {les caractéristiques du ou des pays le(s) moins peuplé(s)
        // dans vMonde ont été affichées}

        int i = 0;
        int valPop = vMonde.get(0).getPopulation();
        ArrayList<String> pays = new ArrayList<>();

        while (vMonde.size() > i){

            if (vMonde.get(i).getPopulation() < valPop){        // pour tous les pays de monde, verifier population et sauvegarder nom pays

                if (!YN){
                    pays.clear();     // si utilisateur ne veux pas afficher plusieurs pays, enlever tous les precedents
                }
                valPop = vMonde.get(i).getPopulation();
                pays.add(vMonde.get(i).getNom());
            }
            i++;
        }
        System.out.println("\npays le(s) moins peuplé(s) : ");
        for (int ii = 0; ii<pays.size(); ii++){
            System.out.println(vMonde.get(rechPaysT(vMonde, pays.get(ii))));
        }
    }

    public static void contExtremes(ArrayList<Pays> vMonde, ArrayList<String> vCont) {
        // { vMonde non vide } =>
        // { le nombre de pays et le nom du ou des continents comptant
        // le plus grand nombre, respectivement le plus petit nombre
        // de pays ont été affichés }

        int val;
        int valMin = 100;
        int valMax = 0;
        String contMin = "";
        String contMax = "";

        for (int i = 0; i < vCont.size(); i++) {      // pour chaque contient de vCont...
           val = paysDeCont(vMonde, vCont.get(i)).size();

           if (val > valMax) {
               valMax = val;
               contMax = vCont.get(i);
           } else if (val < valMin) {
               valMin = val;
               contMin = vCont.get(i);
           }
        }
        System.out.println("\nContinent comptant le plus de pays (" + valMax + ") : " + contMax);
        System.out.println("\nContinent comptant le moins de pays (" + valMin + ") : " + contMin);
    }
}

