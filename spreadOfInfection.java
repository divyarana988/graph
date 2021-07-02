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
   
   public static class Bpair{
       int vtx, time;
       Bpair(int vtx, int time){
           this.vtx = vtx;
           this.time = time;
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
         graph[v1].add(new Edge(v1, v2));
         graph[v2].add(new Edge(v2, v1));
      }

      int src = Integer.parseInt(br.readLine());
      int t = Integer.parseInt(br.readLine());
      
      ArrayDeque<Bpair> q = new ArrayDeque<>();
      q.add(new Bpair(src, 1));
      int[] vis = new int[vtces];
      int count =0;
      
      while(q.size()>0){
          Bpair rem = q.removeFirst();
          if(vis[rem.vtx]>0){
              continue;
          }
          
          vis[rem.vtx] = rem.time;
          if(rem.time>t){
              break;
          }
          count++;
          
          for(Edge e: graph[rem.vtx]){
              if(vis[e.nbr]==0){
                  q.add(new Bpair(e.nbr, rem.time +1));
              }
          }
      }
      
      System.out.println(count);
      
   }

}