package com.example.emlakburadabanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banner")
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int advertNo;
	private String phone;
	private int total;
	@OneToOne
	private Address address;
}
