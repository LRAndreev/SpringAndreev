package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CompassModel implements Serializable {
    private static final CompassModel instance = new CompassModel();
    private final Map<String, Direction> Cmodel;

    public CompassModel() {
        Cmodel = new HashMap<String, Direction>();
    }

    public static CompassModel getInstance() {
        return instance;
    }

    public void add(String dir, String left, String right) {
        Cmodel.put(dir, new Direction(left, right));
    }

    public Direction getFromList(String dir) {
        return Cmodel.get("dir");
    }

    public Map<String, Direction> getAll() {
        return Cmodel;
    }

    public String getDir(int request) {
        for (Map.Entry<String, Direction> entry : Cmodel.entrySet()) {
            Direction dir = entry.getValue();
            int left = Integer.parseInt(dir.getLeft());
            int right = Integer.parseInt(dir.getRight());
            if (request < 360) {
                if (left > right) {
                    if (request >= left || request <= right) {
                        return entry.getKey();
                    }
                } else
                    // 158  159  202
                        if (left <= request && request <= right) {
                            return entry.getKey();
                        }
                }
            }

            return "Incorrect value";
        }
    }
