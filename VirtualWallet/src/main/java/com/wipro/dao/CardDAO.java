package com.wipro.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.wipro.bean.Card;
import com.wipro.bean.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CardDAO {
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public Card addCard(Card p, int uid) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		User p1 = session.load(User.class, uid);
		p1.setBal(p1.getBal() - p.getCardBal());
		p1.setNoCards(p1.getNoCards() + 1);
		p.setUser(p1);
		session.persist(p);
		tran.commit();
		return p;
	}

	public Card updateCard(Card p, int topupamount) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		Card oldCard = session.load(Card.class, p.getCardid());
		User u = session.load(User.class, p.getUser().getUserid());
		u.setBal(u.getBal() - topupamount);
		oldCard.setUser(u);
		oldCard.setCardBal(p.getCardBal() + topupamount);
		session.update(oldCard);
		tran.commit();
		return oldCard;
	}

	@SuppressWarnings("unchecked")
	public List<Card> listCards() {
		Session session = this.sessionFactory.openSession();
		return session.createQuery("from Card").list();
	}

	public Card getCardById(int id) {
		Session session = sessionFactory.openSession();
		return (Card) session.load(Card.class, id);
	}

	public List<Card> getCardsByuId(int uid) {
		List<Card> cards = hibernateTemplate.loadAll(Card.class);
		List<Card> specificCards = new ArrayList<>();
		for (Card c : cards) {
			if (c.getUser().getUserid() == uid) {
				specificCards.add(c);
			}
		}
		return specificCards;
	}

	public int getBalByuId(int uid) {
		User user = hibernateTemplate.load(User.class, uid);
		return user.getBal();
	}

	public void removeCard(int id) {
		Session session = this.sessionFactory.openSession();
		Card p = (Card) session.load(Card.class, id);
		if (null != p) {
			session.delete(p);
		}
	}
}