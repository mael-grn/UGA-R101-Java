public class Polar implements Comparable<Polar> {

    // attributs
    private int annee; // année de première parution
    private String auteur; // auteur du roman policier
    private String titre; // titre du roman policier

    // constructeur
    public Polar(int annee, String auteur, String titre) {
    	// À compléter
        this.annee = annee;
        this.auteur = auteur;
        this.titre = titre;
    }

    // getters
    public int getAnnee() {
    	// À compléter
        return annee;
    }

    public String getAuteur() {
    	// À compléter
        return auteur;
    }

    public String getTitre() {
    	// À compléter
        return titre;
    }


    @Override
    // ordre naturel sur l'année et à année égale sur l'auteur : ordre (annee, auteur)
    public int compareTo(Polar o) {
    	// À compléter (cf. ordre (ANNEE, AUTEUR) défini dans le sujet
	    if (this.annee > o.annee){
            return 1;
        } else if (this.annee < o.annee){
            return -1;
        } else if (this.auteur.compareTo(o.auteur) > 0){
                return 1;
        } else if (this.auteur.compareTo(o.auteur) < 0){
                return -1;
        } else {
                return 0;
        }
    }

    @Override
    // traduction en chaîne de caractères
    public String toString() {
        // {} => {résultat = (annee, auteur, titre) }
    	return "\nannée : " + annee + "\nauteur : " + auteur + "\ntitre : " + titre;
	// À compléter
    }
}