package chat.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmsg;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datemsg;

	private String textmsg;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="proprietaire")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="destinaire")
	private User user2;

	public Message() {
	}

	public int getIdmsg() {
		return this.idmsg;
	}

	public void setIdmsg(int idmsg) {
		this.idmsg = idmsg;
	}

	public Date getDatemsg() {
		return this.datemsg;
	}

	public void setDatemsg(Date datemsg) {
		this.datemsg = datemsg;
	}

	public String getTextmsg() {
		return this.textmsg;
	}

	public void setTextmsg(String textmsg) {
		this.textmsg = textmsg;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}