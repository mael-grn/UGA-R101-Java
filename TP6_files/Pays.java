public class Pays implements Comparable<Pays> {

    private String nom; // nom d'un pays
    private String continent; // nom de son continent
    private int population; // nombre d'habitants recensés
    private int superficie; // superficie

    // constructeur
    public Pays(String nom, String continent, int population, int superficie) {
	// À compléter...

    }

    // getters
    public String getNom() {
	// À compléter...

    }

    public String getContinent() {
	// À compléter...

    }

    public int getPopulation() {
	// À compléter...

    }

    public int getSuperficie() {
	// À compléter...

    }

    // ordre sur le nom des pays
    @Override
    public int compareTo(Pays o) {
	// À compléter...
	//  	en respectant les informations relatives
	//  	à l'ordre naturel sur la classe Pays,
	//  	données dans le sujet du TP6(A)


    }

    // pour l'affichage d'un pays
    @Override
    public String toString() {
        return nom + "\n - continent : " + continent
                + "\n - population : " + population + "\n - superficie : " + superficie;
    }
}