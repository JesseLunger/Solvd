/*
 * Jesse Lunger
 *
 * Copyright: None
 *
 * Referring to opensource template:  https://github.com/auth0/open-source-template/blob/master/LICENSE
 * for use of Software. See below:
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software
 * and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall
 * be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR  OTHER DEALINGS IN THE SOFTWARE.
 */


/*
* Side note: I'm not actually sure if this implementation is correct, it seems to pass my basic test but
* after completing this sort function, I didn't see any examples online that were as effecient as I
* believe this to be.
* */
public class MergeSort {

    private void swap(Integer[] arr, int x, int y) {
        /*
         *Description: Function that swaps two integers within a integer array
         *
         * Args: arr: int Array
         *       x: index for item to be swapped
         *       y: index for other item to be swapped
         */
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    private void merge(Integer[] arr, int start, int mid, int end) {
        /*
        * Description: Merge that is both n(log(n)) time complexity and O(1) space complexity
        *
        * Args: arr: Array of Integers
        *       start: index for start of left array,
        *       mid: index for the midpoint between the arrays
        *       end: index for the end of the right array.
        */
        int i = start;                      /*i can be located anywhere in entire array*/
        int j = mid + 1;                    /*j will always represent start of right array*/

        /*No need to proceed if last element of left side is less than first element of right*/
        if (arr[mid] <= arr[j]){
            return;
        }
        while (i < j){
            if (arr[i] < arr[j]){                       /*if left is less than right proceed*/
                i++;
            } else {
                swap(arr, i, j);
                i++;
                /*Correction Phase*/
                if ((j < end) && arr[j] > arr[j+1]){    /*if the swap results in right array not sorted move midpoint*/
                    j++;
                }
                if (arr[i] < arr[i - 1]){               /*Swap can result in element behind i to be greater, fix*/
                    swap(arr, (i - 1), i);
                    i++;
                }
            }
        }
    }

    private void recurSort(Integer[] arr, int start, int end) {
        /*
        * Description: Recursive sort that divides array into smaller part to be sorted individually
        *
        * Args: arr: Integer array to be sorted
        *       start: index for start of left array
        *       end: index for start of right array
        */
        if (start < end) {
            int mid = (start + end) / 2;
            recurSort(arr, start, mid);
            recurSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public void sort(Integer[] arr) {
        /*
        * Description: functions that initiates recurSort (see above) or the given array
        *
        * Args: arr: Integer array to be sorted
        * */
        recurSort(arr, 0, arr.length - 1);
    }

}
