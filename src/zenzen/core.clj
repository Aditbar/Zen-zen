(ns zenzen.core
  (:require [cheshire.core :as ch]
            [clj-time.coerce :as time-co]
            [criterium.core :as crt]
            [clojure.string :as cs]
            [clojure.set :refer :all]))

(use 'clojure.java.io)
(require '[clojure.string :as string])
(require '[clojure.edn :as edn])

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

;; problem
(def c
  ["Master Sorceress Stella is an experienced Sorceress who resides in Saint Haven. She has great interest in real estate investment, but hasn't made much profit from it."
   ""
   "She is always conducting strange experiments that annoy others around her. "])

(string/join " " (remove string/blank? c))

;; bikin map soal
(def value
  (->> "./resources/DN"
      (get-path)
      (filter #(= 7 ((frequencies %) \/)))
      (map #(coba %))
      (map #(string/join " " (remove string/blank? %)))
      baal))

;; mgetrik buat dapet map yg key soal
(defn baal
  [list_string]
  (zipmap list_string (repeat :soal)))

;;

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

(defn coba
  [a]
  (-> a
     slurp
     (clojure.string/replace #"\d" "")
     clojure.string/split-lines)
  )

;; subjek, topik, subtopik, kompetensi, indikator, dokumen, soal

(def amir
  (->> "./resources/DN"
       (get-path)
       (filter #(= 7 ((frequencies %) \/)))
       (map #(coba %))
       (map #(string/join " " (remove string/blank? %)))
       (map vector)
       (map #(zipmap % (repeat :soal)))
       (map #(clojure.set/map-invert %))
       ))

(def budi (->>
            (get-path "./resources/DN")
            (map #(clojure.string/split % #"/"))
            (map #(drop 2 %))
            (filter #(= (count %) 6))
            (map #(zipmap % '(:subjek :topik :subtopik :kompetensi :indikator :dokumen)))
            (map #(clojure.set/map-invert %))
            ))

(def list_of_map
  (map merge budi amir))


(defn write-dataset-edn! [out-file raw-dataset-map]
  (with-open [w (clojure.java.io/writer out-file)]
    (binding [*out* w]
      (clojure.pprint/write raw-dataset-map))))

