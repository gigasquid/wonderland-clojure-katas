(ns alphabet-cipher.coder
  (:require [clojure.string :as str]))

(defn rotations [xs]
  (let [n (count xs)]
    (take n (partition n 1 (cycle xs)))))

(def letters (map char (range 97 123)))
(def letters-cypher
  (zipmap letters (map #(zipmap letters %) (rotations letters))))
(def letters-decypher
  (zipmap letters (map #(zipmap % letters) (rotations letters))))

(defn is-valid-candidate? [s key-word-long]
  (.startsWith key-word-long (str/replace key-word-long s "")))

(defn longest-repeated-substring [s]
  "Assumpition is the longuest substring starts from the begining"
  (->> (map #(apply str (take % s)) (range 1 (count s)))
       (filter #(is-valid-candidate? % s))
       first))

(defn encode [key-word message]
  "encodeme"
  (->> (for [[k c] (map vector (cycle key-word) message)]
         (get-in letters-cypher [k c]))
       (apply str)))

(defn decode [key-word message]
  "decodeme"
  (->> (for [[k c] (map vector (cycle key-word) message)]
         (get-in letters-decypher [k c]))
       (apply str)))

(defn decipher [cipher message]
  "decypherme"
  (letfn [(get-cypher-char [xs]
            (for [[k m] xs]
              (first (filter #(= m (get-in letters-decypher [% k]))
                             letters))))]
    (->> (map vector cipher message) ;; vectors of letters
         get-cypher-char
         (apply str)
         longest-repeated-substring)))
