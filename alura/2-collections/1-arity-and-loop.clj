(ns recursion)

(defn factorial
  ([number calculated]
  (if (< number 2)
    calculated
    (recur (dec number) (* number calculated))))

  ([number]
  (factorial number 1)))

(println "Recursion with different arities:" (factorial 21N))

(defn factorial-by-loop
  [number]
  ; code to be run once and parameters to be defined once (below)
  (loop [number number
         calculated 1]
    (if (< number 2)
      calculated
      (recur (dec number) (* number calculated)))))

(println "By loop:" (factorial-by-loop 21N))
