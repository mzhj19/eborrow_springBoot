package com.mzhj19.eborrow.util;

import java.util.HashMap;
import java.util.Map;

public class MappingValues {
    private static final Map<String, Integer> borrowType = new HashMap<>();

    static {
        // Initialize the map with city IDs and names
        borrowType.put("Per hour",1);
        borrowType.put("Per day",2);
        borrowType.put("Per month",3);
        borrowType.put("Per year",4);
    }

    public static Integer getBorrowTypeByName(String borrowTypeName) {
        return borrowType.get(borrowTypeName);
    }
}
