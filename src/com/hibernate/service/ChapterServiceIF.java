package com.hibernate.service;

import java.util.List;

import com.hibernate.resource.Chapter;

public interface ChapterServiceIF 
{
	public void addChapter(Chapter chapter);
	
	public void delete(Chapter chapter);
	
	public List<Chapter> getRecords();
	
	public Chapter getRecord(int chapterID);
	
	public List<Chapter> getActiveRecords(boolean isActive);
	
	public void updateRecord(Chapter chapter);

}
