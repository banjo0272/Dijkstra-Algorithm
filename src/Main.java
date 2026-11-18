import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);

        System.out.println("Graph file to read from: ");
        Dj d = new Dj(in.nextLine().trim());
        System.out.println("List of nodes: " + String.join(", ", d.nodes()));
        while(true) {
            System.out.print("\nStarting node: ");
            String start = in.nextLine().trim();

            if(start.isEmpty()) break;
            if(!d.nodes().contains(start)){
                System.out.println("Invalid");
                continue;
            }
            System.out.println();
            d.run(start);
        }
    }
}
