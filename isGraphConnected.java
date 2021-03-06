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

        System.out.println(gcc(graph));
   }
   
   public static boolean gcc(ArrayList<Edge> []graph){
       
       ArrayList<ArrayList<Integer>> allComps = new ArrayList<>();
       boolean vis[] = new boolean[graph.length];
       
       for(int i=0; i<graph.length; i++){
           
           if(vis[i] == false){
               ArrayList<Integer> res = new ArrayList<>();
               gcc(graph, i, res, vis);
               allComps.add(res);
           }
           
       }
       if(allComps.size()==1){
           return true;
       }
       return false;
   }
   
   public static void gcc( ArrayList<Edge> [] graph, int src,  ArrayList<Integer> res, boolean vis[]){
       
       res.add(src);
       vis[src] = true;
       for(Edge e: graph[src]){
           
           if(vis[e.nbr] == false){
               gcc(graph, e.nbr, res, vis);
           }
       }
       
   }
   
   
   
   
   
}