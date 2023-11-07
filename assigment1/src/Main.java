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
        SelectionSort ss = new SelectionSort();
        Integer[] ssArr = Arrays.copyOf(numbers, numbers.length);
        ss.sort(ssArr);
        System.out.println("Results from Selected Sort: " + Arrays.toString(ssArr));

        /*Test for Merge Sort*/
        MergeSort ms = new MergeSort();
        Integer[] msArr = Arrays.copyOf(numbers, numbers.length);
        ms.sort(msArr);
        System.out.println("Results from Merge Sort: " + Arrays.toString(msArr));

        QuickSort qs = new QuickSort();
        Integer[] qsArr = Arrays.copyOf(numbers, numbers.length);
        qs.sort(qsArr);
        System.out.println("Results from Quick Sort: " + Arrays.toString(qsArr));

    }
}