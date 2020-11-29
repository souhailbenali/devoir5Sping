package com.souhail.personnes.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;
@Entity
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonne;
	@NotNull
	@Size (min = 4,max = 15)
	private String nomPersonne;
	@Min(value = 65)
	 @Max(value = 230)
	private Double taillePersonne;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private Date dateEnregistrement;
	@ManyToOne
	private Departement departement;
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Personne(String nomPersonne, Double taillePersonne, Date dateEnregistrement) {
		super();
		this.nomPersonne = nomPersonne;
		this.taillePersonne = taillePersonne;
		this.dateEnregistrement = dateEnregistrement;
	}

	public Long getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}
	public String getNomPersonne() {
		return nomPersonne;
	}
	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}
	public Double getTaillePersonne() {
		return taillePersonne;
	}
	public void setTaillePersonne(Double taillePersonne) {
		this.taillePersonne = taillePersonne;
	}
	public Date getDateEnregistrement() {
		return dateEnregistrement;
	}
	public void setDateEnregistrement(Date dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	@Override
	public String toString() {
		return "Personne [idPersonne=" + idPersonne + ", nomPersonne=" + nomPersonne + ", taillePersonne="
				+ taillePersonne + ", dateEnregistrement=" + dateEnregistrement + "]";
	}

	
	
}
