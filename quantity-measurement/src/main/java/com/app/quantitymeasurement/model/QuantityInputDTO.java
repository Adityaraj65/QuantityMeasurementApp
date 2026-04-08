package com.app.quantitymeasurement.model;

public class QuantityInputDTO {
    public QuantityDTO getThisQuantityDTO() {
		return thisQuantityDTO;
	}
	public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) {
		this.thisQuantityDTO = thisQuantityDTO;
	}
	public QuantityDTO getThatQuantityDTO() {
		return thatQuantityDTO;
	}
	public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) {
		this.thatQuantityDTO = thatQuantityDTO;
	}
	public QuantityDTO getTargetQuantityDTO() {
		return targetQuantityDTO;
	}
	public void setTargetQuantityDTO(QuantityDTO targetQuantityDTO) {
		this.targetQuantityDTO = targetQuantityDTO;
	}
	private QuantityDTO thisQuantityDTO;
    private QuantityDTO thatQuantityDTO;
    private QuantityDTO targetQuantityDTO;
}