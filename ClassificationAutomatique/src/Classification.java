import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Classification {

    private static ArrayList<Depeche> lectureDepeches(String nomFichier) {
        //creation d'un tableau de dépêches
        ArrayList<Depeche> depeches = new ArrayList<>();
        try {
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String id = ligne.substring(3);
                ligne = scanner.nextLine();
                String date = ligne.substring(3);
                ligne = scanner.nextLine();
                String categorie = ligne.substring(3);
                ligne = scanner.nextLine();
                String lignes = ligne.substring(3);
                while (scanner.hasNextLine() && !ligne.equals("")) {
                    ligne = scanner.nextLine();
                    if (!ligne.equals("")) {
                        lignes = lignes + '\n' + ligne;
                    }
                }
                Depeche uneDepeche = new Depeche(id, date, categorie, lignes);
                depeches.add(uneDepeche);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return depeches;
    }

    public static void classementDepeches(ArrayList<Depeche> depeches, ArrayList<Categorie> categories, String nomFichier) {
        // {depeches non vide, categories non vide, nomfichier existe} => {calcul de la categorie de chaques depeches, et ecriture de la precision}
        float scoreCult = 0, scoreEco = 0, scoreEnv = 0, scorePol = 0, scoreSpo = 0;  //compteur pour le score final
        String catMax;     //categorie maximum temporaire pour les verifications
        try {
            FileWriter file = new FileWriter(nomFichier);
            for (int i = 0; i < depeches.size(); i++) { //pour chaques depeches ...
                ArrayList<PaireChaineEntier> Vectscore = new ArrayList<>();     //arraylist de score qui servira à determiner la categorie de chaque depeche

                for (int j = 0; j < categories.size(); j++) {   //pour chaque categories ...

                    PaireChaineEntier temp = new PaireChaineEntier(categories.get(j).getNom(), categories.get(j).score(depeches.get(i)));   //on crée la liste des scores en fonctions des categories
                    Vectscore.add(temp);

                }
                catMax = UtilitairePaireChaineEntier.chaineMax(Vectscore);
                if(catMax.compareToIgnoreCase(depeches.get(i).getCategorie()) == 0) { //on choisis quel score on veut incrementer
                    if (catMax.compareToIgnoreCase("culture") == 0) {
                        scoreCult = scoreCult + 1;
                    } else if (catMax.compareToIgnoreCase("economie") == 0) {
                        scoreEco = scoreEco + 1;
                    } else if (catMax.compareToIgnoreCase("environnement-sciences") == 0) {
                        scoreEnv = scoreEnv + 1;
                    } else if (catMax.compareToIgnoreCase("politique") == 0) {
                        scorePol = scorePol + 1;
                    } else {
                        scoreSpo = scoreSpo + 1;
                    }
                }
                file.write(i+1 + ":" + catMax + "\n");   //on ecris la categorie de chaques depeches
            }
            file.write("\nCULTURE :         " + scoreCult + "%");
            file.write("\nECONOMIE :        " + scoreEco + "%");
            file.write("\nENVIRONNEMENT :   " + scoreEnv + "%");
            file.write("\nPOLITIQUE :       " + scorePol + "%");
            file.write("\nSPORTS :          " + scoreSpo + "%");
            file.write("\nMOYENNE :         " + (scoreCult+scoreEco+scoreEnv+scorePol+scoreSpo)/5 + "%");
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<PaireChaineEntier> initDico(ArrayList<Depeche> depeches, String categorie) {
        // {categorie existe, depeches non vide} => {dictionnaire de la categorie est initié}
        ArrayList<PaireChaineEntier> resultat = new ArrayList<>();

        //parcours dans le vecteur de Depeches
        for(int i = 0; i<depeches.size(); i++){
            //Si la dépéche correspond à la catégorie
            if(depeches.get(i).getCategorie().compareToIgnoreCase(categorie)==0){
                //On initialise un vecteur temporaire contenant les mots pour les comparer
                ArrayList<String> temp = depeches.get(i).getMots();
                //parours de la liste de mot
                for(int j = 0;j<temp.size();j++){
                    //On initialise le mot à comparer
                    String motTemp = temp.get(j);
                    //Si le vecteur résultat est vide on ajoute le mot avec son compteur à 0
                    if(resultat.size()==0){
                        PaireChaineEntier PaireTemp = new PaireChaineEntier(motTemp,0);
                        resultat.add(PaireTemp);
                        //Sinon on réalise une boucle de recherche du mot temporaires dans le vecteur resultat
                    }else{
                        int indice = 0;
                        while(indice<resultat.size() && motTemp.compareToIgnoreCase(resultat.get(indice).getChaine())!=0){
                            indice++;
                            //Si on retrouve le mot , on ajoute 1 au score du mot
                        }if(indice == resultat.size()){
                            PaireChaineEntier PaireTemp = new PaireChaineEntier(motTemp,0);
                            resultat.add(PaireTemp);
                        }else{
                            resultat.get(indice).setEntier(resultat.get(indice).getEntier()+1);
                        }
                    }
                }
            }
        }
        UtilitairePaireChaineEntier.triFusion(resultat,0,resultat.size()-1);
        return resultat;
    }

    public static boolean estMotDansAutreCat(String mot, String cat, ArrayList<Depeche> dep) {
        // {} => {renvoi true si mot est dans une categorie differente de cat}
        int indCat = getIndCat(cat), i = 0, j=0;
        while (i<499) {
            if (i == indCat) {
                i = i+98;
            } else {
                i++;
            }
            while (j<dep.get(i).getMots().size() && dep.get(i).getMots().get(j).compareToIgnoreCase(mot) != 0) {
                j++;
            }
            if (j<dep.get(i).getMots().size()-1) {
                return true;
            }
        }
        return false;
    }

    public static void calculScores(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
        // {depeche non vide, categorie existe, dictionnaire non nul} => {calcul du score selon les indications du sujet}

        int i=0, j=0, ind;

        while (i<depeches.size()) { //pour chaques depeches...

            while (j<depeches.get(i).getMots().size()) {    //pour chaques mots...
                ind = UtilitairePaireChaineEntier.indicePourChaineDicho(dictionnaire, depeches.get(i).getMots().get(j)); //indice du mot dans dictionnaire si il existe
                if(ind>0 & depeches.get(i).getCategorie().compareToIgnoreCase(categorie) == 0) {    //si il existe, ...
                    dictionnaire.set(ind, new PaireChaineEntier(dictionnaire.get(ind).getChaine(), dictionnaire.get(ind).getEntier()+1));
                } else if (ind >0){ //si il existe pas, ...
                    dictionnaire.set(ind, new PaireChaineEntier(dictionnaire.get(ind).getChaine(), dictionnaire.get(ind).getEntier()-1));
                }
                j++;
            }
            i++;
        }
    }
    public static int calculScoresNbComp(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
        // {depeche non vide, categorie existe, dictionnaire non nul} => {calcul du score selon les indications du sujet}

        int i=0, j=0, ind, nbComp = 0;

        while (i<depeches.size()) { //pour chaques depeches...
            j=0;
            while (j<depeches.get(i).getMots().size()) {    //pour chaques mots...
                ind = UtilitairePaireChaineEntier.indicePourChaineDicho(dictionnaire, depeches.get(i).getMots().get(j)); //indice du mot dans dictionnaire si il existe
                nbComp =nbComp + UtilitairePaireChaineEntier.indicePourChaineDichoNbComp(dictionnaire, depeches.get(i).getMots().get(j)) + 2;
                if(ind>0 & depeches.get(i).getCategorie().compareToIgnoreCase(categorie) == 0) {    //si il existe, ...
                    dictionnaire.set(ind, new PaireChaineEntier(dictionnaire.get(ind).getChaine(), dictionnaire.get(ind).getEntier()+1));
                } else if (ind >0){ //si il existe pas, ...
                    dictionnaire.set(ind, new PaireChaineEntier(dictionnaire.get(ind).getChaine(), dictionnaire.get(ind).getEntier()-1));
                }
                j++;
            }
            i++;
        }
        return nbComp;
    }


    public static int getIndCat(String cat) {
        // {categorie existe, depeche contient 500 depeches par categorie avec 5 categories} => {indice de la categorie dans depeche}
        if (cat.compareToIgnoreCase("environnement-sciences") == 0) {
            return 1;
        } else if (cat.compareToIgnoreCase("culture") == 0) {
            return 101;
        } else if (cat.compareToIgnoreCase("economie") == 0) {
            return 201;
        } else if (cat.compareToIgnoreCase("politique") == 0) {
            return 301;
        } else {
            return 401;
        }
    }
    public static int poidsPourScore(int score) {
        if (score==0) {
            return 1;
        } else if (score==1) {
            return 2;
        } else {
            return 3;
        }
    }

    public static void generationLexique(ArrayList<Depeche> depeches, String categorie, String nomFichier) {
    // {depeche non vide, categorie existe} => {lexique généré en fonction des depeches}

        ArrayList<PaireChaineEntier> dico = initDico(depeches, categorie);
        calculScores(depeches, categorie, dico);
        //System.out.println(calculScoresNbComp(depeches, categorie, dico));

        try {   //creation du fichier lexique si il n'existe pas
            File f = new File("./" + nomFichier);
        } catch ( Exception e) {
            System.err.println(e);
        }

        try {   //creer le lexique dans ce fichier
            FileWriter file = new FileWriter(nomFichier);
            for (int i = 0; i < dico.size(); i++) {
                if (poidsPourScore(dico.get(i).getEntier())>0) {
                    file.write(dico.get(i).getChaine() + ":" + poidsPourScore(dico.get(i).getEntier()));
                    file.write("\n");
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void ecritureTempsExec(long global, long dep, long cat, long genLex, long initLex, long classement) {
        // {ecriture du temps d'execution dans un fichier}
        try {
            FileWriter file = new FileWriter("Temps_exec.txt");
            file.write("TEMPS D'EXECUTION GLOBAL :               " + global + " ms");
            file.write("\n    chargement des depeches :           " + dep + " ms");
            file.write("\n    creation des categories   :         " + cat + " ms");
            file.write("\n    generation lexique :                " + genLex + " ms");
            file.write("\n    initiation lexique :                " + initLex + " ms");
            file.write("\n    ecriture dans le fichier resultat : " + classement + " ms");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Scanner lecteur = new Scanner(System.in);
        System.out.print("\nVoulez-vous afficher le nombre de comparaison ? le temps d'execution sera doublé ! (saisir UNIQUEMENT true ou false) : ");
        Boolean avecNbComp = lecteur.nextBoolean();
        lecteur.nextLine();

        long tempsGlobal = System.currentTimeMillis();

        //Chargement des dépêches en mémoire :
        //une arrayListe de depeche est crée, avec chaque depeches et ses information numerotées de 1 à 500
        long tempsDepeches = System.currentTimeMillis();
        System.out.println("Chargement des dépêches...");
        ArrayList<Depeche> depeches = lectureDepeches("./depeches.txt");
        System.out.println("Depeches chargées");
        tempsDepeches = System.currentTimeMillis() - tempsDepeches;

        //creation de fichiers et categories :
        //les objets categories de chaques categories sont créés. une arrayListe de ces categorie est créée
        long tempsFichiersCategories = System.currentTimeMillis();
        System.out.println("\nChargement des categories...");
        Categorie Culture = new Categorie("culture");
        Categorie Economie = new Categorie("economie");
        Categorie EnvironnementSciences = new Categorie("environnement-Sciences");
        Categorie Politique = new Categorie("politique");
        Categorie Sports = new Categorie("sports");
        ArrayList<Categorie> cat = new ArrayList<>(Arrays.asList(Culture, Economie, EnvironnementSciences, Politique, Sports));
        System.out.println("Categories chargées");
        tempsFichiersCategories = System.currentTimeMillis() - tempsFichiersCategories;

        //generation du lexique :
        //les fonctions/methodes initDico et CalculScore sont utilisé pour generer les lexiques de chaques categories, ainsi que le score de chaques mots.
        //ensuite, une boucle ecris dans les fichiers de le lexiques les mots correspondant, et son poid calculé grace à poid pour score
        long tempsGenerationLexique;
        tempsGenerationLexique = System.currentTimeMillis();
        System.out.println("\nGeneration du lexique...");
        System.out.println("    Culture...");
        generationLexique(depeches, "culture", "CULTURE.txt");
        System.out.println("    Economie...");
        generationLexique(depeches, "economie", "ECONOMIE.txt");
        System.out.println("    Environnement-Sciences...");
        generationLexique(depeches, "environnement-Sciences", "ENVIRONNEMENT-SCIENCES.txt");
        System.out.println("    Politique...");
        generationLexique(depeches, "politique", "POLITIQUE.txt");
        System.out.println("    Sports...");
        generationLexique(depeches, "sports", "SPORTS.txt");
        System.out.println("Lexique généré");
        tempsGenerationLexique = System.currentTimeMillis() - tempsGenerationLexique;

        //initiation du lexique
        //le lexique créé precedemment est chargé pour chaques categories
        long tempsInitLexique = System.currentTimeMillis();
        System.out.println("\nInitiation du lexique...");
        System.out.println("    Culture...");
        Culture.initLexique("CULTURE.txt");
        System.out.println("    Economie...");
        Economie.initLexique("ECONOMIE.txt");
        System.out.println("    Environnement-Sciences...");
        EnvironnementSciences.initLexique("ENVIRONNEMENT-SCIENCES.txt");
        System.out.println("    Politique...");
        Politique.initLexique("POLITIQUE.txt");
        System.out.println("    Sports...");
        Sports.initLexique("SPORTS.txt");
        System.out.println("Lexique initié");
        tempsInitLexique = System.currentTimeMillis() - tempsInitLexique;

        long tempsEcritureResultats;
        //test de l'efficacité
        //la procedure classement depeches est appelé, elle vas ecrir pour chaques depeche sa categorie calculée.
        //le calcul de score pour chaques depeches est effectué, ainsi categorie.score() et UtilitairePaireChaineEntier.entierPourChaine() sont appelés
        tempsEcritureResultats = System.currentTimeMillis();
        System.out.println("\nEcriture des données...");
        ArrayList<Depeche> test = lectureDepeches("./test.txt");
        classementDepeches(test, cat, "fichier-sortie.txt");
        System.out.println("Fin du programme, éxécuté sans erreurs");
        tempsEcritureResultats = System.currentTimeMillis() - tempsEcritureResultats;

        tempsGlobal = System.currentTimeMillis() - tempsGlobal;

        ecritureTempsExec(tempsGlobal, tempsDepeches, tempsFichiersCategories, tempsGenerationLexique, tempsInitLexique, tempsEcritureResultats);

        if (avecNbComp) {
            int nbCompScore=0;
            int nbCompCalculScore=0;
            for (int i=0; i<cat.size(); i++) {
                nbCompCalculScore = nbCompCalculScore + calculScoresNbComp(depeches, cat.get(i).getNom(), initDico(depeches, cat.get(i).getNom()));
                for (int j=0; j<depeches.size(); j++) {
                    nbCompScore=nbCompScore+cat.get(i).scoreNbComp(depeches.get(j));
                }
            }
            System.out.println("\n*** Nombre de comparaison de Score : " + nbCompScore);
            System.out.println("\n*** Nombre de comparaison de CalculScore : " + nbCompCalculScore);
            System.out.println("\n*** Total : " + (nbCompCalculScore + nbCompScore));
        }
    }
}

