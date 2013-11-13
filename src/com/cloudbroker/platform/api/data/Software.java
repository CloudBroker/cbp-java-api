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

public class Software extends Base {
	@XStreamAlias("core-runtime-fee")
	private double coreRuntimeFee;

	@XStreamAlias("runtime-fee")
	private double runtimeFee;
	
	@XStreamAlias("use-nfs")
	private boolean useNFS;
	
	@XStreamAlias("job-fee")
	private double jobFee;

	@XStreamAlias("user-fee")
	private double userFee;

	@XStreamAlias("organization-fee")
	private double organizationFee;

	@XStreamAlias("temp-core-runtime-fee")
	private double tempCoreRuntimeFee;

	@XStreamAlias("temp-runtime-fee")
	private double tempRuntimeFee;

	@XStreamAlias("temp-job-fee")
	private double tempJobFee;

	@XStreamAlias("temp-user-fee")
	private double tempUserFee;

	@XStreamAlias("temp-organization-fee")
	private double tempOrganizationFee;

	private String version = "";
	private String product = "";
	private String website = "";

	@XStreamAlias("documentation-link")
	private String documentationLink = "";

	private String status = "";
	private String description = "";

	@XStreamAlias("included-jobs")
	private String includedJobs;

	@XStreamAlias("included-users")
	private String includedUsers;

	@XStreamAlias("included-runtime")
	private String includedRuntime;

	@XStreamAlias("included-core-runtime")
	private String includedCoreRuntime;

	@XStreamAlias("maximum-jobs")
	private String maximumJobs;

	@XStreamAlias("maximum-users")
	private String maximumUsers;

	@XStreamAlias("maximum-runtime")
	private String maximumRuntime;

	@XStreamAlias("maximum-core-runtime")
	private String maximumCoreRuntime;

	@XStreamAlias("organization-id")
	private String organizationID;

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public double getCoreRuntimeFee() {
		return coreRuntimeFee;
	}

	public void setCoreRuntimeFee(double coreRuntimeFee) {
		this.coreRuntimeFee = coreRuntimeFee;
	}

	public double getRuntimeFee() {
		return runtimeFee;
	}

	public void setRuntimeFee(double runtimeFee) {
		this.runtimeFee = runtimeFee;
	}

	public double getJobFee() {
		return jobFee;
	}

	public void setJobFee(double jobFee) {
		this.jobFee = jobFee;
	}

	public double getUserFee() {
		return userFee;
	}

	public void setUserFee(double userFee) {
		this.userFee = userFee;
	}

	public double getOrganizationFee() {
		return organizationFee;
	}

	public void setOrganizationFee(double organizationFee) {
		this.organizationFee = organizationFee;
	}

	public double getTempCoreRuntimeFee() {
		return tempCoreRuntimeFee;
	}

	public void setTempCoreRuntimeFee(double tempCoreRuntimeFee) {
		this.tempCoreRuntimeFee = tempCoreRuntimeFee;
	}

	public double getTempRuntimeFee() {
		return tempRuntimeFee;
	}

	public void setTempRuntimeFee(double tempRuntimeFee) {
		this.tempRuntimeFee = tempRuntimeFee;
	}

	public double getTempJobFee() {
		return tempJobFee;
	}

	public void setTempJobFee(double tempJobFee) {
		this.tempJobFee = tempJobFee;
	}

	public double getTempUserFee() {
		return tempUserFee;
	}

	public void setTempUserFee(double tempUserFee) {
		this.tempUserFee = tempUserFee;
	}

	public double getTempOrganizationFee() {
		return tempOrganizationFee;
	}

	public void setTempOrganizationFee(double tempOrganizationFee) {
		this.tempOrganizationFee = tempOrganizationFee;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDocumentationLink() {
		return documentationLink;
	}

	public void setDocumentationLink(String documentationLink) {
		this.documentationLink = documentationLink;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getUseNFS() {
		return useNFS;
	}
	
	public void setUseNFS(boolean useNFS) {
		this.useNFS = useNFS;
	}

	public static String getURL() {
		return "/softwares";
	}
}
