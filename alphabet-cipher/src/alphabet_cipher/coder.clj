(ns alphabet-cipher.coder
  (:require [clojure.set :as set])
)

;; ----------------------------
;;   ABCDEFGHIJKLMNOPQRSTUVWXYZ
;; A abcdefghijklmnopqrstuvwxyz
;; B bcdefghijklmnopqrstuvwxyza
;; C cdefghijklmnopqrstuvwxyzab
;; D defghijklmnopqrstuvwxyzabc
;; E efghijklmnopqrstuvwxyzabcd
;; F fghijklmnopqrstuvwxyzabcde
;; G ghijklmnopqrstuvwxyzabcdef
;; H hijklmnopqrstuvwxyzabcdefg
;; I ijklmnopqrstuvwxyzabcdefgh
;; J jklmnopqrstuvwxyzabcdefghi
;; K klmnopqrstuvwxyzabcdefghij
;; L lmnopqrstuvwxyzabcdefghijk
;; M mnopqrstuvwxyzabcdefghijkl
;; N nopqrstuvwxyzabcdefghijklm
;; O opqrstuvwxyzabcdefghijklmn
;; P pqrstuvwxyzabcdefghijklmno
;; Q qrstuvwxyzabcdefghijklmnop
;; R rstuvwxyzabcdefghijklmnopq
;; S stuvwxyzabcdefghijklmnopqr
;; T tuvwxyzabcdefghijklmnopqrs
;; U uvwxyzabcdefghijklmnopqrst
;; V vwxyzabcdefghijklmnopqrstu
;; W wxyzabcdefghijklmnopqrstuv
;; X xyzabcdefghijklmnopqrstuvw
;; Y yzabcdefghijklmnopqrstuvwx
;; Z zabcdefghijklmnopqrstuvwxy
;; ----------------------------

;; Keyword (repeated) | sconessconessco
;; Message            | meetmebythetree

;; Loop up the keyword letter in the column
;; Go down to the message letter row
;; Read the interection letter. 

(defn fill-out-keyword
  [keyword message]
  (take (count message) (cycle keyword)))

;; https://github.com/kaz-yos/wonderland-clojure-katas/blob/master/alphabet-cipher/src/alphabet_cipher/coder.clj#L8
(def alphabet (map str (map #(char %) (range 97 (+ 97 26)))))

(def infinite-alphabet (cycle alphabet))

(def cycled-alphabets (for [n (range 0 26)]
                        (take 26 (drop n infinite-alphabet))))

(def substitution-chart
  (zipmap alphabet (map #(zipmap alphabet %) cycled-alphabets)))

(defn get-row-col [r c]
  (let [r (get-in substitution-chart [(str r) (str c)])]
    r))

(defn encode 
  [keyword message]
  (let [keyword (fill-out-keyword keyword message)]
    (apply str (map #(get-row-col %1 %2) (seq keyword) (seq message)))))

;; Keyword (repeated) | sconessconessco
;; Message            | egsgqwtahuiljgs

;; Loop up the keyword letter in the column
;; Go down the column till you see the message letter
;; Read the column letter. 

(defn decode-row-value
  [r v]
  (let [row (substitution-chart (str r))]
    ;; return the key which contains v as a value
    ;; invert the map and use the value to get the key
    ((set/map-invert row) (str v))))

;; ----------------------------------------------------------------------------------------------------
(defn decode [keyword message]
  ;; get the row for the keyword value
  ;; find the key (column) which contains the message 
  (let [keyword (fill-out-keyword keyword message)]
    (apply str (map #(decode-row-value %1 %2) (seq keyword) (seq message)))))

;; find the largest word which is repeated in the decyper
;; "sconessconessconessconessconessc" -> "scones"
;; partion by character until you find two matches
(defn find-repeated-word 
  [s]
  (loop [i 1]
    (if (> i (count s))
      s
      (let [p (partition i s)]
        (if (= (first p) (second p))
          (apply str (first p))
          (recur (inc i))
          )))))

(defn decipher 
  [cypher message]
  ;; if you have the original mesage and the encoded message
  ;; you can figure out the secret keyword
  ;; "e" "m" => "s"
  (let []
    (find-repeated-word (apply str (map #(decode-row-value %1 %2) (seq message) (seq cypher))))))
