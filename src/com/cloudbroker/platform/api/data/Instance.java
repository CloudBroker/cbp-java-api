/**
 * Copyright 2015 CloudBroker GmbH, Zurich, Switzerland
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

@XStreamAlias("instance")
public class Instance extends Base{
	public static String getURL() {
		return "/instances";
	}
	
	@XStreamAlias("software-id")
	private String softwareID = "";

	@XStreamAlias("deployment-id")
	private String deploymentID = "";

	@XStreamAlias("region-id")
	private String regionID = "";
	
	@XStreamAlias("instance-type-id")
	private String instanceTypeID = "";
	
	@XStreamAlias("opened-port")
	private String openedPort = "0";
	
	@XStreamAlias("external-ip-address")
	private String externalIP;

	@XStreamAlias("disable-autostop")
	private boolean disableAutostop = false;

	private boolean isolated = false;

	public String getExternalIP() {
		return externalIP;
	}

	public void setExternalIP(String externalIP) {
		this.externalIP = externalIP;
	}

	public String getSoftwareID() {
		return softwareID;
	}

	public void setSoftwareID(String softwareID) {
		this.softwareID = softwareID;
	}

	public String getDeploymentID() {
		return deploymentID;
	}

	public void setDeploymentID(String deploymentID) {
		this.deploymentID = deploymentID;
	}

	public String getRegionID() {
		return regionID;
	}

	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}

	public String getInstanceTypeID() {
		return instanceTypeID;
	}

	public void setInstanceTypeID(String instanceTypeID) {
		this.instanceTypeID = instanceTypeID;
	}

	public boolean isDisableAutostop() {
		return disableAutostop;
	}

	public void setDisableAutostop(boolean disableAutostop) {
		this.disableAutostop = disableAutostop;
	}

	public boolean isIsolated() {
		return isolated;
	}

	public void setIsolated(boolean isolated) {
		this.isolated = isolated;
	}

	public String getOpenedPort() {
		return openedPort;
	}

	public void setOpenedPort(String openedPort) {
		this.openedPort = openedPort;
	}
	
}
