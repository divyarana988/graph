import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt) {
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }
   
   public static class Spair implements Comparable<Spair>{
       int vtx, wsf;
       String psf;
       
       Spair(int vtx, int wsf, String psf){
           this.vtx = vtx;
           this.wsf = wsf;
           this.psf = psf;
       }
       public int compareTo(Spair o){
           return this.wsf-o.wsf;
       }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      int src = Integer.parseInt(br.readLine());
      
      shortestPath(graph, src);
   }
   
   public static void shortestPath(ArrayList<Edge>[]graph, int src){
       PriorityQueue<Spair> pq = new PriorityQueue<>();
       pq.add(new Spair(src, 0, ""+src));
       boolean vis[] = new boolean [graph.length];
       
       while(pq.size()>0){
           Spair pair = pq.remove();
           if(vis[pair.vtx]==false){
               vis[pair.vtx] = true;
               System.out.println(pair.vtx+" via "+pair.psf+" @ "+pair.wsf);
               
               for(Edge e: graph[pair.vtx]){
                   if(vis[e.nbr]==false){
                       pq.add(new Spair(e.nbr, pair.wsf+e.wt, pair.psf+e.nbr));
                   }
               }
           }
       }
   }  
}