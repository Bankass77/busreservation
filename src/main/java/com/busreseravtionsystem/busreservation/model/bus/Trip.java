package com.busreseravtionsystem.busreservation.model.bus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(schema = "bankass", name = "trip")
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;

	@Column
	private int fare;

	@Column
	private int journeyTime;

	@Column
	private  Stop sourceStop;

	@Column
	private Stop destStop;

	@Column
	private Bus bus;
	@Column
	private Agency agency;

}
