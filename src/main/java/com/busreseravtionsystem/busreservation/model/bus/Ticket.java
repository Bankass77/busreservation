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

import com.busreseravtionsystem.busreservation.model.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(schema = "bankass", name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private long id;

	@Column(name = "seat_number")
	private int seatNumber;

	@Column(name = "journey_date")
	private String journeyDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_schedule_id")
	private TripSchedule tripSchedule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id")
	private User passenger;

}
