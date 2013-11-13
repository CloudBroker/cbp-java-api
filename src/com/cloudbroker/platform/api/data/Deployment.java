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

public class Deployment extends Base{

	public static String getURL() {
		return "/deployments";
	}
	
	@XStreamAlias("architecture-id")
	private String architectureID = "";

	@XStreamAlias("resource-id")
	private String resourceID = "";
	
	@XStreamAlias("region-id")
	private String regionID = "";
	
	@XStreamAlias("software-id")
	private String softwareID = "";
	
	private String release = "";
	private String name = "";
	private String status = "";

	@XStreamAlias("ec2-clusters")
	private boolean ec2Clusters = false;

	public String getArchitectureID() {
		return architectureID;
	}
	
	public String getArchitectureId() {
		return getArchitectureID();
	}

	public String getResourceID() {
		return resourceID;
	}

	public String getResourceId() {
		return getResourceID();
	}
	public String getRegionID() {
		return regionID;
	}
	
	public String getRegionId() {
		return getRegionID();
	}

	public String getSoftwareID() {
		return softwareID;
	}
	
	public String getSoftwareId() {
		return getSoftwareID();
	}

	public String getRelease() {
		return release;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public boolean ec2Clusters() {
		return ec2Clusters;
	}
	
	
}
