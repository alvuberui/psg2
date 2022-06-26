package org.springframework.samples.petclinic.customerAgreementVersions;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;


@Entity
@Table(name = "CA_types")
public class CAType extends NamedEntity {

}
