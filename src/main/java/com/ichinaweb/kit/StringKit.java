package com.ichinaweb.kit;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringKit {

	public static String doVerify(String id_no) {
		char pszSrc[] = id_no.toCharArray();
		int iS = 0;
		int iW[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		char szVerCode[] = new char[] { '1', '0', '0', '9', '8', '7', '6', '5',
				'4', '3', '2' };
		int i;
		for (i = 0; i < id_no.length(); i++) {
			iS += (int) (pszSrc[i] - '0') * iW[i];
		}
		int iY = iS % 11;
		return szVerCode[iY] + id_no;
	}
	/**
	 * Object转成String
	 * @param obj
	 * @return
	 */
	public static String obj2String(Object obj){
		if(obj==null){
			return "";
		}else{
			return obj.toString();
		}
	}
	/**
	 * 校验字符传中对应的表达式值是否存在
	 * 
	 * @param pattern
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static boolean checkStrings(String pattern, String str)
			throws Exception {
		List<String> result = new ArrayList<String>();
		boolean bln = false;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		int i = 0;
		while (m.find()) {
			result.add(m.group(1));
			i++;
		}
		if (i > 0)
			bln = true;
		return bln;
	}

	/**
	 * 产生一个4位随机数字
	 * 
	 * @return
	 */
	public static String getRandFour() {
		return ((Math.random() * 10000 + 1000) + "").substring(0, 4);
	}
	
	/**
	 * 产生一个6位随机数字
	 * 
	 * @return
	 */
	public static String getRandSix() {
		return ((Math.random() * 1000000 + 100000) + "").substring(0, 6);
	}
	
	/**
	 * 产生一个固定的整数
	 * @param b
	 * @return
	 */
	public static String getNextInt(int b){
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		sb.append((rand.nextInt(9)+1));
		for(int i=1;i<b;i++){
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
	
	/**
	 * 产生一个很长的数字串
	 * 
	 * @return
	 */
	public static String getRandSome() {
		return DateKit.fmtDateSSS(new Date()) + getRandFour();
	}

	/**
	 * 得到一个随即的字母或数字
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getRandString() {
		String retStr = "";
		String strTable = "3456789ABCDEFGHJKMNPQRSTUVWXY";
		int len = strTable.length();
		double dblR = Math.random() * len;
		int intR = (int) Math.floor(dblR);
		char c = strTable.charAt(intR);
		retStr = c + "";
		return retStr;
	}

	/**
	 * 首字母变小写
	 */
	public static String firstCharToLowerCase(String str) {
		Character firstChar = str.charAt(0);
		String tail = str.substring(1);
		str = Character.toLowerCase(firstChar) + tail;
		return str;
	}

	/**
	 * 首字母变大写
	 */
	public static String firstCharToUpperCase(String str) {
		Character firstChar = str.charAt(0);
		String tail = str.substring(1);
		str = Character.toUpperCase(firstChar) + tail;
		return str;
	}

	/**
	 * 字符串为 null 或者为 "" 时返回 true
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim())
				|| "null".equals(str.trim()) ? true : false;
	}
	
	
	/**
	 * 如果有一个为null 或"" 则返回true
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isBlank(String... strings) {
		if (strings == null)
			return true;
		for (String str : strings)
			if (isBlank(str))
				return true;
		return false;
	}

	/**
	 * 字符串不为 null 而且不为 "" 时返回 true
	 */
	public static boolean notBlank(String str) {
		return str == null || "".equals(str.trim()) ? false : true;
	}

	/**
	 * 如果有一个为null 或"" 则返回false
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean notBlank(String... strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}

	/**
	 * 如果等于null或"" 则返回"" 否则直接返回结果
	 * 
	 * @param str
	 * @return
	 */
	public static String nullShow(String str) {
		if (isBlank(str)) {
			return "";
		}
		return str;
	}

	/**
	 * 如果有一个为null 则返回false
	 * 
	 * @param paras
	 * @return
	 */
	public static boolean notNull(Object... paras) {
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}

	/**
	 * 将字符串转换成HTML格式的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String toHtml(String str) {
		String html = str;
		if (str == null || str.length() == 0) {
			return "";
		} else {
			html = html.replaceAll("&", "&amp;");
			html = html.replaceAll("<", "&lt;");
			html = html.replaceAll(">", "&gt;");
			html = html.replaceAll("\r\n", "\n");
			html = html.replaceAll("\n", "<br>\n");
			html = html.replaceAll("\"", "&quot;");
			html = html.replaceAll(" ", "&nbsp;");
			return html;
		}
	}

	/**
	 * 将HTML格式的字符串转换成常规显示的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String toText(String str) {
		String text = str;
		if (str == null || str.length() == 0) {
			return "";
		} else {
			text = text.replaceAll("&amp;", "&");
			text = text.replaceAll("&lt;", "<");
			text = text.replaceAll("&gt;", ">");
			text = text.replaceAll("<br>\n", "\n");
			text = text.replaceAll("<br>", "\n");
			text = text.replaceAll("&quot;", "\"");
			text = text.replaceAll("&nbsp;", " ");
			return text;
		}
	}

	/**
	 * md5加密
	 * 
	 * @param in
	 * @return
	 */
	public static String myMD5(String in) {
		try {
			String MerInfoString = in;
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update(MerInfoString.getBytes("GBK"));
			byte[] digesta = alg.digest();
			String hs = "";
			String stmp = "";
			int n;
			if (digesta != null) {
				for (n = 0; n < digesta.length; n++) {
					stmp = (java.lang.Integer.toHexString(digesta[n] & 0XFF));
					if (stmp.length() == 1)
						hs = hs + "0" + stmp;
					else
						hs = hs + stmp;
				}
			}
			return hs;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * md5加密
	 * 
	 * @param in
	 * @return
	 */
	public static String myMD5UTF8(String in) {
		try {
			String MerInfoString = in;
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update(MerInfoString.getBytes("UTF-8"));
			byte[] digesta = alg.digest();
			String hs = "";
			String stmp = "";
			int n;
			if (digesta != null) {
				for (n = 0; n < digesta.length; n++) {
					stmp = (java.lang.Integer.toHexString(digesta[n] & 0XFF));
					if (stmp.length() == 1)
						hs = hs + "0" + stmp;
					else
						hs = hs + stmp;
				}
			}
			return hs;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 判断输入的密码是否是纯数字
	 * 
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static boolean verifyNum(String num) {
		String s = num;
		return s.matches("\\d*");
	}

	/**
	 * 判断输入的密码是否是纯数字
	 * 
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static boolean verifyOrder(String num) {
		String s = num;
		return s.matches("\\d{1,3}");
	}
	
	/**
	 * 验证手机号
	 * 
	 * @param mail
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		String check = "^1[3,4,5,7,8]\\d{9}$";
		return regexMatcher(mobile, check);
	}

	/**
	 * 校验制券码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean regexTicket(String str) {
		String check = "^(\\d{4,8})|(\\d{4,8}[*][1-9][0-9]*)$";
		return regexMatcher(str, check);
	}

	/**
	 * 校验制券码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean regexTicketAcct(String str) {
		String check = "^(\\d{4,8})|(\\d{4,8}[*][1-9][0-9]*)|(\\d{4,8}[*][1-9][0-9]*\\.[0-9][0-9]{0,1})$";
		return regexMatcher(str, check);
	}

	/**
	 * 校验制券金额
	 * 
	 * @param str
	 * @return
	 */
	public static boolean regexMoney(String str) {
		String check="^([*][1-9]\\d*)|([*]\\d*\\.\\d{0,2})$";
		return regexMatcher(str, check);
	}
	/**
	 * 校验制券金额
	 * 
	 * @param str
	 * @return
	 */
	public static boolean regexCancelMoney(String str) {
		String check="^([-][0-9]\\d*)|([-]\\d*\\.\\d{0,2})$";
		return regexMatcher(str, check);
	}
	/**
	 * 校验制券金额
	 * 
	 * @param str
	 * @return
	 */
	public static boolean regexRoom(String str) {
		String check="^([/]\\d{4})$";
		return regexMatcher(str, check);
	}

	public static boolean regexMatcher(String str, String check) {
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(str);
		return matcher.matches();
	}

	/**
	 * 隐藏手机号中间4位
	 * 
	 * @param month
	 * @return
	 */
	public static String hideMob(String mobile) {
		return mobile.substring(0, 3) + "****" + mobile.substring(7);
	}

	/**
	 * 显示手机号中间4位
	 * 
	 * @param month
	 * @return
	 */
	public static String showMob(String mobile) {
		return "***" + mobile.substring(3, 7)
				+ "************".substring(7, mobile.length());
	}

	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 进行加法运算
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 进行减法运算
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sub(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 进行乘法运算
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double mul(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 进行除法运算
	 * 
	 * @param d1
	 * @param d2
	 * @param len
	 * @return
	 */
	public static double div(String d1, String d2, int len) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 进行比较大小
	 * 
	 * @param d1
	 * @param d2
	 * @param len
	 * @return ，-1表示小于，0是等于，1是大于
	 */
	public static int compare(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		int a = b1.compareTo(b2);
		return a;
	}

	/**
	 * 进行四舍五入操作
	 * 
	 * @param d
	 * @param len
	 * @return
	 */
	public static double round(String d, int len) {
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 字节数组转16进制
	 * 
	 * @param digesta
	 *            字节数组
	 * @param len
	 *            转换长度
	 * @param append
	 *            没字节后追加内容
	 * @return String
	 */
	static public String ToHex(byte[] digesta, int len, String append) {
		if (digesta == null)
			return "";

		int show_len = len;
		if (show_len > digesta.length)
			show_len = digesta.length;

		String stmp = null;
		StringBuffer strBuf = new StringBuffer();
		for (int n = 0; n < show_len; n++) {
			stmp = (java.lang.Integer.toHexString(digesta[n] & 0XFF));

			if (stmp.length() == 1)
				strBuf.append("0");
			strBuf.append(stmp);

			if (append != null)
				strBuf.append(append);
		}

		String t = strBuf.toString();
		strBuf = null;
		return t;
	}

	/**
	 * 字符串转16进制
	 * 
	 * @param src
	 *            字符串
	 * @param charset
	 *            字符编码
	 * @return String
	 */
	static public String toHex(String src, String charset) {
		if (src == null || src.equals(""))
			return "";
		byte[] arr = null;
		try {
			arr = src.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (arr != null)
			return ToHex(arr, arr.length, null);
		else
			return "";
	}

	/**
	 * 字符串转16进制，缺省GBK编码
	 * 
	 * @param src
	 *            字符串
	 * @return String
	 */
	static public String toHex(String src) {
		return toHex(src, "GBK");
	}

	/**
	 * 字符串转16进制
	 * 
	 * @param s
	 * @return
	 */
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/**
	 * 截取字符串--- 从左往右
	 * 
	 * @param s
	 *            字符串
	 * @param length
	 *            长度
	 * @return
	 * @throws Exception
	 */
	public static String bSubstring(String s, int length) throws Exception {
		byte[] bytes = s.getBytes("Unicode");
		String temp = "";
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始
		for (; i < bytes.length && n < length; i++) {
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1) {
				n++; // 在UCS2第二个字节时n加1
			} else {// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0) {
					n++;
				}
			}
		}// 如果i为奇数时，处理成偶数
		if (i % 2 == 1) {
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0) {
				i = i - 1;
				// 补一个空格
				temp = " ";
			} else
				// 该UCS2字符是字母或数字，则保留该字符
				i = i + 1;
		}
		return new String(bytes, 0, i, "Unicode") + temp;
	}

	/**
	 * 截取字符串 从右往左
	 * 
	 * @param s
	 *            字符串
	 * @param length
	 *            长度
	 * @return
	 * @throws Exception
	 */
	public static String rSubstring(String s, int length) throws Exception {
		byte[] bytes = s.getBytes("Unicode");
		StringBuffer sb = new StringBuffer();
		for (int d = 0; d < bytes.length; d++) {
			sb.append(bytes[d] + " ");
		}
		String temp = "";
		int n = 0; // 表示当前的字节数
		int i = bytes.length; // 要截取的字节数，从第3个字节开始

		while (i > 0 && n < length) {
			i = i - 1;
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1) {
				n++; // 在UCS2第二个字节时n加1
			} else {// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0) {
					n++;
				}
			}

		}// 如果i为奇数时，处理成偶数

		if (i % 2 == 1) {
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0) {
				i = i + 1;
				// 补一个空格
				temp = " ";
			} else
				// 该UCS2字符是字母或数字，则保留该字符
				i = i - 1;

		}

		return temp + new String(bytes, i, bytes.length - i, "Unicode");
	}

	/**
	 * 截取打印信息
	 * 
	 * @param print
	 * @return
	 * @throws Exception
	 */
	public static String[] spiltPrint(String print) throws Exception {
		String[] obj = new String[3];
		int a = print.getBytes("Unicode").length;
		String msg = "";
		String bool = "0";// 没有多余的
		System.out.println(a);
		if (a > 3000) {
			msg = StringKit.bSubstring(print, 3000);
			int d = msg.lastIndexOf("\n") + 1; // 保持不切一行
			msg = print.substring(0, d);
			print = print.substring(d);
			bool = "1";
		} else {
			msg = print;
			print = "";
		}
		obj[0] = msg;
		obj[1] = print;
		obj[2] = bool;
		return obj;
	}

	/**
	 * 将元为单位的转换为分 （乘100）
	 * 
	 * @param amount
	 * @return
	 */
	public static String changeY2F(String currency) {
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "") + "00");
		}
		return amLong.toString();
	}

	/**
	 * 将分为单位的转换为元 （除100）
	 * 
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String changeF2Y(Long amount) {
		return foramtNum(BigDecimal.valueOf(amount).divide(new BigDecimal(100))
				.toString());
	}

	public static String foramtNum(String num) {
		if (isBlank(num)) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("####0.00");
		return df.format(Double.parseDouble(num));
	}
	/**
	 * 格式化整数
	 * @param num
	 * @return
	 */
	public static String foramtNumZero(String num) {
		if (isBlank(num)) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("####0");
		return df.format(Double.parseDouble(num));
	}
	public static String GetValue(String body, String key) {
		String tt = body;
		if (tt == null)
			tt = "";

		String iOther = "";
		String head = "<" + key + ">";
		String tail = "</" + key + ">";

		int iLocate_other_beg = 0;
		int iLocate_other_end = 0;
		iLocate_other_beg = tt.indexOf(head);
		iLocate_other_beg += head.length();
		iLocate_other_end = tt.indexOf(tail);
		if (iLocate_other_end > 0)
			iOther = tt.substring(iLocate_other_beg, iLocate_other_end);
		return iOther;
	}

	/**
	 * 截取打印信息
	 * 
	 * @param print
	 * @return
	 * @throws Exception
	 */
	public static String[] spiltGoods(String print) throws Exception {
		String[] obj = new String[3];
		int a = print.getBytes("Unicode").length;
		String msg = "";
		String bool = "0";// 没有多余的
		System.out.println(a);
		if (a > 16) {
			msg = StringKit.bSubstring(print, 16);
			print = print.substring(msg.trim().length());
			bool = "1";
		} else {
			msg = print;
			print = "";
		}
		obj[0] = msg;
		obj[1] = print;
		obj[2] = bool;
		return obj;
	}

	public static String _8id(String id) {
		if (id.length() < 8) {
			int length0 = 8 - id.length();
			for (int i = 0; i < length0; i++) {
				id = "0" + id;
			}
		}
		return id;
	}

	/**
	 * 生成8为随机串
	 * 
	 * @param keyword
	 * @return
	 */
	public static String ShortText(String keyword) {
		final String[] CHARS_DIC = new String[] { "a", "b", "c", "d", "e", "f",
				"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
				"4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
				"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };

		// final String keyword = "http://sports.sina.com.cn/nba";
		byte[] encryptedTextBytes = null;

		try {
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			md5Digest.reset();
			md5Digest.update(keyword.getBytes("UTF-8"));
			encryptedTextBytes = md5Digest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			String hex1 = Integer.toHexString(0xff & encryptedTextBytes[i * 2]);
			String hex2 = Integer
					.toHexString(0xff & encryptedTextBytes[i * 2 + 1]);

			hex1 = hex1.length() == 1 ? "0" + hex1 : hex1;
			hex2 = hex2.length() == 1 ? "0" + hex2 : hex2;
			int index = (int) Long.parseLong(hex1 + hex2, 16)
					% CHARS_DIC.length;
			result.append(CHARS_DIC[index]);
		}

		return result.toString();
	}
	
	/**
	 * 获取支付类型
	 * @param type
	 * @return
	 */
	public static String getPayType(String type){
		String msg ="未知";
		if("1".equals(type)){
			msg="房款支付";
		}else if("2".equals(type)){
			msg="余额支付";
		}else if("3".equals(type)){
			msg="微信支付";
		}else if("4".equals(type)){
			msg="现款支付";
		}else if("5".equals(type)){
			msg="AA赠送";
		}else if("6".equals(type)){
			msg="刷卡支付";
		}else if("7".equals(type)){
			msg="余额支付+微信支付";
		}
		return msg;
	}
	/**
	 * 消费单开卡类型
	 * @param type
	 * @return
	 */
	public static String getConType(int openType){
		String conType=openType+"未知";
		if(openType==0){
			conType="房间低消";
		}else if(openType==1){
			conType="免低消";
		}else if(openType==2){
			conType="AA派对";
		}else if(openType==3){
			conType="存酒点单";
		}else if(openType==4){
			conType="套餐免通";
		}else if(openType==-1){
			conType="通用";
		}
		return conType;
	}
	/**
	 * map 转成list
	 * @param map
	 * @return
	 */
	public static List mapToList(Map map) {
		List list = new ArrayList();
		Iterator iter = map.entrySet().iterator(); // 获得map的Iterator
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			list.add(entry.getValue());
		}
		return list;
	}
	
	/**
	 * 类似Oracle nvl()函数, 如果value值不为空, 则直接返回. 否则返回defaultStr
	 * @param value
	 * @param defaultStr
	 * @return String
	 */
	public static String nvl(String value, String defaultStr) {
		return value = notBlank(value) ? value : defaultStr;
	}
	
	/**
	 * 将map转换成请求参数
	 * @param param
	 * @return
	 */
	public static String mapToString(Map<String, String> param) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			if (StringKit.notBlank(entry.getKey()) && entry.getValue() != null) {
				sb.append("&" + entry.getKey() + "=" + entry.getValue());
			}
		}
		return sb.length() > 0 ? sb.substring(1) : "";
	}

	public static String sortedMapToString(SortedMap<String, String> param) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			if (StringKit.notBlank(entry.getKey()) && entry.getValue() != null) {
				sb.append(entry.getKey() + entry.getValue());
			}
		}
		return sb.toString();
	}
}
