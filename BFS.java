import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;

      Edge(int src, int nbr) {
         this.src = src;
         this.nbr = nbr;
      }
   }
   
   public static class Pair{
       int vtx;
       String psf;
       
       Pair(int vtx, String psf){
           this.vtx = vtx;
           this.psf = psf;
       }
   }
   
   public static void bfs(ArrayList<Edge>[]graph, int src){
       
       Queue<Pair> queue = new ArrayDeque<>();
       
       queue.add(new Pair(src, "" + src));
       boolean visited[] = new boolean[graph.length];
       
       while(queue.size()>0){
           
           Pair fpair = queue.remove();
           if(visited[fpair.vtx] == false){
               visited[fpair.vtx] = true;
               System.out.println(fpair.vtx + "@" + fpair.psf);
               
           }
           
           for(Edge e: graph[fpair.vtx]){
               if(visited[e.nbr] == false){
                   queue.add(new Pair(e.nbr, fpair.psf+e.nbr));
               }
           }
           
       }
       
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = scn.nextInt();
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = scn.nextInt();
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         graph[v1].add(new Edge(v1, v2));
         graph[v2].add(new Edge(v2, v1));
      }

      int src = scn.nextInt(); 
      bfs(graph, src);
   }
}