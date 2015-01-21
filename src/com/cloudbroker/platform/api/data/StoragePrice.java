/**
 * Copyright 2013 CloudBroker GmbH, Zurich, Switzerland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For additional information please contact us via the following email
 * support@cloudbroker.com
 *
 */

package com.cloudbroker.platform.api.data;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("storage-price")
public class StoragePrice extends Base{
	
	@XStreamAlias("storage-id")
	private String storageID = "";

	@XStreamAlias("cloud-data-in-fee")
	private double cloudDataInFee;
	
	@XStreamAlias("cloud-data-out-fee")
	private double cloudDataOutFee;

	@XStreamAlias("cloud-storage-fee")
	private double cloudStorageFee;
	
	@XStreamAlias("compute-data-in-fee")
	private double computeDataInFee;
	
	@XStreamAlias("compute-data-out-fee")
	private double computeDataOutFee;
	
	@XStreamAlias("persistent-data-out-fee")
	private double persistentDataOutFee;
	
	@XStreamAlias("persistent-data-in-fee")
	private double persistentDataInFee;
	
	@XStreamAlias("persistent-storage-fee")
	private double persistentStorageFee;
	
	@XStreamAlias("persistent-requests-fee")
	private double persistentRequestsFee;
	
	public String getStorageID() {
		return storageID;
	}
	
	public String getStorageId() {
		return getStorageID();
	}

	public void setStorageID(String storageID) {
		this.storageID = storageID;
	}
	
	public void setStorageId(String storageId) {
		setStorageID(storageID);
	}

	public double getCloudDataInFee() {
		return cloudDataInFee;
	}
	
	public double getCloudStorageFee() {
		return cloudStorageFee;
	}

	public double getCloudDataOutFee() {
		return cloudDataOutFee;
	}
	
	public double getComputeDataInFee() {
		return computeDataInFee;
	}

	public double getComputeDataOutFee() {
		return computeDataOutFee;
	}
	
	public void setPersistentDataInFee(double persistentDataInFee){
		this.persistentDataInFee = persistentDataInFee;
	}
	
	public double getPersistentDataInFee(){
		return persistentDataInFee;
	}
	
	public void setPersistentDataOutFee(double persistentDataOutFee){
		this.persistentDataOutFee = persistentDataOutFee;
	}
	
	public double getPersistentDataOutFee(){
		return persistentDataOutFee;
	}
	
	public void setPersistentStorageFee(double persistentStorageFee) {
		this.persistentStorageFee = persistentStorageFee;
	}
	
	public double getPersistentStorageFee() {
		return persistentStorageFee;
	}
	
	public void setPersistentRequestsFee(double persistentRequestsFee) {
		this.persistentRequestsFee = persistentRequestsFee;
	}
	
	public double getPersistentRequestsFee(){
		return persistentRequestsFee;
	}

	public static String getURL() {
		return "/storage_prices";
	}

}
