package zbc.com.cn.licode;

class Demo {

    public static void main(String[] args) {
        System.out.println(getSum(100));
    }


    private static int getSum(int num) {
        if (num <= 1) {
            return 1;
        }

//        return  getSum(--num)+getSum();
        return 3;
    }


}
