public interface ListeInterface<TypeInfo> {
    // TypeInfo : type des informations que l'on peut mettre dans la liste
    // exemple : Integer, Rectangle, Pays, Polar

    // prototype des services minimum que doit rendre une liste
    /**
     * cette (this) liste est-elle vide
     * @return true si cette liste est vide ; false sinon
     */
    boolean estVide();

    /**
     * nombre de cellules de cette (this) liste
     * @return nombre d'éléments de cette liste
     */
    int getLongueur();

    /**
     * insertion d'un nouvel élément contentant nouvelleInfo en tête de cette (this) liste
     * @pre Aucune
     * @post une nouvelle cellule contenant nouvelleInfo est ajouté en tête de cette liste
     * @param uneInfo information portée par le nouvel élément de tête
     */
    void insereTete(TypeInfo uneInfo);

    /**
     * suppression de la première cellule de cette liste
     * @pre Aucune
     * @post cette liste est privée de son premier élément
     */
    void supprimeTete();

    // les positions dans une liste sont numérotées à partir de 1

    /**
     * insertion d'une cellule contentant nouvelleInfo à la position nouvellePosition
     * position est légale si 1 <= position <= getLongueur() + 1
     * @pre Aucune
     * @post nouvelleInfo est à la position dans cette liste,
     *       et la valeur retournée est true ; sinon la valeur retournée est false
     * @param position position à laquelle insérer nouvelleInfo
     * @param nouvelleInfo information à insérer dans la liste
     * @return true si l'insertion est réussie, false sinon
     */
    boolean insereAtPosit(int position, TypeInfo nouvelleInfo);

    /**
     * suppression de la cellule visée au rang position
     * position est légale si 1 <= position <= getLongueur()
     * @pre Aucune
     * @post l'élément visé est supprimé de cette liste si la position est légale,
     *       et la valeur retournée est true ; sinon la valeur retournée est false
     * @param position rang de la cellule visée à supprimer
     * @return true si la suppression est faite (position légale) ; false sinon
     */
    boolean supprimeAtPosit(int position);

    /**
     * valeur de l'information portée par l'élément de rang position visé
     * position est légale si 1 <= position <= getLongueur()
     * @pre Aucune
     * @post l'information portée par l'élement visé est retournée si la position est légale
     * @param position rang de l'élément visé
     * @return information portée par l'élément visé si la position est légale
     * @throws ExceptionMauvaisIndice si la position est illégale
     */
    TypeInfo getInfoAtPosit(int position) throws ExceptionMauvaisIndice;

    /**
     * remplacement de l'information portée par l'élément au rang position
     * position est légale si 1 <= position <= getLongueur()
     * @pre Aucune
     * @post l'information de l'élément visé vaut nouvelleInfo si la position est légale
     * @param position  rang de l'élément visé
     * @param nouvelleInfo  nouvelle valeur de l'élément visé
     * @return true si le remplacement est possible () ; false sinon
     */
    boolean setInfoAtPosit(int position, TypeInfo nouvelleInfo);

    /**
     * suppression de toutes les cellules de la liste
     * @post la liste est vide et le nombre d'éléments vaut 0
     */
    void vide();
}