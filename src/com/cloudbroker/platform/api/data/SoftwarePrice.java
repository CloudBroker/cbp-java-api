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

import com.cloudbroker.platform.api.exceptions.CloudbrokerPlatformAPIException;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("software-price")
public class SoftwarePrice extends Base{
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
	
	@XStreamAlias("non-commercial-rebate")
	private int nonCommercialRebate;
	
	public String getSoftwareID() {
		return softwareID;
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

	public int getNonCommercialRebate() {
		return nonCommercialRebate;
	}

	public static String getURL() {
		throw new CloudbrokerPlatformAPIException("Can not list software prices. Please use CloudbrokerClient#getSoftwarePrice(String softwareID) to get price");
	}

}
