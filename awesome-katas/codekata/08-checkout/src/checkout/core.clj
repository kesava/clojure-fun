(ns checkout.core)

(defn formatPrice
  [price]
  (with-precision 2 (Float/parseFloat price)))
  
(defn- reader->prices [rdr]
  (->> (line-seq rdr)
       (map clojure.string/lower-case)))

(def ^:private generate-price-list
  (fn [dictionary price-row-parser filename]
    (with-open [rdr (clojure.java.io/reader filename)]
      (reduce price-row-parser dictionary (reader->prices rdr)))))

(defn discount-parser
  [discountStr regularPrice]
  (if (nil? discountStr)
    (fn [qty] (* qty (formatPrice regularPrice)))
    (let [discounts (clojure.string/split discountStr #"for")
          minItems (formatPrice (first discounts))
          discountPrice (formatPrice (second discounts))]
      (fn [qty]
          (let [remQ (rem qty minItems)
                qutQ (quot qty minItems)]
            (if (> qutQ 0)
              (+ (* qutQ discountPrice) (* remQ (formatPrice regularPrice)))
              (* remQ (formatPrice regularPrice))))))))

(defn price-row-parser
  [hmap row]
  (let [rowPairs (clojure.string/split row #",")
        key (keyword (first rowPairs))
        keystar (keyword (str (first rowPairs) "*"))]
    (assoc hmap
           key (formatPrice (get rowPairs 1))
           keystar (discount-parser (get rowPairs 2) (get rowPairs 1)))))

(defn price-for-item
  ([item]
   (get (generate-price-list {} price-row-parser "./resources/pricelist.txt") (keyword (clojure.string/lower-case item))))
  ([item qty]
   (let [keywordstar (keyword (str (clojure.string/lower-case item) "*"))
         discountFn (get (generate-price-list {} price-row-parser "./resources/pricelist.txt") keywordstar identity)]
     (discountFn qty))))

(defn checkout-counter
  [itemlist]
  (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} (apply vector itemlist)))

(defn checkout
  [itemlist]
  (reduce + 0 (map (fn [[k v]] (price-for-item k v)) (checkout-counter itemlist))))
