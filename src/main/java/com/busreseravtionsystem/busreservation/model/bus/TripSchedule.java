package com.busreseravtionsystem.busreservation.model.bus;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (schema = "bankass", name = "tripschedule")
public class TripSchedule {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private Trip tripDetail;
	
	@OneToMany
	private List<Ticket> ticketsSold;
	
	@Column
	private String tripDate;
	
	@Column
	private int availableSeats;
	

}
