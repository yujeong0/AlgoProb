package data_structure;

public class _03_HashTable {

	public static void main(String[] args) {
		HashTable ht = new HashTable(5);
		ht.put("a", "1");
		ht.put("b", "2");
		ht.put("c", "3");
		ht.put("d", "4");
		ht.put("h", "5");
		System.out.println(ht.toString());
		ht.remove("c");
		System.out.println(ht.toString());
		System.out.println(ht.get("d").toString());
	}
	
	public static class Item {
		public String key;
		public String value;
		public Item next = null;
		
		public Item(String key, String value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Item [key=" + key + ", value=" + value + ", next=" + next + "]";
		}
		
	}
	
	public static class HashTable {
		private static int CAPACITY;
		private static Item[] hashTable;
		
		public HashTable(int size) {
			CAPACITY = size;
			hashTable = new Item[size];
		}
		
		public int hash(String key) {	// key에 따른 해시값 반환
			int r = 1;
			for (int i = 0; i < key.length(); i++) {
				r *= key.charAt(i);
				r %= CAPACITY;
			}
			return r;
		}
		
		public Item get(String key) {	// key에 맞는 list 검색하여 Item 반환
			int hash = hash(key);
			Item cur = hashTable[hash];
			while(cur != null) {
				if(cur.key == key) {
					return cur;
				}
				cur = cur.next;
			}
			return cur;
		}
		
		public void put(String key, String value) {
			int hash = hash(key);
			Item cur = hashTable[hash];
			if(cur == null) {
				hashTable[hash] = new Item(key, value);
				return;
			}
			while(true) {
				if(cur.next == null) {
					cur.next = new Item(key, value);
					return;
				}
				cur = cur.next;
			}
		}
		
		public void remove(String key) {
			int hash = hash(key);
			Item cur = hashTable[hash];
			if(cur == null) {
				return;
			}
			Item prev = null;
			while(cur != null) {
				if(cur.key == key) {
					if(prev == null) {
						hashTable[hash] = cur.next;
						return;
					}
					prev.next = cur.next;
					return;
				}
				prev = cur;
				cur = cur.next;
			}
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < CAPACITY; i++) {
				Item cur = hashTable[i];
				while(cur != null) {
					sb.append("key=" + cur.key + ", val=" + cur.value + "\n");
					cur = cur.next;
				}
			}
			return sb.toString();
		}
		
		
	}
	
	
}
