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

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Job extends Base{
	
	@XStreamAlias("software-id")
	private String softwareID = "";

	@XStreamAlias("resource-id")
	private String resourceID = "";

	@XStreamAlias("region-id")
	private String regionID = "";
	
	@XStreamAlias("ec2-region-id")
	private String ec2RegionID = "";

	@XStreamAlias("access-id")
	private String accessID = "";
	
	@XStreamAlias("archive-output")
	private boolean archiveOutput;
	
	@XStreamAlias("job-outcome-string")
	private String jobOutcome = "";

	@XStreamAlias("argument-string")
	private String argumentString = "";
	
	private String description = "";

	@XStreamAlias("executable-id")
	private String executableID = "";
	
	@XStreamAlias("generating-deployment-id")
	private String generatingDeploymentID = "";
	
	@XStreamAlias("instance-type-id")
	private String instanceTypeID = "";
	
	@XStreamAlias("job-outcome-id")
	private String jobOutcomeID = "";
	
	@XStreamAlias("license-id")
	private String licenseID = "";
	
	private int nodes = 0;

	@XStreamAlias("previous-job-id")
	private String previousJobID = "";

	@XStreamAlias("start-immediately")
	private boolean startImmediately = true;
	
	private String status = "";
	
	@XStreamAlias("stderr-file-name")
	private String stderrFileName = "";

	@XStreamAlias("stdout-file-name")
	private String stdoutFileName = "";
	
	@XStreamAlias("stop-reason")
	private String stopReason = "";
	
	@XStreamAlias("deployment-id")
	private String deploymentID;
	
	@XStreamAlias("used-storage")
	private long usedStorage;

	@XStreamAlias("used-core-runtime")
	private int usedCoreRuntime;

	@XStreamAlias("used-runtime")
	private int usedRuntime;
	
	@XStreamAlias("user-id")
	private String userID = "";
	
	@XStreamAlias("hours-paid")
	private int hoursPaid;
	
	@XStreamAlias("hours-paid-for-instance")
	private int hoursPaidForInstance;
	
	@XStreamAlias("no-instance-shutdown")
	private boolean noInstanceShutdown;
	
	@XStreamAlias("job-tag-content")
	private String jobTagContent;
	
	@XStreamAlias("storage-id")
	private String storageID = "";
	
	@XStreamAlias("requested-instance-id")
	private String requestedInstanceID = "";
	
	@XStreamAlias("last-load-files")
	private Date lastLoadFiles;
	
	@XStreamAlias("permanently-running")
	private boolean permanentlyRunning = false;

	@XStreamAlias("no-execution")
	private boolean noExecution = false;
	
	@XStreamAlias("tag")
	private Tag tag = new Tag();
	
	public boolean isPermanentlyRunning() {
		return permanentlyRunning;
	}

	public void setPermanentlyRunning(boolean permanentlyRunning) {
		this.permanentlyRunning = permanentlyRunning;
	}

	public boolean isNoExecution() {
		return noExecution;
	}

	public void setNoExecution(boolean noExecution) {
		this.noExecution = noExecution;
	}

	public String getJobOutcome() {
		return jobOutcome;
	}

	public void setJobOutcome(String jobOutcome) {
		this.jobOutcome = jobOutcome;
	}
	
	public Date getLastLoadFiles() {
		return lastLoadFiles;
	}

	public String getAccessID() {
		return accessID;
	}

	public String getArgumentString() {
		return argumentString;
	}

	public void setArgumentString(String argumentString) {
		this.argumentString = argumentString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isArchiveOutput() {
		return archiveOutput;
	}

	public void setArchiveOutput(boolean archiveOutput) {
		this.archiveOutput = archiveOutput;
	}

	public String getExecutableID() {
		return executableID;
	}

	public void setExecutableID(String executableID) {
		this.executableID = executableID;
	}

	public String getGeneratingDeploymentID() {
		return generatingDeploymentID;
	}

	public void setGeneratingDeploymentID(String generatingDeploymentID) {
		this.generatingDeploymentID = generatingDeploymentID;
	}

	public String getInstanceTypeID() {
		return instanceTypeID;
	}

	public void setInstanceTypeID(String instanceTypeID) {
		this.instanceTypeID = instanceTypeID;
	}

	public String getLicenseID() {
		return licenseID;
	}

	public void setLicenseID(String licenseID) {
		this.licenseID = licenseID;
	}

	public int getNodes() {
		return nodes;
	}

	public void setNodes(int nodes) {
		this.nodes = nodes;
	}

	public String getPreviousJobID() {
		return previousJobID;
	}

	public void setPreviousJobID(String previousJobID) {
		this.previousJobID = previousJobID;
	}

	public boolean isStartImmediately() {
		return startImmediately;
	}

	public void setStartImmediately(boolean startImmediately) {
		this.startImmediately = startImmediately;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStderrFileName() {
		return stderrFileName;
	}

	public void setStderrFileName(String stderrFileName) {
		this.stderrFileName = stderrFileName;
	}

	public String getStdoutFileName() {
		return stdoutFileName;
	}

	public void setStdoutFileName(String stdoutFileName) {
		this.stdoutFileName = stdoutFileName;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	public String getDeploymentID() {
		return deploymentID;
	}
	
	public String getDeploymentId() {
		return getDeploymentID();
	}

	public void setDeploymentID(String deploymentID) {
		this.deploymentID = deploymentID;
	}
	
	public void setDeploymentId(String deploymentId) {
		setDeploymentID(deploymentId);
	}

	public long getUsedStorage() {
		return usedStorage;
	}

	public void setUsedStorage(long usedStorage) {
		this.usedStorage = usedStorage;
	}

	public int getUsedCoreRuntime() {
		return usedCoreRuntime;
	}

	public void setUsedCoreRuntime(int usedCoreRuntime) {
		this.usedCoreRuntime = usedCoreRuntime;
	}

	public int getUsedRuntime() {
		return usedRuntime;
	}

	public void setUsedRuntime(int usedRuntime) {
		this.usedRuntime = usedRuntime;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public void setExecutable(Executable executable) {
		this.executableID = executable.getID();
		this.softwareID = executable.getSoftwareID();
	}
	
	public String getSoftwareID() {
		return softwareID;
	}

	public void setSoftwareID(String softwareID) {
		this.softwareID = softwareID;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public String getRegionID() {
		return regionID;
	}

	public void setRegionID(String regionID) {
		this.regionID = regionID;
		this.ec2RegionID = regionID;
	}
	
	public void setTagContent(String content) {
		this.tag.setContent(content);
	}
	
	public String getTagContent() {
		return tag.getContent();
	}

	public static String getURL() {
		return "/jobs";
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public void setStorageID(String storageID) {
		this.storageID = storageID;
	}

	public String getStorageID() {
		return storageID;
	}

	public boolean isNoInstanceShutdown() {
		return noInstanceShutdown;
	}

	public void setNoInstanceShutdown(boolean noInstanceShutdown) {
		this.noInstanceShutdown = noInstanceShutdown;
	}
	
	public void setJobID(String jobID) {
		this.id = jobID;
	}
	
	public String getRequestedInstanceID() {
		return requestedInstanceID;
	}

	public void setRequestedInstanceID(String requestedInstanceID) {
		this.requestedInstanceID = requestedInstanceID;
	}
}
