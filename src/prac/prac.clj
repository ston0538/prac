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

; after
(defn flower-colors [{:keys [flower1 flower2]}]
  (str "The flowers are " flower1 " and " flower2))

(flower-colors {:flower1 "red" :flower2 "blue"})

; 지연의 힘
(take 3 (range))

(range 5)

(count (take 1000 (range)))

(repeat 3 "rabbit")

(take 5 (repeat "rabbit"))

(let [rabbit (repeat 3 "rabbit")] (first rabbit))

(rand-int 100)

(repeat 5 (rand-int 100))

#(rand-int 10)

(#(rand-int 10))

(repeatedly 5 #(rand-int 10))

(take 3 (cycle ["big", "small"]))
(take 8 (cycle ["big", "small"]))

(take 3 (rest (cycle ["big", "small"])))

; 재귀
(def adjs ["normal", "too small", "too big", "is swimming"])

(defn alice-is [in out]
  (if (empty? in) out
      (alice-is (rest in) (conj out (str "Alice is " (first in))))))

(defn alice-loop [input]
  (loop [in input out []]
    (if (empty? in) out
        (recur (rest in) (conj out (str "Alice is " (first in)))))))

(alice-is adjs [])
(alice-loop adjs)

; recur의 재귀 호출 스택
(defn countdown [n] (if (= n 0) n (countdown (- n 1))))
(defn countdown-loop [n] (if (= n 0) n (recur (- n 1))))

(countdown 3)
(countdown 100000)
(countdown-loop 100000)


; 데이터 변환
(def animals [:mouse :duck :pig :dog])

; 키워드를 문자열로 변환
(#(str %) :mouse)

(map #(str %) animals)

; 정수 무한 시퀀스 map
(take 3 (map #(str %) (range)))
(take 20 (map #(str %) (range)))

; 부수효과
(println "Look at the mouse!")

(def animal-print (map #(str %) animals))
(def animal-print-sideEffect (map #(println %) animals))

animal-print-sideEffect

; 부수효과 강제
(def animal-print-sideEffect-doall (doall (map #(println %) animals)))
animal-print-sideEffect-doall

; map2 - map의 인수
(def animals ["mouse", "pig", "dog", "cow"])
(def colors ["black", "red", "white", "purple"])

(defn gen-animal-string [animal color]
  (str color "-" animal))