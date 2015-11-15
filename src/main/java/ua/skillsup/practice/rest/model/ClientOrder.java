package ua.skillsup.practice.rest.model;

/**
 * Created by oleksii on 11/15/15.
 */
public class ClientOrder {

	private BeerKind kind;
	private int count;

	public BeerKind getKind() {
		return kind;
	}

	public void setKind(BeerKind kind) {
		this.kind = kind;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
