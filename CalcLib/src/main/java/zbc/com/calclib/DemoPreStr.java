package zbc.com.calclib;


public class DemoPreStr {

    public static void main(String[] args) {
        String[] arr = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(arr));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int minLength = strs[0].length();
        int index = 0;
        for (int i = 1; i < strs.length; i++) {
            if (minLength > strs[i].length()) {
                minLength = strs[i].length();
                index = i;
            }
        }

        for (int i = 0; i < strs[index].length(); i++) {
            char Char = strs[index].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (Char != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(Char);
        }
        return sb.toString();
    }

/*

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int minLength = strs[0].length();
        int index = 0;
        for (int i = 1; i < strs.length; i++) {
            if (minLength > strs[i].length()) {
                minLength = strs[i].length();
                index = i;
            }
        }

        for (int i = 0; i < strs[index].length(); i++) {
            char Char = strs[index].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (Char != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(Char);
        }
        return sb.toString();
    }

*/

}
