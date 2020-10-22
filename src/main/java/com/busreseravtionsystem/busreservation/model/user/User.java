package com.busreseravtionsystem.busreservation.model.user;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table (schema = "bankass", name = "users",uniqueConstraints =@UniqueConstraint(columnNames={"_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
@Getter
@Setter
public class User  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long _id;
	
	@NonNull
	@Column
	@NotBlank
	@NotEmpty(message="First name is required")
	private String firstName;
	
	@NonNull
	@Column
	@NotBlank
	@NotEmpty(message="Last name is required")
	private String lastName;
	
	@NonNull
	@Column
	@Email
	@NotBlank
	@NotEmpty(message=" email is required")
	private String email;
	
	@NonNull
	@Column
	@Email
	@NotBlank
	@NotEmpty(message=" password  is required")
	private String password;
	
	@NonNull
	@Column
	@Email
	@NotBlank
	@NotEmpty(message=" mobile number is required")
	/*
	 * @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
	 * message="Mobile number is invalid")
	 */
	private String mobileNumber;
	
	
	//User role
	@OneToMany (cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "users")
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
	//@CollectionTable (schema = "bankass",name = "user_role", joinColumns = @JoinColumn(name="_id"))
	@JoinTable(schema = "bankass",name = "user_role",joinColumns = {@JoinColumn(name="user_id" , referencedColumnName="id")}, inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "id")})
	private Set<Role> roles;

	
	
	private String getFullName()
{
	return firstName !=null? firstName.concat("").concat("lastName"): "";
		
}
}
