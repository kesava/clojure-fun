(ns pickpeaks.core)

(defn local-maxima?
  "Given three elements, return true if the middle one is local maxima"
  [[x y z]]
  (and (>= y z) (> y x)))

(defn local-maxima-result
 "Returns the localmaxima if it exists"
 [[x y z]]
 (if (local-maxima? [x y z]) y ""))

(defn pick-peaks-list
  ""
  [arr]
  (keep-indexed #(if (local-maxima? %2) [(+ %1 1) (local-maxima-result %2)]) (partition 3 1 arr)))

(defn pick-peaks
 "I don't do a whole lot."
 [arr]
 (let [peaksPos (pick-peaks-list arr)] 
  {:pos (apply vector (map #(first %) peaksPos)) :peak (apply vector (map #(second %) peaksPos))}))