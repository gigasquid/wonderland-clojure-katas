(ns alphabet-cipher.coder
  (require [clojure.set]))

(def alphabet (seq "abcdefghijklmnopqrstuvwxyz"))
(def letter-to-num (into {} (map vector alphabet (range))))
(def num-to-letter (clojure.set/map-invert letter-to-num))

(defn encrypt [op key-char msg-char]
  (num-to-letter (mod (op (letter-to-num msg-char)
                          (letter-to-num key-char))
                      (count alphabet))))

(defn apply-encryption [op secret message]
  (apply str (map #(encrypt op % %2) (cycle secret) message)))

(defn shortest-repeated-seq [string]
  (some #(if (every? true? (map = (cycle %) string)) %)
        (for [n (range 1 (count string))] (take n string))))

(defn encode [secret message]
  (apply-encryption + secret message))

(defn decode [secret message]
  (apply-encryption - secret message))

(defn decipher [cipher message]
  (apply str (shortest-repeated-seq (decode message cipher))))
