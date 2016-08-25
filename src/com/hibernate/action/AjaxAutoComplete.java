package com.hibernate.action;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.opensymphony.xwork2.ActionSupport;

public class AjaxAutoComplete extends ActionSupport
{
	private List<String> countries;
	private String country;
	
	public String execute() 
	{
		String data = "Afghanistan, Zimbabwe, India, United States";
        countries = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(data, ",");
 
        while (st.hasMoreTokens()) {
            countries.add(st.nextToken().trim());
        }
        return SUCCESS;
    }
    public String getCountry() {
        return this.country;
    }
 
    public List<String> getCountries() {
        return countries;
    }
 
    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
