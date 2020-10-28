package com.busreseravtionsystem.busreservation.model.bus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(schema = "bankass", name = "stop", indexes = @Index(columnList = "code", name = "idx_stop_code",unique = true))
@Entity
@Accessors(chain = true)
public class Stop {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stop_id")
	private long id;

	@Column
	 private String code;
	@Column
	 private String detail;
	
	@Column
	 private String name;
	 
	

}
