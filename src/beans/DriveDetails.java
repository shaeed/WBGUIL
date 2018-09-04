/**
 * Bean for holding the drive details.
 * Written on 23/01/2014
 * Author: SHAEED KHAN
 * Place: CDAC, E city, Bangalore
 * Project: Web Based Graphical User interface for Linux
 * */

package beans;

public class DriveDetails {
	public String fileSystem;
	public String name;
	public String totalSize;
	public String usedSpace;
	public String availableSpace;
	public float usedPercentage;
	public String address;
	public String getFileSystem() {
		return fileSystem;
	}
	public void setFileSystem(String fileSystem) {
		this.fileSystem = fileSystem;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}
	public String getUsedSpace() {
		return usedSpace;
	}
	public void setUsedSpace(String usedSpace) {
		this.usedSpace = usedSpace;
	}
	public String getAvailableSpace() {
		return availableSpace;
	}
	public void setAvailableSpace(String availableSpace) {
		this.availableSpace = availableSpace;
	}
	public float getUsedPercentage() {
		return usedPercentage;
	}
	public void setUsedPercentage(float usedPercentage) {
		this.usedPercentage = usedPercentage;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
