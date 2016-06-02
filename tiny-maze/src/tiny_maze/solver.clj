(ns tiny-maze.solver)

(def start :S)
(def end :E)
(def wall 1)
(def path 0)

(defn get-location [co-ord maze]
  (get-in maze co-ord))

(defn maze-coords [maze]
  (for [i (range (count maze))
        j (range (count (first maze)))]
    [i j]))

(defn walkable? [coord maze]
  (if (#{start end path} (get-location coord maze)) true false))

(defn escaped? [path maze]
  (= end (get-location (last path) maze)))

(defn get-start [maze]
  (some #(and (= start (get-location % maze)) %)
        (maze-coords maze)))

(defn get-neighbours [[i j] maze]
  (let [neighbour-coords [[(dec i) j] [i (dec j)]
                          [(inc i) j] [i (inc j)]]]
    (reduce #(if (#{path end} (get-location %2 maze)) (conj % %2) %)
            [] neighbour-coords)))

(defn graph [maze]
  (->>
    (maze-coords maze)
    (filter #(walkable? % maze))
    (map #(vector % (get-neighbours % maze)))
    (into {})))

(defn solver [from pathways visited]
  (let [moves (filter #(not ((set visited) %)) (pathways from))]
    (if (empty? moves)
      [visited]
      (mapcat #(solver % pathways (conj visited %)) moves))))

(defn paths [maze]
  (let [start (get-start maze)
        graph (graph maze)
        visited [start]]
    (solver start graph visited)))

(defn best-solution [maze]
  (->>
    (paths maze)
    (filter #(escaped? % maze))
    (sort-by count)
    (first)))

(defn solve-maze [maze]
  (let [solution (best-solution maze)]
    (reduce #(assoc-in % %2 :x) maze solution)))
