package ua.skillsup.practice.rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.skillsup.practice.rest.exceptions.NotEnoughBeerException;
import ua.skillsup.practice.rest.model.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleksii on 11/15/15.
 */
@Service
public class BarService {

	@Value("${contact.country}")
	private String country;
	@Value("${contact.city}")
	private String city;
	@Value("${contact.phone}")
	private String phoneNumber;
	@Value("${contact.address}")
	private String address;
	private int darkBeerGlasses = 50;
	private int lightBeerGlasses = 50;
	private int aleGlasses = 50;

	public List<String> getBeerKinds() {
		return BeerKind.getAllKinds();
	}

	public ContactInformation getContactInformation() {
		ContactInformation ci = new ContactInformation();
		ci.setCountry(country);
		ci.setCity(city);
		ci.setAddress(address);
		ci.setPhoneNumber(phoneNumber);
		return ci;
	}

	public BarStatus getBarStatus() {
		BarStatus status = new BarStatus();
		status.setGlassesOfLight(lightBeerGlasses);
		status.setGlassesOfDark(darkBeerGlasses);
		status.setGlassesOfAle(aleGlasses);
		return status;
	}

	public BarStatus executeClientOrder(ClientOrder order) {
		switch (order.getKind()) {
			case Dark:
				checkBeerCount(darkBeerGlasses, order.getCount(), "Not enough Dark Beer!");
				darkBeerGlasses -= order.getCount();
				break;
			case Light:
				checkBeerCount(lightBeerGlasses, order.getCount(), "Not enough Light Beer!");
				lightBeerGlasses -= order.getCount();
				break;
			case Ale:
				checkBeerCount(aleGlasses, order.getCount(), "Not enough Ale!");
				aleGlasses -= order.getCount();
				break;
			default: throw new NotEnoughBeerException("I haven't such beer!");
		}
		return getBarStatus();
	}

	private void checkBeerCount(int have, int required, String message) {
		if (have < required) {
			throw new NotEnoughBeerException(message);
		}
	}

	public BarStatus executeRefillOrder(RefillOrder refillOrder) {
		darkBeerGlasses += refillOrder.getDark();
		lightBeerGlasses += refillOrder.getLight();
		aleGlasses += refillOrder.getAle();
		return getBarStatus();
	}
}