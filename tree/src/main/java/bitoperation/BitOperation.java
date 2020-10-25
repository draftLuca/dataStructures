package bitoperation;

/**
 * 源码：最直接的二进制表示方法
 * 反码：
 *      正数：和原码一样
 *      负数：符号位不变，其余位按位取反
 * 补码(真正参与运算)：
 *      正数：和原码一样
 *      负数：符号位不变，反码+1
 * 左移(<<)：
 *      规则：高位丢失，低位补0
 * 右移(>>):
 *      规则：正数->低位丢失，高位补0
 *           负数->低位丢失，高位补1
 *
 * 补码->源码 （把补码看做源码，再求补码，此时计算出来的补码就是源码）
 *
 * sizeof 运算符，不是一个函数，求一个类型变量所占内存的大小
 *
 */
public class BitOperation {

    public static void main(String[] args) {

        int a = 10 ,b = 0;
        //左移(<<)
        System.out.println(a<<2);
        System.out.println(a>>2);


    }

}
