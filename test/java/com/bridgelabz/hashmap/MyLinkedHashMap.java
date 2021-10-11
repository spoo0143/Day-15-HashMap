package com.bridgelabz.hashmap;

import java.util.ArrayList;

public class MyLinkedHashMap<K, V> {
	public final int numBuckets;
	ArrayList<LinkedList<K>> myBucketArray;

	public MyLinkedHashMap() {
		this.numBuckets = 10;
		this.myBucketArray = new ArrayList<>(numBuckets);
		for (int i = 0; i < numBuckets; i++) {
			this.myBucketArray.add(null);
		}
	}

	public int getBucketIndex(K key) {
		int hashCode = Math.abs(key.hashCode());
		int index = hashCode % numBuckets;
		System.out.println("Key:" + key + " hashcode:" + hashCode + " index: " + index);
		return index;
	}

	public V get(K key) {
		int index = this.getBucketIndex(key);
		LinkedList<K> linkedList = this.myBucketArray.get(index);
		if (linkedList == null)
			return null;
		MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) linkedList.search(key);
		return (myMapNode == null) ? null : myMapNode.getValue();
	}

	public void add(K key, V value) {
		int index = this.getBucketIndex(key);
		LinkedList<K> linkedList = this.myBucketArray.get(index);
		if (linkedList == null) {
			linkedList = new LinkedList<>();
			this.myBucketArray.set(index, linkedList);
		}
		MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) linkedList.search(key);
		if (myMapNode == null) {
			myMapNode = new MyMapNode<>(key, value);
			linkedList.append(myMapNode);
		} else {
			myMapNode.setValue(value);
		}
	}

	public String toString() {
		return "MyLinkedHashMap List{ "+ myBucketArray + '}';
	}
}

