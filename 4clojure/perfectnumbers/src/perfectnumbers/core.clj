(ns perfectnumbers.core)

(defn is-divisor?
  [numerator denominator]
  (= (rem numerator denominator) 0))

(defn list-of-divisors-within-boundary
    [x]
    (let [boundary (Math/floor (Math/sqrt x))]
      (filter #(is-divisor? x %) (range 1 (inc boundary)))))

(defn list-of-divisors-over-boundary
  [x list-of-divisors-within-boundary]
  (map #(quot x %) list-of-divisors-within-boundary))

(defn list-of-divisors
  [x]
  (let [list-of-divisors-within-boundary (list-of-divisors-within-boundary x) list-of-divisors-over-boundary (list-of-divisors-over-boundary x list-of-divisors-within-boundary)]
    (distinct (concat list-of-divisors-within-boundary list-of-divisors-over-boundary))))

(defn is-perfect-number?
  "find if a perfect number"
  [x]
  (= (* x 2) (reduce + 0 (list-of-divisors x))))