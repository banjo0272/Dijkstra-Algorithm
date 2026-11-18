import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

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

        PriorityQueue<Map.Entry<String, Double>> pq= new PriorityQueue<>(Map.Entry.comparingByValue());

        dist.put(start, 0.0);
        pq.add(Map.entry(start, 0.0));

        while(!pq.isEmpty()){
            Map.Entry<String, Double> cur= pq.poll();
            String u= cur.getKey();
            if(cur.getValue() > dist.get(u)) continue;
            for(Map.Entry<String, Double> e: graph.get(u).entrySet()) {
                String v= e.getKey();

                double alt =dist.get(u)+e.getValue();
                if(alt< dist.get(v)){
                    dist.put(v, alt);
                    prev.put(v, Double.valueOf(u));
                    pq.add(Map.entry(v, alt));
                }
            }
        }
        DecimalFormat fmt = new DecimalFormat("0.###")

        for(String node : graph.keySet()) {
            if(dist.get(node) == Double.POSITIVE_INFINITY){
                System.out.println(start + "= " + node+ ": unreachable");
                continue;
            }
        }


        LinkedList<String> path = new LinkedList<>();
        for(String n= node; n != null; n=prev.get(n)) {
            path.addFirst(n);
        }

        System.out.println(String.join("-> ", path) + " dist: " + fmt.format(dist.get(node)));

    }
}
