/**
 * Class for reading the root drives and home folders.
 * Written on 23/01/2014
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

import beans.DriveDetails;

public class Root {

	public List<DriveDetails> getRootFileSystem(){
		
		List<DriveDetails> drives = new ArrayList<DriveDetails>();
		try {
			Process pr = Runtime.getRuntime().exec("df -H");
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String singleLine;
			String temp;
			//Skipping the first line
			br.readLine();
			//Reading the single lines and adding them to the list
			StringTokenizer str = null;
			while((singleLine = br.readLine()) != null){
				str = new StringTokenizer(singleLine);
				temp = str.nextToken();
				if(temp.contains("/")) {
					DriveDetails dd = new DriveDetails();
					//File system
					dd.fileSystem = temp;

					//Total Size
					//temp = str.nextToken();
					//temp = temp.substring(0, temp.length()-1);
					//dd.totalSize = Float.parseFloat(temp);
					dd.totalSize = str.nextToken();

					//Used space
					//temp = str.nextToken();
					//temp = temp.substring(0, temp.length()-1);
					//dd.usedSpace = Float.parseFloat(temp);
					dd.usedSpace = str.nextToken();

					//Available space
					//temp = str.nextToken();
					//temp = temp.substring(0, temp.length()-1);
					//dd.availableSpace = Float.parseFloat(temp);
					dd.availableSpace = str.nextToken();
					
					//Percentage usage
					temp = str.nextToken();
					temp = temp.substring(0, temp.length()-1);
					dd.usedPercentage = Float.parseFloat(temp);

					//Name
					temp = singleLine.substring(singleLine.lastIndexOf(str.nextToken()));
					
					if(temp.equals("/"))
						dd.name = "Root";
					else {
						dd.name = temp.substring(temp.lastIndexOf("/"));
					}
					
					//Address
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
					dd.address = adr.toString();
					
					//Adding the drive to the list
					drives.add(dd);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return drives;
	}

}
