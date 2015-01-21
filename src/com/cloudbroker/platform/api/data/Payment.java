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

public class Payment extends Base {
	
	@XStreamAlias("am-chng")
	private double amountChange;
	
	private double amount;
	
	@XStreamAlias("billing-item-id")
	private String billingItemID = "";
	
	private String description = "";

	@XStreamAlias("invoice-id")
	private String invoiceID = "";
	
	@XStreamAlias("organization-id")
	private String organizationID = "";

	@XStreamAlias("payment-code")
	private String paymentCode;
	
	private String purpose = "";

	public double getAmountChange() {
		return amountChange;
	}

	public double getAmount() {
		return amount;
	}

	public String getBillingItemID() {
		return billingItemID;
	}
	
	public String getBillingItemId() {
		return getBillingItemID();
	}

	public String getDescription() {
		return description;
	}

	public String getInvoiceID() {
		return invoiceID;
	}
	
	public String getInvoiceId() {
		return getInvoiceID();
	}

	public String getOrganizationID() {
		return organizationID;
	}
	
	public String getOrganizationId() {
		return getOrganizationID();
	}

	public int getPaymentCode() {
		return (paymentCode.isEmpty()) ? 0: Integer.parseInt(paymentCode);
	}

	public String getPurpose() {
		return purpose;
	}

	public static String getURL() {
		return "/payments";
	}

}
