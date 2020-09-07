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