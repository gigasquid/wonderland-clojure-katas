(ns alphabet-cipher.coder)

(def ALPHABET "abcdefghijklmnopqrstuvwxyz")

(def indexed-alphabet
  (zipmap ALPHABET (iterate inc 0)))

(defn chars-for-col [col]
  (take (count ALPHABET) (drop (get indexed-alphabet col) (cycle ALPHABET))))

(defn col->char [col row] (get (zipmap (chars-for-col col) ALPHABET) row))
(defn col->row [col char] (get (zipmap ALPHABET (chars-for-col col)) char))
(defn map-matrix [mapper keyword message]
  (let [pairs (map vector (cycle keyword) message)
        encoded (map #(apply mapper %) pairs)]
    (apply str encoded)))

(defn encode [keyword message] (map-matrix col->row keyword message))
(defn decode [keyword message] (map-matrix col->char keyword message))


(defn decipher [cipher message]
  "decypherme")
