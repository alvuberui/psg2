package org.springframework.samples.petclinic.cause;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.owner.Owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "donations")
public class Donation extends BaseEntity{

    @Column(name="amount")
    private Double amount;

    @Column(name="date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate date;

    @ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

    @ManyToOne
	@JoinColumn(name = "cause_id")
	private Cause cause;

    public Donation() {
		this.date = LocalDate.now();
	}

}
