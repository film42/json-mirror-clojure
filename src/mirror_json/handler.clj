(ns mirror-json.handler
  (:use mirror-json.cors)
  (:use compojure.core)
  (:use ring.util.response)
  (:use ring.adapter.jetty)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

(defn empty-json []
  {:body {}})

(defn mirror [body]
  {:headers {"Content-Type" "application/json"}
   :body body})

(defn delete []
  {:status 204})

(defn options []
  {
   :body {}})

(defroutes app-routes
  (GET     "/*" [] (empty-json))
  (POST    "/*" {body :body} (mirror body))
  (PUT     "/*" {body :body} (mirror body))
  (DELETE  "/*" {body :body} (delete body))
  (OPTIONS "/*" {} (options)))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-response)
      (wrap-cors)))
