package wes.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_theme")
public class ThemeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTheme;

	@NotBlank
	private String description;

	@OneToMany(mappedBy = "themePosting", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "themePosting" })
	private List<PostingModel> themePosting = new ArrayList<>();

	public Long getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(Long idTheme) {
		this.idTheme = idTheme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PostingModel> getThemePosting() {
		return themePosting;
	}

	public void setThemePosting(List<PostingModel> themePosting) {
		this.themePosting = themePosting;
	}

	

}
