(defn valor-descontado
  "Retorna o valor com o desconto determinado"
  [valor-bruto percentual-de-desconto]
  (let [maximo 100
        taxa-de-desconto  (/ percentual-de-desconto maximo)
        desconto          (* valor-bruto taxa-de-desconto)]
    (- valor-bruto desconto)))

(defn valor-com-desconto-de-dez
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [desconto  10]
    (- valor-bruto desconto)))

(println "Desconto de 10% para 100 reais:" (valor-com-desconto-de-dez 100))

(println "500 < 100:" (< 500 100))

(defn desconto-condicional
  "Desconta só a partir de 200"
  [bruto desconto]
  (println "Verificando possibilidade de desconto...")
  (if (< bruto 200)
    (do (println "Desconto indisponível.")
     bruto)
    (do (println "Calculando desconto de" desconto "%:")
     (valor-descontado bruto desconto))))

(println (desconto-condicional 100 25))
(println (desconto-condicional 200 25))
(println "Classe de 150N:" (class 150N))
