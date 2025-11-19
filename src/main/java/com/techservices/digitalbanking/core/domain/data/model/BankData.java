/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bank_data", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
@Setter
@Getter
@ToString
public class BankData {

	@Id
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;
}
