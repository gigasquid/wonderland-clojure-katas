(ns wonderland-number.finder)

(defn same-digits? [x y]
  (let [xs (set (str x))
        ys (set (str y))]
    (= xs ys)))

(defn wonderland-number []
  (loop [nums (range 100000 999999)]
    (if (and (same-digits? (first nums) (* (first nums) 2))
             (same-digits? (first nums) (* (first nums) 3))
             (same-digits? (first nums) (* (first nums) 4))
             (same-digits? (first nums) (* (first nums) 5))
             (same-digits? (first nums) (* (first nums) 6)))
      (first nums)
      (recur (rest nums)))))
