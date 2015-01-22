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

@XStreamAlias("instance-type")
public class InstanceType extends Base{
	private boolean active;
	
	@XStreamAlias("architecture-id")
	private String architectureID;
	private int cpus;
	
	@XStreamAlias("ebs-only")
	private boolean ebsOnly;
	
	@XStreamAlias("ec2-clusters")
	private boolean ec2Clusters;
	
	@XStreamAlias("maximum-nodes")
	private String maximumNodes;
	
	private String description = "";
	
	@XStreamAlias("resource-id")
	private String resourceID = "";
	
	@XStreamAlias("instance-storage")
	private String instanceStorage;
	
	@XStreamAlias("root-storage")
	private String rootStorage;
	
	@XStreamAlias("cloud-api-name")
	private String cloudApiName;
	
	private double memory;
	
	public String getArchitectureID() {
		return architectureID;
	}

	public void setArchitectureID(String architectureID) {
		this.architectureID = architectureID;
	}

	public boolean ec2Clusters() {
		return ec2Clusters;
	}

	public void setEc2Clusters(boolean ec2Clusters) {
		this.ec2Clusters = ec2Clusters;
	}

	public double getMemory() {
		return memory;
	}

	public void setMemory(double memory) {
		this.memory = memory;
	}
	
	public String getInstanceStorage() {
		return instanceStorage;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getCpus() {
		return cpus;
	}

	public void setCpus(int cpus) {
		this.cpus = cpus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}
	
	public static String getURL() {
		return "/instance_types";
	}
	
	public String getCloudApiName() {
		return cloudApiName;
	}
	
	public void setCloudApiName(String cloudApiName) {
		this.cloudApiName = cloudApiName;
	}
}
