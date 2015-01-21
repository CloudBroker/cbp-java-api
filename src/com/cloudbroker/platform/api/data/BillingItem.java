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

@XStreamAlias("billing-item")
public class BillingItem extends Base {

	@XStreamAlias("activity-log-id")
	private String activityLogID = "";
	
	@XStreamAlias("billing-code")
	private int billingCode;

	@XStreamAlias("collecting-amount")
	private double collectingAmount;

	private String description = "";

	@XStreamAlias("fee-amount")
	private double feeAmount;
	
	@XStreamAlias("fee-type")
	private String feeType = "";

	@XStreamAlias("invoice-id")
	private String invoiceID = "";

	@XStreamAlias("model-identifier")
	private String modelIdentifier = "";

	@XStreamAlias("model-name")
	private String modelName = "";

	@XStreamAlias("open-amount")
	private double openAmount;
	
	@XStreamAlias("price-model-identifier")
	private String priceModelIdentifier = "";
	
	@XStreamAlias("price-model-name")
	private String priceModelName = "";
	
	private double quantity;
	
	@XStreamAlias("quantity-amount")
	private double quantityAmount;

	private String status = "";

	@XStreamAlias("total-amount")
	private double totalAmount;
	
	@XStreamAlias("user-id")
	private String userID = "";

	private double vat;
	
	@XStreamAlias("vat-amount")
	private double vatAmount;
	
	@XStreamAlias("tag-id")
	private String tagID;
	
	
	private String createdAt;

	public String getActivityLogID() {
		return activityLogID;
	}
	
	public String getActivityLogId() {
		return getActivityLogID();
	}

	public void setActivityLogID(String activityLogID) {
		this.activityLogID = activityLogID;
	}
	
	public void setActivityLogId(String activityLogId) {
		setActivityLogID(activityLogID);
	}
	

	public int getBillingCode() {
		return billingCode;
	}

	public void setBillingCode(int billingCode) {
		this.billingCode = billingCode;
	}

	public double getCollectingAmount() {
		return collectingAmount;
	}

	public void setCollectingAmount(double collectingAmount) {
		this.collectingAmount = collectingAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getInvoiceID() {
		return invoiceID;
	}
	
	public String getInvoiceId() {
		return getInvoiceID();
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}
	
	public void setInvoiceId(String invoiceId) {
		setInvoiceID(invoiceId);
	}

	public String getModelIdentifier() {
		return modelIdentifier;
	}

	public void setModelIdentifier(String modelIdentifier) {
		this.modelIdentifier = modelIdentifier;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public double getOpenAmount() {
		return openAmount;
	}

	public void setOpenAmount(double openAmount) {
		this.openAmount = openAmount;
	}

	public String getPriceModelIdentifier() {
		return priceModelIdentifier;
	}

	public void setPriceModelIdentifier(String priceModelIdentifier) {
		this.priceModelIdentifier = priceModelIdentifier;
	}

	public String getPriceModelName() {
		return priceModelName;
	}

	public void setPriceModelName(String priceModelName) {
		this.priceModelName = priceModelName;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getQuantityAmount() {
		return quantityAmount;
	}

	public void setQuantityAmount(double quantityAmount) {
		this.quantityAmount = quantityAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUserID() {
		return userID;
	}
	
	public String getUserId() {
		return getUserID();
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public void setUserId(String userId) {
		setUserID(userId);
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(double vatAmount) {
		this.vatAmount = vatAmount;
	}
	
	public String getTagID() {
		return tagID;
	}
	
	public Date getDate() {
		return getDateFromString(createdAt);
	}

	public static String getURL() {
		return "/billing_items";
	}

}
