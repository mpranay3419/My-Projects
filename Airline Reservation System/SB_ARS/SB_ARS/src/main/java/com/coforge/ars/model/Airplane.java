package com.coforge.ars.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tbl_airplane")
public class Airplane {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long planeId;

	@NotBlank(message = "Plane Name is required")
	@Column(nullable = false)
	private String planeName;

	@NotNull(message = "Capacity is required")
	@Positive(message = "Capacity must be greater than 0")
	@Column(nullable = false)
	private Integer capacity;
    public Airplane() {
    }

	public Airplane(Long planeId, String planeName, Integer capacity) {
		super();
		this.planeId = planeId;
		this.planeName = planeName;
		this.capacity = capacity;
	}

	public Long getPlaneId() {
		return planeId;
	}

	public void setPlaneId(Long planeId) {
		this.planeId = planeId;
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Airplane [planeId=" + planeId + ", planeName=" + planeName + ", capacity=" + capacity + "]";
	}


}