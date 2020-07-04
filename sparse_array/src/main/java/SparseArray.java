import java.io.*;

/**
 * 稀疏算法
 * 模拟五子棋
 */
public class SparseArray {

    /**
     * 1.创建二维数组
     * 2.将二维数组转稀疏数组
     * 3.将稀疏数组存盘，后 从磁盘中获取稀疏数组 恢复二维数组
     * 4.将稀疏数组恢复成二维数组
     *
     * @param args
     */
    public static void main(String[] args) {
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        chessArr1[4][6] = 2;
        //1.原始的二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //2.二维数组转稀疏数组
        int sum = 0;
        //记录值不为零的总数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //创建对应的稀疏数组
        int spareseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        spareseArr[0][0] = 11;
        spareseArr[0][1] = 11;
        spareseArr[0][2] = sum;
        //遍历二维数组，将非零的数存放到稀疏数组
        //count 记录是第几个非零的数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    spareseArr[count][0] = i;
                    spareseArr[count][1] = j;
                    spareseArr[count][2] = chessArr1[i][j];

                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("-----------稀疏数组-----------");
        for (int i = 0; i < spareseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", spareseArr[i][0], spareseArr[i][1], spareseArr[i][2]);
        }

        //3.将稀疏数组存盘，后 从磁盘中获取稀疏数组 恢复二维数组
        write(spareseArr,sum+1);
        spareseArr = read(sum+1);
        //4.将稀疏数组恢复到二维数组
        int chessArr2[][] = new int[spareseArr[0][0]][spareseArr[0][1]];
        System.out.println();
        System.out.println("-----------恢复后的二维数组-------------");
        for (int i = 1; i < spareseArr.length; i++) {
            chessArr2[spareseArr[i][0]][spareseArr[i][1]] = spareseArr[i][2];
        }
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * 从磁盘中取值
     *
     * @return
     */
    private static int[][] read(int sum) {
        int[][] sparseArr = new int[sum][3];
        File file = new File("sparseArray.txt");
        try {
            FileReader read = new FileReader(file);
            BufferedReader in = new BufferedReader(read);
            String line;
            int row=0;
            while((line = in.readLine())!=null) {
                String[] temp = line.split("\t");
                for (int i=0;i<temp.length;i++) {
                    sparseArr[row][i] = Integer.parseInt(temp[i]);
                }
                row++;
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("-----------从磁盘中读取的稀疏数组---------------");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        return sparseArr;
    }

    /**
     * 存盘
     * 全局格式化快捷键  ctl+alt+z
     * @param spareseArr
     */
    private static void write(int[][] spareseArr,int sum) {
        File file = new File("sparseArray.txt");
        try {
            FileWriter out = new FileWriter(file);
            for (int i = 0; i < sum; i++) {
                for (int j = 0; j < 3; j++) {
                    out.write(spareseArr[i][j] + "\t");
                }
                out.write("\r\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("存盘成功");
    }
}
