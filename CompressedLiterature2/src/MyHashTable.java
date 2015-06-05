import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyHashTable<K, V> {

    private int entries;
    private int buckets;
    private int probesMax;
    private ArrayList<Integer> probesHistogram;
    private Map<K, Integer> probesTracker;
    private Entry<K, V>[] myHashTable;

    public MyHashTable(int capacity) {

        buckets = capacity;
        entries = probesMax = 0;
        probesHistogram = new ArrayList<Integer>();
        probesTracker = new HashMap<K, Integer>();
    }

    public void put(K searchKey, V newValue) {

        int probes = 0;
        // If buckets full, don't put anything in
        if (entries < buckets) {
            int spot = hash(searchKey);

            // Check if bucket is already occupied:
            while (get(searchKey) != null) {

                // Now check if it is same key -> update value
                if (myHashTable[spot].key.equals(searchKey)) {
                    myHashTable[spot].value = newValue;
                    break;
                } else {
                    // If not key, linear probe:
                    spot = ++spot % buckets;
                    probes++;
                }

            }
            // Keep track of the probe for this searchKey
            probesTracker.put(searchKey, hash(searchKey) + probes);

            // We are now in empty spot, put value there.
            myHashTable[spot].value = newValue;

        }

    }

    public V get(K searchKey) {
        return myHashTable[probesTracker.get(searchKey)].value;
    }

    public boolean containsKey(K searchKey) {
        return get(searchKey) != null;
    }

    public boolean isEmpty() {
        return entries == 0;
    }

    public void stats() {

        System.out.println("Hash Table Stats");
        System.out.println("================");
        System.out.println("Number of Entries: ");
        System.out.println("Number of Buckets: ");
        System.out.println("Histogram of Probes: ");
        // sysoutprobeshistogram
        System.out.println("Fill Percentage: ");
        System.out.println("Max Linear Probe: ");
        System.out.println("Average Linear Probe: ");
    }

    private int hash(K key) {
        System.out.println("Hash code obtained: " + key.hashCode() % buckets);
        return key.hashCode() % buckets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (Entry<K, V> entry : myHashTable) {
        }
        return "Not yet done!";
    }

    private class Entry<K, V> {

        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
