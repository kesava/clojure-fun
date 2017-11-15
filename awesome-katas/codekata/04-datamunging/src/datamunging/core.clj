(ns datamunging.core)
(use 'clojure.java.io)
(require '[clojure.string :as str])

(defn file->vec
  "Read a file and output a vector"
  [filenamewithpath]
  (with-open [rdr (reader filenamewithpath)]
    (into [] (line-seq rdr))))
  
(defn read-column
  "Given a filename and column number, it gives a list of the given column values"
  [filevector colnum]
  (->> filevector
    (map #(str/split % #"\s+"))
    (map #(if (< colnum (count %)) (% colnum) nil))))

(defn diff-cols
  "diff cols"
  [filenamewithpath col1 col2]
  (map - (map #(Float/parseFloat %) (read-column (file->vec filenamewithpath) col1)) (map #(Float/parseFloat %) (read-column (file->vec filenamewithpath) col2))))
  

; (defn read-multiple-columns
;   "Given a filename and column number, it gives a list of the given column values"
;   [filevector & cols]
;   (->> filevector
;     (map #(str/split % #"\s+"))
;     (map #(if (< colnum (count %)) (% colnum) nil))))