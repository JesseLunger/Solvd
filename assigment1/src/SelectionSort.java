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

public class SelectionSort {
    /* Description: Selected Sort class with runtime n^2
    *
    * Args: None
    */
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

    public void sort(Integer[] arr) {
        /* Description: Selected Sort algorithm that does a comparison of each
        * entry in given array and swaps appropriately for minimum -> maximum
        *
        * Args: arr: Int array to be sorted
        */
        for (int i = 0; i < arr.length; i++) {
            int minimum = i;
            for (int j = i+ 1; j < arr.length; j++){
                if (arr[minimum] > arr[j]){
                    minimum = j;
                }
            }
            swap(arr, i, minimum);
        }
    }
}