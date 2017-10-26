package org.sweep.swiss.entites;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String email, String password, boolean activer, String role) {
		super(email, password, activer, role);
		// TODO Auto-generated constructor stub
	}

	

}
