package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CheckShit {
    public static void main(String[] args) {

        List<Integer> troep = new ArrayList<Integer>();
        List<Integer> list =  new ArrayList<>(); list.add(1); list.add(2); list.add(3); list.add(4);
        troep.addAll(list);
        for (int l : troep) {
            if (Collections.max(troep) > 3) {
                System.out.println(Collections.max(troep));
                break;
//            System.out.println();
            }
        }
    int a = 3, b =6;
        //System.out.println(a > b ? b : a);
}
    }
