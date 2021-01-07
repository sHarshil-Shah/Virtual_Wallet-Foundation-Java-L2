package com.wipro.bean;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;

	private String uName;

	private String pass;

	private int noCards;

	private int bal;

	public User() {
		super();
	}

	public User(int id, String uName, String pass, int noCards, int bal) {
		super();
		this.userid = id;
		this.uName = uName;
		this.pass = pass;
		this.noCards = noCards;
		this.bal = bal;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getNoCards() {
		return noCards;
	}

	public void setNoCards(int noCards) {
		this.noCards = noCards;
	}

	public int getBal() {
		return bal;
	}

	public void setBal(int bal) {
		this.bal = bal;
	}

	@Override
	public String toString() {
		return "User [id=" + userid + ", uName=" + uName + ", pass=" + pass + ", noCards=" + noCards + ", bal=" + bal
				+ "]";
	}
}