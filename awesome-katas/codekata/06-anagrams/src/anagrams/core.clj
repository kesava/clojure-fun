(ns anagrams.core (:use [clojure [string :only [split-lines join]]]))

(defn- reader->words [rdr]
  (let [single-word? #(nil? (re-find #"[^a-z]" %))]
    (->> (line-seq rdr)
        (map clojure.string/lower-case))))  

(def ^:private generate-dictionary
  (memoize (fn [dictionary add-word filename]
            (with-open [rdr (clojure.java.io/reader filename)]
              (reduce add-word dictionary (reader->words rdr))))))

(defn word->key [word]
  (clojure.string/join (sort word)))

(defn add-to-map [hmap word]
  (let [key (word->key word) words (get hmap key #{})]
    (assoc hmap key (conj words word))))
  

(defn map-anagrams [filename word]
  (get (generate-dictionary {} add-to-map filename) (word->key word)))

(defn anagrams-count [filename]
  (let [dict (generate-dictionary {} add-to-map filename)]
    (count (filter #(> (count (get dict %)) 1) (keys dict)))))
