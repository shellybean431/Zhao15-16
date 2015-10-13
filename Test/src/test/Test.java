/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static java.lang.Math.floor;

/**
 *
 * @author Sheldon
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    
    static void mergesort(int a[], int p, int r)
    {
        int q;
        if(p < r)
        {
            q = (int) floor( (p+r) / 2);
            mergesort(a, p, q);
            mergesort(a, q+1, r);
            merge(a, p, q, r);
        }
    }

    static void merge(int[] a, int p, int q, int r)
    {
        int[] b = new int[a.length];     //same size of a[]
        int i, j, k;
        k = 0;
        i = p;
        j = q+1;
        while(i <= q && j <= r)
        {
            if(a[i] < a[j])
            {
                b[k++] = a[i++];       // same as b[k]=a[i]; k++; i++;
            }
            else
            {
                b[k++] = a[j++];
            }
    }
  
  while(i <= q)
  {
    b[k++] = a[i++];
  }
  
  while(j <= r)
  {
    b[k++] = a[j++];
  }
  
  for(i=r; i >= p; i--)
  {
    a[i] = b[--k];        // copying back the sorted list to a[]
  } 

}
    
   static void quicksort(int a[], int p, int r)    
{
  if(p < r)
  {
    int q;
    q = partition(a, p, r);
    quicksort(a, p, q);
    quicksort(a, q+1, r);
  }
}

static int partition(int a[], int p, int r)
{
  int i, j, pivot, temp;
  pivot = a[p];
  i = p;
  j = r;
  while(true)
  {
   while(a[i] < pivot && a[i] != pivot)
   i++;
   while(a[j] > pivot && a[j] != pivot)
   j--;
   if(i < j)
   {
    temp = a[i];
    a[i] = a[j];
    a[j] = temp;
   }
   else
   {
    return j;
   }
  }
}
    public static void main(String[] args) {
        // TODO code application logic here
        String uno = "Sheldon Z";
        String dos = "Sheldon Zhao";
        String tres = "Sheldon";
        
        String one = "SomeRandom Guy";
        String two = "Some RandomGuy";
        String three = "Some Random Guy";
        System.out.println(two.compareTo(tres));
    int[] test = {123, 124, 9, 403, 383, 372, 401, 71, 91, 1053, 911};
        mergesort(test, 0, test.length-1);
        for (int i=0; i<test.length; i++)
        {
            System.out.println(test[i]);
        }
    }
    
}
