(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (> (play-round [2 :club] [3 :club]) 0))
    (is (< (play-round [3 :club] [2 :club]) 0)))
  (testing "queens are higher rank than jacks"
    (is (> (play-round [:jack :club] [:queen :club]) 0))
    (is (< (play-round [:queen :club] [:jack :club]) 0)))
  (testing "kings are higher rank than queens"
    (is (> (play-round [:queen :club] [:king :club]) 0))
    (is (< (play-round [:king :club] [:queen :club]) 0)))
  (testing "aces are higher rank than kings"
    (is (> (play-round [:king :club] [:ace :club]) 0))
    (is (< (play-round [:ace :club] [:king :club]) 0)))
  (testing "if the ranks are equal, clubs beat spades"
    (is (> (play-round [2 :spade] [2 :club]) 0))
    (is (< (play-round [2 :club] [2 :spade]) 0)))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (> (play-round [2 :club] [2 :diamond]) 0))
    (is (< (play-round [2 :diamond] [2 :club]) 0)))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (> (play-round [2 :diamond] [2 :heart]) 0))
    (is (< (play-round [2 :heart] [2 :diamond]) 0))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"))

