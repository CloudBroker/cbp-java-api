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

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Invoice extends Base {
	private String beginning;
	private String ending;
	
	@XStreamAlias("fee-sum")
	private double feeSum;
	
	private String number = "";

	@XStreamAlias("organization-id")
	private String organizationID = "";
	
	private String status = "";
	
	@XStreamAlias("total-sum")
	private double totalSum;

	@XStreamAlias("vat-sum")
	private double vatSum;

	public Date getBeginning() {
		return getDateFromString(beginning);
	}

	public Date getEnding() {
		return getDateFromString(ending);
	}

	public double getFeeSum() {
		return feeSum;
	}

	public void setFeeSum(double feeSum) {
		this.feeSum = feeSum;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOrganizationID() {
		return organizationID;
	}
	
	public String getOrganizationId() {
		return getOrganizationID();
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}
	
	public void setOrganizationId(String organizationId) {
		setOrganizationID(organizationId);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}

	public double getVatSum() {
		return vatSum;
	}

	public void setVatSum(double vatSum) {
		this.vatSum = vatSum;
	}

	public static String getURL() {
		return "/invoices";
	}

}
