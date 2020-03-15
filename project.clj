(defproject perf-test-clj-clore-async "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.async "1.0.567"]]
  :main ^:skip-aot perf-test-clj-clore-async.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
