(ns tiny-maze.solver)

(def maze1 [[:S 0 1]
            [1  0 1]
            [1  0 :E]])

(def maze2 [[:S 0 0 1]
            [1  1 0 0]
            [1  0 0 1]
            [1  1 0 :E]])

(def maze3 [[:S 1 0 0 0]
            [0  1 0 1 0]
            [0  1 0 1 0]
            [0  0 0 1 :E]])

(def sol-maze3 [[:x 1 :x :x :x]
                [:x  1 :x 1 :x]
                [:x  1 :x 1 :x]
                [:x  :x :x 1 :x]])

(defn x [v]
  (first v))

(defn y [v]
  (second v))

(defn adj-list [v maze]
  (let [x-index (x v)
        y-index (y v)]
    (filter #(not (nil? %)) 
            (list (if (or (= 0 (get (get maze x-index) (- y-index 1)))
                          (= :E (get (get maze x-index) (- y-index 1))))
                    [x-index (- y-index 1)]
                    nil)
                  (if (or (= 0 (get (get maze (+ x-index 1)) y-index))
                          (= :E (get (get maze (+ x-index 1)) y-index)))
                    [(+ x-index 1) y-index]
                    nil)
                  (if (or (= 0 (get (get maze x-index) (+ y-index 1)))
                          (= :E (get (get maze x-index) (+ y-index 1))))
                    [x-index (+ y-index 1)]
                    nil)
                  (if (or (= 0 (get (get maze (- x-index 1)) y-index))
                          (= :E (get (get maze (- x-index 1)) y-index)))
                    [(- x-index 1) y-index]
                    nil)))))

(defn path-ending-at [e prevs]
  (loop [prev (prevs e)
         path '()]
    (if prev
      (recur (prevs prev) (conj path prev))
      (conj (vec path) e))))

(defn mark-path [e prevs maze]
  (let [mutable-maze (to-array-2d maze)
        path (path-ending-at e prevs)]
    (loop [p (first path)
           ps (rest path)]
      (if p
        (do
          (aset mutable-maze (x p) (y p) :x)
          (recur (first ps) (rest ps)))
        (map vec mutable-maze)))))

(defn solve-maze [maze]
  (loop [M maze
         Q [[0 0]]
         v [0 0]
         prev nil
         seen #{v}
         prev-map {v nil}
         dist-map {v 0}]
    (if (= :E (get (get maze (x v)) (y v)))
      (mark-path v prev-map maze) 
      (let [alist (filter #(not (seen %)) (adj-list v M))
            new-Q (into (vec (rest Q)) alist)
            new-seen (into seen alist)
            new-prev-map (into prev-map (map #(vec [% v]) alist))
            new-dist-map (into dist-map
                               (map #(vec [% (+ (dist-map v) 1)]) alist))]
        (recur M 
               new-Q 
               (first new-Q) 
               v 
               new-seen 
               new-prev-map 
               new-dist-map)))))

