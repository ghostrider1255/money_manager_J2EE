package com.hibernate.dao.impl;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.hibernate.resource.Budget;
import com.hibernate.dao.JFreeChartDaoIF;
import com.hibernate.resource.Expense;
import com.hibernate.resource.Income;
import com.hibernate.resource.UserCodeValue;
import com.hibernate.utils.MyUtils;
import com.hibernate.utils.dbutil.HibernateUtil;

public class JFreeChartDaoImpl implements JFreeChartDaoIF{

	@Override
	public JFreeChart getCategoryPieChart(Budget budget) 
	{
		Session session=null;
		JFreeChart categoryChart=null;
		DefaultPieDataset dataSet=new DefaultPieDataset();
		try
		{
			session=HibernateUtil.getSession();
			Criteria categoryTypeCriteria=session.createCriteria(Income.class);
			categoryTypeCriteria.setProjection(Projections.projectionList()
					.add(Projections.sum("amount"),"CategoryCount")
					.add(Projections.groupProperty("catergory")))
					.createAlias("user", "u")
					.add(Restrictions.eq("u.personID",budget.getUser().getPersonID()));
			/*categoryTypeCriteria
				.add(Restrictions.le("transactionDate", budget.getBudgetEndDate()))
				.add(Restrictions.ge("transactionDate", budget.getBudgetStartDate()));*/
			
			categoryTypeCriteria
			.add(Restrictions.sqlRestriction("STR_TO_DATE(transactionDate,'%d-%c-%Y')<=STR_TO_DATE('"+budget.getBudgetEndDate()+"','%d-%c-%Y')"))
			.add(Restrictions.sqlRestriction("STR_TO_DATE(transactionDate,'%d-%c-%Y')>=STR_TO_DATE('"+budget.getBudgetStartDate()+"','%d-%c-%Y')"))
			.addOrder(Order.desc("CategoryCount"));
			
			List<Object[]> categoryList=categoryTypeCriteria.list();
			List<Object[]> dupCategoryList=categoryList;
			if(categoryList!=null && categoryList.size()>0)
			{
				double countSum=0;
				for(Object[] object:dupCategoryList)
				{
					countSum+=(double)object[0];
				}
				for(Object[] object1:categoryList)
				{
					double categoryCount=(double)object1[0];
					UserCodeValue tempCategory=(UserCodeValue)object1[1];
					
					dataSet.setValue(tempCategory.getUserCodeDesc()+" %", MyUtils.roundDouble(((categoryCount/countSum)*100), 2));
				}
			}
			else
			{
				dataSet.setValue("Empty Value %", .01);
			}
			categoryChart=ChartFactory.createPieChart3D("Income Category %", dataSet, true, true, false);
			categoryChart.setBorderVisible(false);
			categoryChart.getTitle().setFont(new Font("Times New Roman",Font.BOLD,14));
			
			final PiePlot3D plot = ( PiePlot3D ) categoryChart.getPlot( );             
		    plot.setStartAngle( 270 );             
		    plot.setForegroundAlpha( 0.60f );             
		    plot.setInteriorGap( 0.33);
		    plot.setDirection(Rotation.ANTICLOCKWISE);
		    plot.setBackgroundPaint(new Color(238, 238, 238));
		    //plot.setOutlinePaint(new Color(238,238,238));
		   
			
			//HibernateUtil.commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			HibernateUtil.close();
		}
		return categoryChart;
	}
	@Override
	public JFreeChart getExpenseCategoryPieChart(Budget budget) 
	{
		Session session=null;
		JFreeChart categoryChart=null;
		DefaultPieDataset dataSet=new DefaultPieDataset();
		try
		{
			session=HibernateUtil.getSession();
			
			Criteria categoryTypeCriteria=session.createCriteria(Expense.class);
			categoryTypeCriteria.setProjection(Projections.projectionList()
					.add(Projections.sum("amount"),"CategoryCount")
					.add(Projections.groupProperty("category")))
					.createAlias("user", "u")
					.add(Restrictions.eq("u.personID",budget.getUser().getPersonID()));
			/*categoryTypeCriteria.add(Restrictions.le("transactionDate", budget.getBudgetEndDate()))
								.add(Restrictions.ge("transactionDate", budget.getBudgetStartDate()));*/
			categoryTypeCriteria
							.add(Restrictions.sqlRestriction("STR_TO_DATE(transactionDate,'%d-%c-%Y')<=STR_TO_DATE('"+budget.getBudgetEndDate()+"','%d-%c-%Y')"))
							.add(Restrictions.sqlRestriction("STR_TO_DATE(transactionDate,'%d-%c-%Y')>=STR_TO_DATE('"+budget.getBudgetStartDate()+"','%d-%c-%Y')"))
							.addOrder(Order.desc("CategoryCount"));
			
			
			List<Object[]> categoryList=categoryTypeCriteria.list();
			List<Object[]> dupCategoryList=categoryList;
			if(categoryList!=null && categoryList.size()>0)
			{
				double countSum=0;
				for(Object[] object:dupCategoryList)
				{
					countSum+=(double)object[0];
				}
				for(Object[] object1:categoryList)
				{
					double categoryCount=(double)object1[0];
					UserCodeValue tempCategory=(UserCodeValue)object1[1];
					
					dataSet.setValue(tempCategory.getUserCodeDesc()+" %", MyUtils.roundDouble(((categoryCount/countSum)*100), 2));
				}
			}
			else
			{
				dataSet.setValue("Empty Value %", .01);
			}
			categoryChart=ChartFactory.createPieChart3D("Expense Category %", dataSet, true, true, false);
			categoryChart.setBorderVisible(false);
			categoryChart.getTitle().setFont(new Font("Times New Roman",Font.BOLD,14));
			
			final PiePlot3D plot = ( PiePlot3D ) categoryChart.getPlot( );             
		      plot.setStartAngle( 270 );             
		      plot.setForegroundAlpha( 0.60f );             
		      plot.setInteriorGap( 0.33);
		      plot.setDirection(Rotation.ANTICLOCKWISE);
		      plot.setBackgroundPaint(new Color(238, 238, 238));
			  //plot.setOutlinePaint(new Color(238,238,238));
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			HibernateUtil.close();
		}
		return categoryChart;
	}
	@Override
	public JFreeChart getMonthWiseBarChart(Budget budget)
	{
		JFreeChart monthWiseBarChart=null;

		Session session=null;
		DefaultCategoryDataset dataSet=null;
		final String income="Income";
		final String expense="Expense";
		String sqlQuery="select b.incomeAmount,b.ExpenseAmount,b.budgetStartDate from budget b where b.userID="+budget.getUser().getPersonID()+" order by str_to_date(budgetStartDate,'%d-%c-%Y')";
		try
		{
			session=HibernateUtil.getSession();
			dataSet=new DefaultCategoryDataset();
			SQLQuery hibSQLQuery=session.createSQLQuery(sqlQuery);
			String months[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
			List<Object[]> monthWiseBarChartList=hibSQLQuery.list();
			
			if(monthWiseBarChartList!=null && monthWiseBarChartList.size()>0)
			{
				Object[] objectDup=null;
				boolean isDummyChart=false;
				for(Object[] object:monthWiseBarChartList)
				{
					if(monthWiseBarChartList.size()==1 && ((double)object[0]==0.0) && ((double)object[1]==0.0))
					{
						for(int iMonths=0;iMonths<5;iMonths++)
						{
							dataSet.addValue(1000, "Dummy Income Data", months[iMonths]);
							dataSet.addValue(500, "Dummy Expense Data", months[iMonths]);
							isDummyChart=true;
						}
					}
					else
					{
						dataSet.addValue((double)object[0], income, MyUtils.convertDateFormat((String)object[2], "dd-MM-yyyy", "MMMMM"));
						dataSet.addValue((double)object[1], expense, MyUtils.convertDateFormat((String)object[2], "dd-MM-yyyy", "MMMMM"));
						objectDup=object;
					}
				}
				if(monthWiseBarChartList.size()<5 && !isDummyChart)
				{
					int lastMonth=0;
					for(String month:months)
					{
						if(MyUtils.convertDateFormat((String)objectDup[2], "dd-MM-yyyy", "MMMMM").compareToIgnoreCase(month)==0)
						{
							lastMonth++;
							break;
						}
						lastMonth++;
					}
					int remMonths=0;
					for(;remMonths+monthWiseBarChartList.size()<5;remMonths++)
					{
						if(lastMonth==11)
						{
							lastMonth=0;
						}
						dataSet.addValue(0, income, months[lastMonth]);
						dataSet.addValue(0, expense, months[lastMonth]);
						lastMonth++;
					}
					
				}
			}
			else
			{
				for(int iMonths=0;iMonths<5;iMonths++)
				{
					dataSet.addValue(100, "Dummy Income Data", months[iMonths]);
					dataSet.addValue(100, "Dummy Expense Data", months[iMonths]);
				}
				//todayDate=MyUtils.convertDateToString(new Date(), "dd-MM-yyyy");
				
			}
			
			monthWiseBarChart=ChartFactory.createBarChart3D("Income Expense Stats", "Months", "Rupees", dataSet, PlotOrientation.VERTICAL, true, true, false);
			
			BarRenderer barRender=(BarRenderer)monthWiseBarChart.getCategoryPlot().getRenderer();
			if(monthWiseBarChartList.size()>1)
			{
				barRender.setMaximumBarWidth(.10);
			}
				
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			HibernateUtil.close();
		}
		return monthWiseBarChart;
	}

}
