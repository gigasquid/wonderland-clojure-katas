(ns magic-square.puzzle
  (:require [clojure.math.combinatorics :as combo]))



;;; SEM TODO: try transducers

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn isqrt [x]
  (let [rr (Math/sqrt x)
        r (quot rr 1)]
    (when (== rr r)
      (long r))))

;; borrowed from miner/halfbaked.clj
(defn range-down
  "Returns a seq of integers from HIGH (exclusive) down to LOW (inclusive).
   LOW defaults to 0. STEP is a positve decrement, defaults to 1.  Like
   `(reverse (range low high step))' but a bit faster."
  ([high] (range (dec high) -1 -1))
  ([high low] (range (dec high) (dec low) -1))
  ([high low step]
     ;; calculate nearest multiple of step + offset using mod
   (range (- (dec high) (mod (- (dec high) low) step)) (dec low) (- step))))

(defn square-dimension [values]
  (isqrt (count values)))

(defn magic-number [values]
  ;; values is a vector of numbers
  (let [rank (square-dimension values)]
    (assert rank "The Magic Square must have a proper integer square count of values")
    (/ (reduce + values) rank)))

(defn magic-square?
  ;; a "square" is a sequence of rows, all the rows, columns and diagonals should add up to
  ;; the same "magic" number
  ([square] (magic-square? (reduce + (first square)) square))
  ([magic-num rows]
   (let [rank (count rows)
         mag? (fn [row] (== magic-num (reduce + row)))]
     (and (every? mag? rows)
          (let [cols (map (fn [i] (map #(nth % i) rows)) (range rank))
                diag1 (map nth rows (range rank))
                diag2 (map nth rows (range-down rank))]
            (and (every? mag? cols)
                 (mag? diag1)
                 (mag? diag2)))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; brute force, exhaustive search, slow way

(defn magic-values? [values]
  (let [rank (square-dimension values)]
    (assert rank "The Magic Square must have a proper integer square count of values")
    (let [rows (partition rank values)]
      (magic-square? (magic-number values) rows))))

;; slow way
(defn brute-all-magic-seqs [values]
  ;; lazy seq of all solutions
  (filter magic-values? (combo/permutations values)))

(defn to-square [vals]
  ;; tests require vector of vectors (not just seqs)
  (mapv vec (partition (isqrt (count vals)) vals)))

(defn brute-all-magic-squares [values]
  (map to-square (brute-all-magic-seqs values)))

;; fine for 3x3, but too slow for 4x4
(defn brute-magic-square [values]
  (first (brute-all-magic-squares values)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; faster way
(defn possible-unordered-rows [magic-num values]
  ;; unique groupings that add up to the magic number
  (let [rank (square-dimension values)
        mag? (fn [row] (== magic-num (reduce + row)))]
    (filter mag? (combo/combinations values rank))))

;; NB -- assumes all items must be unique across all rows.
(defn unique? [rows]
  (apply distinct? (apply concat rows)))

(defn possible-groups [rows]
  ;; find the groupings of magic rows that have no overlap
  (let [rank (count (first rows))]
    (filter unique? (combo/combinations rows rank))))

(defn possible-squares [group]
  ;; a group is a collection of possible rows that have no overlap
  ;; we still need to consider reordering the rows internally, and as a sequence
  ;; to generate all the possible squares
  (apply combo/cartesian-product (map combo/permutations group)))

(defn faster-all-magic-squares [values]
  (let [magnum (magic-number values)
        rows (possible-unordered-rows magnum values)]
    (map vec (filter #(magic-square? magnum %)
                     (mapcat possible-squares
                             (mapcat combo/permutations (possible-groups rows)))))))

(defn faster-magic-square [values]
  (first (faster-all-magic-squares values)))

(defn magic-square [values]
  (faster-magic-square values))
  
;; good for testing
(defn magic-nxn [n]
  ;; square N x N of integers [1 .. N*N]
  (magic-square (range 1 (inc (* n n)))))
 

(comment

  (time (brute-magic-square (range 1 10)))
  ;; "Elapsed time: 183.712991 msecs"
  ;; [[2 7 6] [9 5 1] [4 3 8]]

  (time (magic-nxn 3))
  ;; "Elapsed time: 1.922156 msecs"
  ;; [[2 7 6] [9 5 1] [4 3 8]]

  (time (magic-nxn 4))
  ;; "Elapsed time: 21819.502502 msecs"
  ;; [[1 2 15 16] [13 14 3 4] [12 7 10 5] [8 11 6 9]]

)
