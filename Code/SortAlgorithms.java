 import java.util.Stack;
 public class SortAlgorithms {
     // exchange data
         protected static void exch(Student[] ST, int i, int j) 
         { 
             // exchange the surname
             String tmpsur = ST[i].sur; 
             ST[i].sur = ST[j].sur; 
             ST[j].sur = tmpsur; 
                 
             // exchange the given name
             String tmpgiv = ST[i].giv; 
             ST[i].giv = ST[j].giv; 
             ST[j].giv = tmpgiv; 
             // exchange the id
             String tmpid = ST[i].id; 
             ST[i].id = ST[j].id; 
             ST[j].id = tmpid; 
             
             // exchange the score
              double tmpsc = ST[i].score; 
              ST[i].score = ST[j].score; 
              ST[j].score = tmpsc; 
              
             // exchange the Grade
              String tmpGR = ST[i].Grade; 
              ST[i].Grade = ST[j].Grade; 
              ST[j].Grade = tmpGR; 
              
         }
 
         
         // compare and decide whether v < w
         public static boolean less(Comparable v, Comparable w) 
         { 
             return v.compareTo(w) < 0; 
         }
             
         
         // using selection sort to sort a[] into increasing order. 
         public static  Student[] sort(Student[] ST, int num) 
         { 
             int N = ST.length; // array length 
             
             // a[i] is used to store the smallest data
             for (int i = 0; i < N; ++i) { 
                 int min = i; // index of minimal. 
                 
                 switch(num) {
                 
                 // sort by surname
                 case 1:
                     
                     for (int j = i+1; j < N; ++j) {			
                         if (less(ST[j].sur, ST[min].sur)) {min = j;} 
                     }
                         exch(ST, i, min); // exchange 
                         break;
                         
                 // sort by id
                 case 2:
                 
                 for (int j = i+1; j < N; ++j) {			
                     if (less(ST[j].id, ST[min].id)) {min = j;} 
                 }
                     exch(ST, i, min); // exchange 
                     break;
                             
                 // sort by score
                 case 3:
                     
                     for (int j = i+1; j < N; ++j) {			
                         if (less(ST[j].score, ST[min].score)) {min = j;} 
                     }
                         exch(ST, i, min); // exchange 
                         break;	
                                         
                 // sort by Grade
                 case 4:
                     
                     for (int j = i+1; j < N; ++j) {			
                         if (less(ST[j].score, ST[min].score)) {min = j;} 
                     }
                         exch(ST, i, min); // exchange 
                         break;	
                                             
                 // sort by GPA
                 case 5:
                     
                     for (int j = i+1; j < N; ++j) {			
                         if (less(ST[j].GPA, ST[min].GPA)) {min = j;} 
                     }
                         exch(ST, i, min); // exchange 
                         break;	
                                             
                         
                 } // end the switch			
             }
             return ST;				
         }
 
         
         // ask the user for sorting field 
         public static Student[] sortField(Student[] ST) {
             
             Student[] stu = sort(ST,GUI.num); // sort by correlative sortfield
                     
             return sortWay(stu); // sort by correlative sortway and return the result
                 
         } 
         
         
         // ask the user for sorting way
         public static Student[] sortWay(Student[] ST) {
                         
             Student[] newST = ST;
             
             // use java stack to implement descent
             if(GUI.RadioButton2.isSelected()) {         		
                 Stack<Student> sta = new Stack<Student>();
                 for(int i = 0; i < ST.length; ++i) {
                     sta.push(ST[i]);				
                 }
                 
                 for(int j = 0; j < ST.length; ++j) {
                     newST[j] = sta.pop();
                 }					
             }
                 
             return newST;
         }
         
 
 } // end the class