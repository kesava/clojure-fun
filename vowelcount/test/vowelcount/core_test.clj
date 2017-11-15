(ns vowelcount.core-test
  (:require [clojure.test :refer :all]
            [vowelcount.core :refer :all]))

(deftest counter-test
  (testing "count the vowels"
    (is (= (counter "hello") {:a 0 :e 1 :i 0 :o 1 :u 0}))))
