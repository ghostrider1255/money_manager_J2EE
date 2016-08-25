package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.Chapter;
import com.hibernate.service.ChapterServiceIF;

public class ChapterServiceImpl implements ChapterServiceIF{

	@Override
	public void addChapter(Chapter chapter) {
		DaoFactory.getChapterInstance().addChapter(chapter);
		
	}

	@Override
	public void delete(Chapter chapter) {
		DaoFactory.getChapterInstance().delete(chapter);
		
	}

	@Override
	public List<Chapter> getRecords() {
		
		return DaoFactory.getChapterInstance().getRecords();
	}

	@Override
	public Chapter getRecord(int chapterID) {
		
		return DaoFactory.getChapterInstance().getRecord(chapterID);
	}

	@Override
	public List<Chapter> getActiveRecords(boolean isActive) {
		
		return DaoFactory.getChapterInstance().getActiveRecords(isActive);
	}

	@Override
	public void updateRecord(Chapter chapter) {
		DaoFactory.getChapterInstance().updateRecord(chapter);
		
	}

}
