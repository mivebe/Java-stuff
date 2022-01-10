package com.mivebe;

import java.util.HashMap;

public class MapAndHashMap {
    public static void HashMap(){
        HashMap<String, Integer> nameIds = new HashMap<>();
        nameIds.put("Gosho", 12345);
        nameIds.put("Pesho", 33345);
        nameIds.put("Tosho", 222222);

        System.out.println(nameIds);

        System.out.printf("Carl", nameIds.get("Carl"));
        System.out.printf("Pesho", nameIds.containsKey("Pesho"));
        System.out.printf("Pesho", nameIds.containsValue("Pesho"));
        System.out.printf("33345", nameIds.containsValue("33345"));

        nameIds.put("Pesho", 99999);
        nameIds.replace("Ceco", 88888);
        nameIds.putIfAbsent("Asancho", 75457545);
        nameIds.remove("Asancho", 75457545);
        System.out.println(nameIds);
    }
}
