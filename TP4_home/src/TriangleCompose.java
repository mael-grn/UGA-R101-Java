import java.security.PrivateKey;

public class TriangleCompose {

    // Attributs
    private Point A, B, C;

    //Constructeur composition
    public TriangleCompose(Point A, Point B, Point C) {
        this.A = new Point(A.getX(), A.getY());
        this.B = new Point(B.getX(), B.getY());
        this.C = new Point(C.getX(), C.getY());
    }

    // methodes : getters
    public Point getA() {
        return A;
    }

    public Point getB() {
        return B;
    }

    public Point getC() {
        return C;
    }

    //methodes : autres
    public double coteAB() {
        // résultat = longueur du côté d'extrémités A et B}
        return (double) Math.sqrt(Math.pow((B.getX() - A.getX()), 2) + Math.pow((B.getY() - A.getY()), 2));
    }

    public double coteAC() {
        // résultat = longueur du côté d'extrémités A et C}
        return (double) Math.sqrt(Math.pow((C.getX() - A.getX()), 2) + Math.pow((C.getY() - A.getY()), 2));
    }

    public double coteBC() {
        // résultat = longueur du côté d'extrémités B et C}
        return (double) Math.sqrt(Math.pow((C.getX() - B.getX()), 2) + Math.pow((C.getY() - B.getY()), 2));
    }

    public double perimetre(){
        //{} => {resultat = perimetre du triangle}
        return (coteAB() + coteAC() + coteBC());
    }

    public double surface(){
        //{} => {resultat => surface du triangle}
        double p = perimetre()/2;
        double s = Math.sqrt(p*(p-coteAB())*(p-coteBC())*(p-coteAC()));
        return s;
    }

    public void deplacer(int dx, int dy){
        // {} => {A, B et C ont été déplacés horizontalement de dx
        // et verticalement de dy}
        A.deplace(dx, dy);
        B.deplace(dx, dy);
        C.deplace(dx, dy);
    }
}