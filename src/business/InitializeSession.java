/**
 * Class for initialising all the session related informations.
 * Written on 24/01/2014
 * Author: SHAEED KHAN
 * Place: CDAC, E city, Bangalore
 * Project: Web Based Graphical User Interface for Linux
 * */

package business;

import java.util.Map;
import java.util.Stack;

public class InitializeSession {
	
	public boolean initialize(Map<String, Object> session){
		
		//Creating the stack for maintaining the history
		Stack<String> backHistory = new Stack<String>();
		Stack<String> forwardHistory = new Stack<String>();
		//Adding the history to session
		session.put("backHistory", backHistory);
		session.put("forwardHistory", forwardHistory);
		
		//making session old
		//Means adding the sessionStatus variable to the session
		session.put("sessionStatus", "old"); 
		
		//Language map
		session.put("langMap", InitializeApp.getLanguageList());
		//Icon List
		session.put("icons", InitializeApp.getIconList());
		//Viewers
		session.put("viewers", InitializeApp.getViewers());
		
		return true;
	}
}
