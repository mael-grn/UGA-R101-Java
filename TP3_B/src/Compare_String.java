import java.util.Scanner;

public class Compare_String {

    private static int compareToManuel(String ch1, String ch2){

        // {} => {la saisie de deux chaines, puis leurs comparaison comme compare to mais sans l'utiliser}

        // si ch1 est vide, on retourne l'opposé du nombre de caractère de ch2
        if (ch1.length() == 0) {
            return (0-(ch2.length()));

        // si ch2 est vide, inversement
        } else if (ch2.length() == 0) {
            return (ch1.length());
        }

        int comparaison = 0;
        int i = 0;

        // dans la boucle apres le &, c'est pour pas depasser le nombre de char des chaines.
        while(comparaison == 0 & (i < ch1.length() & i < ch2.length())){
            comparaison = (int) ch1.charAt(i) - ch2.charAt(i);
            i++;
        }
        // voir les conditions au debut du PDF
        if (comparaison == 0) {
            return ch1.length() - ch2.length();
        } else {
            return comparaison;
        }


    }

    public static void main(String[] args) {

        String unChat = "chat";
        String unPetitChat = "chaton";
        String unChien = "chien";
        String toBeReplaced = "";
        Scanner lecteur = new Scanner(System.in);

        // 1 - Premiers tests
        System.out.println("------------------------------------------------------");
        System.out.println("Premiers tests de comparaison de chaînes");
        System.out.println("------------------------------------------------------");
        System.out.println("Chaînes comparées...");
        System.out.println("    unChat --> \"" + unChat + "\"");
        System.out.println("    unChien --> \"" + unChien + "\"");
        System.out.println("    unPetitChat --> \"" + unPetitChat + "\"");
        System.out.println("Comparaisons entre ces chaînes (casse prise en compte)");
        System.out.println("    unChat comparé à unChien --> " + unChat.compareTo(unChien));
        System.out.println("    unChien comparé à unPetitChat --> " + unChien.compareTo(unPetitChat));
        System.out.println("    unPetitChat comparé à unChat ---> " + unPetitChat.compareTo(unChat));
        System.out.println("    unPetitChat comparé à unPetitChat ---> " + unPetitChat.compareTo(unPetitChat));
        System.out.println("------------------------------------------------------");

        /* reponse aux questions :

        1 - ordre lexicographique : unchat, unpetitchat, unchien
        2 - unChat comparé à un chien : unChien suit unChat
            unChien comparé à unPetitChat : unChien suit unPetitChat
            unPetitChat comparé à unChat : unPetit Chat suit unChien
            unPetitChat comparé à unPetitChat : les deux sont identiques
         */

        // 2 - Comparaisons entre des chaînes saisies par l'utilisateur

        // chaînes à comparer
        String ch1 = "", ch2 = "";
        // résultat de la comparaison entre ch1 et ch2
        int resComp = 0;

        /*
            À COMPLÉTER...
            Initialisation par saisie des chaînes ch1 et ch2
            Les messages invitant l'utilisateur à saisir chaque chaîne doivent être clairs
       */
        System.out.print("saisir un premier string (ch1) : " );
        ch1 = lecteur.nextLine();

        System.out.print("saisir un deuxieme string (ch2) : ");
        ch2 = lecteur.nextLine();

        /*
            À NE PAS MODIFIER !
        */
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("Comparaison entre les chaînes \"" + ch1 + "\" et \"" + ch2 + "\"");
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        /*
            À MODIFIER...
            Initialisation des variables nbCarCh1 et nbCarCh2 (cf. commentaires)
       */
        // nombre de caractères de ch1 (à initialiser)
        int nbCarCh1 = ch1.length();
        // nombre de caractères de ch2 (à intialisiser)
        int nbCarCh2 = ch2.length();

        /*
            À NE PAS MODIFIER !
        */
        // position du dernier caractère pouvant être comparé dans ch1 et ch2
        int indDerCarToCompare = Math.min(nbCarCh1, nbCarCh2)-1;
        /*
            À COMPLÉTER...
            Nouvelles déclarations jugées nécessaires,
            Instructions de calcul du résultat de la comparaison entre ch1 et ch2
        */

        resComp = compareToManuel(ch1, ch2);

       /*
            À NE PAS MODIFIER !
        */
        System.out.println("Résultat de la comparaison de \"" + ch1 + "\" et \"" + ch2 + "\" par programme : " + resComp);
        System.out.println();
        System.out.println("Vérification...");
        System.out.println("Résultat de la comparaison de \"" +ch1 + "\" et \"" + ch2 + "\" avec compareTo : " + ch1.compareTo(ch2));
    }
}
