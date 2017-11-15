(ns vowelcount.core)
  
(defn counter
  "I count vowels in a string"
  [str]
  (let [vowelCounts (atom {:a 0 :e 0 :i 0 :o 0 :u 0})
        strList (apply vector (clojure.string/lower-case str))]
    (doseq [char strList] (case char
                            \a (swap! vowelCounts update-in [:a] inc)
                            \e (swap! vowelCounts update-in [:e] inc)
                            \i (swap! vowelCounts update-in [:i] inc)
                            \o (swap! vowelCounts update-in [:o] inc)
                            \u (swap! vowelCounts update-in [:u] inc)
                            "default"))
    @vowelCounts))