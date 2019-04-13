(defproject zenzen "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [me.raynes/fs "1.4.6"]
                 [dk.ative/docjure "1.12.0"]
                 [cheshire "5.8.0"]
                 [clj-time "0.14.4"]
                 [criterium "0.4.4"]
                 [net.cgrand/xforms "0.18.2"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot zenzen.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
