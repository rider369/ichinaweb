package com.ichinaweb.kit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 所有的时间操作方法
 *
 */
public class DateKit {
	/**
	 * 格式化时间
	 * 
	 * @param stime
	 * @return
	 * @throws ParseException
	 */
	public static String fmtDateSSS(Date stime) {
		if (stime == null) {
			return "";
		}
		return simpleDate(stime,"yyyyMMddHHmmssSSS");

	}
	/**
	 * 格式化时间
	 * 
	 * @param stime
	 * @return
	 * @throws ParseException
	 */
	public static String fmtDateQ(Date stime) {
		if (stime == null) {
			return "";
		}
		return simpleDate(stime,"yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * 格式化时间
	 * 
	 * @param stime
	 * @return
	 * @throws ParseException
	 */
	public static String fmtDate(Date stime) {
		if (stime == null) {
			return "";
		}
		return simpleDate(stime,"yyyy-MM-dd");

	}
	
	/**
	 * 格式化时间-输入格式的
	 * @param stime
	 * @param format
	 * @return
	 */
	public static String simpleDate(Date stime,String format){
		SimpleDateFormat myFmt = new SimpleDateFormat(format);
		String temp;
		try {
			temp = myFmt.format(stime);
			return temp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	
	/**
	 * 格式化时间 后时分秒都为0
	 * 
	 * @param stime
	 * @return
	 * @throws ParseException
	 */
	public static Date getStartDate(Date stime) {
		String temp = fmtDate(stime) + " 00:00:00";
		return stringToDateQ(temp);
	}

	/**
	 * 格式化时间 后时分秒为：23：59：59
	 * 
	 * @param stime
	 * @return
	 */
	public static Date getEndDate(Date stime) {
		String temp = fmtDate(stime) + " 23:59:59";
		return stringToDateQ(temp);
	}
	
	
	/**
	 * 字符串转换成时间格式-不带
	 * 
	 * @param time
	 * @return
	 */
	public static Date stringToDate(String time) {
		return stringToDate(time,"yyyy-MM-dd");
	}
	/**
	 * 字符串转换成时间格式-带时分秒
	 * 
	 * @param time
	 * @return
	 */
	public static Date stringToDateQ(String time) {
		return stringToDate(time,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 将字符串转换成日期-格式
	 * @param time
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String time,String format){
		if (time == null || "".equals(time.trim())) {
			return null;
		}
		SimpleDateFormat myFmt = new SimpleDateFormat(format);
		Date stime = null;
		try {
			stime = (Date) myFmt.parseObject(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return stime;
	}
	
	/**
	 * 根据当前时间推后的几天 正为退后 负为前进
	 * 
	 * @param num
	 * @return
	 */
	public static Date getPostDay(int num) {
		return getPostDay(new Date(),num);
	}
	/**
	 * 根据输入时间退后几天
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date getPostDay(Date date,int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num); // 加几天或减几天
		return calendar.getTime();
	}
	
	/**
	 * 得到当前时间+推后的几小时
	 * 
	 * @param num
	 * @return
	 */
	public static Date getPostHour(int num) {
		return getPostHour(new Date(),num);
	}
	
	/**
	 * 根据输入时间+推后的几小时
	 * 
	 * @param num
	 * @return
	 */
	public static Date getPostHour(Date date,int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, num); // 加或减几小时
		return calendar.getTime();
	}
	
	/**
	 * 得到当前时间+推后的几分钟
	 * 
	 * @param num
	 * @return
	 */
	public static Date getPostMinute(int num) {
		return getPostMinute(new Date(),num);
	}

	/**
	 * 得到当前时间+推后的几分钟
	 * 
	 * @param num
	 * @return
	 */
	public static Date getPostMinute(Date date,int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, num); //加或减几分钟
		return calendar.getTime();
	}
	
	/**
	 * 比较2个时间的大小
	 * 
	 * @param stime
	 * @param etime
	 * @return
	 */
	public static boolean checkDate(Date stime, Date etime) {
		Calendar ca1 = Calendar.getInstance();
		Calendar ca2 = Calendar.getInstance();
		ca1.setTime(stime);
		ca2.setTime(etime);
		if (ca1.getTimeInMillis() > ca2.getTimeInMillis()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 时间差多少
	 * @param stime
	 * @param etime
	 * @param a  1：秒 ，2： 分钟，3：小时，4:天
	 * @return
	 */
	public static long differDate(Date stime, Date etime,int a) {
		Calendar ca1 = Calendar.getInstance();
		Calendar ca2 = Calendar.getInstance();
		ca1.setTime(stime);
		ca2.setTime(etime);
		long distanceMin = ca1.getTimeInMillis() - ca2.getTimeInMillis();
		if(a==1){
			return distanceMin/(1000);
		}else if(a==2){
			return distanceMin/(1000*60);
		}else if(a==3){
			return distanceMin/(1000*60*60);
		}else if(a==4){
			return distanceMin/(1000*60*60*24);
		}else{
			return distanceMin;
		}
	}
	
	/**
	  * 得到当前时间-小时
	  * @return
	  */
	 public static int getDateHour(){
		 return  getDateHour(new Date());
		 
		 
	 }
	 /**
	  * 根据时间得到小时
	  * @param stime
	  * @return
	  */
	 public static int getDateHour(Date stime){
		 
		 return Integer.parseInt(simpleDate(stime,"HH"));
	 }
	 /**
	  * 得到当前时间的Long
	  * @return
	  */
	 public static String getNowLong(){
		 return (new Date()).getTime()+"";
	 }
	 
	 /*
	 * 产生开始结束日期字符串
	 * @param date_rule   日志
	 * @param start_date  开始时间
	  * @return  返回日期字串,格式yyyy-mm-dd 
	  */
	//
	public static String genDateStrFromRule(String date_rule,int index,Date start_date){
		if ( date_rule == null || date_rule.trim().equals("")) return null;
		String ret_date = null;
		int pos = index;
		if ( start_date == null) start_date = new Date(); //计算开始时间，dhpdhp
		ret_date = fmtDate(DateKit.getScanDate(date_rule, start_date,pos+1));
		return ret_date;
	}
	 
	/**
	 * 判断时间
	 * @param msg               	小时	天	周	月	年	固定时间
			周期	hours#10:11#11:11	cycday#1	cycweek#1	cycmonth#1	cycyear#1	fixed#2013-11-11#2014-12-14
			时间之前		befday#1	befweek#1	befmonth#1	befyear#1	
			时间之后（包含当天）		aftday#1	aftweek#1	aftmonth#1	aftyear#1	
						周一到周日为1-7例如： aftweek#1#2	1号到30号		
					0当天	0当周	0当月		

	 * @param date  目标时间
	 * @param sort  1 开始时间  2 结束时间
	 * @return  yyyy-mm-dd hh:MM:ss
	 */
	public static Date  getScanDate(String msg,Date date,int sort){
		Date resDate= new Date();//定返回值
		String resStr="";
		Date sDate=null;//返回的开始时间
		Date eDate=null;//返回的结束时间
		if(date==null) date = new Date();//如果传入的时间为空，默认为当前时间
		//获取当前时间，并进行格式化
		String strNow = fmtDate(new Date());
		String strOld = fmtDate(date);
		Date dateNow = stringToDate(strNow);
		Date dateOld = stringToDate(strOld);
		//获取时间实例
		Calendar nowCal=Calendar.getInstance();//当前时间实例
		Calendar oldCal=Calendar.getInstance();//指定时间实例
		nowCal.setTime(dateNow);//当前时间
		oldCal.setTime(dateOld);//指定时间
		msg = StringKit.nullShow(msg);
		boolean bool = true;
		//拆分规则数组
		String timeRule[] = msg.split("#");
		if(timeRule.length<=1){
			
			resStr=strNow;
			resStr= sort==1?(resStr+" 00:00:00"):(resStr+" 23:59:59");
			bool = false;
		}
		String rule = timeRule[0].toLowerCase();//将规则表示转换成小写
		if(bool){
			if("cychours".equals(rule)){      //周期小时
				resStr =sort==1?(strNow+" "+timeRule[1]+":00"):(strNow+" "+timeRule[2]+":59");
			}else if("hours".equals(rule)){  //小时
				resStr= sort==1?(strNow+" "+timeRule[1]+":00"):(strNow+" "+timeRule[1]+":59");
			}else if("cycfixed".equals(rule)){
				resStr= sort==1?(timeRule[1]+" 00:00:00"):(timeRule[2]+" 23:59:59");
			}else if("fixed".equals(rule)){
				resStr= sort==1?(timeRule[1]+" 00:00:00"):(timeRule[1]+" 23:59:59");
			}else if("cycday".equals(rule)){
				long timeNow=nowCal.getTimeInMillis();//求当前时间
				long timeOld=oldCal.getTimeInMillis();
				int a=(int) ((timeNow-timeOld)/(1000*60*60*24));//化为天
				int b = a%Integer.parseInt(timeRule[1]);//取余数

				nowCal.add(Calendar.DATE, -b);
				sDate = nowCal.getTime();//算出开始时间
				nowCal.add(Calendar.DATE, Integer.parseInt(timeRule[1])-1);
				eDate = nowCal.getTime();//算出结束时间
				resStr= sort==1?(fmtDate(sDate)+" 00:00:00"):(fmtDate(eDate)+" 23:59:59");
			}else if("cycweek".equals(rule)){
				int p1 = nowCal.get(Calendar.YEAR)*54+nowCal.get(Calendar.WEEK_OF_YEAR);
				int p2 = oldCal.get(Calendar.YEAR)*54+oldCal.get(Calendar.WEEK_OF_YEAR);
				int tnum=p1-p2;
				int tyu = tnum%Integer.parseInt(timeRule[1]);
				
				nowCal.add(Calendar.DATE, 0-tyu*7);
				sDate = nowCal.getTime();
				oldCal.setTime(sDate);
				nowCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				sDate = nowCal.getTime();
				oldCal.add(Calendar.DATE, (Integer.parseInt(timeRule[1])-1)*7);
				
				eDate = getSundayOfWeek(oldCal.getTime());
				resStr= sort==1?(fmtDate(sDate)+" 00:00:00"):(fmtDate(eDate)+" 23:59:59");
			}else if("cycmonth".equals(rule)){
				int p1 = nowCal.get(Calendar.MONTH)+nowCal.get(Calendar.YEAR)*12;
				int p2 = oldCal.get(Calendar.MONTH)+oldCal.get(Calendar.YEAR)*12;
				int tnum=p1-p2;
				int tyu = tnum%Integer.parseInt(timeRule[1]);
				nowCal.set(Calendar.DAY_OF_MONTH, 1);
				nowCal.add(Calendar.MONTH, 0-tyu);
				sDate = nowCal.getTime();
				oldCal.setTime(sDate);
				oldCal.add(Calendar.MONTH, Integer.parseInt(timeRule[1])-1);
				sDate = nowCal.getTime();
				eDate = getLastOfMonth(oldCal.getTime());
				resStr= sort==1?(fmtDate(sDate)+" 00:00:00"):(fmtDate(eDate)+" 23:59:59");
			}else if("cycyear".equals(rule)){
				int p1 = nowCal.get(Calendar.YEAR);//当前是哪一年
				int p2 = oldCal.get(Calendar.YEAR);//指定是哪一年
				int tnum = p1 - p2;//相差几年
				int tyu = tnum%Integer.parseInt(timeRule[1]);
				nowCal.add(Calendar.YEAR, 0-tyu);
				sDate = nowCal.getTime();
				oldCal.setTime(sDate);
				oldCal.add(Calendar.YEAR, Integer.parseInt(timeRule[1])-1);
				eDate = oldCal.getTime();
				resStr= sort==1?(simpleDate(sDate,"yyyy")+"-01-01 00:00:00"):(simpleDate(eDate,"yyyy")+"-12-31 23:59:59");
			}else if("befsecond".equals(rule)){
				Calendar hourCal=Calendar.getInstance();//指定时间实例
				hourCal.setTime(date);
				hourCal.add(Calendar.SECOND, 0-Integer.parseInt(timeRule[1]));
				eDate = hourCal.getTime();
				resStr= fmtDateQ(eDate);
			}else if("befminute".equals(rule)){
				Calendar hourCal=Calendar.getInstance();//指定时间实例
				hourCal.setTime(date);
				hourCal.add(Calendar.MINUTE, 0-Integer.parseInt(timeRule[1]));
				eDate = hourCal.getTime();
				resStr= fmtDateQ(eDate);
			}else if("befhour".equals(rule)){
				Calendar hourCal=Calendar.getInstance();//指定时间实例
				hourCal.setTime(date);
				hourCal.add(Calendar.HOUR_OF_DAY, 0-Integer.parseInt(timeRule[1]));
				eDate = hourCal.getTime();
				resStr= fmtDateQ(eDate);
			}else if("befday".equals(rule)){
				oldCal.add(Calendar.DATE, 0-Integer.parseInt(timeRule[1]));
				eDate = oldCal.getTime();
				resStr= sort==1?(fmtDate(eDate)+" 00:00:00"):(fmtDate(eDate)+" 23:59:59");
			}else if("befweek".equals(rule)){
				oldCal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);//获取周六，不能获取周日
				oldCal.add(Calendar.DATE, 0-Integer.parseInt(timeRule[1])*7);
				eDate = oldCal.getTime();
				oldCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				sDate = oldCal.getTime();
				
				//判断指定
				if(timeRule.length==3&&timeRule[2].matches("[1-7]")){
					nowCal.setTime(sDate);
					oldCal.setTime(eDate);
					if("1".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					}else if("2".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
					}else if("3".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
					}else if("4".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
					}else if("5".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
					}else if("6".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
					}else if("7".equals(timeRule[2])){
						nowCal.setTime(getSundayOfWeek(sDate));
						oldCal.setTime(getSundayOfWeek(eDate));
						
					}
					sDate = nowCal.getTime();
					eDate = oldCal.getTime();
					
				}else{
					eDate = getSundayOfWeek(eDate);
				}
				
				
				
				resStr= sort==1?(fmtDate(sDate)+" 00:00:00"):(fmtDate(eDate)+" 23:59:59");
			}else if("befmonth".equals(rule)){
				oldCal.set(Calendar.DAY_OF_MONTH, 1);
				oldCal.add(Calendar.MONTH, 0-Integer.parseInt(timeRule[1]));
				sDate = oldCal.getTime();
				eDate = getLastOfMonth(sDate);
				
				//判断指定
				if(timeRule.length==3&&timeRule[2].matches("([1-3][0])|[1-2]?[1-9]")){
					int temp = getLastDayOfMonth(sDate);
					if(temp>=Integer.parseInt(timeRule[2])){
						nowCal.setTime(sDate);
						oldCal.setTime(eDate);
						nowCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(timeRule[2]));
						oldCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(timeRule[2]));
						sDate = nowCal.getTime();
						eDate = oldCal.getTime();
					}
				}
				
				resStr= sort==1?(fmtDate(sDate)+" 00:00:00"):(fmtDate(eDate)+" 23:59:59");
			}else if("befyear".equals(rule)){
				oldCal.add(Calendar.YEAR, 0-Integer.parseInt(timeRule[1]));
				sDate = oldCal.getTime();
				resStr= sort==1?(simpleDate(sDate,"yyyy")+"-01-01 00:00:00"):(simpleDate(sDate,"yyyy")+"-12-31 23:59:59");
			}else if("aftsecond".equals(rule)){
				Calendar hourCal=Calendar.getInstance();//指定时间实例
				hourCal.setTime(date);
				hourCal.add(Calendar.SECOND, Integer.parseInt(timeRule[1]));
				eDate = hourCal.getTime();
				resStr= fmtDateQ(eDate);
			}else if("aftminute".equals(rule)){
				Calendar hourCal=Calendar.getInstance();//指定时间实例
				hourCal.setTime(date);
				hourCal.add(Calendar.MINUTE, Integer.parseInt(timeRule[1]));
				eDate = hourCal.getTime();
				resStr= fmtDateQ(eDate);
			}else if("afthour".equals(rule)){
				Calendar hourCal=Calendar.getInstance();//指定时间实例
				hourCal.setTime(date);
				hourCal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(timeRule[1]));
				eDate = hourCal.getTime();
				resStr= fmtDateQ(eDate);
			}else if("aftday".equals(rule)){
				oldCal.add(Calendar.DATE, Integer.parseInt(timeRule[1]));
				eDate = oldCal.getTime();
				resStr= sort==1?(fmtDate(eDate)+" 00:00:00"):(fmtDate(eDate)+" 23:59:59");
			}else if("aftweek".equals(rule)){
				oldCal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				oldCal.add(Calendar.DATE, Integer.parseInt(timeRule[1])*7);
				eDate = oldCal.getTime();
				oldCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				sDate = oldCal.getTime();
				
				//判断指定
				if(timeRule.length==3&&timeRule[2].matches("[1-7]")){
					nowCal.setTime(sDate);
					oldCal.setTime(eDate);
					if("1".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					}else if("2".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
					}else if("3".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
					}else if("4".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
					}else if("5".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
					}else if("6".equals(timeRule[2])){
						nowCal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
						oldCal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
					}else if("7".equals(timeRule[2])){
						nowCal.setTime(getSundayOfWeek(sDate));
						oldCal.setTime(getSundayOfWeek(eDate));
					}
					sDate = nowCal.getTime();
					eDate = oldCal.getTime();
					
				}else{
					eDate = getSundayOfWeek(eDate);
				}
				
				
				resStr= sort==1?(fmtDate(sDate)+" 00:00:00"):(fmtDate(eDate)+" 23:59:59");
			}else if("aftmonth".equals(rule)){
				oldCal.set(Calendar.DAY_OF_MONTH, 1);
				oldCal.add(Calendar.MONTH, Integer.parseInt(timeRule[1]));
				sDate = oldCal.getTime();
				eDate = getLastOfMonth(sDate);
				
				//判断指定
				if(timeRule.length==3&&timeRule[2].matches("([1-3][0])|[1-2]?[1-9]")){
					int temp = getLastDayOfMonth(sDate);
					if(temp>=Integer.parseInt(timeRule[2])){
						nowCal.setTime(sDate);
						oldCal.setTime(eDate);
						nowCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(timeRule[2]));
						oldCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(timeRule[2]));
						sDate = nowCal.getTime();
						eDate = oldCal.getTime();
					}
					
				}
				
				
				resStr= sort==1?(fmtDate(sDate)+" 00:00:00"):(fmtDate(eDate)+" 23:59:59");
			}else if("aftyear".equals(rule)){
				oldCal.add(Calendar.YEAR, Integer.parseInt(timeRule[1]));
				sDate = oldCal.getTime();
				resStr= sort==1?(simpleDate(sDate,"yyyy")+"-01-01 00:00:00"):(simpleDate(sDate,"yyyy")+"-12-31 23:59:59");
			}
		}
		
		resDate=stringToDateQ(resStr);
		if(resDate==null){
			resDate=resDate;
		}
		
		
		return resDate;
	}
	
	/**
	  *得到当前周周日 
	  */
	 public static Date getSundayOfWeek(Date date ) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (dayofweek == 0)
	   dayofweek = 7;
	  c.add(Calendar.DATE, -dayofweek + 7);
	  return c.getTime();
	 }
	 
	 /**
	  *得到当前月月末
	  */
	 public static Date getLastOfMonth(Date date ) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  c.set(Calendar.DATE, 1);
	  c.add(Calendar.MONTH, 1);
	  c.add(Calendar.DATE, -1);
	  return c.getTime();
	 }
	 
	 /**
	  *得到当前月月末
	  */
	 public static int getLastDayOfMonth(Date date ) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  c.set(Calendar.DATE, 1);
	  c.add(Calendar.MONTH, 1);
	  c.add(Calendar.DATE, -1);
	  return c.get(Calendar.DAY_OF_MONTH);
	 }
	 
	 
	 public static Integer getCircleDay(String msg){
		 String days[] = msg.split("#");
		 if(days.length == 2){
			 if(days[0].equals("cycday")){
				 return Integer.parseInt(days[1]);
			 }else if(days[0].equals("cycweek")){
				 return Integer.parseInt(days[1])*7;
			 }else if(days[0].equals("cycmonth")){
				 return Integer.parseInt(days[1])*30;
			 }else if(days[0].equals("cycyear")){
				 return Integer.parseInt(days[1])*365;
			 }else{
				 return 0;
			 }
		 }
		 return 0;
	 }
	 //转换成指定长度的字符串 ，转换错误用def_val
	 public static String fmtLenDigit(String src,int len,int def_val,int min,int max){
		 int val = -1;
		 try{ val = Integer.parseInt(src); }
		 catch(Exception e) { val = def_val; }
		 if ( val < min) val = min; //设定最小值
		 if ( val > max) val = max; //设定最大值
		 String ret = String.valueOf(val);
		 int add_len = len - ret.length(); //增加0的数量
		 for(int i=0;i<add_len;i++)
			  ret = "0"+ret;
		 return ret;
	 }
	 //格式化时分HH:MI 
	 public static String fmtTimeHHMI(String src,int def_hour,int def_min){
		 if ( src == null) src = "";
		 String arr[] = src.split(":"); int pos = 0;
		 String hour = null;
		 String min  = null;
		 pos = 0;
		 if ( arr != null && arr.length > pos) hour = arr[pos];
		 
		 pos =1 ;
		 if ( arr != null && arr.length > pos) min = arr[pos];
		 
		 hour = fmtLenDigit(hour,2,def_hour,0,23);
		 min  = fmtLenDigit(min ,2,def_min,0,59);
		 return hour+":"+min;
	 }
	 
	 public static void main(String[] args){
		
	 }
}
