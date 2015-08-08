(ns alphabet-cipher.coder-test
  (:require [clojure.test :refer :all]
            [alphabet-cipher.coder :refer :all]))

(deftest test-encode
  (testing "can encode given a secret keyword"
    (is (= "hmkbxebpxpmyllyrxiiqtoltfgzzv"
           (encode "vigilance" "meetmeontuesdayeveningatseven")))
    (is (= "egsgqwtahuiljgs"
           (encode "scones" "meetmebythetree")))))

(deftest second-char-greater-then-first-number-of-rotations-returned-correctly
  (testing "given s to e, number of rotations is 12")
  (is (= 3 (number-of-alphabet-rotations-from-to \b \e))))

(deftest second-char-less-then-first-number-of-rotations-returned-correctly
  (testing "given s to e, number of rotations is 12")
  (is (= 12 (number-of-alphabet-rotations-from-to \s \e))))

(deftest test-decode
  (testing "can decode an cyrpted message given a secret keyword"
    (is (= "meetmeontuesdayeveningatseven"
           (decode "vigilance" "hmkbxebpxpmyllyrxiiqtoltfgzzv")))
    (is (= "meetmebythetree"
           (decode "scones" "egsgqwtahuiljgs")))))
