(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(def suits-score
  (zipmap suits (iterate (partial + 1) 1)))

(def ranks-score
  (zipmap ranks (iterate (partial + 10) 10)))

(defn card-score [[rank suit]]
  (+ (get suits-score suit)
     (get ranks-score rank)))

(defn play-round [player1-card player2-card]
  (let [card1-score (card-score player1-card)
        card2-score (card-score player2-card)]
    (- card2-score card1-score)))

(defn play-game [player1-cards player2-cards])
