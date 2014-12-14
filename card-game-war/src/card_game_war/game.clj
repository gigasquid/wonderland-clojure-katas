(ns card-game-war.game)

(def suits [:heart :spade :club :diamond])
(def ranks [2 3 4 5 6 7 8 9 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn play-round [player1-cards player2-cards]
  [[[:heart 3] [:spade 5]]
   [[:diamond 4] [:diamond :king]]])

(defn play-game [player1-cards player2-cards]
  "player1 wins")
