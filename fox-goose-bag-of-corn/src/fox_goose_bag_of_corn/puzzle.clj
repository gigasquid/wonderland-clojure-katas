(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])

(def end-step [[] [:boat] [:corn :fox :goose :you]])

(defn left-side [step]
  (nth step 0))

(defn on-left-side? [step]
  (contains? (set (left-side step)) :you))

(defn boat [step]
  (set (nth step 1)))

(defn item-on-boat? [step]
  (= 3 (count (boat step))))

(defn empty-boat? [step]
  (= 1 (count (boat step))))

(defn alone-on-boat? [step]
  (= 2 (count (boat step))))

(defn remove-from-boat [step]
  [:boat])

(defn get-item-from-boat [step]
  (vec (filter #(not= % :boat) (boat step))))

(defn right-side [step]
  (nth step 2))

(defn on-right-side? [step]
  (contains? (set (right-side step)) :you))

(defn put-on-boat [item]
  (vec (set [:boat :you item])))

(defn get-on-boat-alone []
  [:boat :you])

(defn remove-item [coll item]
  (vec (remove #(or (= item %) (= % :you)) coll)))

(defn remove-me [coll]
  (vec (remove #(= % :you) coll)))

(defn safe? [side]
  (and (not (and (contains? (set side) :goose) 
                 (contains? (set side) :fox)))
       (not (and (contains? (set side) :corn)
                 (contains? (set side) :goose)))))


(defn next-step [step dir]
  (cond
    (= step (first start-pos)) (vec [[(remove-item (left-side step) :goose)
                                      (put-on-boat :goose)
                                      (vec (right-side step))]
                                     :right])
    (and (item-on-boat? step) 
         (= dir :right))       (vec [[(into [] (left-side step))
                                      (remove-from-boat step)
                                      (vec 
                                        (concat 
                                          (right-side step)
                                          (get-item-from-boat step)))]
                                     :left])
    (and (on-right-side? step)
         (safe? 
           (right-side step))) (vec [[(into [] (left-side step))
                                      (get-on-boat-alone)
                                      (remove-item 
                                        (right-side step) :justme)]
                                     :left])
    (alone-on-boat? step)      (vec [[(vec 
                                        (concat (left-side step)
                                                (get-item-from-boat step)))
                                      (remove-from-boat step)
                                      (vec (right-side step))]
                                     :right])
    (and (on-left-side? step)
         (seq (left-side 
                step)))        (vec [[(remove-item (left-side step)
                                                   ((comp first remove-me)
                                                     (left-side step)))
                                      (put-on-boat ((comp first remove-me)
                                                     (left-side step)))
                                      (vec (right-side step))]
                                     :right])
    (and 
      (on-right-side? step)
      (not 
        (safe?
          (right-side step)))) (vec [[(vec (left-side step))
                                      (put-on-boat ((comp first remove-me)
                                                    (right-side step)))
                                      (remove-item (right-side step)
                                                   ((comp first remove-me)
                                                    (right-side step)))]
                                     :left])
    (and (item-on-boat? step)
         (= dir :left))        (vec [[(vec
                                        (concat
                                          (left-side step)
                                          (get-item-from-boat step)))
                                      (remove-from-boat step)
                                      (into [] (right-side step))]
                                     :right])))



(defn river-crossing-plan []
  (loop [curr-step-and-dir (vec [(first start-pos) :right])
         all-steps start-pos]
    ;(println (map set (first curr-step-and-dir))) 
    (if (= (map set (first curr-step-and-dir))
           (map set end-step))
      all-steps
      (recur (next-step (first curr-step-and-dir) 
                        (second curr-step-and-dir)) 
             (conj all-steps 
                   (first (next-step (first curr-step-and-dir)
                                     (second curr-step-and-dir))))))))

;  [[[:fox :goose :corn :you] [:boat] []]
;   [[:fox :corn] [:boat :goose :you] []]
;   [[:fox :corn] [:boat] [:goose :you]]
;   [[:fox :corn] [:boat :you] [:goose]]
;   [[:fox :corn :you] [:boat] [:goose]]
;   [[:fox] [:boat :corn :you] [:goose]]
;   [[:fox] [:boat] [:goose :corn :you]]
;   [[:fox] [:boat :goose :you] [:corn]]
;   [[:fox :goose :you] [:boat] [:corn]]
;   [[:goose] [:boat :fox :you] [:corn]]
;   [[:goose] [:boat] [:corn :fox :you]]
;   [[:goose] [:boat :you] [:corn :fox]]
;   [[:goose :you] [:boat] [:corn :fox]]
;   [[] [:boat :goose :you] [:corn :fox]]
;   [[] [:boat] [:corn :fox :goose :you]]]
