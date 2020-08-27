(ns prac.prac
  (:require [camel-snake-kebab.core :as csk]))


(defn prac [input]
  (str "Serpent! You said: "
       (csk/->snake_case input)))

(defn -main [& args]
  (println (prac (first args))))
