(ns recursion)

(defn factorial-with-sum
  [number calculated]
  (if (< number 2)
    calculated
    (recur (dec number) (* number calculated))))

(defn factorial
  [number]
  (factorial-with-sum number 1))

(println (factorial 21N))
