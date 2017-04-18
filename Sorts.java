import java.lang.Math;

public class Sorts
{
    public static void main(String[] args) {
        int number_size = 1000000;
        int[] mergeSizes = new int[]{10, 100, 1000, 10000, 100000, 1000000, 5000000};
        int[] selectSizes = new int[]{10, 100, 1000, 10000, 100000, 200000, 300000};
        int runs = 5;
        int sets = 7;
        
        //PrintTable
        System.out.println("Merge Sort, average of " + runs + " runs: ");
        System.out.format("%-1s %7s ", "|", "n");
        System.out.format("%-1s %15s ", "|", "C(n)");
        System.out.format("%-1s %15s %-1s %n", "|", "T(n)", "|");
        for (int i = 0; i < sets; i++) {

            long count = 0;
            long time = 0;

            for (int j = 0; j < runs; j++) {

                long start;
                long end;
                int[] nums = new int[mergeSizes[i]];

                for (int k = 0; k < mergeSizes[i]; k++) {
                    nums[k] = (int)(Math.random() * number_size);
                }
                
                //Per class instructions calculate time needed
                start = System.nanoTime();
                count += mergesort(nums);
                end = System.nanoTime();
                time += end - start;
                
            }
            

            System.out.format("%-1s %7d ", "|", mergeSizes[i]);
            System.out.format("%-1s %15d ", "|", (count/runs));
            System.out.format("%-1s %15d %-1s %n", "|", (time/runs), "|");
        }
      
        System.out.println("\n\nInsertion Sort, average of " + runs + " runs:");
        System.out.format("%-1s %7s ", "|", "n");
        System.out.format("%-1s %15s ", "|", "C(n)");
        System.out.format("%-1s %15s %-1s %n", "|", "T(n)", "|");

        for (int i = 0; i < sets; i++) {

            long count = 0;
            long time = 0;
            
            for (int j = 0; j < runs; j++) {

                long start;
                long end;
                int[] nums = new int[selectSizes[i]];

                for (int k = 0; k < selectSizes[i]; k++) {
                    nums[k] = (int)(Math.random() * number_size);
                }
                start = System.nanoTime();
                count += insertionsort(nums);
                end = System.nanoTime();
                time += end - start;
                            }
            //FORMAT PRINTOUT
            System.out.format("%-1s %7d ", "|", selectSizes[i]);
            System.out.format("%-1s %15d ", "|", (count/runs));
            System.out.format("%-1s %15d %-1s %n", "|", (time/runs), "|");
        }
       
    }
    /*--------------Insertion Sort -----------------------*/
    public static long insertionsort( int[] list)
    {
        long count = 0;
        int temp;
        for (int i = 1; i < list.length; i++) {
        
         for(int j = i ; j > 0 ; j--){
                count++;
                if(list[j] < list[j-1]){
                    temp = list[j];
                    list[j] = list[j-1];
                    list[j-1] = temp;
                }
            }
        }
        return count;
        
    }
/* --------------------Merge Sort --------------------*/
    //merges sorted slices a[i.. j] and a[j + 1 É  k] for 0<=  i <=j < k < a.length

    public static long merge ( int[] a,  int i, int j , int k)
    {
        int size = k-i+1;
        int[] temp = new int[size];
        int tempi = i;
        int tempj = j+1;
        long compare = 0;
        

        for (int l = 0; l < size ; l++) {
            if(tempi == j+1)
            {
            
                temp[l] = a[tempj++];
                
            }else 
               if(tempj == k+1)
            {
            
                temp[l] = a[tempi++];
                
            }else 
               if(a[tempi] <= a[tempj])
            {
            
                temp[l] = a[tempi++];
                compare++;
                
            }else
            {
                temp[l] = a[tempj++];
                compare++;
            }
        }
        
        for (int l = 0; l < size; l++) {
            a[i++] = temp[l];
        }
        return compare;
    }
    //sorts  list[ i .. k]  for 0<=i <= k < list.length
    private  static  long mergesort(int[] list,  int i ,  int j)

  {
        long compare = 0;
        if((j-i)<=0)
        {
            return compare;
        }else
        {
            int middle = (i + j) / 2;
            compare += mergesort(list, i, middle);
            compare += mergesort(list, middle + 1, j);
            compare += merge(list, i, middle, j);
        }
        return compare;
    }


   
    //Sorts the array using mergesort
    public static long mergesort(int[] a )
    {
        long compare = mergesort(a,0, a.length-1);
        return compare;
    }
    public static boolean isSorted( int[] a)
    {
        for (int i = 1; i < a.length; i++) {
            if(a[i]<a[i-1])
                return false;
        }
        return true;
    }
}

