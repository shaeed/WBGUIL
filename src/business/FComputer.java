/**
 * Class for showing the my computer window.
 * Written on 24/01/2014
 * Author: SHAEED KHAN
 * Place: CDAC, E city, Bangalore
 * Project: Web Based Graphical User Interface for Linux
 * */

package business;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import kernel.Root;
import kernel.RunCommand;
import beans.DriveDetails;
import beans.FilesFolders;

public class FComputer implements SessionAware {
	private String path;
	private String forwardButton;
	private String backButton;
	private String refresh;
	private Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> map) {
		session = map;
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

	public String openComputer(){
		//Checking the session status
		if(!session.containsKey("sessionStatus")){
			//This is new session
			//Initialising the session
			new InitializeSession().initialize(session);
			//Path for back button & forward button
			backButton = "";
			forwardButton = "";
			session.put("forwardButton", "");
			session.put("backButton", "");
		}
		else {
			//This is old session
			//Path for back button
			backButton = "";
			session.put("backButton", "");
		}
		//Refresh Path
		refresh = "";
		session.put("refresh", "");
		
		//Getting the drives
		List<DriveDetails> drives = new Root().getRootFileSystem();
		//Getting the user's folder
		List<FilesFolders> userFolders = new RunCommand().getFilesFolders("ls -lh", "/home");
		//Removing the lost+found from the user's folder list
		Iterator<FilesFolders> it = userFolders.iterator();
		while(it.hasNext()){
			if(it.next().name.equals("lost+found")){
				it.remove();
				break;
			}
		}
		//Storing to the session
		session.put("drives", drives);
		session.put("userFolders", userFolders);
		session.put("windowTitle", "My Computer");
		session.put("fpath", "Computer");
		
		return "success";
	}
}
