public class HelloJack {
    public static void main(String[] args) {
        int rows = 4; // 金字塔的层数
        int num = 1; // 打印的数字，从1开始
        int spaces = 3; // 第一行开始的空格数

        for (int i = 1; i <= rows; i++) {
            // 打印空格
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }

            // 打印数字
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print(num);
            }

            // 换行
            System.out.println();

            // 计算下一行的空格数和数字
            spaces--;
            num++;
        }
    }
}
