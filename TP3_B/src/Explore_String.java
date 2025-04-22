public class Explore_String {

    //----------------------------------------------------------------------------------------------------------fonction
    private static int nb0ccCar(String uneChaine, char uncar) {

        //{} => {résultat = nombre de fois où le caractère unCar
        // apparaît dans la chaîne uneChaine}
        int tailleUneChaine = uneChaine.length();
        int compteur = 0;

        for (int i = 0; i < tailleUneChaine; i++) {

            // verifier pour le caractère n°i, si il correspond à uncar.
            if (uneChaine.charAt(i) == uncar) {
                compteur++;
            }
        }
        return compteur;
    }
    //----------------------------------------------------------------------------------------------------------fonction
    private static int nbLettresMajSansAccent(String uneChaine) {

        // {} => {résultat = nombre de lettres majuscules
        // non accentuées dans la chaîne uneChaine}
        int longeurUneChaine = uneChaine.length();
        int ascii;
        int compteur = 0;

        for (int i = 0; i < longeurUneChaine; i++){

            // verifier pour le caractère n°i, si son code ascii correspond à une lettre maj sans accent (65 à 90)
            ascii = uneChaine.charAt(i);

            if (ascii >= 65 & ascii <= 90) {
                compteur++;
            }
        }
        return compteur;
    }
    //----------------------------------------------------------------------------------------------------------fonction
    private static int nbMots(String uneChaine) {
        // {uneChaine ne contient que :
        // * des lettres de l'alphabet (accentuées ou non)
        // * des espaces
        // * un point final}
        // => {résultat = nombre de mots dans uneChaine
        int longueurUneChaine = uneChaine.length();
        int ascii;
        // compteur = 1 car il y a mot-1 espaces
        int compteur = 1;

        for (int i = 0; i < longueurUneChaine; i++) {

            // verifier si le caractère n°i est un espace (avec code ascii)
            ascii = uneChaine.charAt(i);
            if (ascii == 32) {
                compteur++;
            }

        }
        return compteur;
    }
    //----------------------------------------------------------------------------------------------------------fonction
    private static int longPlusLongsMots(String uneChaine) {
        // {uneChaine ne contient que :
        // * des lettres de l'alphabet (accentuées ou non)
        // * des espaces
        // * un point final}
        // => {résultat = nombre de lettres maximal des mots de uneChaine

        int ascii = 0; //valeur ascii du caractère etudié dans for
        int nbLettres = 0; //valeur temp nblettre du mot etudié
        int nbLettresMax = 0; //valeur definitive du mot le plus long
        int longueurUneChaine = uneChaine.length(); //longueur uneChaine
        char carCourant; //caractère etudié dans for

        // pour le nombre de lettres qu'il y à dans uneChaine...
        for (int i = 0; i < longueurUneChaine; i++) {

            // on recupere le code ascii dun caractère étudié
            carCourant = uneChaine.charAt(i);
            ascii = carCourant;

            //si code ascii n'est pas espace, incrementer nb lettre du mot, sinon sauvegarder sa valeur plus le renitialiser
            if (ascii != 32){
                nbLettres++;

            } else {

                if (nbLettres > nbLettresMax) {
                    nbLettresMax = nbLettres;
                    System.out.println("mot " + nbLettres);
                    nbLettres = 0;
                } else {
                    System.out.println("mot " + nbLettres);
                    nbLettres = 0;
                }
            }
        }

        return nbLettresMax;
    }
    //--------------------------------------------------------------------------------------------------------------main
    public static void main(String[] args) {
        String lipogramme = "Un rond pas tout à fait clos, fini par un trait horizontal";
        String lesVoyellesSansAccent = "aeiouy";

        String ch1 = "il y a huit mots dans cette phrase .";
        String ch2 = "Il était une fois un petit bonhomme de Foix.";

        int longueurLesVoyelles = lesVoyellesSansAccent.length();

        // afficher nbr de fois qu'apparait un car de lesvoyelles dans lipogramme
        System.out.println("***");
        for (int i = 0; i < longueurLesVoyelles; i++) {
            System.out.print(lesVoyellesSansAccent.charAt(i) + " apparait ");
            System.out.println(nb0ccCar(lipogramme, lesVoyellesSansAccent.charAt(i)) + " fois dans lipogramme");
        }
        // afficher nb lettres maj dans ch1 et ch2
        System.out.println("");
        System.out.println("***");
        System.out.println("nombre de lettres MAJ dans ch1 : " + nbLettresMajSansAccent(ch1));
        System.out.println("nombre de lettres MAJ dans ch2 : " + nbLettresMajSansAccent(ch2));

        // afficher nb de mots dans ch1 et ch2
        System.out.println("");
        System.out.println("***");
        System.out.println("nombre de mots dans ch1 : " + nbMots(ch1));
        System.out.println("nombre de mots dans ch2 : " + nbMots(ch2));

        // afficher le plus long mot dans ch1 et ch2
        System.out.println("");
        System.out.println("***");
        System.out.println("longueur du mot le plus long dans ch1 : " + longPlusLongsMots(ch1));
        System.out.println("longueur du mot le plus long dans ch2 : " + longPlusLongsMots(ch2));
    }
}
