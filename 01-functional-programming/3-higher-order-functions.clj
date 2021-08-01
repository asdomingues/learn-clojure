(ns curso.aula3)

; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false))
(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))

; else opcional
(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true))

; usando when
(defn aplica-desconto?
  [valor-bruto]
  (when (> valor-bruto 100)
    true))

; retirando when porque a condição basta
(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))

; menos imperativo
(defn mais-caro-que-100?
  [valor-bruto]
  (> valor-bruto 100))

; vislumbrando lambda
(defn mais-caro-que-100?  [valor-bruto]  (> valor-bruto 100))

; função anônima
(fn [valor-bruto] (> valor-bruto 100))

; mais curto
(fn [v] (> v 100))

; abreviado e recebendo um parametro só pra usar inline
#(> %1 100)

; nomeando pra usar pelo nome mais com sintaxe breve
(def mais-caro-que-100?  #(> % 100))

(println (mais-caro-que-100? 1000))
(println (mais-caro-que-100? 100))
