/* Jesse Lunger
*
* Main function used to test the functionality of different sort functions
* */


import java.util.Arrays;
public class Main {
    public static void main(String[] args){
        Integer[] numbers = {5, 70, 3, -99, 60, 35, 10, 45, -66, 88, 60};

        System.out.println("Initial Array: " + Arrays.toString(numbers));

        /*Test for Selected Sort*/
        SelectionSort SS = new SelectionSort();
        Integer[] SSArr = Arrays.copyOf(numbers, numbers.length);
        SS.sort(SSArr);
        System.out.println("Results from Selected Sort: " + Arrays.toString(SSArr));

        /*Test for Merge Sort*/
        MergeSort MS = new MergeSort();
        Integer[] MSArr = Arrays.copyOf(numbers, numbers.length);
        MS.sort(MSArr);
        System.out.println("Results from Merge Sort: " + Arrays.toString(MSArr));


    }
}