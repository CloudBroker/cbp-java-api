package com.cloudbroker.platform.api.data;

import com.cloudbroker.platform.api.exceptions.CloudbrokerPlatformAPIException;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("resource-price")
public class ResourcePrice extends Base{
	
	@XStreamAlias("resource-id")
	private String resourceID = "";
	
	@XStreamAlias("deployment-storage-fee")
	private double deploymentStorageFee;
	
	@XStreamAlias("instance-data-out-fee")
	private double instanceDataOutFee;
	
	@XStreamAlias("windows-surcharge")
	private int windowsSurcharge;
	
	public String getResourceID() {
		return resourceID;
	}

	public double getDeploymentStorageFee() {
		return deploymentStorageFee;
	}

	public double getInstanceDataOutFee() {
		return instanceDataOutFee;
	}

	public int getWindowsSurcharge() {
		return windowsSurcharge;
	}

	public static String getURL() {
		throw new CloudbrokerPlatformAPIException("Can not list resource prices. Please use CloudbrokerClient#getResourcePrice(String resourceID) to get price");
	}

}
