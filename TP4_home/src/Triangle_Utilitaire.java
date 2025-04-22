public class Triangle_Utilitaire {


    public static double arrondi2(double unDouble) {
        // {} => {résultat = valeur de unFloat après arrondi à 2 décimales}
        return (double) Math.round(100 * unDouble) / 100;

    }

    public static void afficherTriangleCompose(TriangleCompose T) {
        //{} => {les coordonnées des trois sommets de T ont été affichées
        // ainsi que les longueurs de ses côtés, son périmètre et
        // sa surface après arrondi à 2 décimales}
        System.out.println("\ncoordonnés A : " + T.getA());
        System.out.println("coordonnés B : " + T.getB());
        System.out.println("coordonnés C : " + T.getC());

        System.out.println("\ncoté AB : " + T.coteAB());
        System.out.println("coté AC : " + T.coteAC());
        System.out.println("coté BC : " + T.coteBC());

        System.out.println("\nPerimetre : " + arrondi2(T.perimetre()));

        System.out.println("\nSurface : " + arrondi2(T.surface()));
    }

    public static void afficherTriangleAgrege(TriangleAgrege T) {
        //{} => {les coordonnées des trois sommets de T ont été affichées
        // ainsi que les longueurs de ses côtés, son périmètre et
        // sa surface après arrondi à 2 décimales}

        System.out.println("\ncoordonnés A : " + T.getA());
        System.out.println("coordonnés B : " + T.getB());
        System.out.println("coordonnés C : " + T.getC());

        System.out.println("\ncoté AB : " + T.coteAB());
        System.out.println("coté AC : " + T.coteAC());
        System.out.println("coté BC : " + T.coteBC());

        System.out.println("\nPerimetre : " + arrondi2(T.perimetre()));

        System.out.println("\nSurface : " + arrondi2(T.surface()));
    }

}
