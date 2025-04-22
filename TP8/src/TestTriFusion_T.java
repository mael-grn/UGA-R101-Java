import java.util.ArrayList;

public class TestTriFusion_T {


    public static void fusionTabGTabD(ArrayList<Integer> vInt,
                                      int inf, int m, int sup) {
        //{ inf <= sup, m = (inf+sup)/2, vInt[inf..m] trié, vInt[m+1..sup] trié
        // => { VInt[inf..sup] trié }
        ArrayList<Integer> temp = new ArrayList<>();
        int i = 0,ind=0,in=0;
        while ( m>inf) {
            if (vInt.get(inf) < vInt.get(m + 1)) {
                temp.add(vInt.get(inf));
                inf++;

            } else {
                temp.add(vInt.get(m + 1));
                m++;
            }
        }

        while (i< temp.size()) {
            while (ind < vInt.size()) {
                if (temp.get(i) == vInt.get(ind)) {
                    vInt.remove(ind);
                }
                ind++;
            }
            ind = 0;
            i++;
        }
        while(in<vInt.size()){
            temp.add(vInt.get(in));
            in++;
        }

        int h=0;
        while(h<vInt.size()){
            vInt.remove(vInt.get(h));
        }
        int n=0;
        while(n< temp.size()){
            vInt.add(temp.get(n));
            n++;
        }
        System.out.println(vInt);
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
}
