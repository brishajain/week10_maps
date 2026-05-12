package edu.ttap.maps;

/**
 * Class of pairs of two values
 */
public class Pair<K, V> {
    public K key;

    public V val;

    /**
     * Creates the pair of values
     * @param key is the key for the map
     * @param val is the value for the map
     */
    public Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }
}