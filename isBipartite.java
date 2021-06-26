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

    public static class Bpair{
        int vtx; int level;
        Bpair(int vtx, int level){
            this.vtx = vtx;
            this.level = level;
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

      System.out.println(isBipartite(graph));
   }
   
   public static boolean isBipartite(ArrayList<Edge>[]graph){
       boolean vis[] = new boolean[graph.length];
       for(int v=0; v<graph.length; v++){
           if(vis[v] == false){
               boolean res = isCompB(graph, v, vis);
               if(res==false){
                   return false;
               }
           }
       }
       return true;
   }
   
   public static boolean isCompB(ArrayList<Edge>[]graph, int src, boolean vis[]){
       Queue<Bpair> que = new ArrayDeque<>();
       que.add(new Bpair(src,0));
       HashMap<Integer, Integer> hm = new HashMap<>();
       while(que.size()>0){
           Bpair pair = que.remove();
           if(vis[pair.vtx]==true){
               if(pair.level%2!=hm.get(pair.vtx)%2){
                   return false;
               }
           }
           else{
               hm.put(pair.vtx, pair.level);
               vis[pair.vtx] = true;
               for(Edge e: graph[pair.vtx]){
                   if(vis[e.nbr]==false){
                       que.add(new Bpair(e.nbr, pair.level+1));
                   }
               }
           }
       }
       return true;
   }  
}