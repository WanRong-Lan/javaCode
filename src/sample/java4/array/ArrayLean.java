package sample.java4.array;

import java.util.Arrays;

public class ArrayLean {
    private static void  print(String str){
        System.out.println(str);
    }
    private static void printArray(int[] arr){
        System.out.print("["+arr[0]);
        for(int i=1;i<arr.length;i++){
            System.out.print(",");
            System.out.print(arr[i]);
        }
        System.out.print("]\n");
    }

    /**
     * int 数组两元素位置互换
     * @param arr int 数组
     * @param a 元素坐标
     * @param b 元素坐标
     */
    private static void swap(int[] arr,int a,int b){
        int item = arr[a];
        arr[a]=arr[b];
        arr[b]=item;
    }
    public void ArrayDome1(){
        // 数组定义的方式
        int[] arr1 = new int[3];
        int arr11[] = new int[4];
        int[] arr2 = new int[]{1,2,3,4};
        int[] arr3 = {1,2,3,4};
        int[] arr4 = new int[3];
        arr4[0]=1;
        arr4[1]=1;

        System.out.println(arr4);
    }

    /**
     * 数组的操作
     */
    public void ArrayDome2(){
        //获取数组元素
        int[] arr1 = new int[2];
        arr1[0]=0;
        arr1[1]=1;
        // 遍历获取
        for(int i=0;i<arr1.length;i++){
            print("arr1["+i+"]="+arr1[i]);
        }

    }

    /**
     * 生成指定int 数组
     * @param length 生成数组的长度
     * @return int[] 对象数组
     */
    public int[] getIntArrayList(int length){
        try{
            if(length<=0){
                throw new Exception("Array length <= 0 ");
            }
            int[] arr = new int[length];
            for(int i=0;i<length;i++){
                arr[i] = (int) (Math.random()*(length*10)+1);
            }
            return arr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new int[0];

    }
    /**
     * 获取数组最大值和最小值
     */
    public void getMax(int[] arr){
        int max = arr[0];
        for(int i =1;i<arr.length;i++){
            if(max<arr[i]){
                max = arr[i];
            }
        }
        print(max+"");
    }

    /**
     * 获取最小值
     * @param arr
     */
    public void getMin(int[] arr){
        int min = arr[0];
        for(int i=1;i<arr.length;i++){
            if(min>arr[i]){
                min=arr[i];
            }
        }
        print(min+"");
    }

    /**
     * 折半查找
     * 要求：保证该数组是有序数组
     * 应用：折半查找可以确定插入元素key所在的位置，那么最后return 的是mid值。
     * @param arr
     * @param key
     * @return
     */
    public int halfSearch1(int[] arr,int key){
        System.out.print("数组：");
        printArray(arr);
        System.out.println("对半查找，查值："+key);
        int min,max,mid;
        min=0;
        max=arr.length-1;
        mid=(max+min)/2;
        while (arr[mid]!=key){
            if(key>arr[mid]){
                min = min+1;
            }else if(key<arr[mid]){
                max = max-1;
            }
            if(min>max){
                System.out.println("arr查无："+key);
                 return -1;

            }
            mid=(max+min)/2;
        }
        System.out.println("查找arr["+mid+"]="+key);
        return mid;

    }

    public int halfSearch2(int[] arr,int key){
        System.out.print("数组：");
        printArray(arr);
        System.out.println("对半查找方法2，查找值："+key);
        int min = 0,max=arr.length-1,mid;
        while (min<=max){
            mid=(max+min)>>1;
            if(key>arr[mid]){
                min++;
            }else if(key<arr[mid]){
                max--;
            }else{
                System.out.println("查找arr["+mid+"]="+key);
                return mid;
            }
        }
        System.out.println("arr查无："+key);
        return -1;
    }

    /**
     * 选择排序
     * @param arr
     */
    public void selectSort(int[] arr){
        System.out.print("原数组：");
        printArray(arr);
        for(int i=0,len=arr.length;i<len;i++){
            for(int j = i+1;j < len; j++){
                if(arr[i]<arr[j]){
                    swap(arr,i,j);
                }
            }
            System.out.print("第"+i+1+"次选择排序：");
            printArray(arr);
        }
        System.out.print("选择排序后：");
        printArray(arr);
    }

    /**
     * 选择排序 优化版
     * @param arr
     */
    public void selectSort1(int[] arr){
        System.out.print("原数组：");
        printArray(arr);
        for(int i=0,len=arr.length;i<len;i++){
            int k = i;
            for(int j = i+1;j < len; j++){
                if(arr[k]>arr[j]){
                    k=j;
                }
            }
            swap(arr,i,k);
            System.out.print("第"+(i+1)+"次选择排序：");
            printArray(arr);
        }
        System.out.print("选择排序后：");
        printArray(arr);
    }

    /**
     * 冒泡排序
     * @param arr
     */
    public void bubbleSort(int[] arr){
        System.out.print("原数组：");
        printArray(arr);
        for(int i=0,len=arr.length;i<len-1;i++){
            for(int j=len-1;j>i;j--){
                if(arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                }
            }
            System.out.print("第"+i+"次冒泡排序：");
            printArray(arr);
        }
        System.out.print("冒泡排序后：");
        printArray(arr);
    }

    public void bubbleSort1(int[] arr){
        System.out.print("原数组：");
        printArray(arr);
        for (int i=0,len=arr.length;i<len-1;i++){
            int j=0;
            while (j<len-1-i){
                if(arr[j]>arr[++j]){
                    swap(arr,j-1,j);
                }
            }
        }

        System.out.print("冒泡排序后：");
        printArray(arr);
    }

    public void arraySort(int[] arr){
        System.out.print("排序前：");
        printArray(arr);
        System.out.print("排序后");
        Arrays.sort(arr);
        printArray(arr);
    }




    public static void main(String[] args) {
        ArrayLean al = new ArrayLean();
//        al.ArrayDome1();
//        al.ArrayDome2();
//        al.getMax(al.getIntArrayList(100));
//        al.getMin(al.getIntArrayList(1000));
//        al.selectSort(al.getIntArrayList(5));
//        al.bubbleSort(al.getIntArrayList(10));
//        al.arraySort(al.getIntArrayList(10));
        int[] arr = al.getIntArrayList(10);
//        al.selectSort1(arr);
        al.bubbleSort1(arr);
//        int key = arr[9];
//        al.arraySort(arr);
//        al.halfSearch2(arr,key);
//
//        // 二维数组
//        int[][] arrInt = new int[3][];
//        arrInt[0] = new int[3];
//        arrInt[1] = new int[1];
//        arrInt[2] = new int[2];



//
//        int[] arr = {22,43,26,38,13,59,28,41};
//        for(int i=0,len = arr.length;i<len-2;i++){
//            int minIndex=i;
//            for(int j=len-1;j>i;j--){
//                if(arr[i]>arr[j]){
//                    minIndex=j;
//                }
//            }
//            int temp = arr[i];
//            arr[i]=arr[minIndex];
//            arr[minIndex]=temp;
//
//        }
//        printArray(arr);
    }
}
