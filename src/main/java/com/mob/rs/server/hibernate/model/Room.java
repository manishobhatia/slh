package com.mob.rs.server.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id", columnDefinition = "serial")
	private Integer id;

	@Column(name = "number")
	private String number; // immutable

	@Column(name = "capacity")
	private Integer capacity;

	@Column(name = "building_id", insertable = false, updatable = false)
	private Integer buildingId; // also stores the building_id so we can
								// reference this without fetching the parent
								// building

	Room() {
		// default constructor
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public Integer getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	// no setters for number, building nor id
}
