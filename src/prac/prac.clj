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


; currying
; 1.partial
(defn grow [name direction]
  (if (= direction :small)
    (str name " is growing smaller")
    (str name " is growing bigger")))
(grow "Alice" :small)
(grow "Alice" :big)

(partial grow "Alice")
((partial grow "Alice") :small)

; 2.comp
(defn toggle-grow [direction] (if (= direction :small) :big :small))

(toggle-grow :big)
(toggle-grow :small)

(defn oh-my [direction] (str "Oh My! You are growing " direction))

(oh-my (toggle-grow :big))

(defn surprise [direction] ((comp oh-my toggle-grow) direction))

(surprise :small)

(defn adder [x y] (+ x y))

(adder 3 4)

(def adder-5 (partial adder 5))

((partial adder 5) 10)

(adder-5 10)

; destructure
; let basic
(let [developer "Alice" rabbit "Alick Rabbit"] [developer rabbit])


; vector before
(let [color "blue" size 12] (str "The " color " door is " size))

(let [x ["blue", "small"]
      color (first x)
      size (last x)]
  (str "The " color " door is " size))

; vector after 
(let [[color size] ["blue" 12]]
  (str "The " color " door is " size))

(let [[color [size] :as original] ["blue" [12]]]
  {:color color :size size :original original})

; map basic
{:jam1 "apple" :jam2 "melon"}

; map destructure
(let [{flower1 :flower1 flower2 :flower2} {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " flower1 " and " flower2))

; map destructure "or"
(let [{flower1 :flower1 flower2 :flower2 :or {flower2 "missing"}} {:flower1 "red"}]
  (str "The flowers are " flower1 " and " flower2))

; map destructure "as"
(let [{flower1 :flower1 :as all-flowers} {:flower1 "red"}]
  [flower1 all-flowers])

; map destructure "keys"
(let [{:keys [flower1 flower2]} {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " flower1 " and " flower2))

; function args destructure
; before
(defn flower-colors [colors]
  (str "The flower are " (:flower1 colors) " and " (:flower2 colors)))

(flower-colors {:flower1 "red" :flower2 "blue"})

(defn flower-colors [{:keys [flower1 flower2]}]

  (str "The flowers are " flower1 " and " flower2))