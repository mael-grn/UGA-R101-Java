public class ListeChainee<TypeInfo extends Comparable<TypeInfo>> implements ListeInterface<TypeInfo> {

    private Cellule<TypeInfo> tete;
    private int nbCellules;

    /**
     * Getter de la tête présent uniquement pour des raisons pédagogiques
     * utilisé seulement dans la classe Utilitaire
     */
    public Cellule<TypeInfo> getTete() {
        return tete;
    }

    /**
     * Construction d'une liste vide
     */
    public ListeChainee() {
        tete = null;
        nbCellules = 0;
    }

    @Override
    public boolean estVide() {
        return nbCellules == 0;
    }

    @Override
    public void vide() {
        tete = null;
        nbCellules = 0;
    }

    @Override
    public int getLongueur() {
        return nbCellules;
    }

    private Cellule<TypeInfo> insereTeteWorker(Cellule<TypeInfo> celluleCourante, TypeInfo uneInfo) {
        celluleCourante = new Cellule<TypeInfo>(uneInfo, celluleCourante);
        nbCellules = nbCellules + 1;
        return celluleCourante;
    }

    @Override
    public void insereTete(TypeInfo uneInfo) {
        this.tete = insereTeteWorker(tete, uneInfo);
    }

    private Cellule<TypeInfo> supprimeTeteWorker(Cellule<TypeInfo> celluleCourante) {
        celluleCourante = celluleCourante.getCelluleSuivante();
        nbCellules = nbCellules - 1;
        return celluleCourante;
    }

    @Override
    public void supprimeTete() {
        if (nbCellules != 0) {
            tete = supprimeTeteWorker(tete);
        }
    }

    private int compterNbOccValWorker(Cellule<TypeInfo> celluleCourante, TypeInfo val) {
        if (celluleCourante == null) {
            return 0;
        } else if (celluleCourante.getInfo().compareTo(val) == 0) {
            return 1 + compterNbOccValWorker(celluleCourante.getCelluleSuivante(), val);
        } else {
            return compterNbOccValWorker(celluleCourante.getCelluleSuivante(), val);
        }
    }

    public int compterNbOccVal(TypeInfo val) {
        return compterNbOccValWorker(tete, val);
    }

    /**
     * L'insertion est toujours possible lorsque le worker est appelé
     *
     * @param celluleCourante
     * @param position
     * @param nouvelleInfo
     */
    private Cellule<TypeInfo> insereAtPositRecWorker(Cellule<TypeInfo> celluleCourante, int position, TypeInfo nouvelleInfo) {
        if (position == 1) {
            Cellule<TypeInfo> nouvelleCellule = insereTeteWorker(celluleCourante, nouvelleInfo);
            return nouvelleCellule;
        } else {
            Cellule<TypeInfo> reste = insereAtPositRecWorker(celluleCourante.getCelluleSuivante(), position - 1, nouvelleInfo);
            celluleCourante.setCelluleSuivante(reste);
            return celluleCourante;
        }
    }


    @Override
    public boolean insereAtPosit(int position, TypeInfo nouvelleInfo) {
        if (position > 0 & position <= nbCellules + 1) {
            tete = insereAtPositRecWorker(tete, position, nouvelleInfo);
            return true;
        } else {
            return false;
        }
    }

    /**
     * La suppression est possible donc pas de test sur la liste vide
     *
     * @param celluleCourante
     * @param position
     * @return
     */
    private Cellule<TypeInfo> supprimeAtPositRecWorker(Cellule<TypeInfo> celluleCourante, int position) {
        if (position == 1) {
            // supprimer en tête
            Cellule<TypeInfo> nouvelleListe = supprimeTeteWorker(celluleCourante);
            return nouvelleListe;
        } else {
            Cellule<TypeInfo> nouvelleSuite = supprimeAtPositRecWorker(celluleCourante.getCelluleSuivante(), position - 1);
            celluleCourante.setCelluleSuivante(nouvelleSuite);
            return celluleCourante;
        }
    }

    @Override
    public boolean supprimeAtPosit(int position) {
        if (position > 0 & position < nbCellules) {
            // Suppression possible
            tete = supprimeAtPositRecWorker(tete, position);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param celluleCourante
     * @param position        acceptable pour cette liste
     * @return
     */
    private TypeInfo getInfoAtPositRecWorker(Cellule<TypeInfo> celluleCourante, int position) {
        if (position == 1) {
            return celluleCourante.getInfo();
        } else {
            return getInfoAtPositRecWorker(celluleCourante.getCelluleSuivante(), position - 1);
        }
    }

    @Override
    public TypeInfo getInfoAtPosit(int position) throws ExceptionMauvaisIndice {
        if (position >= 1 & position <= nbCellules) {
            return getInfoAtPositRecWorker(tete, position);
        } else {
            // on utilise le constructeur avec paramètre pour construire un message d'erreur parlant
            throw new ExceptionMauvaisIndice("Indice de valeur " + position + " illégal dans getInfoAtPosit, la position doit être dans l'intervalle [1" + ", " + nbCellules + "]\n");
        }
    }

    /**
     * @param celluleCourante
     * @param position        acceptable pour cette liste
     * @return
     */
    private boolean setInfoAtPositRecWorker(Cellule<TypeInfo> celluleCourante, int position, TypeInfo nouvelleInfo) {
        if (position == 1) {
            celluleCourante.setInfo(nouvelleInfo);
            return true;
        } else {
            return setInfoAtPositRecWorker(celluleCourante.getCelluleSuivante(), position - 1, nouvelleInfo);
        }
    }

    @Override
    public boolean setInfoAtPosit(int position, TypeInfo nouvelleInfo) {
        if (position >= 1 & position <= nbCellules) {
            return setInfoAtPositRecWorker(tete, position, nouvelleInfo);
        } else {
            return false;
        }
    }

    private void afficheGaucheDroiteRecWorker(Cellule<TypeInfo> celluleCourante) {
        if (celluleCourante != null) {
            System.out.print(celluleCourante.getInfo() + " -> ");
            afficheGaucheDroiteRecWorker(celluleCourante.getCelluleSuivante());
        }
        // si la liste de Cellules est vide, ne rien faire
    }

    public void afficheGaucheDroiteRec() {
        System.out.print("Affichage GD : ");
        afficheGaucheDroiteRecWorker(tete);
        System.out.println();
    }

    public void afficheGaucheDroiteIter() {
        Cellule<TypeInfo> celluleCourante = tete;

        while (celluleCourante != null) {
            System.out.print(celluleCourante.getInfo() + " -> ");
            celluleCourante = celluleCourante.getCelluleSuivante();
        }
    }

    private void afficheDroiteGaucheRecWorker(Cellule<TypeInfo> celluleCourante) {
        if (celluleCourante != null) {
            afficheDroiteGaucheRecWorker(celluleCourante.getCelluleSuivante());
            System.out.print(" <- " + celluleCourante.getInfo() );
        }
        // si la liste de Cellules est vide, ne rien faire
    }

    public void afficheDroiteGaucheRec() {
        System.out.print("Affichage DG : ");
        afficheDroiteGaucheRecWorker(tete);
        System.out.println();
    }
}