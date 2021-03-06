package com.epam.movietheater.domain;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class User extends DomainObject {

	private String firstName;

	private String lastName;

	private String email;
	
	private LocalDate birthday;

	private NavigableSet<TicketDTO> ticketDTOs = new TreeSet<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public NavigableSet<TicketDTO> getTickets() {
		return ticketDTOs;
	}

	public void setTickets(NavigableSet<TicketDTO> ticketDTOs) {
		this.ticketDTOs = ticketDTOs;
	}

	public void addTickets(Set<TicketDTO> ticketDTOs) {
		this.ticketDTOs.addAll(ticketDTOs);
	}
	
	
	public LocalDate getBirthday() {
		return birthday;
	}
	
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, email);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", birthday=" + birthday
				+ "]";
	}
	
	
}
