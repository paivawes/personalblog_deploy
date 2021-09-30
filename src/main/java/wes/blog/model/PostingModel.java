package wes.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_posting")
public class PostingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPosting;

	@NotBlank
	private String title;

	@NotBlank
	private String text;

	@ManyToOne
	@JsonIgnoreProperties("myPosting")
	private PersonModel myPosting;

	@ManyToOne
	@JsonIgnoreProperties("themePosting")
	private ThemeModel themePosting;

	public Long getIdPosting() {
		return idPosting;
	}

	public void setIdPosting(Long idPosting) {
		this.idPosting = idPosting;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public PersonModel getMyPosting() {
		return myPosting;
	}

	public void setMyPosting(PersonModel myPosting) {
		this.myPosting = myPosting;
	}

	public ThemeModel getThemePosting() {
		return themePosting;
	}

	public void setThemePosting(ThemeModel themePosting) {
		this.themePosting = themePosting;
	}

	
}
