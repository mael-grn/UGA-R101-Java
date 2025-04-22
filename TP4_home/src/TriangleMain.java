import java.util.Scanner;

public class TriangleMain {

    public static void main(String[] args) {

        Point A = new Point(1, 1);
        Point B = new Point(3, 5);
        Point C = new Point(5, 1);

        int dx = 3;
        int dy = -4;

        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("*** Points originaux : ");
        System.out.println("coordonées A : " + A.getX() + ", " + A.getY());
        System.out.println("coordonées B : " + B.getX() + ", " + B.getY());
        System.out.println("coordonées C : " + C.getX() + ", " + C.getY());

        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("*** Triangle Composé");
        TriangleCompose tComp = new TriangleCompose(A, B, C);
        Triangle_Utilitaire.afficherTriangleCompose(tComp);

        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("*** Triangle Agrege");
        TriangleAgrege tAg = new TriangleAgrege(A, B, C);
        Triangle_Utilitaire.afficherTriangleAgrege(tAg);

        Scanner lecteur = new Scanner(System.in);
        System.out.println("\n----------------------------------------------------------------------");
        System.out.print("saisir la valeur dx pour deplacer : ");
        dx = lecteur.nextInt();
        lecteur.nextLine();

        System.out.print("saisir la valeur dy pour deplacer"); // commande pour deplacer les points et afficher les rectangles, affiché en plus des autres infos
        dy = lecteur.nextInt();
        lecteur.nextLine();

        tComp.deplacer(dx, dy);
        tAg.deplacer(dx, dy);

        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("triangle composé deplacé");
        Triangle_Utilitaire.afficherTriangleCompose(tComp);

        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("triangle agregé deplacé");
        Triangle_Utilitaire.afficherTriangleAgrege(tAg);



    }
}
