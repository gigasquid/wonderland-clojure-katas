(ns fox-goose-bag-of-corn.puzzle-test
  (:require [clojure.test :refer :all]
            [fox-goose-bag-of-corn.puzzle :refer :all]))

(def solution
  [[[:fox :goose :corn :you] [:boat] []]
   [[:fox :corn] [:boat :you :goose] []]
   [[:fox :corn] [:boat] [:goose :you]]
   [[:fox :corn] [:boat :you] [:goose]]
   [[:fox :corn :you] [:boat] [:goose]]
   [[:fox] [:corn :boat :you] [:goose]]
   [[:fox] [:boat] [:goose :you :corn]]
   [[:fox] [:boat :you :goose] [:corn]]
   [[:fox :goose :you] [:boat] [:corn]]
   [[:goose] [:boat :you :fox] [:corn]]
   [[:goose] [:boat] [:corn :you :fox]]
   [[:goose] [:boat :you] [:corn :fox]]
   [[:goose :you] [:boat] [:corn :fox]]
   [[] [:boat :you :goose] [:corn :fox]]
   [[] [:boat] [:corn :fox :goose :you]]])


(deftest test-river-crossing-plan
  (testing "the fox, goose, corn, and you all made it to the other side of the river"
    (is (= (map (partial map set) solution)
           (map (partial map set) (river-crossing-plan))))))
