import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Categorie {

    private String nom; // le nom de la catégorie p.ex : sport, politique,...
    private ArrayList<PaireChaineEntier> lexique = new ArrayList<>(); //le lexique de la catégorie

    // constructeur
    public Categorie(String nom) {
        this.nom = nom;
    }


    public String getNom() {
        return nom;
    }


    public ArrayList<PaireChaineEntier> getLexique() {
        return lexique;
    }


    // initialisation du lexique de la catégorie à partir du contenu d'un fichier texte
    public void initLexique(String nomFichier) {
        try {
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                int ind = Integer.parseInt(ligne.substring(ligne.length() - 1));
                ligne = ligne.substring(0, ligne.length() - 2);
                PaireChaineEntier unePaire = new PaireChaineEntier(ligne, ind);
                lexique.add(unePaire);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //calcul du score d'une dépêche pour la catégorie
    public int score(Depeche d) {   //calcul du score pour une depeche d
        int i = 0, score = 0;
        while (i < d.getMots().size()) {
            score = score + UtilitairePaireChaineEntier.entierPourChaineTrie(lexique, d.getMots().get(i));
            i++;
        }
        return score;
    }
    public int scoreNbComp(Depeche d) {
        int i=0,score=0, compt=1;
        while(i<d.getMots().size()){
            compt = compt+UtilitairePaireChaineEntier.entierPourChaineTrieNbComp(lexique,d.getMots().get(i));
            i++;
            compt++;
        }
        return compt;
    }
}
