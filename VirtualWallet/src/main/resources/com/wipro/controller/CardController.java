package com.wipro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wipro.bean.*;
import com.wipro.dao.CardDAO;

@Controller
public class CardController {

	private CardDAO cardDAO;

	@Autowired(required = true)
	@Qualifier(value = "cardDAO")
	public void setcardDAO(CardDAO ps) {
		this.cardDAO = ps;
	}

	@RequestMapping(value = "/cards", method = RequestMethod.GET)
	public String listCards(Model model) {
		model.addAttribute("listCards", this.cardDAO.listCards());
		return "card";
	}

	// For add and update card both
	@RequestMapping(value = "/card/add", method = RequestMethod.POST)
	public String addCard(@ModelAttribute("card") Card p, ModelMap model, RedirectAttributes ra,
			HttpServletRequest request) {
		if (p.getCname() == null && p.getCardBal() == 0) {
			ra.addFlashAttribute("msg", "Virtual card creation Failed, Invalid card name and amount");
			return "redirect:../create_card/error";
		} else if (p.getCname() == null) {
			ra.addFlashAttribute("msg", " Virtual card creation Failed, Invalid card name");
			return "redirect:../create_card/error";
		} else if (p.getCardBal() == 0) {
			ra.addFlashAttribute("msg", " Virtual card creation Failed, Invalid amoun");
			return "redirect:../create_card/error";
		}
		int uid = p.getUser().getUserid();
		model.put("card", p);
		model.put("uid", uid);
		model.put("bal", p.getUser().getBal());
		if (p.getCardBal() <= 10000) {
			p = this.cardDAO.addCard(p, uid);
			request.getSession().setAttribute("user", p.getUser());
			ra.addFlashAttribute("card", p);
			return "redirect:../create_card/success";
			// return "createCardSuccess";
		} else if (p.getCardBal() > 10000) {
			ra.addFlashAttribute("msg",
					"Virtual card creation Failed, Total amount exceeds the maximum limit (INR 10,000) allowed");
			return "redirect:../create_card/error";
		} else {
			ra.addFlashAttribute("msg", "Sorry, there was an error encountered, try again later");
			return "redirect:../create_card/error";
		}
	}

	@RequestMapping("/removeCard/{id}")
	public String removeCard(@PathVariable("id") int id) {
		this.cardDAO.removeCard(id);
		return "redirect:/cards";
	}

	@RequestMapping("/editCard")
	public String topupCard(@ModelAttribute("card") Card card, RedirectAttributes ra) {
		ra.addFlashAttribute("card", card);
		return "redirect:/editCard/" + card.getCardid();
	}

	@RequestMapping("/editCard/{id}")
	public String editCard(@PathVariable("id") int id, @ModelAttribute("card") Card card, RedirectAttributes ra,
			HttpServletRequest request) {
		Card oldCard = this.cardDAO.getCardById(id);
		// System.out.println("OLD card:" + oldCard.toString());
		if (oldCard.getCardBal() + card.getCardBal() <= 10000) {
			oldCard = this.cardDAO.updateCard(oldCard, card.getCardBal());
			request.getSession().setAttribute("user", oldCard.getUser());
			ra.addFlashAttribute("card", oldCard);
			return "redirect:/topup_card/success";
		} else if (oldCard.getCardBal() + card.getCardBal() > 10000) {
			ra.addFlashAttribute("msg", "TopUp Failed, Total amount exceeds the maximum limit(INR 10000) allowed");
			return "redirect:../topup_card/error";
		} else {
			ra.addFlashAttribute("msg", "Sorry, there was an error encountered, try again later");
			return "redirect:../topup_card/error";
		}
	}

	@RequestMapping(value = "/view_cards", method = RequestMethod.GET)
	public ModelAndView viewCards(@ModelAttribute("user") User user, @ModelAttribute("card") Card card,
			HttpServletRequest request) {
		User user1 = (User) request.getSession().getAttribute("user");
//		// System.out.println("User from session: " + user1);
		int uid = user1.getUserid();
		ModelAndView mv = new ModelAndView("viewcards");
		List<Card> allCards = this.cardDAO.getCardsByuId(uid);
		// System.out.println("Card size: " + allCards.size());
		mv.addObject("cards", allCards);
		mv.addObject("uid", uid);

		if (allCards.size() == 3)
			mv.addObject("info", "You have reached the limit to create new card");
		return mv;
	}

	@RequestMapping(value = "/topup_card", method = RequestMethod.POST)
	public ModelAndView topupCard(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		int uid = user.getUserid();
		// System.out.println("Dashboard: Top Up Card: UserID: " + uid);
		ModelAndView mv = new ModelAndView("topupcard");
		List<Card> cardList = this.cardDAO.getCardsByuId(uid);
		// System.out.println("Card size: " + cardList.size());
		mv.addObject("cardList", cardList);
		mv.addObject("uid", uid);
		mv.addObject("bal", user.getBal());
		mv.addObject("card", new Card());
		return mv;
	}

	@RequestMapping(value = "/create_card", method = RequestMethod.GET)
	public ModelAndView newCardForm(@ModelAttribute("user") User user, HttpServletRequest request) {
		User user1 = (User) request.getSession().getAttribute("user");
		ModelAndView mv = new ModelAndView("createcard", "card", new Card());
		mv.addObject("uid", user1.getUserid());
		mv.addObject("bal", user1.getBal());
		return mv;
	}

	@RequestMapping(value = "/create_card/success", method = RequestMethod.GET)
	public ModelAndView createCardSuccess(@ModelAttribute("card") Card mycard, HttpServletRequest request) {
		return new ModelAndView("createCardSuccess", "card", mycard);
	}

	@RequestMapping(value = "/create_card/error", method = RequestMethod.GET)
	public ModelAndView createCardError(@ModelAttribute("msg") String msg) {
		return new ModelAndView("createCardError", "msg", msg);
	}

	@RequestMapping(value = "/topup_card/success", method = RequestMethod.GET)
	public ModelAndView topupCardSuccess(@ModelAttribute("card") Card card) {
		return new ModelAndView("topupCardSuccess", "card", card);
	}

	@RequestMapping(value = "/topup_card/error", method = RequestMethod.GET)
	public ModelAndView topupCardError(@ModelAttribute("msg") String msg) {
		return new ModelAndView("createCardError", "msg", msg);
	}
}