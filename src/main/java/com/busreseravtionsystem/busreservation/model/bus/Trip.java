package com.busreseravtionsystem.busreservation.model.bus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(schema = "bankass", name = "trip")
@Slf4j
@Getter
@Setter
@Accessors (chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "trip_id")
	private long id;

	@Column
	private int fare;

	@Column
	private int journeyTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "source_stop_id")
	private  Stop sourceStop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dest_stop_id")
	private Stop destStop;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bus_id")
	private Bus bus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id")
	private Agency agency;

}
