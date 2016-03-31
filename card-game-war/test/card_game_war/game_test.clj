(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= {:player2 [[:heart 5] [:club :ace]]} 
           (play-round [:heart 5] [:club :ace]))))
  (testing "queens are higher rank than jacks"
    (is (= {:player1 [[:diamond :jack] [:spade :queen]]}
           (play-round [:spade :queen] [:diamond :jack]))))
  (testing "kings are higher rank than queens"
    (is (= {:player1 [[:heart :queen] [:club :king]]}
           (play-round [:club :king] [:heart :queen]))))
  (testing "aces are higher rank than kings"
    (is (= {:player2 [[:diamond :king] [:spade :ace]]}
           (play-round [:diamond :king] [:spade :ace]))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= {:player1 [[:spade 2] [:club 2]]}
           (play-round [:club 2] [:spade 2]))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= {:player1 [[:club 10] [:diamond 10]]}
           (play-round [:diamond 10] [:club 10]))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= {:player2 [[:diamond :ace] [:heart :ace]]}
           (play-round [:diamond :ace] [:heart :ace])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= (play-game (take 26 cards) (drop 26 cards))
           {"Player 2 WINS!!!"
            [[:spade 2] [:diamond 2] [:spade 3] [:diamond 3] [:spade 4] 
             [:diamond 4] [:spade 5] [:diamond 5] [:spade 6] [:diamond 6] 
             [:spade 7] [:diamond 7] [:spade 8] [:diamond 8] [:spade 9] 
             [:diamond 9] [:spade 10] [:diamond 10] [:spade :jack] 
             [:diamond :jack] [:spade :queen] [:diamond :queen] 
             [:spade :king] [:diamond :king] [:spade :ace] [:diamond :ace]
             [:club 2] [:heart 2] [:club 3] [:heart 3] [:club 4] [:heart 4]
             [:club 5] [:heart 5] [:club 6] [:heart 6] [:club 7] [:heart 7]
             [:club 8] [:heart 8] [:club 9] [:heart 9] [:club 10] 
             [:heart 10] [:club :jack] [:heart :jack] [:club :queen] 
             [:heart :queen] [:club :king] [:heart :king] [:club :ace] 
             [:heart :ace]]}))))
