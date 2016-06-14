(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [rank suit]))

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

(defn play-game [player1-cards player2-cards]
  (let [[c1 & cards-1] player1-cards
        [c2 & cards-2] player2-cards]
    (if-not (and c1 c2)
      (do
        (printf "Player %s won\n" (if c1 "1" "2"))
        [player1-cards player2-cards]) ;; Game's over - return the cards for the players.
      (if (neg? (play-round c1 c2)) ;; Evaluate the top cards
        (recur (conj (vec cards-1) c1 c2)
               cards-2)
        (recur cards-1
               (conj (vec cards-2) c2 c1))))))

(defn create-game
  "Shuffle the deck and run a game!"
  []
  (let [deck (shuffle cards)
        [player1-cards player2-cards] (split-at (/ (count deck) 2) deck)]
    (play-game player1-cards player2-cards)))
