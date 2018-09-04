/**
 * Class for reading the files and folders.
 * Written on 24/01/2014
 * Author: SHAEED KHAN
 * Place: CDAC, E city, Bangalore
 * Project: Web Based Graphical User Interface for Linux
 * */

package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.struts2.interceptor.SessionAware;

import kernel.RunCommand;
import beans.FileFolderProperties;
import beans.FilesFolders;

public class FExplorer implements SessionAware{
	private String path;
	private String forwardButton;
	private String backButton;
	private String refresh;
	private String title;
	private String viewer;
	private FileFolderProperties property;
	
	public FileFolderProperties getProperty(String path) {
		property = new FileFolderProperties();
		property.name = new RunCommand().getFileFolderProperty("stat ", path);
			
		return property;
	}
	public void setProperty(FileFolderProperties property) {
		this.property = property;
	}
	private Map<String, Object> session;
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getForwardButton() {
		return forwardButton;
	}
	public void setForwardButton(String forwardButton) {
		this.forwardButton = forwardButton;
	}
	public String getBackButton() {
		return backButton;
	}
	public void setBackButton(String backButton) {
		this.backButton = backButton;
	}
	public String getRefresh() {
		return refresh;
	}
	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getViewer() {
		return viewer;
	}
	public void setViewer(String viewer) {
		this.viewer = viewer;
	}
	
	public String back(){
		//Getting the back history & forwardHistory
		@SuppressWarnings("unchecked")
		Stack<String> backHistory = (Stack<String>)session.get("backHistory");
		@SuppressWarnings("unchecked")
		Stack<String> forwardHistory = (Stack<String>)session.get("forwardHistory");

		//Setting the path for forward button
		forwardButton = (String)session.get("fpath");
		session.put("forwardButton", forwardButton);
		//Updating the forward history
		forwardHistory.push(forwardButton);
		//Adding the fpath
		session.put("fpath", backButton);
		//Getting the files & folders
		List<FilesFolders> list = new RunCommand().getFilesFolders("ls -lh", backButton);
		session.put("filesFolders", list);

		//Path for refresh
		refresh = backButton;
		session.put("refresh", refresh);
		//Path for back button
		if(backHistory.isEmpty()) {
				backButton = "";
				session.put("backButton", backButton);
			}
		else {
			backButton = backHistory.pop();
			session.put("backButton", backButton);
		}
		session.put("windowTitle", getWindowTitle(refresh));

		return "success";
	}

	public String forward(){
		//Getting the backHistory & forwardHistory
		//@SuppressWarnings("unchecked")
		//Stack<String> backHistory = (Stack<String>)session.get("backHistory");
		@SuppressWarnings("unchecked")
		Stack<String> forwardHistory = (Stack<String>)session.get("forwardHistory");
		//Path for back button
		backButton = (String)session.get("fpath");
		session.put("backButton", backButton);
		//Adding the fpath
		session.put("fpath", forwardButton);
		//Getting the files & folders
		List<FilesFolders> list = new RunCommand().getFilesFolders("ls -lh", forwardButton);
		session.put("filesFolders", list);

		//Path for refresh
		refresh = forwardButton;
		session.put("refresh", refresh);
		//Path for back button
		if(forwardHistory.isEmpty()) {
			forwardButton = "";
			session.put("forwardButton", forwardButton);
		}
		else {
			forwardButton = forwardHistory.pop();
			session.put("forwardButton", forwardButton);
		}
		session.put("windowTitle", getWindowTitle(refresh));

		return "success";
	}

	public String refresh(){
		//Getting the files & folders
		List<FilesFolders> list = new RunCommand().getFilesFolders("ls -lh", refresh);
		session.put("filesFolders", list);
		session.put("windowTitle", getWindowTitle(refresh));
		return "success";
	}

	public String path(){
		//Checking the session status
		if(!session.containsKey("sessionStatus")){
			//This is new session
			//Initialising the session
			new InitializeSession().initialize(session);
			//Path for back button
			backButton = "";
			session.put("backButton", backButton);
		}
		else {
			//This is old session
			//Path for back button
			backButton = (String)session.get("fpath");
			session.put("backButton", backButton);
			//Updating the history
			@SuppressWarnings("unchecked")
			Stack<String> backHistory = (Stack<String>)session.get("backHistory");
			backHistory.push(backButton);
		}
		//Making the Path of Forward button to null
		forwardButton = "";
		session.put("forwardButton", forwardButton);
		//Path for refresh button
		refresh = path;
		session.put("refresh", refresh);
		//Adding the path to the session
		session.put("fpath", path);
		//Getting the files & folders
		List<FilesFolders> list = new RunCommand().getFilesFolders("ls -lh", path);
		session.put("filesFolders", list);
		session.put("windowTitle", getWindowTitle(refresh));

		return "success";
	}
	
	public String getProperties(){
		property = new FileFolderProperties();
		property.name = path+" shaeed";
		return "success";
	}
	
	private String getWindowTitle(String path){
		char[] temp = path.substring(path.lastIndexOf('/')+1).toCharArray();
		int len = temp.length;
		StringBuilder title = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if(temp[i] == '\\')
				continue;
			title.append(temp[i]);
		}
		return title.toString();
	}
	
	public String fileOpener(){
		//Getting the viewer
		//Map<String, String> viewers = (Map<String, String>)session.get("viewers");
		Map<String, String> viewers = InitializeApp.getViewers();
		String viewr = "shaeed";
		
		if(viewers.containsKey(this.viewer)){
			viewr = viewers.get(this.viewer);
			if(new RunCommand().openFile(viewr, path)){
				return "success";
			}
		}
		
		return "not-found";
	}
}
