(ns wonderland-number.finder-test
  (:require [clojure.test :refer :all]
            [wonderland-number.finder :refer :all]))

(defn hasAllTheSameDigits? [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))]
    (= s1 s2)))

(deftest test-six-digits
  (testing "it can tell whether a number has 6 digits"
    (is (= true (six-digits? 123456)))
    (is (= false (six-digits? 12345)))))

(deftest test-same-digits-after-double
  (testing "it can tell whether number has same digits after it doubles"
    (is (= true (same-digits-as-product? 2 142857)))
    (is (= false (same-digits-as-product? 2 123)))))

(deftest test-same-digits-after-triple
  (testing "it can tell whether number has same digits after it triples"
    (is (= true (same-digits-as-product? 3 142857)))
    (is (= false (same-digits-as-product? 3 123)))))

(deftest test-same-digits-after-quad
  (testing "it can tell whether number has same digits after it quadruples"
    (is (= true (same-digits-as-product? 4 142857)))
    (is (= false (same-digits-as-product? 4 123)))))

(deftest test-same-digits-after-quin
  (testing "it can tell whether number has same digits after it quintuples"
    (is (= true (same-digits-as-product? 5 142857)))
    (is (= false (same-digits-as-product? 5 123)))))

(deftest test-same-digits-after-six
  (testing "it can tell whether number has same digits after it sextuples"
    (is (= true (same-digits-as-product? 6 142857)))
    (is (= false (same-digits-as-product? 6 123)))))

(deftest test-wonderland-number
  (testing "A wonderland number must have the following things true about it"
    (let [wondernum (wonderland-number)]
      (is (= 6 (count (str wondernum))))
      (is (hasAllTheSameDigits? wondernum (* 2 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 3 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 4 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 5 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 6 wondernum))))))
