(ns aula4)

(def prices [30 70 100])

; VECTOR
(println "out of bounds: ([30 70 100] 17)")
(println "get out of bounds:" (get prices 17))
(println "get with default:" (get prices 17 0))
(println "append to new vector:" (conj prices 10))
(println "appending and redefining a vector:" (def prices (conj prices 10)))
(println "increment a value to new vector:" (update prices 1 inc))

(defn ints-from-two-to-sqrt
  [number]
  (range 2 (+ 1N (Math/floor (Math/sqrt number)))))

(defn remainder-by-each
  [numerator, denominators]
  (map #(rem numerator %) denominators))

(defn divisible-by-any?
  [number, denominators]
  (zero?
   (count
    (filter zero? (remainder-by-each number denominators)))))

(defn prime?
  [number]
  (let [possible-factors (ints-from-two-to-sqrt number)]
    (and
     (int? number)
     (> number 1)
     (divisible-by-any? number possible-factors))))

(defn primes-up-to
  [number]
  (filter prime? (range (+ 1 number))))

(defn distant-by-two?
  [number-1 number-2]
  (= (- number-2 number-1) 2))

(defn twin-primes?
  [pair]
  (let [number-1 (get pair 0 0)
        number-2 (get pair 1 0)]
    (distant-by-two? number-1 number-2)))

(defn get-pair-of-consecutives
  [numbers]
  (let [total (count numbers)]
    (map vector (take (- total 1) numbers) (rest numbers))))

(defn twin-primes-up-to
  [number]
  (let [primes (primes-up-to number)]
    (filter vector? (map #(when (twin-primes? %) %) (get-pair-of-consecutives primes)))))

(defn twin-primes-up-to-number-sum
  [number]
  (reduce concat [] (twin-primes-up-to number)))

(reduce + 0 (set (twin-primes-up-to-number-sum 5)))
