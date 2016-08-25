package com.hibernate.service.impl;

import org.jfree.chart.JFreeChart;

import com.hibernate.resource.Budget;
import com.hibernate.dao.DaoFactory;
import com.hibernate.service.JFreeChartServiceIF;

public class JFreeChartServiceImpl implements JFreeChartServiceIF{

	@Override
	public JFreeChart getCategoryPieChart(Budget budget) {
		return DaoFactory.getJFreeChartInstance().getCategoryPieChart(budget);
	}

	@Override
	public JFreeChart getExpenseCategoryPieChart(Budget budget) {
		return DaoFactory.getJFreeChartInstance().getExpenseCategoryPieChart(budget);
	}

	@Override
	public JFreeChart getMonthWiseBarChart(Budget budget) {
		return DaoFactory.getJFreeChartInstance().getMonthWiseBarChart(budget);
	}

}
