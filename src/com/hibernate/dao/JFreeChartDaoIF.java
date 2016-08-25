package com.hibernate.dao;

import org.jfree.chart.JFreeChart;

import com.hibernate.resource.Budget;

public interface JFreeChartDaoIF 
{
	public JFreeChart getCategoryPieChart(Budget budget);
	
	public JFreeChart getExpenseCategoryPieChart(Budget budget);
	
	public JFreeChart getMonthWiseBarChart(Budget budget);
}
