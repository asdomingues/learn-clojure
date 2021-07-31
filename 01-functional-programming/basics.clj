(println "Hello" "world")

(def file-number 0)
(println file-number)

(defn set-discount
  [expected-discount]
  (def discount expected-discount)
  discount
  )

(set-discount 0.1)
(println "Discount set:" discount)

(defn offer-price
  [original-price discount-to-use]
  (* original-price (- 1 discount-to-use))
  )

(def final-price (offer-price 10 discount))
(println "Final price:" final-price)
