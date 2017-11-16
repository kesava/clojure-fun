(ns perfectnumbers.core-test
  (:require [clojure.test :refer :all]
            [perfectnumbers.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
                
            
   (= (is-perfect-number? 6) true)
            
   (= (is-perfect-number? 7) false)
            
   (= (is-perfect-number? 496) true)
            
   (= (is-perfect-number? 500) false)
            
   (= (is-perfect-number? 8128) true)))