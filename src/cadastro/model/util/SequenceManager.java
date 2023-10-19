package cadastro.model.util;

import java.util.HashMap;
import java.util.Map;

public class SequenceManager {
    private Map<String, Integer> sequenceMap;

    public SequenceManager() {
        sequenceMap = new HashMap<>();
    }

    public int getValue(String sequenceName) {
        if (!sequenceMap.containsKey(sequenceName)) {
            sequenceMap.put(sequenceName, 1);
        }

        int nextValue = sequenceMap.get(sequenceName);
        sequenceMap.put(sequenceName, nextValue + 1);
        return nextValue;
    }
}
