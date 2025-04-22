import java.util.ArrayList;
import java.util.*;

public class ALGOINTEGERTRI {
    public static int triBulleOutille(ArrayList<Integer> vInt) {
// { vInt quelconque }
// => { * vInt a été trié par la méthode du tri à bulles amélioré
// * résultat = nombre de comparaisons entre deux éléments de vInt }
        ArrayList<Integer> vInt2 = vInt;
        int j;
        int nb_comp=0;
        boolean onAPermute = true;
        int index = 0;
        while (onAPermute) {
            j = vInt2.size() - 1;
            onAPermute = false;
            while (j > index) {
                nb_comp++;
                if (vInt.get(j).compareTo(vInt2.get(j - 1)) < 0) {
                    int temporary = vInt2.get(j);
                    vInt2.set(j, vInt2.get(j - 1));
                    vInt2.set(j - 1, temporary);
                    onAPermute = true;
                }
                j = j - 1;
            }
            index++;
        }
        vInt=vInt2;
        return nb_comp;
    }
    public static int triSelectOutille(ArrayList<Integer> vInt) {
// { vInt quelconque }
// => { * vInt a été trié par la méthode du tri par sélection
// * résultat = nombre de comparaisons entre deux éléments de vInt }
        int index=0;
        int nb_comp=0;
        while (index<vInt.size()-1){
            int indMin=index;
            int j=index+1;
            while (j<vInt.size()){
                nb_comp++;
                if (vInt.get(j).compareTo(vInt.get(indMin)) <0){
                    indMin=j;
                }
                j=j+1;
            }
            if (indMin!=index){
                int temporary= vInt.get(index);
                vInt.set(index, vInt.get(indMin));
                vInt.set(indMin, temporary);
            }
            index++;
        }
        return nb_comp;
    }
    public static int triInsertOutille(ArrayList<Integer> vInt) {
// { vInt quelconque }
// => { * vInt a été trié par la méthode du tri par insertion
// * résultat = nombre de comparaisons entre deux éléments de vInt }
        int j;
        int nb_comp=0;
        int valeurAPlacer;
        int index=1;
        while (index<vInt.size()){
            j=index;
            valeurAPlacer=vInt.get(index);
            while (j>0 && vInt.get(j-1).compareTo(valeurAPlacer)>0){
                vInt.set(j, vInt.get(j-1));
                j--;
                nb_comp++;
            }
            if (j>0){
                nb_comp++;}

            vInt.set(j,valeurAPlacer);
            index++;
        }
        return nb_comp;
    }
    public static int triFusionOutille(ArrayList<Integer> vInt, int inf, int sup) {
// { vInt[inf..sup] non vide }
// => { * vInt[inf..sup] trié
// * résultat = nombre de comparaisons entre deux éléments }
        int nb_comp=0;
        if (inf < sup) {
            int m = (inf + sup) / 2;
            nb_comp=nb_comp+triFusionOutille(vInt, inf, m);
            nb_comp=nb_comp+triFusionOutille(vInt, m + 1, sup);
            nb_comp=nb_comp+fusionTabGTabDOutille(vInt, inf, m, sup);
        }
        return nb_comp;
    }

    public static int fusionTabGTabDOutille(ArrayList<Integer> vInt,
                                            int inf, int m, int sup) {
//{ inf <= sup, m = (inf+sup)/2, vInt[inf..m] trié,vInt[m+1..sup] trié }
// => { * vInt[inf..sup] trié
// * résultat = nombre de comparaisons entre deux éléments }
            int nb_comp=0;
            ArrayList<Integer> temp = new ArrayList<>();
            int index_1 = inf;
            int index_2 = m + 1;

            while (index_2 <= sup & index_1 <= m) {
                nb_comp++;
                if (vInt.get(index_1) < vInt.get(index_2)) {
                    temp.add(vInt.get(index_1));
                    index_1++;
                } else {
                    temp.add(vInt.get(index_2));
                    index_2++;
                }
            }
            while (index_1 <= m) {
                temp.add(vInt.get(index_1));
                index_1++;
            }
            while (index_2 <= sup) {
                temp.add(vInt.get(index_2));
                index_2++;
            }
            for (int k=0;k<temp.size();k++){
                vInt.set(k+inf,temp.get(k));
            }
            return nb_comp;
        }

    public static void main(String[] args) {
        ArrayList<Integer> test=new ArrayList<>(Arrays.asList(12, 7, 9, 14, 5, 17, 6, 8, 12));
        ArrayList<Integer> test2=new ArrayList<>(Arrays.asList(-44, -45, 9, -12, 30, 56, 7, -3, 19, -45, 9, 23, 11, 150, 28, 34, 1, 25));
        ArrayList<Integer> test3=new ArrayList<>(Arrays.asList(17, 14, 12, 12, 9, 8, 7, 6, 5));
        System.out.println(test3);
        ArrayList<Integer> vint=new ArrayList<>(test3);
        System.out.println(vint);
        int nb_comp=triFusionOutille(vint,0,vint.size()-1);
        System.out.println(vint);
        System.out.println(nb_comp);
    }
}
