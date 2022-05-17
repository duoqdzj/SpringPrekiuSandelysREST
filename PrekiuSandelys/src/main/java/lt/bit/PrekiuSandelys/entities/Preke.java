package lt.bit.PrekiuSandelys.entities;

import javax.persistence.*;

@Entity
@Table(name="prekiu_sandelys")
public class Preke {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String summary;
	
	@Column 
	private Integer quantity;
	
	@Column 
	private Double price;
	
	@Column 
	private String pic;

	public Preke() {
		super();
	}

	public Preke(String name, String summary, Integer quantity, Double price, String pic) {
		super();
		
		this.name = name;
		this.summary = summary;
		this.quantity = quantity;
		this.price = price;
		this.pic = pic;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "Preke [id=" + id + ", name=" + name + ", summary=" + summary + ", quantity=" + quantity + ", price="
				+ price + ", pic=" + pic + "]";
	}
	
	

	
	
}