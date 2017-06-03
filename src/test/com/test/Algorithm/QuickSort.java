package com.test.Algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by eds on 2017/6/1.
 */
public class QuickSort {

    @Test
    public void testQuickSort(){
        int[] array = new int[]{1,4,5,2,1,3,7,9,6,2,3,22,1,-5,3,-9};
        QuickSort.quickSort2(array,0,array.length-1);
//        sort(array,0,10);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }

    public static void quickSort(int[] array,int left,int right){
        if (left>right){return;}
        int i=left;
        int j=right;
        int tmp;
        int middle = array[left];

        while(i<j){

            while (i<j && array[j]>=middle)
                j--;


            while(i<j && array[i]<= middle)
                i++;
            if (i<j) {
                 tmp = array[i];
                array[i]=array[j];
                array[j]=tmp;
            }

        }

        //while退出条件i=j
        array[left]=array[i];
        array[i]=middle;


        //分治理
        quickSort(array,left,i-1);
        quickSort(array,i+1,right);

    }

    public static void quickSort2(int[] array,int left,int right){
        if (left>right){return;}
        int i=left;
        int j=right;
        int tmp;
        int middle = array[left];

        while(i<j){

            while (i<j && array[j]>=middle)
                j--;
            if (i<j){
                array[i]=array[j];
                i++;
            }


            while(i<j && array[i]<= middle)
                i++;
            if (i<j) {
               array[j]=array[i];
                j--;
            }

        }
        //循环退出，此时i
        array[i]=middle;
        //分治理
        quickSort(array,left,i-1);
        quickSort(array,i+1,right);
    }

    public static int partition(int []array,int lo,int hi){
        //三数取中
        int mid=lo+(hi-lo)/2;
        if(array[mid]>array[hi]){
            swap(array[mid],array[hi]);
        }
        if(array[lo]>array[hi]){
            swap(array[lo],array[hi]);
        }
        if(array[mid]>array[lo]){
            swap(array[mid],array[lo]);
        }
        int key=array[lo];

        while(lo<hi){
            while(array[hi]>=key&&hi>lo){
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }
    public static void sort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        int index=partition(array,lo,hi);
        sort(array,lo,index-1);
        sort(array,index+1,hi);
    }

    public static void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
    }

}
