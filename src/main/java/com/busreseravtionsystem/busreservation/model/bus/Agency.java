package com.busreseravtionsystem.busreservation.model.bus;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.busreseravtionsystem.busreservation.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(schema = "bankass", name = "agency", indexes = @Index(unique = true, columnList = "code", name = "idx_agency_code"))
public class Agency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "agency_id")
	private long id;

	@Column
	private String code;

	@Column
	private String name;

	@Column
	private String details;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "agency", fetch = FetchType.LAZY)
	private Set<Bus> buses;

}
