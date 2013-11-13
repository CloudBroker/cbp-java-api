package com.cloudbroker.platform.api.data;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("resource-price")
public class ResourcePrice extends Base{
	
	@XStreamAlias("region-id")
	private String regionID = "";

	@XStreamAlias("resource-id")
	private String resourceID = "";
	
	@XStreamAlias("compute-data-in-fee")
	private double computeDataInFee;
	
	@XStreamAlias("compute-data-out-fee")
	private double computeDataOutFee;
	
	@XStreamAlias("deployment-storage-fee")
	private double deploymentStorageFee;
	
	@XStreamAlias("image-requests-fee")
	private double imageRequestsFee;
	
	@XStreamAlias("image-storage-fee")
	private double imageStorageFee;
	
	@XStreamAlias("instance-data-in-fee")
	private double instanceDataInFee;
	
	@XStreamAlias("instance-data-out-fee")
	private double instanceDataOutFee;
	
	@XStreamAlias("instance-requests-fee")
	private double instanceRequestsFee;
	
	@XStreamAlias("instance-storage-fee")
	private double instanceStorageFee;
	
	public String getRegionID() {
		return regionID;
	}
	
	public String getRegionId() {
		return getRegionID();
	}

	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}
	
	public void setRegionId(String regionId) {
		setRegionID(regionId);
	}

	public String getResourceID() {
		return resourceID;
	}
	
	public String getResourceId() {
		return getResourceID();
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}
	
	public void setResourceId(String resourceId) {
		setResourceID(resourceId);
	}

	public double getComputeDataInFee() {
		return computeDataInFee;
	}
	
	public double getDeploymentStorageFee() {
		return deploymentStorageFee;
	}
	
	public double getComputeDataOutFee() {
		return computeDataOutFee;
	}
		
	public void setImageRequestsFee(double imageRequestsFee) {
		this.imageRequestsFee = imageRequestsFee;
	}
	
	public double getImageRequestsFee() {
		return imageRequestsFee;
	}
	
	public void setImageStorageFee(double imageStorageFee) {
		this.imageStorageFee = imageStorageFee;
	}
	
	public double getImageStorageFee() {
		return imageStorageFee;
	}
	
	public void setInstanceDataInFee(double instanceDataInFee) {
		this.instanceDataInFee = instanceDataInFee;
	}
	
	public double getInstanceDataInFee() {
		return instanceDataInFee;
	}
	
	public void setInstanceDataOutFee(double instanceDataOutFee) {
		this.instanceDataOutFee = instanceDataOutFee;
	}
	
	public double getInstanceDataOutFee() {
		return instanceDataOutFee;
	}
	
	public void setInstanceRequestsFee(double instanceRequestsFee) {
		this.instanceRequestsFee = instanceRequestsFee;
	}
	
	public double getInstanceRequestsFee() {
		return instanceRequestsFee;
	}
	
	public void setInstanceStorageFee(double instanceStorageFee) {
		this.instanceStorageFee = instanceStorageFee;
	}
	
	public double getInstanceStorageFee() {
		return instanceStorageFee;
	}

	public static String getURL() {
		return "/resource_prices";
	}

}
