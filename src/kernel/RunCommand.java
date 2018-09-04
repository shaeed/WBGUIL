/**
 * Class for running a Linux Kernel command.
 * Written on 24/01/2014
 * Author: SHAEED KHAN
 * Place: CDAC, E city, Bangalore
 * Project: Web Based Graphical User Interface for Linux
 * */

package kernel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import beans.FilesFolders;

public class RunCommand {
	public List<FilesFolders> getFilesFolders(String command, String path){
		List<FilesFolders> list = new ArrayList<FilesFolders>();
		try {
			//Running the linux command
			Process pr = Runtime.getRuntime().exec(command+" "+path);
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String singleLine;
			String temp;
			String type;
			//Skipping the first line of result
			br.readLine();
			//Reading the single lines and adding them to the list
			StringTokenizer str = null;
			while((singleLine = br.readLine()) != null){
				str = new StringTokenizer(singleLine);
				FilesFolders ff = new FilesFolders();
				//Reading the type
				type = str.nextToken();
				//Skipping the 3 columns
				str.nextToken();str.nextToken();str.nextToken();
				//Calculating the size
				ff.size = str.nextToken()+'B';
				//Skipping the 3 columns
				str.nextToken();str.nextToken();str.nextToken();
				//Reading the Name
				temp = singleLine.substring(singleLine.lastIndexOf(str.nextToken()));

				//Checking for file or folder
				if(type.charAt(0) == '-'){
					//It is file
					if(temp.charAt(temp.length()-1) == '~')
						continue;
					int indx = temp.lastIndexOf('.');
					if(indx >= 0)
						ff.type = temp.substring(temp.lastIndexOf('.')+1);
					else
						ff.type = "unknown";
				}
				else{
					//It is folder
					ff.type = "folder.type";
				}
				//Storing name & address
				ff.name = temp;
				//Generating address
				char[] arr = temp.toCharArray();
				int len = arr.length;
				StringBuilder adr = new StringBuilder();
				for (int i = 0; i < len; i++) {
					if(arr[i] == ' ')
						adr.append("\\ ");
					else
						adr.append(arr[i]);
				}
				//adr.append('/');
				ff.address = path+"/"+adr.toString();
				System.out.println(ff.address);
				//Adding the Object ff to the list.
				list.add(ff);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getFileFolderProperty(String command, String path){
		String result = "";
		//Running the linux command
		Process pr;
		try {
			pr = Runtime.getRuntime().exec(command+" "+path);
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String singleLine;
			while((singleLine = br.readLine()) != null){
				result += singleLine;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public boolean openFile(String viewer, String filePath){
		//Running the linux command
		Process pr;
		try {
			pr = Runtime.getRuntime().exec(viewer+" "+filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
