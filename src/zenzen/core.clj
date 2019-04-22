(ns zenzen.core
  (:require [cheshire.core :as ch]
            [clj-time.coerce :as time-co]
            [criterium.core :as crt]
            [clojure.string :as cs]
            [clojure.set :refer :all]))

(use 'clojure.java.io)

;; find . -name '.DS_Store' -type f -delete

(def asa
  (->> "resources/Seal"
       clojure.java.io/file
       .list
       seq
       ))

;;(seq (.list (clojure.java.io/file "resources/Seal")))
(def a
  "./resources/DN/Elf/Darkness/Mara/black mara/Argenta.txt")

(def b
  "./resources")

(defn get-path
  [path]
  (map #(.getPath %)
       (file-seq (clojure.java.io/file path))))

;; ambil data tiap file
(->> "./resources/DN"
     (get-path)
     (filter #(= 5 ((frequencies %) \/)))
     (map #(slurp %)))

(defn read_txt
  [full_path]
  (with-open [rdr (reader full_path)]
   (doseq [line (line-seq rdr)]
     (println line))))



(defn mapping
  [main-path]
  (->>
    (get-path main-path)
    (map #(clojure.string/split % #"/"))
    (map #(drop 2 %))
    (filter #(= (count %) 4))
    (map #(zipmap % '(:a :b :c :d)))
    (map #(clojure.set/map-invert %))))

(mapping "./resources/DN")

;; effective way
(->> "./resources/DN"
     (get-path)
     (filter #(= 7 ((frequencies %) \/)))
     )