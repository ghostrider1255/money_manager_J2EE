package com.hibernate.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hibernate.utils.constants.ConfigProperties;

public class MyUtils 
{
	private static final String _generatorFolder="Configuration";
	private static String _propertiesFile="configuration.properties";
	private static final String _userFolder="user.dir";
	private static final String _applicationRoot="application.rootDirectory";
	private static Properties _systemProperties=null;
	
	private static String app_date_format=null;
	
	static{
		try
		{
			ClassLoader classLoader= Thread.currentThread().getContextClassLoader();
			_systemProperties= new Properties();
			_systemProperties.load(classLoader.getResourceAsStream(_propertiesFile));
			app_date_format=getProperty(ConfigProperties.APPLICATION_DATE_FORMAT);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Properties getApplicationProperties()
	{
		return _systemProperties;
	}
	public static String getProperty(String name) throws NoSuchFieldException
	{
		String returnValue = (String) _systemProperties.get( name);
		if( returnValue == null) {
			throw new NoSuchFieldException( name);
		}
		return returnValue;
	}
	public static boolean getProperty( String l_name, boolean l_defaultValue) throws NoSuchFieldException {
		String value = (String) _systemProperties.get( l_name);
		if( value == null) {
			
			return l_defaultValue;
		}
		return Boolean.valueOf( value.trim());
	}
	
	public static boolean makeDirectory( File dir) {
		if( !dir.exists()) {
			boolean flag = dir.mkdirs();
			return flag;
		}
		else return true;
	}

	public static File getRootDirectory( String client) {
		return new File( client);
	}

	public static FilenameFilter getFileNameFilter( final String pattern) {
		return new FilenameFilter() {

			public boolean accept( File dir, String name) {
				return name.toLowerCase().endsWith( pattern.toLowerCase());
			}
		};
	}
	
	public static File getConfigDirectory() {
		String pwd = System.getProperty( _userFolder);
		String root = System.getProperty( _applicationRoot, pwd);
		File rootDir = new File( root);
		return new File( rootDir, _generatorFolder);
	}
	public static int daysDifference(String fromDate,String toDate)
	{
		int year1 = Integer.valueOf( fromDate.substring( 0, 4)).intValue();
		int month1 = Integer.valueOf( fromDate.substring( 4, 6)).intValue();
		int day1 = Integer.valueOf( fromDate.substring( 6)).intValue();
		int year2 = Integer.valueOf( toDate.substring( 0, 4)).intValue();
		int month2 = Integer.valueOf( toDate.substring( 4, 6)).intValue();
		int day2 = Integer.valueOf( toDate.substring( 6)).intValue();
		Calendar first = new GregorianCalendar( year1, month1 - 1, day1);
		Calendar second = new GregorianCalendar( year2, month2 - 1, day2);
		long diffMillis = second.getTimeInMillis() - first.getTimeInMillis();
		long diffDays = diffMillis / (24 * 60 * 60 * 1000);
		return (int) diffDays;
	}
	public static double getDifferenceInTime( Calendar startTime, Calendar endTime) {
		double diffMillis = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		double diffInTime = diffMillis / (1000);
		diffInTime = roundDouble( diffInTime, 4);
		return diffInTime;
	}
	public static final double roundDouble( double d, int places) {
		return Math.round( d * Math.pow( 10, (double) places)) / Math.pow( 10, (double) places);
	}
	@SuppressWarnings("deprecation")
	public static String computeEndDate(String p_BillDateStr) throws Exception
	{
		Date d1 = null;
		SimpleDateFormat p_simpleDate = new SimpleDateFormat("yyyyMMdd");
		Date d = p_simpleDate.parse(p_BillDateStr);
		d1 = new Date();
		d1.setYear(d.getYear());
		d1.setMonth(d.getMonth() + 1);
		d1.setDate(d.getDate());
		d1.setDate(d1.getDate() - 1);
		return p_simpleDate.format(d1);
	}
	public static String computeMonthStartDate(String currentDate)
	{
		Pattern datecharPatter=Pattern.compile("[0-9]{8}");
		Matcher unformattedDateMatcher=datecharPatter.matcher(currentDate);
		if(currentDate!=null && currentDate.trim().length()>0 && currentDate.trim().length()==8 && unformattedDateMatcher.find())
		{
			currentDate=currentDate.substring(0,6)+"01";
		}
		return currentDate;
	}
	@SuppressWarnings("deprecation")
	public static String computestartDate(String p_BillDateStr) throws Exception
	{
		Date d1=null;
		SimpleDateFormat p_simpleDate=new SimpleDateFormat("yyyyMMdd");
		Date d=p_simpleDate.parse(p_BillDateStr);
		d1=new Date();
		d1.setYear(d.getYear());
		d1.setMonth(d.getMonth()-1);
		d1.setDate(d.getDate());
		d1.setDate(d1.getDate());
		return  p_simpleDate.format(d1);
	}
	public static String computePeriodStartDate(String startDate) throws Exception
	{
		
		if (startDate.contains("-"))
		{
			Pattern p_p_date = Pattern.compile("[0-9]{4}-[\\d]{2}");
			  Matcher p_m_date = p_p_date.matcher(startDate);
			  if (p_m_date.find())
			  {
				  startDate = startDate.substring(startDate.indexOf("-")+1)+"-01-"+startDate.substring(0,startDate.indexOf("-")); 
			  }
		}
		else if(startDate.trim().length()!=8)
		{
			startDate = "01 "+startDate;
		}
		

		startDate=getDateString(startDate);
		return startDate;
	}
	public static String convertDateToString(Date date,String dateFormat)
	{
		String formatedDate="";
		try
		{
			SimpleDateFormat finalDateFormat=new SimpleDateFormat(dateFormat);
			formatedDate=finalDateFormat.format(date);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();	
		}
		return formatedDate;
	}
	public static String convertDateFormat(String inputDate,String inputDateFormat,String outputDateFormat)
	{
		String formatedDate="";
		try
		{
			SimpleDateFormat inputFormat=new SimpleDateFormat(inputDateFormat);
			SimpleDateFormat outputFormat=new SimpleDateFormat(outputDateFormat);
			
			Date convertedDate=inputFormat.parse(inputDate);
			formatedDate=outputFormat.format(convertedDate);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return formatedDate;
	}
	public static Date convertToDate(String dateString,String dateFormat)
	{
		Date outputDate=null;
		try
		{
			SimpleDateFormat outputFormat=new SimpleDateFormat(dateFormat);
			outputDate=outputFormat.parse(dateString);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return outputDate;
	}
	@SuppressWarnings("deprecation")
	public static String getDateString(String dateStr)
	{
		int year = 0;
		Pattern p_pattern =Pattern.compile("[A-Z]+");
		String dateString = dateStr.trim();
		SimpleDateFormat simp_date=new SimpleDateFormat("yyyyMMdd");
		Matcher p_mathces=p_pattern.matcher(dateString.toUpperCase());
		if(!p_mathces.find())
		{
			while(dateString.indexOf(".")!=-1)
				dateString= dateString.replace("."," ").trim();
			dateString=dateString.replaceAll("-","/");
			dateString=dateString.replaceAll(" ","/");
		}
		try
		{
			dateString=simp_date.format(new Date(dateString));
			dateStr=dateString;
		}
		catch(Exception e)
		{
			try
			{
				dateString+=" "+year;
				dateString=simp_date.format(new Date(dateString));
				dateStr=dateString;
			}
			catch (Exception ex)
			{
				System.out.println( "WARNING: Error in formatting DATE "+dateStr);
			}
		}
		return  dateStr;
	}
	
}
