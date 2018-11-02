package br.com.inmetrics.weaponry.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Weaponry extends ResourceSupport {

	@NotBlank
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
