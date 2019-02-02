(ns alphabet-cipher.coder
  (:require [clojure.string :as string]))

(defn make-string-with-same-length-as-other [str1 str2]
  (loop [str1 str1
         str2 str2]
    (if (>= (count str1) (count str2))
      (subs str1 0 (count str2))
      (recur (str str1 str1) str2))))

(defn letter->index [str]
  (string/index-of "abcdefghijklmnopqrstuvwxyz" str))

(defn get-matrix-element [row-column-pair]
  (let [[row column] row-column-pair
        matrix ["abcdefghijklmnopqrstuvwxyz"
                "bcdefghijklmnopqrstuvwxyza"
                "cdefghijklmnopqrstuvwxyzab"
                "defghijklmnopqrstuvwxyzabc"
                "efghijklmnopqrstuvwxyzabcd"
                "fghijklmnopqrstuvwxyzabcde"
                "ghijklmnopqrstuvwxyzabcdef"
                "hijklmnopqrstuvwxyzabcdefg"
                "ijklmnopqrstuvwxyzabcdefgh"
                "jklmnopqrstuvwxyzabcdefghi"
                "klmnopqrstuvwxyzabcdefghij"
                "lmnopqrstuvwxyzabcdefghijk"
                "mnopqrstuvwxyzabcdefghijkl"
                "nopqrstuvwxyzabcdefghijklm"
                "opqrstuvwxyzabcdefghijklmn"
                "pqrstuvwxyzabcdefghijklmno"
                "qrstuvwxyzabcdefghijklmnop"
                "rstuvwxyzabcdefghijklmnopq"
                "stuvwxyzabcdefghijklmnopqr"
                "tuvwxyzabcdefghijklmnopqrs"
                "uvwxyzabcdefghijklmnopqrst"
                "vwxyzabcdefghijklmnopqrstu"
                "wxyzabcdefghijklmnopqrstuv"
                "xyzabcdefghijklmnopqrstuvw"
                "yzabcdefghijklmnopqrstuvwx"
                "zabcdefghijklmnopqrstuvwxy"]]
    (get (get matrix row) column)))

(defn make-row-column-pair [pair]
  [(letter->index (first pair)) (letter->index (second pair))])

(defn encode [keyword message]
  (let [keyword (make-string-with-same-length-as-other keyword message)
        message message]
    (->> keyword
         (map vector message)
         (map make-row-column-pair)
         (map get-matrix-element)
         (reduce str))))

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")
