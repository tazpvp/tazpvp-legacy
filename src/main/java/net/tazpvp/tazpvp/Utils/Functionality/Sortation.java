package net.tazpvp.tazpvp.Utils.Functionality;

import java.util.*;

public class Sortation {
    public static LinkedHashMap<UUID, Double> sortByValue(HashMap<UUID, Double> hm) {
        List<Map.Entry<UUID, Double> > list = new LinkedList<>(hm.entrySet());
        list.sort(Map.Entry.<UUID,Double>comparingByValue().reversed());

        LinkedHashMap<UUID, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<UUID, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
