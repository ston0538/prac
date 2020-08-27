(ns prac.prac-test
  (:require [clojure.test :refer :all]
            [prac.prac :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest test-prac
  (testing "Cries serpent! with a snake_case version of the input"
    (is (= "Serpent! You said: hello_there"
           (prac "hello_there")))))