(ns alphabet-cipher.coder-test
  (:require [clojure.test :refer :all]
            [alphabet-cipher.coder :refer :all]))

(deftest test-make-string-with-same-length-as-other
  (testing "Make one string with the same length as other one"
    (is (= "vigilancevigilancevigilancevi"
           (make-string-with-same-length-as-other "vigilance" "meetmeontuesdayeveningatseven")))
    (is (= "onestringon"
           (make-string-with-same-length-as-other "onestring" "otherstring")))
    (is (= "same-length"
           (make-string-with-same-length-as-other "same-length" "same-length")))
    (is (= "sconessconessco"
           (make-string-with-same-length-as-other "scones" "meetmebythetree")))))

(deftest test-letter->index
  (testing "Map a string letter into a index integer"
    (is (= (letter->index "a") 0))
    (is (= (letter->index "b") 1))
    (is (= (letter->index "z") 25))))

(deftest test-get-matrix-element
  (testing "Get element from matrix passing the row and column"
    (is (= (get-matrix-element [0 0]) \a))
    (is (= (get-matrix-element [0 1]) \b))
    (is (= (get-matrix-element [1 2]) \d))
    (is (= (get-matrix-element [30 22]) nil))))

(deftest test-make-row-column-pair
  (testing "Make row-column pair based on the matrix of strings"
    (is (= (make-row-column-pair [\s \m]) [18 12]))
    (is (= (make-row-column-pair [\c \e]) [2 4]))))

(deftest test-encode
  (testing "can encode a message with a secret keyword"
    (is (= "hmkbxebpxpmyllyrxiiqtoltfgzzv"
           (encode "vigilance" "meetmeontuesdayeveningatseven")))
    (is (= "egsgqwtahuiljgs"
           (encode "scones" "meetmebythetree")))))

(deftest test-decode
  (testing "can decode a message given an encoded message and a secret keyword"
    (is (= "meetmeontuesdayeveningatseven"
           (decode "vigilance" "hmkbxebpxpmyllyrxiiqtoltfgzzv")))
    (is (= "meetmebythetree"
           (decode "scones" "egsgqwtahuiljgs")))))

(deftest test-decipher
  (testing "can extract the secret keyword given an encrypted message and the original message"
    (is (= "vigilance"
           (decipher "opkyfipmfmwcvqoklyhxywgeecpvhelzg" "thequickbrownfoxjumpsoveralazydog")))
    (is (= "scones"
           (decipher "hcqxqqtqljmlzhwiivgbsapaiwcenmyu" "packmyboxwithfivedozenliquorjugs")))
    (is (= "abcabcx"
           (decipher "hfnlphoontutufa" "hellofromrussia")))))
