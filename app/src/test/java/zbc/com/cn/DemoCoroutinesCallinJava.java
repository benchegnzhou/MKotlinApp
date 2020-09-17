package zbc.com.cn;

class DemoCoroutinesCallinJava {
    public static void main(String[] args) {
        //使用这样的代码可以阻塞住线程，获取结果，但是相比kotlin回调还是蛮不一样的
        System.out.println(CoroutinetsUseForJavaKt.loadString());
    }
}
