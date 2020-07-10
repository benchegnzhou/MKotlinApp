package zbc.com.calclib;

public class Demo {

    public static void main(String[] args) {
        System.out.println(countAndSay2(4));
    }


    public static String countAndSay2(int n) {
      return   countAndSay2("1", 1, 5);
    }

    public static String countAndSay2(String startStr, int current, int n) {
        if (current == n) {
            return startStr;
        }
        StringBuffer sb = new StringBuffer();
        int count = 1;
        char currentChar = startStr.charAt(0);
        for (int i = 1; i < startStr.length(); i++) {
            if (currentChar != startStr.charAt(i)) {
                sb.append(count).append(currentChar);
                count = 1;
                currentChar=startStr.charAt(i);
            } else {
                count++;
            }
        }

        return countAndSay2(sb.append(count).append(currentChar).toString(), ++current, n);
    }

    public static String countAndSay(int n) {
        String result = "1";

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n - 1; i++) {
            char[] chars = result.toCharArray();
            int count = 0;
            Character character = null;

            for (int j = 0; j < chars.length; j++) {
                if (count == 0) {
                    character = chars[j];
                    count++;
                    if (j == chars.length - 1) {
                        sb.append(count).append(character);
                        result = sb.toString();
                        sb = new StringBuffer();
                        break;
                    }
                } else if (chars[j] == character) {
                    character = chars[j];
                    count++;
                    if (j == chars.length - 1) {
                        sb.append(count).append(character);
                        result = sb.toString();
                        sb = new StringBuffer();
                        break;
                    }
                } else {
                    sb.append(count).append(character);
                    character = chars[j];
                    count = 1;
                    if (j == chars.length - 1) {
                        sb.append(count).append(character);
                        result = sb.toString();
                        sb = new StringBuffer();
                        break;
                    }
                }
            }
        }
        return result;
    }

}
