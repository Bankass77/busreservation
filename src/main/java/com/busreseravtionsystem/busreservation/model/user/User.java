package com.busreseravtionsystem.busreservation.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(schema = "bankass", name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {
		"users_id" }), indexes = @Index(columnList = "email", name = "idx_users_email", unique = true))
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
@Getter
@Setter
@Accessors(chain = true)
public class User  {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "users_id")
	private long id;

	@NonNull
	@Column(name = "first_name", updatable = true)
	@NotBlank
	@NotEmpty(message = "First name is required")
	private String firstName;

	//@NonNull
	@Column(name = "last_name", updatable = true)
	@NotBlank
	@NotEmpty(message = "Last name is required")
	private String lastName;

	@NonNull
	@Column
	@Email
	@NotBlank
	@NotEmpty(message = " email is required")
	private String email;

	@NonNull
	@Column
	@NotBlank
	@NotEmpty(message = " password  is required")
	private String password;

	@NonNull
	@Column(name = "mobile_number", updatable = true)
	@NotBlank
	@NotEmpty(message = " mobile number is required")
	/*
	 * @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
	 * message="Mobile number is invalid")
	 */
	private String mobileNumber;

	// User role
	/*
	 * @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(schema = "bankass", name = "users_role", joinColumns = {
	 * 
	 * @JoinColumn(name = "users_id") }, inverseJoinColumns = { @JoinColumn(name =
	 * "role_id") })
	 */
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(schema = "bankass",name = "users_roles", 
	    joinColumns = @JoinColumn(name = "users_id"), 
	    inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;

	public String getFullName() {
		return firstName != null ? firstName.concat("").concat("lastName") : "";

	}
}
