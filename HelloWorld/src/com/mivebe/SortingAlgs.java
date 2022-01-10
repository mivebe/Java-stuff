package com.mivebe;

import java.util.*;

public class SortingAlgs {
    public static void test(){
        test("insertion",10);
    }
    public static void test(String method){
        test(method, 10);
    }
    public static void test(String method, int numberCount){
        Random rand = new Random();
        int[] numbers = new int[numberCount];

        for (int i = 0; i < numbers.length; i++){
            numbers[i] = rand.nextInt(100);
        }

        System.out.println("Before: ");
        printArray(numbers);

        if (method == "insertion") {
            insertionSort(numbers);
        }else if (method == "quick"){
            quickSort(numbers);
        }else if (method == "merge"){
            mergeSort(numbers);
        }else if (method == "bubble"){
            bubbleSort(numbers);
        }else if (method == "bogus"){
            boguSort(numbers);
        }

        System.out.println("\nAfter: ");
        printArray(numbers);


    }

    private static void printArray(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

    private static void merge(int[] arr, int[] leftHalf, int[] rightHalf){
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize){
            if (leftHalf[i] <= rightHalf[j]){
                arr[k] = leftHalf[i];
                i++;
            }else {
                arr[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while (i < leftSize){
            arr[k] = leftHalf[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            arr[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    private static void swap(int[] arr, int index1, int index2){
        int container = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = container;
    }

    private static boolean isSorted(List<Integer> arrList) {
        if (arrList == null) {
            return true;
        }

        int length = arrList.size();
        if (length <= 1) {
            return true;
        }

        for (int i = 0; i < length - 1; i++){
            if (arrList.get(i) > arrList.get(i + 1)){
                return false;
            }
        }

        return true;
    }

    public static void insertionSort(int[] inputArray){
        for (int i = 1; i < inputArray.length; i++){
            int curV = inputArray[i];
            int j = i - 1;

            while (j >= 0 && inputArray[j] > curV){
                inputArray[j + 1] = inputArray[j];
                j--;
            }
            inputArray[j + 1] = curV;
        }
    }

    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int lowIndex, int highIndex){
        if(lowIndex >= highIndex){
            return;
        }

        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, highIndex);

        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while(leftPointer < rightPointer) {
            while (arr[leftPointer] <= pivot && leftPointer < rightPointer){
                leftPointer++;
            }
            while (arr[rightPointer] >= pivot && leftPointer < rightPointer){
                rightPointer--;
            }

            swap(arr, leftPointer, rightPointer);
        }

        swap(arr, leftPointer, highIndex);

        quickSort(arr, lowIndex, leftPointer-1);
        quickSort(arr, leftPointer +1 , highIndex);
    }

    public static void mergeSort(int[] arr) {
        int arrLength = arr.length;

        if(arrLength < 2) {
            return;
        }

        int midPoint = arrLength / 2;
        int[] leftHalf = new int[midPoint];
        int[] rightHalf = new int[arrLength - midPoint];

        for (int i = 0; i < midPoint; i++) {
            leftHalf[i] = arr[i];
        }
        for (int i = midPoint; i < arrLength; i++){
            rightHalf[i - midPoint] = arr[i];
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(arr, leftHalf, rightHalf);
    }

    public static void bubbleSort(int[] arr) {
        boolean swapped = false;
        while (!swapped) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                swapped = true;
                    int container = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = container;
                }
            }
        }
    }

    public static void boguSort(int[] arr) {
        List<Integer> arrList = new ArrayList<>();

        for (int value : arr){
            arrList.add(Integer.valueOf(value));
        }

        while (!isSorted(arrList)) {
            Collections.shuffle(arrList);
        }

        for (int i = 0; i < arrList.size(); i++) {
            arr[i] = arrList.get(i);
        }

    }
}
