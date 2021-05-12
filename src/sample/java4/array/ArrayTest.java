package sample.java4.array;


public class ArrayTest {
    private static void  print(String str){
        System.out.println(str);
    }
    /**
     * 十进制转换16进制数
     * @param num
     */
    public void toHex1(int num){
        print("十进制数toHex1："+num);
        StringBuffer sb = new StringBuffer();
        while (num>0){
            int temp = num&15;
            if(temp>9){
                sb.append((char)(temp-10+'A'));
            }else{
                sb.append(temp);
            }
            num = num>>>4;
        }
        print("转化十六进制数toHex1："+sb.reverse());
    }

    /**
     * 十进制转换十六进制数----查表法
     * @param num
     */
    public void toHex2(int num){
        print("十进制数查表法："+num);
        // 映射表
        char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        // 临时容器,int 类型存储是4字节
        char[] arr = new char[8];
        int pos = 8;

        while (num!=0){
            int temp = num&15;
            arr[--pos] = chars[temp];
            num=num>>>4;
        }
        System.out.print("转化十六进制数查表法：");
        for(int i=0,len=arr.length;i<len;i++){
            System.out.print(arr[i]);
        }
        print("");

    }
    /**
     * 十进制转二进制
     * @param num
     */
    public void toBin1(int num){
        print("十进制数："+num);
        StringBuffer sb = new StringBuffer();
        while (num>0){
            sb.append(num%2);
            num=num/2;
        }
        print("转化二进制数："+sb.reverse().toString());
    }

    /**
     * 十进制转二进制 查表法
     * @param num
     */
    public void toBin2(int num){
        print("十进制转二进制查表法："+num);
        char[] chs = {'0','1'};

        char[] arr = new char[32];
        int pos = 32;
        while (num!=0){
            int temp = num&1;
            arr[--pos] = chs[temp];
            num = num>>>1;
        }
        System.out.print("十进制转二进制查表法结果：");
        for(int i=0,len=arr.length;i<len;i++){
            System.out.print(arr[i]);
        }
        print("");
    }

    /**
     *
     * @param num 十进制数
     * @param base 转换的进制，2：二进制，16：十六进制
     * @param offset 计算位移的数
     */
    public static void trans(int num,int base,int offset){
        System.out.print("十进制："+num);
        // 定义表
        char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        // 定义容器
        char[] arr = new char[32];
        int pos = arr.length;
        while (num!=0){
            int temp = num&(base-1);
            arr[--pos] = chars[temp];
            num=num>>>offset;
        }
        System.out.print("\n转换"+base+"进制结果：");
        for(int i=0,len = arr.length;i<len;i++){
            System.out.print(arr[i]);
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        ArrayTest at = new ArrayTest();
//        at.toBin1(100);
        at.toHex1(60);
        at.toHex2(60);
        at.toBin2(6);
        trans(6,2,1);
        trans(60,8,3);
        trans(60,16,4);
    }
}
