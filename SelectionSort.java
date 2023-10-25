package Solvd;
import java.util.Arrays;
public class SelectionSort {
    public Integer[] numbers = {5, 70, 3, 60, 35, 10, 45, 88, 60};
    public int length = numbers.length;

    public void swap(Integer[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void main(String[] args) {
        SelectionSort sorter = new SelectionSort();
        for (int i = 0; i < sorter.length; i++) {
            int minimum = i;
            for (int j = i+ 1; j < sorter.length; j++){
                if (sorter.numbers[minimum] > sorter.numbers[j]){
                    minimum = j;
                }
            }
            if (minimum != i) {
                sorter.swap(sorter.numbers, i, minimum);
            }
        }
        System.out.println(Arrays.toString(sorter.numbers));
    }
}
