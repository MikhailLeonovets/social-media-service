package com.itechart.socialmediaservice.repository.entity;

import com.itechart.socialmediaservice.repository.entity.superclass.Identity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "interest")
public class Interest extends Identity {
	@Column(name = "name")
	private String name;

	@Column(name = "version")
	@Version
	private Long version;

	@ManyToMany
	@JoinTable(
			name = "user_interest",
			joinColumns = @JoinColumn(name = "interest_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<User> interests;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Interest interest = (Interest) o;
		return getId() != null && Objects.equals(getId(), interest.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
				"id = " + getId() + ", " +
				"name = " + getName() + ")";
	}
}
