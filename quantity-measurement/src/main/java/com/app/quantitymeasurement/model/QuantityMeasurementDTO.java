package com.app.quantitymeasurement.model;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class QuantityMeasurementDTO {

    private double thisValue;
    private double thatValue;

    private String operation;  

    private String resultString;
    private double resultValue;

    private boolean error;
    private String errorMessage;
	public double getThisValue() {
		return thisValue;
	}
	public void setThisValue(double thisValue) {
		this.thisValue = thisValue;
	}
	public double getThatValue() {
		return thatValue;
	}
	public void setThatValue(double thatValue) {
		this.thatValue = thatValue;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	public double getResultValue() {
		return resultValue;
	}
	public void setResultValue(double resultValue) {
		this.resultValue = resultValue;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}