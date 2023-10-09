package com.zhy.utils.string;

/**
 * <p> String 增强工具类 </p>
 *
 * @author zhouhongyin
 * @since 2023/10/9 9:58
 */
public class StringEnhancerUtils extends StringUtils {

    /**
     * <p>下划线转驼峰</p>
     *
     * <pre>
     * user_name  ---->  userName
     * userName   --->  userName
     * </pre>
     *
     * @param underlineName 带有下划线的名字
     * @return 驼峰字符串
     */
    public static String underlineToHump(String underlineName) {
        char[] charArray = underlineName.toCharArray();
        boolean underlineBefore = false;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] == 95) {
                underlineBefore = true;
            } else if (underlineBefore) {
                buffer.append(charArray[i] -= 32);
                underlineBefore = false;
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }

    /**
     * <p>驼峰转下划线</p>
     *
     * <pre>
     * userName  ---->  user_name
     * user_name  ---->  user_name
     * </pre>
     *
     * @param humpName 驼峰命名
     * @return 带下滑线的String
     */
    public static String humpToUnderline(String humpName) {
        char[] charArray = humpName.toCharArray();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_").append(charArray[i] += 32);
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println("------------------------");
        System.out.println(StringEnhancerUtils.underlineToHump("user_name"));
        System.out.println(StringEnhancerUtils.humpToUnderline("userName"));
    }
}
