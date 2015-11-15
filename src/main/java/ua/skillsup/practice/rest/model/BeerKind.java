package ua.skillsup.practice.rest.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleksii on 11/15/15.
 */
public enum BeerKind {
	Dark, Light, Ale;

	public static List<String> getAllKinds() {
		return Arrays.asList(Dark.name(), Light.name(), Ale.name());
	}
}
