import java.util.ArrayList;
import java.util.Scanner;

public class monde {

    public static void main(String[] args) {

        final ArrayList<Pays> leMonde = InitMonde.creerMonde();
        ArrayList<String> lesContinents = new ArrayList<>();
        Scanner lecteur = new Scanner(System.in);

        int i = 0;
        int indCont;

        /*
        while (leMonde.size() > i){     //affichage des pays
            System.out.println(i + " : " + leMonde.get(i));
            i++;
        }   //  affichage du monde
        */      //affichage de leMonde

        /*
        QUESTIONS :
        1. les instrucitons d'affichage le monde affiches les informations pour tous les pays,
        on se retrouve avec trop d'info.
         */     //Reponse aux questions


        while (i < leMonde.size()) {
            indCont = Utilitaire.indString(lesContinents, leMonde.get(i).getContinent());

            //si le continent etudié de lemonde n'est pas dans le vecteur lesContinents,
            // l'ajouter au bon endroit (utilisation de indstring)

            if (indCont != -1) {
                lesContinents.add(indCont, leMonde.get(i).getContinent());
            }
            i++;
        } // ajouter les contients de leMonde dans lesContients



        String unCont;
        ArrayList<Pays> PaysCont;

        unCont = Utilitaire.saisieCont(lesContinents);
        PaysCont = Utilitaire.paysDeCont(leMonde, unCont);

        System.out.println("Premier pays : " + PaysCont.get(0).getNom());
        System.out.println("Dernier Pays : " + PaysCont.get(PaysCont.size()-1).getNom());

        System.out.println("Nombre de Pays : " + PaysCont.size());


        System.out.println("\nsaisir un Pays de " + unCont + " : ");
        System.out.print(">>> ");
        String unPays = lecteur.nextLine();

        if (Utilitaire.rechPaysT(PaysCont, unPays) == PaysCont.size()){
            System.out.println("ce pays n'est pas dans " + unCont);
        } else {
            System.out.println(PaysCont.get(Utilitaire.rechPaysT(PaysCont, unPays)));
        }

        System.out.println("\nvoulez vous afficher les statistiques detaillées ?");
        Boolean saisieYN = Utilitaire.saisieYN();

        Utilitaire.paysMoinsPeuples(leMonde, saisieYN);

        Utilitaire.contExtremes(leMonde, lesContinents);



    }
}
