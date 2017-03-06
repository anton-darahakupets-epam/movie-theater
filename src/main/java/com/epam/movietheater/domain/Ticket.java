package com.epam.movietheater.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket extends DomainObject  implements Comparable<Ticket>{

	private LocalDateTime dateTime;

	private long seat;

	public Ticket(LocalDateTime dateTime, long seat) {
		this.dateTime = dateTime;
		this.seat = seat;
	}

	public Ticket() {}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setSeat(long seat) {
		this.seat = seat;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public long getSeat() {
		return seat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, seat);
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
		Ticket other = (Ticket) obj;
		if (dateTime == null) {
			if (other.dateTime != null) {
				return false;
			}
		} else if (!dateTime.equals(other.dateTime)) {
			return false;
		}
		if (seat != other.seat) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TicketDTO[" + "seat=" + seat + "]";
	}

	@Override
	public int compareTo(Ticket o) {
		if (o == null) {
			return 1;
		}
		int result = dateTime.compareTo(o.getDateTime());
		if (result == 0) {
			result = Long.compare(seat, o.getSeat());
		}
		return result;
	}

}
