(ns wonderland-number.finder)

;; A wonderland number is a six digit number
;; 2, 3, 4, 5, 6 * the number ends with a number with all the same digits as the wonderland number

(defn six-digit-numbers []
  ;; sequence of all six digit numbers
  (range 100000 1000000))

;; from the test file
(defn same-digits? [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))]
    (= s1 s2)))

(defn is-wonderland-number? [num]
  (and
   (same-digits? num (* 2 num))
   (same-digits? num (* 3 num))
   (same-digits? num (* 4 num))
   (same-digits? num (* 5 num))
   (same-digits? num (* 6 num))))

(defn wonderland-number []
  (first (filter is-wonderland-number? (six-digit-numbers))))
