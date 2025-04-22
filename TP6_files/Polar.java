public class Polar implements Comparable<Polar> {

    // attributs
    private int annee; // année de première parution
    private String auteur; // auteur du roman policier
    private String titre; // titre du roman policier

    // constructeur
    public Polar(int annee, String auteur, String titre) {
    	// À compléter
	
    }

    // getters
    public int getAnnee() {
    	// À compléter
	
    }
    public String getAuteur() {
    	// À compléter
	
    }
    public String getTitre() {
    	// À compléter
	
    }

    @Override
    // ordre naturel sur l'année et à année égale sur l'auteur : ordre (annee, auteur)
    public int compareTo(Polar o) {
    	// À compléter (cf. ordre (ANNEE, AUTEUR) défini dans le sujet
	
    }

    @Override
    // traduction en chaîne de caractères
    public String toString() {
        // {} => {résultat = (annee, auteur, titre) }
    	
	// À compléter
	
    }
}