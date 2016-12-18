// tag::sample[]
package hello;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userName;
    
    @Column(columnDefinition="text")
    private String image;

    protected User() {}

    public User(String userName, String image) {
        this.userName = userName;
        this.image = image;
    }



// end::sample[]

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
}

