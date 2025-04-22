import java.util.ArrayList;
import java.util.Arrays;

public class TestTriFusion {
    public static void fusionTabGTabD(ArrayList<Integer> vInt,
                                      int inf, int m, int sup) {
        //{ inf <= sup, m = (inf+sup)/2, vInt[inf..m] trié, vInt[m+1..sup] trié}



        ArrayList<Integer> temp = new ArrayList<>();
        int i = inf;
        int j = m + 1;
        int ind = 0;

        while (ind < sup){

            System.out.println(temp);

            if (vInt.get(i) < vInt.get(j)){
                temp.add(vInt.get(i));
                i++;
            } else {
                temp.add(vInt.get(j));
                j++;
            }
            ind ++;
        }




        for (i = 0; i < temp.size(); i++){
            vInt.set(i, vInt.get(i));
        }
    }
    public static void triFusion(ArrayList<Integer> vInt, int inf, int sup) {
        //{vInt[inf..sup] non vide} => {vInt[inf..sup] trié}
        if (inf < sup) {
            int m = (inf + sup) / 2;
            triFusion(vInt, inf, m);
            triFusion(vInt, m + 1, sup);
            fusionTabGTabD(vInt, inf, m, sup);
        }
    }


    public static void main(String[] args) {

        ArrayList<Integer> vInt = new ArrayList<>(Arrays.asList(0,5,9,4,2,6,1,8,7,3,10));



        System.out.println(vInt);

        triFusion(vInt, 0, vInt.size()-1);

        System.out.println(vInt);
    }
}
