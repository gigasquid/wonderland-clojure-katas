(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn play-round [player1-card player2-card]
  (cond 
    (> (.indexOf ranks (second player1-card)) 
       (.indexOf ranks (second player2-card))) {:player1 [player2-card
                                                          player1-card]}
    (< (.indexOf ranks (second player1-card))
       (.indexOf ranks (second player2-card))) {:player2 [player1-card
                                                          player2-card]}
    :equal-card (if (> (.indexOf suits (first player1-card))
                       (.indexOf suits (first player2-card)))
                  {:player1 [player2-card player1-card]}
                  {:player2 [player1-card player2-card]})))

(defn play-game [player1-cards player2-cards]
  (loop [p1-cards (vec player1-cards)
         p2-cards (vec player2-cards)]
    (if (empty? p1-cards)
      {"Player 2 WINS!!!" p2-cards}
      (if (empty? p2-cards)
        {"Player 1 WINS!!!" p1-cards}
        (let [round-winner (play-round (first p1-cards)
                                       (first p2-cards))]
          (if (round-winner :player1)
            (recur (concat (vec (rest p1-cards))
                           (round-winner :player1))
                   (vec (rest p2-cards)))
            (recur (vec (rest p1-cards))
                   (concat (vec (rest p2-cards))
                           (round-winner :player2)))))))))
