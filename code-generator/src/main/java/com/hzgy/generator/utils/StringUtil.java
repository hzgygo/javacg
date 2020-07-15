package com.hzgy.generator.utils;

//import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 编码
     **/
    private static final String ENCODING = "UTF-8";

    /**
     * 编码
     **/
    private static final String ISO_ENCODING = "ISO8859-1";

    /**
     * 首字母小写
     *
     * @param s 转换字符串
     * @return 转换后字符串
     */
    public static String toLowerCaseFirstOne(String s) {
        if (s == null) {
            return null;
        }
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 首字母转大写
     *
     * @param s 转换字符串
     * @return 转换后字符串
     */
    public static String toUpperCaseFirstOne(String s) {
        if (s == null) {
            return null;
        }
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    /**
     * 验证手机号
     *
     * @param phoneNum 手机号
     * @return 是否匹配
     */
    public static boolean isMobilePhoneNO(String phoneNum) {
        if (phoneNum == null) {
            return false;
        }
        String exp = "^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$";
        Pattern p = Pattern.compile(exp);
        Matcher m = p.matcher(phoneNum);
        return m.matches();

    }

    /**
     * 浮点和整数验证
     *
     * @param numStr 验证字符串
     * @return 是否匹配
     */
    public static boolean isFooatOrInt(String numStr) {
        if (numStr == null) {
            return false;
        }
        String exp = "[0-9]{1,14}(\\.{0,1}[0-9]{1,2})?";
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(numStr);
        return matcher.matches();
    }

    /**
     * 验证邮箱
     *
     * @param email 验证邮箱地址
     * @return 是否匹配
     */
    public static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        String exp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 验证密码组成
     *
     * @param pwd 密码
     * @return 是否匹配
     */
    public static boolean validPwd(String pwd) {
        if (pwd == null) {
            return false;
        }
        String exp = "^[a-zA-Z0-9`~!@#$%^&*()+=|{}':;,\\[\\].<>/?]{6,20}$";
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(pwd);
        return matcher.matches();
    }

    /**
     * 这是典型的随机洗牌算法。
     * 流程是从备选数组中选择一个放入目标数组中，
     * 将选取的数组从备选数组移除（放至最后，并缩小选择区域） 算法时间复杂度O(n)
     *
     * @return 随机8为不重复数组
     */
    public static String generateRandom(int len) {
        StringBuilder randomCode = new StringBuilder();
        // 初始化备选数组
        int[] defaultNums = new int[10];
        for (int i = 0; i < defaultNums.length; i++) {
            defaultNums[i] = i;
        }
        Random random = new Random();
        int[] nums = new int[len];
        // 默认数组中可以选择的部分长度
        int canBeUsed = 10;
        // 填充目标数组
        for (int i = 0; i < nums.length; i++) {
            // 将随机选取的数字存入目标数组
            int index = random.nextInt(canBeUsed);
            nums[i] = defaultNums[index];
            // 将已用过的数字扔到备选数组最后，并减小可选区域
            swap(index, canBeUsed - 1, defaultNums);
            canBeUsed--;
        }
        if (nums.length > 0) {
            for (int num : nums) {
                randomCode.append(num);
            }
        }
        return randomCode.toString();
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 字符串编码转换 iso->utf-8
     *
     * @param data 转换字符串
     * @return 转换后字符串
     */
    public static String encoding(String data) {
        try {
            if (data == null) {
                return null;
            }
            if (data.equals(new String(data.getBytes(ISO_ENCODING), ISO_ENCODING))) {
                return data = new String(data.getBytes(ISO_ENCODING), ENCODING);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 生成业务流水号 :业务代码_yyyyMMddHHmmssSSS
     *
     * @param prefix 前缀业务代码
     * @return 返回流水号值
     */
    public static String genertFlowNumber(String prefix) {
        if (prefix == null) {
            prefix = "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuilder buffer = new StringBuilder(prefix);
        String dateStr = formatter.format(new Date());
        buffer.append("_").append(dateStr);
        return buffer.toString();
    }

    /**
     * 字符串特殊字符处理
     *
     * @param escapeStr 转换字符串
     * @return 处理后的特殊字符
     */
    public static String escapeSpecialWord(String escapeStr) {
        if (escapeStr == null) {
            return null;
        }
        if (!StringUtils.isEmpty(escapeStr)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (escapeStr.contains(key)) {
                    escapeStr = escapeStr.replace(key, "\\" + key);
                }
            }
        }
        return escapeStr;
    }

    /**
     * 字符串去空格，换行，回车
     *
     * @param replaceStr 处理字符串
     * @return 返回处理后的结果
     */
    public static String replaceBlank(String replaceStr) {
        String dest = null;
        if (replaceStr != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(replaceStr);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 获取随机数字，大小写字母组成的字符串
     *
     * @param length         长度
     * @param isUseNum       是否使用数字
     * @param isUseLowLetter 是否使用消息字母
     * @param isUseUppLetter 是否使用大写字母
     * @return 返回目标随机字符串
     */
    public static String getRandom(int length, boolean isUseNum, boolean isUseLowLetter, boolean isUseUppLetter) {
        Random random = new Random();
        String str = "";
        StringBuilder s = new StringBuilder();
        if (isUseNum) {
            str += "0123456789";
        }
        if (isUseLowLetter) {
            str += "abcdefghijklmnopqrstuvwxyz";
        }
        if (isUseUppLetter) {
            str += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        byte[] bytes = str.getBytes();

        for (int i = 0; i < length; i++) {
            int pos = random.nextInt(str.length() - 1);
            s.append(str.substring(pos, pos + 1));
        }
        return s.toString();
    }

    /**
     * 去掉联字符（-）的UUID
     *
     * @return 返回UUID
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 判断字符串是否为空
     *
     * @param targetStr 目标字符串
     * @return 返回是否值
     */
    public static boolean isEmpty(String targetStr) {
        return ((targetStr == null) || ("".equals(targetStr.trim())));
    }

    /**
     * 判断字符串是否不为空
     *
     * @param targetStr 目标字符串
     * @return 返回是否值
     */
    public static boolean isNotEmpty(String targetStr) {
        return (!(isEmpty(targetStr)));
    }

    /**
     *
     * 字符串中存在星号（表示多个字符）匹配
     * @param pattern  包含星号的字符串
     * @param str 要匹配的字符串
     * @return
     */
    public static boolean wildcardStarMatch(String pattern, String str) {
        int strLength = str.length();
        int strIndex = 0;
        char ch;
        for (int patternIndex = 0, patternLength = pattern.length(); patternIndex < patternLength; patternIndex++) {
            ch = pattern.charAt(patternIndex);
            if (ch == '*') {
                // 通配符星号*表示可以匹配任意多个字符
                while (strIndex < strLength) {
                    if (wildcardStarMatch(pattern.substring(patternIndex + 1), str.substring(strIndex))) {
                        return true;
                    }
                    strIndex++;
                }
            }
            else {
                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
                    return false;
                }
                strIndex++;
            }
        }
        return (strIndex == strLength);
    }

//    public String hexToBase64(String hexString) {
//        if (StringUtils.isEmpty(hexString)) {
//            return null;
//        }
//        byte[] arr = Hex.decode(hexString);
//        return Base64Utils.encodeToString(arr);
//    }
//
//    public String base64ToHex(String base64String) {
//        if (StringUtils.isEmpty(base64String)) {
//            return null;
//        }
//        byte[] arr = Base64Utils.decodeFromString(base64String	);
//        return new String(Hex.encode(arr));
//    }

    // public static void main(String[] args) {
    // // System.out.println(generateRandom(6));
    // // System.out.println(String.format("%05d", 2));
    // // System.out.println(pwd("1234567*~@？890") + "--1234567890");
    // // System.out.println(pwd("qwertyuiop") + "--qwertyuiop");
    // // System.out.println(pwd("1234q56") + "--1234q56");
    // // System.out.println(pwd("123qw456") + "--123qw456");
    // // System.out.println(pwd("12qw3!@456") + "--12qw3!@456");
    // // System.out.println(pwd("1&*23456") + "--1&*23456");
    // // System.out.println(pwd("!@#$%^&*()") + "--!@#$%^&*()");
    // System.out.println(genertFlowNumber("cle"));
    //
    // }
}
