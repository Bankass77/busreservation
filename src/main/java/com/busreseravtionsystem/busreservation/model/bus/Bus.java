package com.busreseravtionsystem.busreservation.model.bus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Accessors(chain = true)
@Entity
@Table(schema = "bankass", name = "bus", indexes = @Index(columnList = "code", name = "idx_bus_code", unique = true))
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bus_id")
	private long id;

	@Column
	private String code;

	@Column
	private int capacity;

	@Column
	private String make;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id")
	private Agency agency;

}
