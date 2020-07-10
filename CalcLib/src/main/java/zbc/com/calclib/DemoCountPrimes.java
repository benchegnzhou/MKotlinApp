package zbc.com.calclib;

import java.util.HashMap;

public class DemoCountPrimes {

    public static void main(String[] args) {

        System.out.println(countPrimes(15));
    }

    public static int countPrimes(int n) {
        int res = 0;
        int[] num = new int[n];
        if (n > 2) {
            res++;
        }
        for (int i = 3; i < n; i += 2) {
            if (num[i] == 0) {
                res++;
                for (int j = 1; j * i < n; j += 2) {
                    num[j * i] = 1;
                    System.out.println(j * i);
                }
            }
        }
        return res;
    }

 /*



    /*HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i < n; i++) {
            map.put(i, i);
        }

    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (i % 2 == 0 && i != 2) {
                continue; //偶数和1排除
            }

            boolean isPrimes = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrimes = false;
                    break;
                }
            }
            if (isPrimes) {
                System.out.println(i);
                count++;
            }
        }
        return count;
    }*/

}
