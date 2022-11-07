package com.function;

public class User{

	private final long id;
	private final String name;
	private final String city;

	/**
	 * @param id
	 * @param name
	 * @param city
	 */
	public User(long id, String name, String city) {
		this.id = id;
		this.name = name;
		this.city = city;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}
}