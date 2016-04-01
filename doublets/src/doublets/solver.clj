(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn one-letter-diff-words [word]
  (loop [currchar 0
         numchars (count word)
         result []]
    (if (< currchar numchars)
      (recur (+ currchar 1)
             numchars
             (concat result
                     (for [x (vec (map str alphabet)) 
                           y [(apply str (drop (+ currchar 1) word))]] 
                       (str (apply str (take currchar word)) 
                               (apply str x y)))))
      (set result))))


  
(defn doublets [word1 word2]
  (let [dictionary (set words)
        dbs (fn f [w1 w2 links]
              (if (= w1 w2)
                links 
                (for [tryword (one-letter-diff-words w1)]
                  (when (and (contains? dictionary tryword)
                           (not (contains? (set links) tryword)))
                    (vec 
                      (remove nil? 
                              (f tryword w2 (conj links tryword))))))))]
    (vec (flatten (remove nil? (dbs word1 word2 (conj [] word1)))))))

