package org.springframework.samples.petclinic.cause;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "causes")
public class Cause extends NamedEntity {

    @Column(name = "description")
    @NotEmpty
    private String description;

    @Column(name="budget_target")
    @NotNull
    private Double budgetTarget;

    @Column(name="organization")
    @NotEmpty
    private String organization;

    @OneToMany(mappedBy = "cause", cascade = CascadeType.REMOVE)
	private Set<Donation> donations;

    private Double budgetAchieved;
    
    public Double getbudgetAchieved(){
        return donations.stream().mapToDouble(x-> x.getAmount()).sum();
    }

    @Column(name="is_closed")
    private Boolean isClosed;

    public Boolean getisClosed(){
        return getbudgetAchieved()>=getBudgetTarget();
    }
    
    protected Set<Donation> getDonationsInternal() {
		if (this.donations == null) {
			this.donations = new HashSet<>();
		}
		return this.donations;
	}

	protected void setDonationsInternal(Set<Donation> donations) {
		this.donations = donations;
	}

	public void addDonation(Donation donation) {
		getDonationsInternal().add(donation);
        donation.setCause(this);
	}
    
}
