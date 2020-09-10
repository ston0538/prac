(ns prac.prac
  (:require [camel-snake-kebab.core :as csk]))

(csk/->snake_case "hello world")
(defn prac [input]
  (str "Serpent! You said: "
       (csk/->snake_case input)))

(defn -main [& args]
  (println (prac (first args))))

(defn drinkable? [x]
  (= x :drinkme))
(every? odd? [1 3 5])
(every? drinkable? [:drinkme :drinkme])
(every? drinkable? [:drinkme1 :drinkme])

(every? (fn [x] (= x :drinkme)) [:drinkme :drinkme])
(every? #(= % :drinkme %) [:drinkme :drinkme])

(not-any? odd? [1 3 5])
(not-any? #(= % :drinkme %) [:drinkme1 :drinkme2])

(some #(> % 3) [1 2 3 4 5])
(some #(> % 3) [1 2 3])

(#{1 2 3 4 5} 8)
(contains? #{1 2 3 4 5} 5)

(some #{3} [1 2 3 4 5])
(some #{4 5} [1 2 3])
(some #{nil} [nil nil])
(some #{false} [false false false])

(if true "It is true" "It tis false")
(if (= :drinkme :KHK) "true", "false")

(let [developer "Alice in Wonderland"])
(if-let [need-to-grow-small (> 5 1)] "drink bottle" "drink coffee")

(defn drink [need-to-grow-small] (when need-to-grow-small "drink bottle"))
(drink true)
(drink false)

(when-let [need-to-grow-small true] "drink bottle")
(when-let [need-to-grow-small false] "drink bottle")

(let [bottle "drinkme"]
  (cond (= bottle "poison") "don't touch"
        (= bottle "drinkme") "grow smaller"
        (= bottle "empty") "all gone"))

(let [x 5]
  (cond
    (> x 10) "bigger then 10"
    (> x 4) "bigger then 4"
    (> x 3) "bigger then 3"))

(let [x 5]
  (cond
    (> x 10) "bigger then 10"
    (> x 11) "bigger then 4"
    (> x 123) "bigger then 3") :else "unknown")

(let [bottle "drinkmes"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"
    "hi"))