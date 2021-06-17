import java.io.*;
import java.util.*;

public class Main {
   
   public static class Edge{
       int src, nbr, wt;
       Edge(int src, int nbr, int wt){
           this.src = src;
           this.nbr = nbr;
           this.wt = wt;
       }
   }

   public static void main(String[] args) throws Exception {
      Scanner scn = new Scanner(System.in);

      int n = scn.nextInt();
      int k = scn.nextInt();
      
      // write your code here
     ArrayList<Edge> [] graph = new ArrayList[n];
     for(int i=0; i<n; i++){
         graph[i] = new ArrayList<>();
     }
     
     for(int i=0; i<k; i++){
         int v1 = scn.nextInt();
         int v2 = scn.nextInt();
         int wt = 0;
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
     }
     
     ArrayList<ArrayList<Integer>> allComps = gcc(graph);
     int res =0;
     
     for(int i=0; i<allComps.size(); i++){
         for(int j=i+1; j<allComps.size(); j++){
             ArrayList<Integer> ith = allComps.get(i);
             ArrayList<Integer> jth = allComps.get(j);
             
             res+=(ith.size()*jth.size());
             
         }
     }
     System.out.println(res);
   }
    
    public static ArrayList<ArrayList<Integer>> gcc(ArrayList<Edge> []graph){
       
       ArrayList<ArrayList<Integer>> allComps = new ArrayList<>();
       boolean vis[] = new boolean[graph.length];
       
       for(int i=0; i<graph.length; i++){
           
           if(vis[i] == false){
               ArrayList<Integer> res = new ArrayList<>();
               gcc(graph, i, res, vis);
               allComps.add(res);
           }
           
       }
      return allComps;
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

