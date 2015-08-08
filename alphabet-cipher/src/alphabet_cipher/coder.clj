(ns alphabet-cipher.coder)

(def alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn convert-alpha-char-to-index [c]
  (- (int (nth (.toUpperCase (str c)) 0)) (int \A)))

(defn rotate-alphabet [num-rotations]
  (let [num-rotate-positions (mod num-rotations (count alphabet))]
    (str
      (apply str (nthnext alphabet num-rotate-positions))
      (apply str (take num-rotate-positions alphabet)))))

(defn adjust-string-length-cycle-string-to-pad [s length]
  (apply str (flatten (take length (cycle (partition 1 (.toUpperCase s)))))))

(defn generate-keyword-message-char-pairs  [keyword message]
  (let [cycled-keyword  (adjust-string-length-cycle-string-to-pad keyword (count message))]
    (partition 2 (interleave cycled-keyword message))))

(defn number-of-alphabet-rotations-from-to [from to]
  (let [from-index (convert-alpha-char-to-index from)
        to-index (convert-alpha-char-to-index to)]
    (if (> from-index to-index)
      (+ to-index (- (count alphabet) from-index))
      (- to-index from-index))))

(defn encode [keyword message]
  (.toLowerCase
    (apply str
           (map
             #(let [keyword-char-index (convert-alpha-char-to-index (first %))
                    message-char-index (convert-alpha-char-to-index (last %))]
               (nth (rotate-alphabet message-char-index) keyword-char-index))
             (generate-keyword-message-char-pairs keyword message)))))

(defn decode [keyword message]
  (.toLowerCase
    (apply str
           (map
             #(let [keyword-char (first %) message-char (last %)]
               (nth alphabet (number-of-alphabet-rotations-from-to keyword-char message-char )))
             (generate-keyword-message-char-pairs keyword message)))))




