package org.example.utils;

import java.util.HashSet;
import java.util.Set;

public class StringSet {

    public static Set<String> EssenceSet;

    static {
        EssenceSet = new HashSet<>();
        EssenceSet.add("append-head");
        EssenceSet.add("append-tail");
        EssenceSet.add("insert");
        EssenceSet.add("delete");
        EssenceSet.add("load");
        EssenceSet.add("save");
    }

    public static Set<String> UnsaveSet;

    static {
        UnsaveSet = new HashSet<>();
        UnsaveSet.add("append-head");
        UnsaveSet.add("append-tail");
        UnsaveSet.add("insert");
        UnsaveSet.add("delete");
    }

    public static Set<String> DebugSet;

    static {
        DebugSet = new HashSet<>();
        DebugSet.add("c");
        DebugSet.add("reset");
    }
}
