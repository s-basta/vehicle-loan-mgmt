package com.vehicleloan.dao.emiStatus;

import java.util.Date;

import com.vehicleloan.constant.PaymentStatus;

public class EMIStatus {
	private Integer statusId;
	private Integer applicationId;
	private PaymentStatus paymentStatus;
	private Date scheduledPaymentDate;
	private Date actualPaymentDate;

	public EMIStatus(Integer statusId, Integer applicationId, PaymentStatus paymentStatus, Date scheduledPaymentDate,
			Date actualPaymentDate) {
		super();
		this.statusId = statusId;
		this.applicationId = applicationId;
		this.paymentStatus = paymentStatus;
		this.scheduledPaymentDate = scheduledPaymentDate;
		this.actualPaymentDate = actualPaymentDate;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getScheduledPaymentDate() {
		return scheduledPaymentDate;
	}

	public void setScheduledPaymentDate(Date scheduledPaymentDate) {
		this.scheduledPaymentDate = scheduledPaymentDate;
	}

	public Date getActualPaymentDate() {
		return actualPaymentDate;
	}

	public void setActualPaymentDate(Date actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
	}
}