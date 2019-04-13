(ns zenzen.core
  (:require [cheshire.core :as ch]
            [clj-time.coerce :as time-co]
            [criterium.core :as crt]
            [clojure.string :as cs]
            [clojure.set :refer :all]))

(def asa
  (->> "resources/Seal"
       clojure.java.io/file
       .list
       seq
       ))

;;(seq (.list (clojure.java.io/file "resources/Seal")))

(map #(.getPath %)
     (file-seq (clojure.java.io/file ".")))

(clojure.string/split "./src/zenzen/core.clj" #"/")

(map #(clojure.string/split % #"/") '("./resources/Seal/warrior/Barbarian"
                                       "./resources/Seal/warrior/Barbarian/Haruw.txt"))

(map #(drop 2 %) bla)

(filter #(= (count %) 4) bla)



(def directory (clojure.java.io/file "resource/Seal"))
(def files (file-seq directory))
(take 10 files)