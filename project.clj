(defproject json-mirror "0.1.0-SNAPSHOT"
  :description "Simple compojure app that responds with any json data thrown at it."
  :url "http://mirror-clojure.herokuapp.com/"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-json "0.2.0"]
                 [ring/ring-jetty-adapter "1.2.0"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler json-mirror.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
