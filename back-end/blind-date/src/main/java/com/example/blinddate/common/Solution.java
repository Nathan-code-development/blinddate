package com.example.blinddate.common;

class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr1 = new int[m];
        int[] arr2 = new int[n];
        for(int i = 0;i < m;i++){
            arr1[i] = nums1[i];
        }
        for(int j = 0;j < n;j++){
            arr2[j] = nums2[j];
        }
        int[] arrMerge = new int[m + n];
        for(int k = 0;k < arrMerge.length;k++){
            if(k < 3){
                arrMerge[k] = arr1[k];
            }else{
                arrMerge[k] = arr2[k - 3];
            }
        }
        for(int a = 0;a < arrMerge.length;a++){
            for(int b = 0;b < arrMerge.length - a - 1;b++){
                if(arrMerge[b] >= arrMerge[b + 1]){
                    int temp = arrMerge[b];
                    arrMerge[b] = arrMerge[b + 1];
                    arrMerge[b + 1] = temp;
                }
            }
        }
        System.out.print("合并结果是 [");
        for(int c = 0;c < arrMerge.length;c++){
            if(c != arrMerge.length - 1) {
                System.out.print(arrMerge[c] + ", ");
            }else{
                System.out.print(arrMerge[c] + "]");
            }
        }
    }

    public static void main(String[] args) {
        int []arr1 = {1, 2, 3, 0, 0, 0};
        int []arr2 = {2, 5, 6};
        int m = 3;
        int n = 3;
        merge(arr1, m, arr2, n);
    }
}