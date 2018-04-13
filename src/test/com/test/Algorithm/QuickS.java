package com.test.Algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by eds on 2018/4/2.
 */
public class QuickS {

    @Test
    public void testSort(){
        int[] array =new int[]{1,2,3,-1,5,8,2,17,33,123,88,99,44};
        quickSort(array,0,array.length-1);

        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }

    /**
     * 分治填坑
     * 
     * @param array
     * @param left
     * @param right
     */
    public static void quickSort(int[] array, int left, int right) {
        // 结束
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;
        int middle = array[i];

        // 一次排序 以middle为中心，左边都小于middle,右边都大于middle
        while (i < j) {
            while (i < j && array[j] >= middle) {
                j--;
            }
            // 当array[j]小于middle时，交换
            if (i < j) {
                array[i] = array[j];
                i++;
            }

            // 从i开始向后 找出大于middle的
            while (i<j && array[i]<= middle) {
                i++;
            }
            //直到 array[i] > middle时，交换位置
            if (i<j){
                array[j]=array[i];
                j--;
            }
            //如此一趟，if i<j 继续从j扫描
        }
        //while循环后，得到i的位置，这个位置就是middle的位置
        array[i]=middle;
        quickSort(array,left,i-1);
        quickSort(array,i+1,right);

    }
}
