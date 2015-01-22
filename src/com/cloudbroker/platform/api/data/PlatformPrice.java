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

@XStreamAlias("platform-price")
public class PlatformPrice extends Base{
	
	@XStreamAlias("enduser-organization-fee")
	private double endUserOrganizationFee;
	
	@XStreamAlias("vendor-fee")
	private double vendorOrganizationFee;
	
	@XStreamAlias("provider-fee")
	private double providerOrganizationFee;
	
	@XStreamAlias("platform-user-fee")
	private double individualUserFee;
	
	@XStreamAlias("non-commercial-rebate")
	private int nonCommercialRebate;
	
	@XStreamAlias("resource-surcharge")
	private int resourceSurcharge;
	
	@XStreamAlias("software-surcharge")
	private int softwareSurcharge;

	public double getEndUserOrganizationFee() {
		return endUserOrganizationFee;
	}

	public double getVendorOrganizationFee() {
		return vendorOrganizationFee;
	}

	public double getProviderOrganizationFee() {
		return providerOrganizationFee;
	}

	public double getIndividualUserFee() {
		return individualUserFee;
	}

	public int getNonCommercialRebate() {
		return nonCommercialRebate;
	}

	public int getResourceSurcharge() {
		return resourceSurcharge;
	}

	public int getSoftwareSurcharge() {
		return softwareSurcharge;
	}

	public static String getURL() {
		throw new CloudbrokerPlatformAPIException("Can not list platform prices. Please use CloudbrokerClient#getPlatformPrice() to get price");
	}
}
