package com.busreseravtionsystem.busreservation.model.user;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(schema = "bankass", name = "role")
public class Role {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private long id;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Collection<User> users;
}
