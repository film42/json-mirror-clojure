(ns mirror-json.server
  (:use ring.adapter.jetty)
  (:use mirror-json.handler))

(defn -main [& args]
	(let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))
