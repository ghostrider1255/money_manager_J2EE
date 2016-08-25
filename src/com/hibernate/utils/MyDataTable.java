package com.hibernate.utils;

import javax.servlet.http.HttpServletRequest;

/*
	columns[0][search][value]
	columns[0][data]
	columns[0][name]
	columns[0][search][regex]
	columns[0][orderable]
	columns[0][searchable]
	columns[1][orderable]
	columns[1][data]
	columns[1][searchable]
	columns[1][search][value]
	columns[1][name]
	columns[1][search][regex]
	columns[2][search][regex]
	columns[2][searchable]
	columns[2][name]
	columns[2][data]
	columns[2][search][value]
	columns[2][orderable]
	columns[3][name]
	columns[3][data]
	columns[3][search][value]
	columns[3][searchable]
	columns[3][orderable]
	columns[3][search][regex]
	columns[4][search][value]
	columns[4][data]
	columns[4][name]
	columns[4][searchable]
	columns[4][search][regex]
	columns[4][orderable]
	search[value]
	draw
	length
	order[0][column]
	order[0][dir]
	start
	search[regex]
*/

public class MyDataTable 
{
	private static MyDataTable _myDataTableInstance;
	private HttpServletRequest request;
	
	private MyDataTable()
	{}
	private MyDataTable(HttpServletRequest request)
	{
		setRequest(request);
	}
	public static MyDataTable getInstance(HttpServletRequest request)
	{
		if(_myDataTableInstance==null)
		{
			_myDataTableInstance=new MyDataTable(request);
		}
		setParams(request);
		return _myDataTableInstance;
	}
	
	private static void setParams(HttpServletRequest request)
	{
		_myDataTableInstance.setsSearch(request.getParameter(DataTableField.searchValue));
		
		_myDataTableInstance.setiDisplayLength(getIntParameter(request,DataTableField.iDisplayLength));
		
		_myDataTableInstance.setiTotalRecords(getIntParameter(request,DataTableField.iTotalRecords));
		
		_myDataTableInstance.setiTotalDisplayRecords(getIntParameter(request,DataTableField.iTotalDisplayRecords));
		
		_myDataTableInstance.setiDisplayStart(getIntParameter(request,DataTableField.iDisplayStart));
		
		_myDataTableInstance.setiColumns(getIntParameter(request,DataTableField.iColumns));
		
		_myDataTableInstance.setiSortingCols(getIntParameter(request,DataTableField.iSortingCols));
		
		_myDataTableInstance.setiSortColumnIndex(getIntParameter(request,DataTableField.iSortColumnIndex));
		
		_myDataTableInstance.setsSortDirection(request.getParameter(DataTableField.sSortDirection));
		
		_myDataTableInstance.setsColumns(request.getParameter(DataTableField.sColumns));
		
		_myDataTableInstance.setbRegex(Boolean.parseBoolean(request.getParameter(DataTableField.bRegex)));
		
		_myDataTableInstance.setLength(getIntParameter(request,DataTableField.length));
		
		_myDataTableInstance.setStart(getIntParameter(request,DataTableField.start));
	}
	private static int getIntParameter(HttpServletRequest request,String paramValue)
	{
		int intValue=0;
		String defaultString=request.getParameter(paramValue);
		if(defaultString==null)
		{
			intValue=0;
		}
		else
		{
			intValue=Integer.parseInt(defaultString);
		}
		return intValue;
	}
	
	private int start;
	
	/* Total number of columns for the respective datatable*/
	private int length;
	
	private boolean bRegex;
	
	/*Total records, before filtering (i.e. the total number of records in the database)*/
	private int iTotalRecords;
	
	/*Total records, after filtering (i.e. the total number of records after filtering has been applied - not just the number of records being returned in this result set)*/
	private int iTotalDisplayRecords;
	
	/*Text used for filtering (search[value])*/
	private String sSearch;
	
    /* Number of records that should be shown in table*/
	private int iDisplayLength;

    /*First record that should be shown(used for paging) (start)*/
	private int iDisplayStart;

    /*Number of columns in table*/
	private int iColumns; 

    /*Number of columns that are used in sorting*/
	private int iSortingCols;
    
    /*Index of the column that is used for sorting*/
	private int iSortColumnIndex;
    
    /*Sorting direction "asc" or "desc"*/
	private String sSortDirection;

    /*Comma separated list of column names*/
	private String sColumns;


	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public int getiSortColumnIndex() {
		return iSortColumnIndex;
	}

	public void setiSortColumnIndex(int iSortColumnIndex) {
		this.iSortColumnIndex = iSortColumnIndex;
	}

	public String getsSortDirection() {
		return sSortDirection;
	}

	public void setsSortDirection(String sSortDirection) {
		this.sSortDirection = sSortDirection;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public boolean isbRegex() {
		return bRegex;
	}
	public void setbRegex(boolean bRegex) {
		this.bRegex = bRegex;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
}
