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

@XStreamAlias("software-price")
public class SoftwarePrice extends Base{
	@XStreamAlias("organization-type-id")
	private String organizationTypeID = "";
	
	@XStreamAlias("software-id")
	private String softwareID = "";
	
	@XStreamAlias("license-organization-fee")
	private double licenseOrganizationFee;
	
	@XStreamAlias("license-user-fee")
	private double licenseUserFee;
	
	@XStreamAlias("license-runtime-fee")
	private double licenseRuntimeFee;
	
	@XStreamAlias("license-core-runtime-fee")
	private double licenseCoreRuntimeFee;
	
	@XStreamAlias("license-job-fee")
	private double licenseJobFee;
	
	@XStreamAlias("software-organization-fee")
	private double softwareOrganizationFee;
	
	@XStreamAlias("software-user-fee")
	private double softwareUserFee;
	
	@XStreamAlias("software-job-fee")
	private double softwareJobFee;
	
	@XStreamAlias("software-runtime-fee")
	private double softwareRuntimeFee;
	
	@XStreamAlias("software-core-runtime-fee")
	private double softwareCoreRuntimeFee;
	
	public String getOrganizationTypeID() {
		return organizationTypeID;
	}
	
	public String getOrganizationTypeId() {
		return getOrganizationTypeID();
	}

	public void setOrganizationTypeID(String organizationTypeID) {
		this.organizationTypeID = organizationTypeID;
	}
	
	public void setOrganizationTypeId(String organizationTypeId) {
		setOrganizationTypeID(organizationTypeId);
	}

	public String getSoftwareID() {
		return softwareID;
	}
	
	public String getSoftwareId() {
		return getSoftwareID();
	}

	public void setSoftwareID(String softwareID) {
		this.softwareID = softwareID;
	}
	
	public void setSoftwareId(String softwareId) {
		setSoftwareID(softwareId);
	}

	public double getLicenseOrganizationFee() {
		return licenseOrganizationFee;
	}

	public double getLicenseUserFee() {
		return licenseUserFee;
	}

	public double getLicenseRuntimeFee() {
		return licenseRuntimeFee;
	}

	public double getLicenseCoreRuntimeFee() {
		return licenseCoreRuntimeFee;
	}

	public double getLicenseJobFee() {
		return licenseJobFee;
	}
	
	public void setSoftwareOrganizationFee(double softwareOrganizationFee) {
		this.softwareOrganizationFee = softwareOrganizationFee;
	}
	
	public double getSoftwareOrganizationFee() {
		return softwareOrganizationFee;
	}
	
	public void setSoftwareUserFee(double softwareUserFee) {
		this.softwareUserFee = softwareUserFee;
	}
	
	public double getSoftwareUserFee() {
		return softwareUserFee;
	}
	
	public void setSoftwareJobFee(double softwareJobFee) {
		this.softwareJobFee = softwareJobFee;
	}
	
	public double getSoftwareJobFee() {
		return softwareJobFee;
	}
	
	public void setSoftwareRuntimeFee(double softwareRuntimeFee) {
		this.softwareRuntimeFee = softwareRuntimeFee;
	}
	
	public double getSoftwareRuntimeFee() {
		return softwareRuntimeFee;
	}
	
	public void setSoftwareCoreRuntimeFee(double softwareCoreRuntimeFee) {
		this.softwareCoreRuntimeFee = softwareCoreRuntimeFee;
	}
	
	public double getSoftwareCoreRuntimeFee() {
		return softwareCoreRuntimeFee;
	}

	public static String getURL() {
		return "/software_prices";
	}

}
