(ns pickpeaks.core-test
  (:require [clojure.test :refer :all]
            [pickpeaks.core :refer :all]))

(deftest local-maxima?-test
  (testing  "local maxima 1"
    (is (not (local-maxima? [0 1 2]))))
 (testing "local maxima 2"
   (is (local-maxima? [1 2 1])))
 (testing "local maxima 3"
   (is (not (local-maxima? [2 1 0])))))

(deftest local-maxima-test
  (testing "local-maxima"
    (is (= (local-maxima-result [0 1 2]) "")))
  (testing "local-maxima"
    (is (= (local-maxima-result [1 2 1]) 2)))
  (testing "local-maxima"
    (is (= (local-maxima-result [1 2 2]) 2)))
  (testing "local-maxima"
    (is (= (local-maxima-result [2 1 0]) ""))))

(deftest pick-peaks-list-test
  (testing "pick-peaks"
    (is (= (pick-peaks-list [0 4 2 1 7 4]) [[1 4] [4 7]]))))

(deftest pick-peaks-test
  (testing "pick-peaks"
    (is (= (pick-peaks [0 4 2 1 7 4]) {:pos [1 4] :peak [4 7]}))))

(deftest pick-peaks-test
  (testing "pick-peaks"
    (is (= (pick-peaks [0 4 2 1 7 4 1 2 8 1]) {:pos [1 4 8] :peak [4 7 8]}))))
  
(deftest pick-peaks-test
  (testing "pick-peaks"
    (is (= (pick-peaks [0 4 2 1 7 4 1 2 2 2 1]) {:pos [1 4 7] :peak [4 7 2]}))))

(deftest pick-peaks-test
  (testing "pick-peaks"
    (is (= (pick-peaks [0 4 2 1 7 4 1 2 2 2 8 1]) {:pos [1 4 7 10] :peak [4 7 2 8]}))))
          
(deftest pick-peaks-test
  (testing "pick-peaks"
    (is (= (pick-peaks [1 2 2 2 1]) {:pos [1] :peak [2]}))))
  
(deftest pick-peaks-test
  (testing "pick-peaks"
    (is (= (pick-peaks [1 2 2 3]) {:pos [] :peak []}))))