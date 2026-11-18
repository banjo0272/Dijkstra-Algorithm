import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dj {
    private final Map<String, Map<String, Double>> graph = new TreeMap<>();

    public Dj(String file) throws IOException {
        for(String line: Files.readAllLines(Paths.get(file))) {
            String[] p= line.trim().split("\\s+");
            if(p.length != 3) continue;
            double w = Double.parseDouble(p[2]);
            graph.computeIfAbsent(p[0], k-> new HashMap<>()).put(p[1], w);
            graph.computeIfAbsent(p[1], k-> new HashMap<>()).put(p[0], w);
        }
    }

    public Set<String> nodes() {
        return graph.keySet();
    }

    public void run(String start) {
        Map<String, Double> dist= new HashMap<>();
        Map<String, Double>prev = new HashMap<>();
        for(String v : graph.keySet()) {
            dist.put(v, Double.POSITIVE_INFINITY);
            prev.put(v, null);
        }



    }
}
