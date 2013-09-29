package com.easyjson.json;

public class JSONElement<K, V> implements IJSONElement<K, V> {
	private K key;
	private V value;

	public JSONElement(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

}
