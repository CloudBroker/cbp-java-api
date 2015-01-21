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

public class Region extends Base{
	private String url = "";

	@XStreamAlias("ec2-clusters")
	private boolean ec2Clusters;

	@XStreamAlias("maximum-nodes")
	private String maximumNodes;
	
	@XStreamAlias("description")
	private String description;
	
	@XStreamAlias("api-name")
	private String apiName;
	
	@XStreamAlias("active")
	private boolean active;
	
	public String getMaximumNodes() {
		return maximumNodes;
	}

	public void setMaximumNodes(String maximumNodes) {
		this.maximumNodes = maximumNodes;
	}

	@XStreamAlias("resource-id")
	private String resourceID = "";
	
	@XStreamAlias("storage-id")
	private String storageID = "";
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEc2Clusters() {
		return ec2Clusters;
	}

	public void setEc2Clusters(boolean ec2Clusters) {
		this.ec2Clusters = ec2Clusters;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceTypeID(String resourceID) {
		this.resourceID = resourceID;
	}
	
	public void setResourceTypeId(String resourceId) {
		setResourceTypeID(resourceId);
	}
	
	public static String getURL() {
		return "/regions";
	}
	
	public String getApiName() {
		return apiName;
	}

	public boolean isActive() {
		return active;
	}

	public void setStorageID(String storageID) {
		this.storageID = storageID;
	}

	public String getStorageID() {
		return storageID;
	}
}
