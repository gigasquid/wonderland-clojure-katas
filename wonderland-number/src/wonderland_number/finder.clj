(ns wonderland-number.finder)

(defn same-digits-as-product? [prod n]
  (let [to-set (comp set str)]
    (= (to-set n) (to-set (* n prod)))))

(defn six-digits? [n]
  (= 6 (count (str n))))

(def wonderland-number-rules
  (conj (map #(partial same-digits-as-product? %) (range 2 4))
        (partial six-digits?)))

(defn is-wonderland-number? [n]
  (every? #(% n) wonderland-number-rules))

(defn wonderland-number []
  (first (filter is-wonderland-number? (range))))
