/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocomplete;
import java.util.*;
/**
 *
 * @author saicharan
 */
class State{
    int number;
    boolean initial;
    boolean fina;   
    State(boolean a,int b)
    {
        this.initial=a;
        this.number=b;
    }
}
 class complete{
     ArrayList<State> al=new ArrayList<State>();
     Stack<Character> s=new Stack<Character>();
     int TT[][]= new int[100][100];
     char alphabet[] =new char[62];
     int count=1;
     int top=-1;
     complete(){

        
        // Set of alphabets
        
        int j=65;
        for(int i=0;i<26;i++){
            alphabet[i]=(char)j;
            j++;
        }
        j=97;
         for(int i=26;i<52;i++){
            alphabet[i]=(char)j;
            j++;
        }
         j=48;
         for(int i=52;i<62;i++){
             alphabet[i]=(char)j;
             j++;
         }
         for(int i=26;i<=52;i++){
        System.out.print(alphabet[i]+" ");
       
    }
           for(int i=0;i<62;i++)
         {
             for(int k=0;k<62;k++)
             {
                 TT[i][k]=-1;
             }
      
          }
        
          System.out.println();
         String str[]={"mop","moth","math","pop","star","stop","talk","top"};
         Arrays.sort(str);
         State s0=new State(true,0);
         al.add(s0);
         int ns=s0.number;
         for(int i=0;i<str.length;i++)
         {
             for(int k=0;k<str[i].length();k++)
             {
                 ns=checktransition(str[i].charAt(k),ns);
             }
             for(State s:al){
                 if(s.number==ns){
                     s.fina=true;
                 }
             }
             ns=0;
            
         }
         for(int i=0;i<26;i++)
         {
             for(int k=0;k<62;k++)
             {
                 System.out.print(TT[i][k]+" ");
             }
            System.out.println();
          }
        
     }
     
      public int checktransition(char c,int ns)
    {
        int inputindex=0;
        for(int i=0;i<62;i++)
        {
            if(alphabet[i]==c)
            {
                inputindex=i;
                break;
            }
        }
        if(TT[ns][inputindex]==-1)
        {
        State s1=new State(true,count++);
       TT[ns][inputindex]=s1.number;
       al.add(s1);
        
        }
    
        
       return TT[ns][inputindex]; 
        
    }
      
      public void Search(String st){
          //System.out.print("working");
          int ns=0;
          for(int i=0;i<st.length();i++){
              int index=-2;
              for(int j=0;j<61;j++){
                  if(st.charAt(i)==alphabet[j]){
                      index=j;
                      break;
                  }
              }
              if(index==-2){
                  System.out.println("Invalid Characters(Only Aplha-Numerics are stored.");
                  System.exit(0);
              }
             
                
             else if(TT[ns][index]==-1){
                  System.out.println("No such string is used");
                  System.exit(0);
              }
             else{
                   ns=TT[ns][index];
                  s.push((Character)alphabet[index]);
                  top++;
              
             }
          }
         // System.out.println(s.size());
         // for(int l=0;l<s.size();l++)
         // System.out.print(s.elementAt(l));
          
         this.DFS(ns);
          
      }
      
      public void DFS(int ns){
        // System.out.println("Dfs of the loop started is "+ns);
          for(int t=0;t<62;t++){
             /* System.out.print(TT[ns][t]+" ");
              if(TT[ns][t]!=-1){
                  System.out.println("DFS of these shoule be run");
                  this.DFS(TT[ns][t]);
              }
              }*/
              if(TT[ns][t]!=-1){
                  //System.out.println(TT[ns][t]);
                  s.push((Character)alphabet[t]);
                  top++;
                  //ns=TT[ns][t];
                 //System.out.println("DFS of the loop running is "+ns+"On accepting "+alphabet[t]);
                  this.DFS(TT[ns][t]);
                 // System.out.println("DFS of the loop runiig is "+ns+"Leaving"+alphabet[t]);
                for(State e:al){
                      if(e.number==TT[ns][t]){
                          if(e.fina==true){
                               for(int l=0;l<s.size();l++){
                               System.out.print(s.elementAt(l));}
                               System.out.println();
                               
                          }
                      }
                  }
                  s.remove(top);
                  top=top-1;
                  //System.out.println();
              }
          }
        // System.out.println("Dfs of the loop ended is "+ns);
      }
 }

          
      
    



public class Autocomplete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        complete c= new complete();
        System.out.println("Enter the String to be used for Autocomplete Search:");
        String st=sc.nextLine();
        c.Search(st);
        
         
    }
   
}
