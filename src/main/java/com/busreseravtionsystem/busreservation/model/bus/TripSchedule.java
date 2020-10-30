package com.busreseravtionsystem.busreservation.model.bus;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(schema = "bankass", name = "tripschedule")
public class TripSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "trip_schedule_id")
	private long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_id")
	private Trip tripDetail;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tripSchedule")
	private List<Ticket> ticketsSold;

	@Column(name = "trip_date")
	private String tripDate;

	@Column(name = "available_seats")
	private int availableSeats;

}
