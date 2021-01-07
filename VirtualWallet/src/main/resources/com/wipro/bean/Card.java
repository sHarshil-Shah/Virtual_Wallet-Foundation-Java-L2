package com.wipro.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbl_card")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cardid;
	private String cname;
	private String cnumber;
	@DateTimeFormat(pattern = "MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	private int cardBal;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private User user;

	public Card() {
		super();
	}

	public Card(int cardid, String cname, String cnumber, Date expiryDate, int cardBal, User user) {
		super();
		this.cardid = cardid;
		this.cname = cname;
		this.cnumber = cnumber;
		this.expiryDate = expiryDate;
		this.cardBal = cardBal;
		this.user = user;
	}

	public int getCardid() {
		return cardid;
	}

	public void setCardid(int cardid) {
		this.cardid = cardid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCnumber() {
		return cnumber;
	}

	public void setCnumber(String cnumber) {
		StringBuilder mynum;
		mynum = new StringBuilder(cnumber);
		mynum = new StringBuilder("");
		Random rand = new Random();
		for (int i = 1; i < 20; i++) {
			if (i % 5 == 0)
				mynum.append(" ");
			else {
				mynum.append(rand.nextInt(9));
			}
		}

		this.cnumber = mynum.toString();
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("IST"));
		cal.add(Calendar.MONTH, 180);
		this.expiryDate = cal.getTime();
	}

	public int getCardBal() {
		return cardBal;
	}

	public void setCardBal(int cardBal) {
		this.cardBal = cardBal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Card [cardid=" + cardid + ", cname=" + cname + ", cnumber=" + cnumber + ", expiryDate=" + expiryDate
				+ ", cardBal=" + cardBal + "]";
	}

}
