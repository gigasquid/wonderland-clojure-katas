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

(def encode (partial map-matrix col->row))
(def decode (partial map-matrix col->char))


(defn decipher [cipher message]
  "decypherme")
