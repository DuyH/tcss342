/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 4 - Compressed Literature 2
 * MyHashTable.java
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * My implementation of a hash table that uses linear probing for collisions.
 * 
 * @author Duy Huynh
 * @version 6/1/2015
 *
 * @param <K> Key
 * @param <V> Value
 */
public class MyHashTable<K, V> {

    /** Number of entries in hash table. */
    private int entries;

    /** Number of buckets in hash table. */
    private int buckets;

    /** Max number of probes in hash table. */
    private int probesMax;

    /** Number of probes total in hash table. */
    private int probesCount;

    /** Histogram of probes in hash table. */
    private List<Integer> probesHistogram;

    /** Keep track of the index needed for each key. */
    private Map<K, Integer> probesTracker;

    /** The hash table. */
    private Entry<K, V>[] myHashTable;

    /**
     * My hash table.
     * 
     * @param capacity Bucket capacity.
     */
    public MyHashTable(final int capacity) {

        myHashTable = new Entry[capacity];
        buckets = capacity;
        entries = probesMax = probesCount = 0;
        probesHistogram = new ArrayList<Integer>();
        probesTracker = new HashMap<K, Integer>();
    }

    /**
     * Put the value associated with key into hash table.
     * 
     * @param searchKey Key lookup.
     * @param newValue Value associated with Key.
     */
    public void put(final K searchKey, final V newValue) {

        int probes = 0;
        // If buckets full, don't put anything in

        if (entries < buckets) {
            int spot = hash(searchKey);

            // Check if bucket is already occupied:
            while (myHashTable[spot] != null) {

                // Now check if it is same key -> update value
                if (myHashTable[spot].key.equals(searchKey)) {
                    myHashTable[spot].value = newValue;
                    break;
                } else {
                    // If not key, linear probe:
                    spot = (spot + 1) % buckets;
                    probes++;
                    probesCount++;
                }

            }

            // Update the maximum probes
            probesMax = Math.max(probes, probesMax);

            // Update probes histogram
            probesHistogram.add(0, probes);

            // Keep track of the probe for this searchKey
            probesTracker.put(searchKey, spot);

            // We are now in empty spot, put value there.
            myHashTable[spot] = new Entry<K, V>(searchKey, newValue);
            entries++;
        }
    }

    /**
     * Retrieve Value associated with Key from hash table.
     * 
     * @param searchKey Key lookup.
     * @return Value associated with Key.
     */
    public V get(final K searchKey) {

        return probesTracker.containsKey(searchKey) ? myHashTable[probesTracker
                .get(searchKey)].value : null;
    }

    /**
     * Whether the hash table contains the specified Key.
     * 
     * @param searchKey Key to find.
     * @return Whether or not Key was found in hash table.
     */
    public boolean containsKey(final K searchKey) {
        return get(searchKey) != null;
    }

    /**
     * Whether or not hash table is void of entries.
     * 
     * @return Whether or not hash table is empty.
     */
    public boolean isEmpty() {
        return entries == 0;
    }

    /**
     * Print out stats regarding hash table.
     */
    public void stats() {

        System.out.println("Hash Table Stats");
        System.out.println("================");
        System.out.println("Number of Entries: " + entries);
        System.out.println("Number of Buckets: " + buckets);
        System.out.println("Histogram of Probes: " + probesHistogram);
        System.out.printf("Fill Percentage:  %.6f%%\n", 100.0 * entries
                / myHashTable.length);
        System.out.println("Max Linear Probe: " + probesMax);
        System.out.printf("Average Linear Probe: %.6f\n", probesCount * 1.0 / entries);
    }

    /**
     * Provides hash code for a key.
     * 
     * @param key The Key to hash.
     * @return Hash code.
     */
    private int hash(final K key) {
        final int mod = key.hashCode() % buckets;
        return (mod < 0) ? mod + buckets : mod;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");

        for (Entry e : myHashTable) {
            if (e != null) {
                sb.append(e + ", ");
            }
        }

        // Trim the last ", " off.
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");

        return sb.toString();

    }

    /**
     * Represents a node of the hash table.
     * 
     * @author Duy Huynh
     *
     * @param <K> Key.
     * @param <V> Value.
     */
    public class Entry<K, V> {

        /** This entry's Key. */
        private K key;

        /** This entry's Value. */
        private V value;

        /**
         * Create an entry with Key and Value.
         * 
         * @param key Entry's Key.
         * @param value Entry's Value.
         */
        public Entry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }

    }

}
