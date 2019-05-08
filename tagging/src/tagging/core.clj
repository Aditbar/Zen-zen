(ns tagging.core
  (:gen-class))

(defn map-keys
  [m ks f & args]
  (merge m
         (into {}
               (for [k ks] [k (apply f (k m) args)]))))

(defn -main
  [path out_file]
  (->> (clojure.java.io/file path)
       file-seq
       (map #(.getPath %))
       (filter #(clojure.string/ends-with? % ".txt"))
       (map #(clojure.string/replace % path ""))
       (map #(clojure.string/split % #"/"))
       (map #(map vector %))
       (map #(zipmap [:subject :topic :sub-topic :compentence :indicator :filename] %))
       (map #(assoc % :problems (->> %
                                     vals
                                     (map first)
                                     (clojure.string/join "/")
                                     (str path)
                                     slurp
                                     vector)))
       (group-by :filename)
       (map second)
       (map #(apply merge-with into %))
       (map #(map-keys % (keys %) set))
       (map #(map-keys % (keys %) vec))
       (map #(map-keys % [:filename :problems] first))
       pr-str
       (spit out_file)))

;; lein run "problems/" "problems-tagged.edn"