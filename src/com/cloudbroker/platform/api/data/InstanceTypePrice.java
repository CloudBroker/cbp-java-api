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
 * platform@cloudbroker.com
 *
 */

package com.cloudbroker.platform.api.data;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("instance-type-price")
public class InstanceTypePrice extends Base{
	
	@XStreamAlias("instance-type-id")
	private String instanceTypeID = "";

	@XStreamAlias("operating-system-id")
	private String operatingSystemID = "";
	
	@XStreamAlias("resource-price-id")
	private String resourcePriceID = "";
	
	@XStreamAlias("compute-runtime-fee")
	private double computeRuntimeFee;
	
	@XStreamAlias("instance-runtime-fee")
	private double instanceRuntimeFee;
	
	public String getInstanceTypeID() {
		return instanceTypeID;
	}
	
	public String getInstanceTypeId() {
		return getInstanceTypeID();
	}

	public void setInstanceTypeID(String instanceTypeID) {
		this.instanceTypeID = instanceTypeID;
	}
	
	public void setInstanceTypeId(String instanceTypeId) {
		setInstanceTypeID(instanceTypeId);
	}

	public String getOperatingSystemId() {
		return getOperatingSystemID();
	}
	
	public String getOperatingSystemID() {
		return operatingSystemID;
	}

	public void setOperatingSystemID(String operatingSystemID) {
		this.operatingSystemID = operatingSystemID;
	}
	
	public void setOperatingSystemId(String operatingSystemId) {
		setOperatingSystemID(operatingSystemId);
	}

	public String getResourcePriceID() {
		return resourcePriceID;
	}
	
	public String getResourcePriceId() {
		return getResourcePriceID();
	}

	public void setResourcePriceId(String resourcePriceID) {
		setResourcePriceID(resourcePriceID);
	}
	
	public void setResourcePriceID(String resourcePriceID) {
		this.resourcePriceID = resourcePriceID;
	}

	public double getComputeRuntimeFee() {
		return computeRuntimeFee;
	}
	
	public void setInstanceRuntimeFee(double instanceRuntimeFee) {
		this.instanceRuntimeFee = instanceRuntimeFee;
	}
	
	public double getInstanceRuntimeFee() {
		return instanceRuntimeFee;
	}

	public static String getURL() {
		return "/instance_type_prices";
	}
}
