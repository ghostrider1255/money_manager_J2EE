package com.hibernate.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.hibernate.resource.Chapter;
import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport 
{
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	private String destPath;
	private Chapter chapter;
	
	public FileUploadAction()
	{
		
	}
	public File getFile() {
		return fileUpload;
	}
	public void setFile(File fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getContentType() {
		return fileUploadContentType;
	}
	public void setContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	public String getFileName() {
		return fileUploadFileName;
	}
	public void setFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	public String getDestPath() {
		return destPath;
	}
	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	
	public String execute()
	{
		destPath="C:\\WebPortal_testing";
		try
		{
			System.out.println("Source File Name:"+fileUpload.getAbsolutePath());
			System.out.println("Destination File Name:"+fileUploadFileName);
			
			File destFile= new File(destPath,fileUploadFileName);
			FileUtils.copyFile(fileUpload, destFile);
		}
		catch(IOException exception)
		{
			exception.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
}
