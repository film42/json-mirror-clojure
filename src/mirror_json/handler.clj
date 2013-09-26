(ns mirror-json.handler
  (:use compojure.core)
  (:use ring.util.response)
  (:use ring.adapter.jetty)
  (:use ring.middleware.cors)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

(defn empty-json []
  {:body {}})

(defn mirror [body]
  {:body body})

(defn delete [body]
  {:status 204})

(defroutes app-routes
  (GET     "/*" [] (empty-json))
  (POST    "/*" {body :body} (mirror body))
  (PUT     "/*" {body :body} (mirror body))
  (DELETE  "/*" {body :body} (delete body))
  (OPTIONS "/*" {body :body} (mirror body)))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-response)
      (wrap-cors :access-control-allow-origin #".+")))
