(ns magic-square.puzzle
  (:require [clojure.math.combinatorics :as combo]))

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn- sum-rows [m]
  (map #(reduce + %) m))

(defn- sum-cols [m]
  [(reduce + (map first m))
   (reduce + (map second m))
   (reduce + (map last m))])

(defn- sum-diagonals [m]
  [(+ (get-in m [0 0]) (get-in m [1 1]) (get-in m [2 2]))
   (+ (get-in m [2 0]) (get-in m [1 1]) (get-in m [0 2]))])

(defn magic-square [values]
  (loop [i 0
         ith-perm (combo/nth-permutation values i)]
    (if (and 
          (= 1 
             (count 
               (set (sum-rows (vec (map vec (partition 3 ith-perm)))))))
          (= (set (sum-rows (vec (map vec (partition 3 ith-perm)))))
             (set (sum-cols (vec (map vec (partition 3 ith-perm)))))
             (set (sum-diagonals (vec (map vec (partition 3 ith-perm)))))))
      (vec (map vec (partition 3 ith-perm)))
      (recur (+ i 1) (combo/nth-permutation values (+ i 1))))))

