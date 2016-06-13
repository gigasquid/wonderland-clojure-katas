(ns alphabet-cipher.coder)

(def ALPHABET "abcdefghijklmnopqrstuvwxyz")

(def indexed-alphabet (zipmap ALPHABET (iterate inc 0)))

(defn chars-for-col [col]
  (take (count ALPHABET)
        (drop (get indexed-alphabet col) (cycle ALPHABET))))

(defn col->char [col row]
  (get (zipmap (chars-for-col col) ALPHABET) row))

(defn col->row [col char]
  (get (zipmap ALPHABET (chars-for-col col)) char))

(defn map-matrix
  "Lookup a value in the matrix using the supplied mapper function."
  [mapper keyword message]
  (let [pairs (map vector (cycle keyword) message)
        encoded (map #(apply mapper %) pairs)]
    (apply str encoded)))

(defn find-repetition
  "Given a word (string), find the shortest sequence of characters that the word is a repetition of (starting from index 0)"
  [word]
  (let [seq-word (seq word)
        word-len (count seq-word)]
    (loop [len 1]
      (let [sub-word (take len seq-word)
            repeated (take word-len (cycle sub-word))]
        (if (= repeated seq-word)
          (apply str sub-word)
          (recur (inc len)))))))

(def encode (partial map-matrix col->row))

(def decode (partial map-matrix col->char))

(defn decipher [cipher message] (find-repetition (map-matrix col->char message cipher)))
